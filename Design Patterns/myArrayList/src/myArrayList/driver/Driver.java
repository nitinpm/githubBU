package myArrayList.driver;

import myArrayList.MyArrayList;
import myArrayList.test.MyArrayListTest;
import myArrayList.util.Results;

public class Driver
{

	public static void main(String[] args) 
	{
		MyArrayList mal = new MyArrayList(args[0]);
		Results results = new Results(args[0]);
        MyArrayListTest test = new MyArrayListTest();

        test.testMe(mal, results);

		results.writeToFile("The sum of all the values in the array list is: " + mal.sum());
		results.writeToStdout("Array after performing MyArrayListTest: " + mal.toString());
	}
	
}
