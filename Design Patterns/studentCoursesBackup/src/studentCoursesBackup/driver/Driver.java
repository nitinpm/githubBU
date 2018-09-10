package driver;

import util.FileProcessor;
import util.MyLogger;
import util.Results;
import util.TreeBuilder;


/**
 * Driver code for the studentCoursesBackup project
 * @author - Nitin Mahadik
 */
public class Driver
{
	public static void main(String[] args) 
	{
		TreeBuilder studentCourseBackup = new TreeBuilder();
		String currDir = System.getProperty("user.dir");

		/*if( args[5] == null || args[5].equals("${arg3}") || (Integer.parseInt(args[5]) < 1 && Integer.parseInt(args[5]) > 4)){
			System.out.println("Incorrect or null argument for the debugValue. Exiting....");
			System.exit(0);
		}*/

		MyLogger.setDebugValue(Integer.parseInt("2"));

		//Process INPUT
		/*if(args[0] == null || args[0].equals("${arg0}") || !args[0].equals("input.txt")){
			System.out.println("Incorrect or null argument for the input file. Exiting....");
			System.exit(0);
		}*/

		FileProcessor input = new FileProcessor(currDir + "\\input.txt");
		String line = input.readLine();
		while(line != null){
			String[] ip = line.split(":");
			studentCourseBackup.insert(Integer.parseInt(ip[0].trim()), ip[1].trim());
			line = input.readLine();
		}




		//Process DELETION
		/*if(args[1] == null || args[1].equals("${arg1}") || !args[1].equals("delete.txt")){
			System.out.println("Incorrect or null argument for the input file. Exiting....");
			System.exit(0);
		}*/

		FileProcessor delete = new FileProcessor(currDir + "\\delete.txt");
		line = delete.readLine();
		while(line != null){
			String[] ip = line.split(":");
			studentCourseBackup.delete(Integer.parseInt(ip[0].trim()), ip[1].trim());
			line = delete.readLine();
		}


		//Generate OUTPUT files
		/*if(args[2].equals("${arg2}") || args[3].equals("${arg3}") || args[4].equals("${arg4}") ||
				!args[2].matches("[a-zA-Z0-9]+.txt") ||
				!args[3].matches("[a-zA-Z0-9]+.txt") ||
				!args[4].matches("[a-zA-Z0-9]+.txt")){
			System.out.println("Null/incorrect argument for the output files. Exiting....");
			System.exit(0);
		}*/

		Results output1 = new Results(currDir + "\\output1.txt");
		Results output2 = new Results(currDir + "\\output2.txt");
		Results output3 = new Results(currDir + "\\output3.txt");

		studentCourseBackup.printNodes(output1, studentCourseBackup.getMainRoot());
		studentCourseBackup.printNodes(output2, studentCourseBackup.getBackup1_root());
		studentCourseBackup.printNodes(output3, studentCourseBackup.getBackup2_root());

		output1.writeToFile("Main Tree");
		output2.writeToFile("backup 1");
		output3.writeToFile("backup 2");

	}
	
}
