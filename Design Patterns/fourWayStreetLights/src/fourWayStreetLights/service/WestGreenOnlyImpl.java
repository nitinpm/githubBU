package service;

import util.Logger;
import util.Results;

public class WestGreenOnlyImpl implements StreetLightsStateI{

    //Reference to the context class object.
    //Referenced during construction.
    private StreetLightsContext trafficSignal;
    private Results results;

    //Keeps track of cars passed the signal before turning RED.
    private int carsPassed = 0;

    public WestGreenOnlyImpl(StreetLightsContext trafficSignal){
        Logger.writeMessage("WEST GREEN constructor created", Logger.DebugLevel.CONSTRUCTOR);
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

        int carsWest = trafficSignal.getCarsWest();
        Logger.writeMessage("WEST: Number of cars : " + carsWest, Logger.DebugLevel.INFO);
        results.storeNewResult("WEST: Number of cars : " + carsWest);

        if(carsPassed < 2) {
            switch (carsWest) {
                case 0:
                    Logger.writeMessage("WEST: No cars moved as no cars", Logger.DebugLevel.INFO);
                    results.storeNewResult("WEST: No cars moved as no cars");
                    break;
                case 1:
                    trafficSignal.setCarsWest(0);
                    carsPassed++;
                    Logger.writeMessage("WEST: One car crossed signal", Logger.DebugLevel.INFO);
                    results.storeNewResult("WEST: One car crossed signal");
                    break;
                default:
                    if(carsPassed == 0) {
                        trafficSignal.setCarsWest(carsWest - 2);
                        carsPassed++;
                        Logger.writeMessage("WEST: Two cars crossed the signal", Logger.DebugLevel.INFO);
                        results.storeNewResult("WEST: Two cars crossed the signal");
                    }
                    else if(carsPassed == 1){
                        trafficSignal.setCarsWest(carsWest - 1);
                        trafficSignal.setState(trafficSignal.getStartStateAllRed());
                        carsPassed = 0;
                        Logger.writeMessage("WEST: One more crossed the signal", Logger.DebugLevel.INFO);
                        results.storeNewResult("WEST: One more crossed the signal");
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
