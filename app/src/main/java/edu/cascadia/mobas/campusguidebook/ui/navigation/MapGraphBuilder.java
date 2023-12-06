package edu.cascadia.mobas.campusguidebook.ui.navigation;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.data.dao.LocationDao;
import edu.cascadia.mobas.campusguidebook.data.model.Location;

public class MapGraphBuilder {
    private LocationDao locationDao;
    private Graph mGraph;

    public MapGraphBuilder(){
        this.locationDao = locationDao;

    }

    private Graph getGraph(){
        List<Location> mLocations= locationDao.getAll();
        Graph Start = null; //Stores the complete graph data structure
        List<Graph> LocationList= null; //Temp for storing blank locations with names.
        Integer itterationCount = 0;

        //Generate LocationList
        for (Location location : mLocations) {
            Graph temp = new Graph();
            temp.setName(location.getName());
            LocationList.add(temp);
        }

        //Build Graph
        Start = LocationList.get(0);
        Graph Current = Start;
        for (Location location : mLocations) {
            if (location.getName() == Current.getName()) {

            }
        }


        for (Graph location : LocationList) {







            /*else {


                for (Graph location2 : LocationList) {

                }
            }*/
            itterationCount++;
        }

        return Start;
    }


}

