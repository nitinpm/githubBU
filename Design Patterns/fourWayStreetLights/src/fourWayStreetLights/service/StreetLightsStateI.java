package service;

import util.Results;

/**
 * Interface for all the states
 */
public interface StreetLightsStateI{
    /**
     * Interface method to handle traffic on each of the states
     * @param results - takes results reference.
     */
    void handleTraffic(Results results);
}