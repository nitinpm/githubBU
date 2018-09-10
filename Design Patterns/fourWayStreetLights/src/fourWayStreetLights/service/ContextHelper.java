package service;

import util.FileProcessor;
import util.Logger;
import util.Results;

/**
 * This class is used to get the input file contents and redirect
 * the input to the context class line by line.
 */
public class ContextHelper {
    private String inputFileName;
    private StreetLightsContext context;
    private Results results;
    public ContextHelper(String inputFileName, Results results){
        Logger.writeMessage("Context helper constructor created", Logger.DebugLevel.CONSTRUCTOR);
        this.inputFileName = inputFileName;
        this.results = results;
        context = new StreetLightsContext();
    }

    /**
     * This method processes the input file and calls the
     * StreetLightsContext for each line of valid input
     * to set the cars on each side and which side is Green.
     */
    public void processInput(){
        FileProcessor fileProcessor = new FileProcessor(inputFileName);
        String line = fileProcessor.readLine();
        int lineNum = 1;
        String carsRegex = "[0-9] [0-9] [0-9] [0-9]";
        String greenSideRegex = "[A-Z]+";
        int carsN, carsE, carsW, carsS;

        while(line != null){
            //Below code to get the cars on each side of the signal.
            //and calling the setter to
            //update the cars count each side in context
            if(line.matches(carsRegex)){
                String[] carsSide = line.split(" ");
                if(carsSide.length == 4) {
                    carsN = Integer.parseInt(carsSide[0]);
                    carsE = Integer.parseInt(carsSide[1]);
                    carsW = Integer.parseInt(carsSide[2]);
                    carsS = Integer.parseInt(carsSide[3]);

                    context.setCars(carsN, carsE, carsW, carsS);
                }
                else
                    Logger.writeMessage("Error processing input: Cars each side row #" + lineNum + " has errors",
                            Logger.DebugLevel.FILE_PROCESSOR);
            }
            else if(line.matches(greenSideRegex)){
                if(line.equals("ALLRED") || line.equals("NORTH") || line.equals("EAST") || line.equals("WEST") || line.equals("SOUTH")) {
                    context.setGreenSide(line);
                    context.handleTraffic(results);
                }
                else
                    Logger.writeMessage("Error processing input: Green Side string has error on row #" + lineNum,
                            Logger.DebugLevel.FILE_PROCESSOR);

            }

            lineNum++;
            line = fileProcessor.readLine();
        }
    }
}
