package service;

import util.Logger;
import util.Results;

public class StreetLightsContext {
    //States
    private StreetLightsStateI eastGreenState;
    private StreetLightsStateI westGreenState;
    private StreetLightsStateI northGreenState;
    private StreetLightsStateI southGreenState;
    private StreetLightsStateI startStateAllRed;

    private int carsNorth = 0, carsEast = 0, carsWest = 0, carsSouth = 0;
    private String greenSide = "ALL_RED";
    private StreetLightsStateI state = startStateAllRed;
    private Results results;

    /**
     * Constructor to initialise each state.
     */
    public StreetLightsContext() {
        Logger.writeMessage("StreetLightsContext constructor created", Logger.DebugLevel.CONSTRUCTOR);
        eastGreenState = new EastGreenOnlyImpl(this);
        westGreenState = new WestGreenOnlyImpl(this);
        northGreenState = new NorthGreenOnlyImpl(this);
        southGreenState = new SouthGreenOnlyImpl(this);
        startStateAllRed = new StartStateImpl(this);
    }

    public StreetLightsStateI getStartStateAllRed() {
        return startStateAllRed;
    }

    //Getters and setters for cars on each side and setting the state
    public void setState(StreetLightsStateI state) {
        this.state = state;
    }

    public void setCars(int carsNorth, int carsEast, int carsWest, int carsSouth) {
        this.carsNorth += carsNorth;
        this.carsEast += carsEast;
        this.carsWest += carsWest;
        this.carsSouth += carsSouth;
    }

    public int getCarsNorth() {
        return carsNorth;
    }

    public void setCarsNorth(int carsNorth) {
        this.carsNorth = carsNorth;
    }

    public int getCarsEast() {
        return carsEast;
    }

    public void setCarsEast(int carsEast) {
        this.carsEast = carsEast;
    }

    public int getCarsWest() {
        return carsWest;
    }

    public void setCarsWest(int carsWest) {
        this.carsWest = carsWest;
    }

    public int getCarsSouth() {
        return carsSouth;
    }

    public void setCarsSouth(int carsSouth) {
        this.carsSouth = carsSouth;
    }

    public void setGreenSide(String greenSide) {
        this.greenSide = greenSide;

        switch (greenSide) {
            case "ALLRED":
                state = startStateAllRed;
                break;
            case "NORTH":
                state = northGreenState;
                break;
            case "EAST":
                state = eastGreenState;
                break;
            case "WEST":
                state = westGreenState;
                break;
            case "SOUTH":
                state = southGreenState;
                break;
        }
    }


    /**
     * Calls the handleTraffic method of the current state.
     * @param results - is the results reference
     */
    public void handleTraffic(Results results) {

        this.results = results;

        if(greenSide.equals("ALLRED")) {
            Logger.writeMessage("\nCurrent Signal is: " + greenSide, Logger.DebugLevel.STATE);
            results.storeNewResult("\nCurrent Signal is: " + greenSide);
        }
        else {
            Logger.writeMessage("\nCurrent Green Signal side is: " + greenSide, Logger.DebugLevel.STATE);
            results.storeNewResult("\nCurrent Green Signal side is: " + greenSide);
        }

        Logger.writeMessage("Cars on each side: N="+getCarsNorth()+" E="+getCarsEast()+" W="+getCarsWest()+" S="+getCarsSouth(),
                Logger.DebugLevel.INFO);
        results.storeNewResult("Cars on each side: N="+getCarsNorth()+" E="+getCarsEast()+" W="+getCarsWest()+" S="+getCarsSouth());
        state.handleTraffic(this.results);
    }

}