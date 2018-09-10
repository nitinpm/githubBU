package service;

import util.Logger;
import util.Results;

public class StartStateImpl implements StreetLightsStateI{
    private StreetLightsContext trafficSignal;
    private Results results;

    public StartStateImpl(StreetLightsContext trafficSignal){
        Logger.writeMessage("StartState ALLRED constructor created", Logger.DebugLevel.CONSTRUCTOR);
        this.trafficSignal = trafficSignal;
    }

    /**
     * Handles traffic when all are RED.
     * @param r - is the results reference.
     */
    public void handleTraffic(Results r){
        this.results = r;
        //All sides are RED in the StartState.
        Logger.writeMessage("ALLRED: No cars moved", Logger.DebugLevel.INFO);
        results.storeNewResult("ALLRED: No cars moved");
    }
}
