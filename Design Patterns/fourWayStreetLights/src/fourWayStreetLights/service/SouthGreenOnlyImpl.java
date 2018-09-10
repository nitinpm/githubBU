package service;

import util.Logger;
import util.Results;

public class SouthGreenOnlyImpl implements StreetLightsStateI{

    //Reference to the context class object.
    //Referenced during construction.
    private StreetLightsContext trafficSignal;
    private Results results;

    //Keeps track of cars passed the signal before turning RED.
    private int carsPassed = 0;

    public SouthGreenOnlyImpl(StreetLightsContext trafficSignal){
        Logger.writeMessage("SOUTH GREEN constructor created", Logger.DebugLevel.CONSTRUCTOR);
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

        int carsSouth = trafficSignal.getCarsSouth();
        Logger.writeMessage("SOUTH: Number of cars : " + carsSouth, Logger.DebugLevel.INFO);
        results.storeNewResult("SOUTH: Number of cars : " + carsSouth);

        if(carsPassed < 2) {
            switch (carsSouth) {
                case 0:
                    Logger.writeMessage("SOUTH: No cars moved as no cars", Logger.DebugLevel.INFO);
                    results.storeNewResult("SOUTH: No cars moved as no cars");
                    break;
                case 1:
                    trafficSignal.setCarsSouth(0);
                    carsPassed++;
                    Logger.writeMessage("SOUTH: One car crossed signal", Logger.DebugLevel.INFO);
                    results.storeNewResult("SOUTH: One car crossed signal");
                    break;
                default:
                    if(carsPassed == 0) {
                        trafficSignal.setCarsSouth(carsSouth - 2);
                        carsPassed++;
                        Logger.writeMessage("SOUTH: Two cars crossed the signal", Logger.DebugLevel.INFO);
                        results.storeNewResult("SOUTH: Two cars crossed the signal");
                    }
                    else if(carsPassed == 1){
                        trafficSignal.setCarsSouth(carsSouth - 1);
                        trafficSignal.setState(trafficSignal.getStartStateAllRed());
                        carsPassed = 0;
                        Logger.writeMessage("SOUTH: One more crossed the signal", Logger.DebugLevel.INFO);
                        results.storeNewResult("SOUTH: One more crossed the signal");
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
