package myArrayList;

import myArrayList.util.FileProcessor;

public class MyArrayList {

    private int arr[] = new int[50];

    private int index;

    /**
     * Getter for accessing it from test class.
     * @return      - reference to private variable index so that we can access it from the test.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter for accessing it from test class.
     * @return      - reference to private array so that we can access it from the test.
     */
    public int[] getArr() {
        return arr;
    }

    /**
     * Empty Constructor : To initialise the private members.
     * Inserts the numbers read from the input file and fills the arr[] private member.
     * @param inputFileName     - takes the input file name from which numbers are to be read.
     */
    public MyArrayList(String inputFileName){
        FileProcessor fileProcessor = new FileProcessor(inputFileName);
        String line = fileProcessor.readLine();
        String regex = "[0-9]";
        while(line != null){
            if(line.length() > 0 && line.matches(regex)){
                int value = Integer.parseInt(line);
                if(value >= 0 && value <= 10000)
                    insertSorted(value);
            }
            line = fileProcessor.readLine();
        }
    }

    /**
     *
     * @param newValue - adds newValue to an existing array and maintains order (sorting).
     *                 - If the value is duplicate, it saves it next to the index of the duplicate.
     *                 - If the array size grows beyond 50 then it creates a new array with previousLength + 25 and increments 25 on each fill.
     */
    public void insertSorted(int newValue){

        if(index == arr.length)
            //expand and copy existing to new array.
            arr = resize();

        int posIndex = findPosToInsert(newValue);

        if((posIndex == index - 1 && index != 1) || posIndex == index)
            arr[index++] = newValue;
        else {
            shiftArray(posIndex);
            arr[posIndex+1] = newValue;
            index++;
        }
    }

    /**
     * Finds the index where the new value should be inserted to maintain the order.
     * @param val   - the newValue whose correct position is to be found.
     * @return      - returns the index next to which the "val" is to be inserted.
     */
    private int findPosToInsert(int val){
        int p = 0;
        if(index == 0)
            return index;

        for(int i = 0; i < index; i++){
            if(val >= arr[i]){
                p = i;
            }
            else {
                p = i-1;
                break;
            }
        }

        return p;
    }

    /**
     * Helper function to the insertSorted which shifts the array by one position
     * @param shiftFromPos      - Shifting to the array is performed from this index
     */
    private void shiftArray(int shiftFromPos){
        for(int i = index - 1; i > shiftFromPos; i--)
            arr[i+1] = arr[i];
    }

    /**
     * Helper function to expand the existing "arr" by 25 each time it gets filled.
     * @return          - A new array with the increased size.
     */
    private int[] resize(){
        int[] newArray = new int[arr.length + arr.length/2];
        for(int l = 0; l < index; l++)
            newArray[l] = arr[l];

        return newArray;
    }

    /**
     *
     * @param value - Removes the "value" parameter from the existing array (all occurrences if multiple)
     *              - It also maintains the indexes (moves the integers after deletion) and sorting.
     */
    public void removeValue(int value){
        int posStart = indexOf(value); //find the first occurrence of the value.
        int count = 0;                 // to keep the count of the value found.

        if(posStart == -1)
            return;

        else{   //finds the count of the value to be removed.
            for(int i = posStart; i < index; i++){
                if(arr[i] == value)
                    count++;
                else
                    break;
            }
        }

        //if the elements to be deleted are the last in array, no movement is necessary only change of "index".
        if(posStart+count == index)
            index -= count;
        else{
            shiftArrayPostDelete(posStart, count);
            index -= count;
        }
    }

    /**
     * Helper function to removeValue which shifts the array to the left after deleting the "value"
     * @param startPosition     - index position where first occurrence of the found value.
     * @param shiftByCount      - number of occurrences is the amount by which elements needs to be shifted left.
     */
    private void shiftArrayPostDelete(int startPosition, int shiftByCount){
        for(int i = startPosition; i < index - shiftByCount; i++)
            arr[i] = arr[i + shiftByCount];
    }


    /**
     *
     * @param value - Finds for the "value" in the existing array and
     * @return      - Returns the index (int) of the found value and a -1 if the value doesn't exist.
     */
    public int indexOf(int value){
        int i = -1;

        for(int j = 0; j < index; j++){
            if(arr[j] == value) {
                i = j;
                break;
            }
        }

        return i;
    }


    /**
     *
     * @return      - Returns the size of the array
     */
    public int size(){
        return index;
    }



    public int sum(){
        int sum = 0;

        for(int i = 0; i < index; i++)
            sum += arr[i];

        return sum;
    }



    @Override
    public String toString(){
        String array;

        if(index > 0)
            array = "{ " + arr[0];
        else
            array = "{ ";

        for(int i = 1; i < index; i++)
            array += ", " + arr[i];

        array += " }";

        return array;
    }


}
