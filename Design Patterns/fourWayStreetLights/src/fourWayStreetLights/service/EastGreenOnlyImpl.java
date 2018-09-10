package service;

import util.Logger;
import util.Results;

public class EastGreenOnlyImpl implements StreetLightsStateI{

    //Reference to the context class object.
    //Referenced during construction.
    private StreetLightsContext trafficSignal;
    private Results results;

    //Keeps track of cars passed the signal before turning RED.
    private int carsPassed = 0;

    public EastGreenOnlyImpl(StreetLightsContext trafficSignal){
        Logger.writeMessage("EAST GREEN constructor created", Logger.DebugLevel.CONSTRUCTOR);
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

        int carsEast = trafficSignal.getCarsEast();
        Logger.writeMessage("EAST: Number of cars : " + carsEast, Logger.DebugLevel.INFO);
        results.storeNewResult("EAST: Number of cars : " + carsEast);

        if(carsPassed < 2) {
            switch (carsEast) {
                case 0:
                    Logger.writeMessage("EAST: No cars moved as no cars", Logger.DebugLevel.INFO);
                    results.storeNewResult("EAST: No cars moved as no cars");
                    break;
                case 1:
                    trafficSignal.setCarsEast(0);
                    carsPassed++;
                    Logger.writeMessage("EAST: One car crossed signal", Logger.DebugLevel.INFO);
                    results.storeNewResult("EAST: One car crossed signal");
                    break;
                default:
                    if(carsPassed == 0) {
                        trafficSignal.setCarsEast(carsEast - 2);
                        carsPassed++;
                        Logger.writeMessage("EAST: Two cars crossed the signal", Logger.DebugLevel.INFO);
                        results.storeNewResult("EAST: Two cars crossed the signal");
                    }
                    else if(carsPassed == 1){
                        trafficSignal.setCarsEast(carsEast - 1);
                        trafficSignal.setState(trafficSignal.getStartStateAllRed());
                        carsPassed = 0;
                        Logger.writeMessage("EAST: One more crossed the signal", Logger.DebugLevel.INFO);
                        results.storeNewResult("EAST: One more crossed the signal");
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
