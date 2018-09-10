package service;

import util.Logger;
import util.Results;

public class NorthGreenOnlyImpl implements StreetLightsStateI{

    //Reference to the context class object.
    //Referenced during construction.
    private StreetLightsContext trafficSignal;
    private Results results;

    //Keeps track of cars passed the signal before turning RED.
    private int carsPassed = 0;

    public NorthGreenOnlyImpl(StreetLightsContext trafficSignal){
        Logger.writeMessage("NORTH GREEN constructor created", Logger.DebugLevel.CONSTRUCTOR);
        this.trafficSignal = trafficSignal;
    }

    /**
     * If green allows one/two cars to pass through
     * Also updates the number of cars waiting on the signal once pass
     * If max 2 cars pass then state is changed to ALLRED, if no other
     * turns GREEN.
     */
    public void handleTraffic(Results r){

        this.results = r;

        int carsNorth = trafficSignal.getCarsNorth();
        Logger.writeMessage("NORTH: Number of cars : " + carsNorth, Logger.DebugLevel.INFO);
        results.storeNewResult("NORTH: Number of cars : " + carsNorth);

        if(carsPassed < 2) {
            switch (carsNorth) {
                case 0:
                    Logger.writeMessage("NORTH: No cars moved as no cars", Logger.DebugLevel.INFO);
                    results.storeNewResult("NORTH: No cars moved as no cars");
                    break;
                case 1:
                    trafficSignal.setCarsNorth(0);
                    carsPassed++;
                    Logger.writeMessage("NORTH: One car crossed signal", Logger.DebugLevel.INFO);
                    results.storeNewResult("NORTH: One car crossed signal");
                    break;
                default:
                    if(carsPassed == 0) {
                        trafficSignal.setCarsNorth(carsNorth - 2);
                        carsPassed++;
                        Logger.writeMessage("NORTH: Two cars crossed the signal", Logger.DebugLevel.INFO);
                        results.storeNewResult("NORTH: Two cars crossed the signal");
                    }
                    else if(carsPassed == 1){
                        trafficSignal.setCarsNorth(carsNorth - 1);
                        trafficSignal.setState(trafficSignal.getStartStateAllRed());
                        carsPassed = 0;
                        Logger.writeMessage("NORTH: One more crossed the signal", Logger.DebugLevel.INFO);
                        results.storeNewResult("NORTH: One more crossed the signal");
                    }
                    break;
            }
        }
        else{
            trafficSignal.setState(trafficSignal.getStartStateAllRed());
            carsPassed = 0;
        }
    }
}
