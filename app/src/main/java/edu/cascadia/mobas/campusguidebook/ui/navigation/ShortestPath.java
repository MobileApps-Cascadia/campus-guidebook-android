package edu.cascadia.mobas.campusguidebook.ui.navigation;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.data.dao.LocationDao;
import edu.cascadia.mobas.campusguidebook.data.model.Location;

public class ShortestPath {
    private LocationDao locationDao;
    List<Location> mLocations = locationDao.getAll();


}
