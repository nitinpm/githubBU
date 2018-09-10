package driver;

import service.ContextHelper;
import util.Logger;
import util.Results;

public class Driver
{
	public static void main(String[] args) 
	{
		String currDir = System.getProperty("user.dir");

		//Validations for the arguments passed
		if(args[0] == null || !args[0].equals("INPUT.txt")){
			System.out.println("Incorrect or null argument for the input file. Exiting....");
			System.exit(0);
		}

		if(!args[1].matches("[a-zA-Z]+.txt")){
			System.out.println("Null argument for the output file. Exiting....");
			System.exit(0);
		}

		if(!args[2].matches("[1-4]")){
			System.out.println("Null or out of range argument for the debug value. Exiting...");
			System.exit(0);
		}

		Results results = new Results(currDir + "/" + args[1]);
		ContextHelper contextHelper = new ContextHelper(currDir + "/" + args[0], results);
		contextHelper.processInput();
		results.writeToFile("Output of the Four Way signal:\n");
		Logger.setDebugValue(Integer.parseInt(args[2]));

	}
	
}
