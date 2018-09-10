package myArrayList.test;

import myArrayList.MyArrayList;
import myArrayList.util.Results;

public class MyArrayListTest {

    private int[] ref;
    public void testMe(MyArrayList mal, Results results){

        String resultString = "";



        //Test 1 : sorted insertion.
        if(isSorted(mal))
            resultString = "Passed...";
        else
            resultString = "Failed...";
        results.storeNewResult("Test #1: Array insertSorted function test : " + resultString);


        resultString = "";


        //Test 2 : size test
        if(isSizeCorrect(mal))
            resultString = "Passed...";
        else
            resultString = "Failed...";
        results.storeNewResult("Test #2: Array size function test : " + resultString);


        resultString = "";



        //Test 3 : insert and remove test
        switch(insertAndRemoveTest(mal)){
            case 0: resultString = "Test #3: Insert and Remove both failed";
                    break;
            case 1: resultString = "Test #3: Insert Passed but Remove failed";
                    break;
            case 2: resultString = "Test #3: Insert failed but Remove Passed";
                    break;
            case 3: resultString = "Test #3: Insert and Remove both Passed";
                    break;
        }
        results.storeNewResult(resultString);



        resultString = "";



        //Test 4 : sum test
        if(sumTest(mal))
            resultString = "Passed...";
        else
            resultString = "Failed...";
        results.storeNewResult("Test #4: Array sum function test : " + resultString);


        resultString = "";


        //Test 5 : indexOf test
        if(indexOfTest(mal))
            resultString = "Passed...";
        else
            resultString = "Failed...";
        results.storeNewResult("Test #5: Array indexOf function test : " + resultString);



        resultString = "";


        //Test 6 : bulk insert test
        if(bulkInsertTest(mal))
            resultString = "Passed...";
        else
            resultString = "Failed...";
        results.storeNewResult("Test #6: Bulk insertion and boundary limit (0 and 10000) insertion test : " + resultString);

        resultString = "";


        //Test 7 : bulk delete test (purge all array data)
        if(bulkRemoveTest(mal))
            resultString = "Passed...";
        else
            resultString = "Failed...";
        results.storeNewResult("Test #7: Bulk remove test : " + resultString);


        //Test 8: Random insert test after bulk delete.
        if(randomInsert(mal))
            resultString = "Passed...";
        else
            resultString = "Failed...";
        results.storeNewResult("Test #8: Random insert after bulk delete : " + resultString);


    }

    //Test Methods
    private boolean isSorted(MyArrayList mal){
        ref = mal.getArr();
        for(int i = 1; i < mal.size(); i++){
            if(ref[i] < ref[i-1])
                return false;
        }
        return true;
    }


    private boolean isSizeCorrect(MyArrayList mal){
        int count = 0;
        for(int i = 0; i < mal.getIndex(); i++)
            count++;

       return count == mal.size();
    }

    private int insertAndRemoveTest(MyArrayList mal){
        int pass = 0;

        //test multiple insertions.
        mal.insertSorted(10); mal.insertSorted(10); mal.insertSorted(10);
        mal.removeValue(555); mal.removeValue(556); mal.removeValue(557);
        mal.insertSorted(556); mal.insertSorted(557); mal.insertSorted(555);
        if(mal.indexOf(10) > 0 && (mal.indexOf(555) + 1 == mal.indexOf(556)) && (mal.indexOf(556) + 1 == mal.indexOf(557)))
            pass = 1;

        mal.removeValue(10);
        if(mal.indexOf(10) == -1)
            pass += 2;

        return pass;
    }

    private boolean sumTest(MyArrayList mal){
        ref = mal.getArr();
        int sum = 0;

        for(int i = 0; i < mal.size(); i++){
            sum += ref[i];
        }

        return sum == mal.sum();
    }

    private boolean indexOfTest(MyArrayList mal){
        mal.insertSorted(10); mal.insertSorted(10); mal.insertSorted(10);
        ref = mal.getArr();
        int testIndex = 0;

        for(int i = 0; i < mal.size(); i++){
            if(ref[i] == 10){
                testIndex = i;
                break;
            }
        }

        return testIndex == mal.indexOf(10);
    }


    private boolean bulkInsertTest(MyArrayList mal){
        int previosSize = mal.size();
        for(int i = 0; i <= 10000; i++)
            mal.insertSorted(i);

        int currentSize = mal.size();

        return (previosSize + 10001 == currentSize);
    }

    private boolean bulkRemoveTest(MyArrayList mal){
        int sizePostBulkRemove;

        for(int i = 0; i <= 10000; i++)
            mal.removeValue(i);

        sizePostBulkRemove = mal.size();

        return sizePostBulkRemove == 0;
    }

    private boolean randomInsert(MyArrayList mal){

        mal.insertSorted(10); mal.insertSorted(0); mal.insertSorted(5);
        mal.insertSorted(2); mal.insertSorted(20); mal.insertSorted(18);
        mal.insertSorted(1); mal.insertSorted(13); mal.insertSorted(7);

        return mal.size() == 9;
    }
}
