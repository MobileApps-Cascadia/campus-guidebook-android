package edu.cascadia.mobas.campusguidebook.ui.navigation;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.data.dao.LocationDao;
import edu.cascadia.mobas.campusguidebook.data.model.Location;

public class ShortestPath {
    private LocationDao locationDao;
    List<Location> mLocations= locationDao.getAll();
    public List<String> calculate (String Start, String End) {
        Integer Distance = 0;
        Location currentLocation = getLocation(Start);
        String From = null;
        List<String> Steps = null;
        while (currentLocation.getNorth() != End || currentLocation.getNorthEast() != End || currentLocation.getEast() != End || currentLocation.getSouthEast() != End || currentLocation.getSouth() != End || currentLocation.getSouthWest() != End || currentLocation.getWest() != End) {
            if (currentLocation.getNorth() != null && currentLocation.getNorth() != From) {
                From = currentLocation.getName();
                Steps.add(currentLocation.getName());
                currentLocation = getLocation(currentLocation.getNorth());
                continue;
            }
            if (currentLocation.getNorthEast() != null && currentLocation.getNorthEast() != From) {
                From = currentLocation.getName();
                Steps.add(currentLocation.getName());
                currentLocation = getLocation(currentLocation.getNorthEast());
                continue;
            }
            if (currentLocation.getEast() != null && currentLocation.getEast() != From) {
                From = currentLocation.getName();
                Steps.add(currentLocation.getName());
                currentLocation = getLocation(currentLocation.getEast());
                continue;
            }
            if (currentLocation.getSouthEast() != null && currentLocation.getSouthEast() != From) {
                From = currentLocation.getName();
                Steps.add(currentLocation.getName());
                currentLocation = getLocation(currentLocation.getSouthEast());
                continue;
            }
            if (currentLocation.getSouth() != null && currentLocation.getSouth() != From) {
                From = currentLocation.getName();
                Steps.add(currentLocation.getName());
                currentLocation = getLocation(currentLocation.getSouth());
                continue;
            }
            if (currentLocation.getSouthWest() != null && currentLocation.getSouthWest() != From) {
                From = currentLocation.getName();
                Steps.add(currentLocation.getName());
                currentLocation = getLocation(currentLocation.getSouthWest());
                continue;
            }
            if (currentLocation.getWest() != null && currentLocation.getWest() != From) {
                From = currentLocation.getName();
                Steps.add(currentLocation.getName());
                currentLocation = getLocation(currentLocation.getWest());
            }
        }
        return Steps;
    }
    private Location getLocation(String loc){
        for (Location location : mLocations) {
            if (location.getName() == loc){
                return location;
            }
        }
        return null;
    }
}
