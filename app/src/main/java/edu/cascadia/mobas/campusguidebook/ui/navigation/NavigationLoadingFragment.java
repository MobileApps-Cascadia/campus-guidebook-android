package edu.cascadia.mobas.campusguidebook.ui.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.Location;



import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.Random;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.Location;
import edu.cascadia.mobas.campusguidebook.databinding.HomeBinding;
import edu.cascadia.mobas.campusguidebook.viewmodel.MainActivityViewModel;



public class NavigationLoadingFragment extends Fragment {

    private List<Location> mLocations;
    private MainActivityViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.list_view_events, container, false);
        mViewModel = new ViewModelProvider(this.requireActivity()).get(MainActivityViewModel.class);

        new AsyncTask<Void, Void, List<Location>>() {
            @Override
            protected List<Location> doInBackground(Void... voids) {
                mLocations = mViewModel.getAllLocations();
                return mLocations;
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPostExecute(List<Location> locations) {

                Bundle bundle = new Bundle();
                //List<String> path = calculate("Parking 1","15 Min Parking 1");
                List<String> path = calculate("Parking 1","Bus stop 1");
                //List<String> path = calculate("Parking 1","CC01 Entry LL");
                Integer i = 0;
                for (String item : path) {
                    i++;
                    Log.d("Nav", "0xB45 - Nav step " + i + ":  " + item);
                }
                bundle.putStringArrayList("NavResults", new ArrayList<>(path));
                Navigation.findNavController(viewRoot).navigate(R.id.action_to_navigation_loading_to_to_navigation, bundle);
            }
        }.execute();


        return viewRoot;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    List<String> Steps = new ArrayList<>();
    List<Integer> excludedNumbers = new ArrayList<>();
    Location currentLocation;
    Integer exeCount = 0;

    boolean returnedPath = false;
    public List<String> calculate (String mStart, String mEnd){

        List<String> mPath = calculatePath(mStart, mEnd);

        while (mPath == null && returnedPath == false) {
            Log.d("t", "0x84WQ - Restarting Path");
            Steps.clear();
            excludedNumbers.clear();
            currentLocation = null;
            mPath = calculatePath(mStart, mEnd);
        }

        returnedPath = true;
        return mPath;
    }
    public List<String> calculatePath (String Start, String End) {
        currentLocation = getLocation(Start);


        List<String> directions = Arrays.asList(
                currentLocation.getNorth(),
                currentLocation.getNorthEast(),
                currentLocation.getEast(),
                currentLocation.getSouthEast(),
                currentLocation.getSouth(),
                currentLocation.getSouthWest(),
                currentLocation.getWest(),
                currentLocation.getNorthWest()
        );

        while (directions.stream().noneMatch(direction -> direction.equals(End))) {
            //Log.d("t", "0x89D3" + Steps);
            directions = Arrays.asList(
                    currentLocation.getNorth(),
                    currentLocation.getNorthEast(),
                    currentLocation.getEast(),
                    currentLocation.getSouthEast(),
                    currentLocation.getSouth(),
                    currentLocation.getSouthWest(),
                    currentLocation.getWest(),
                    currentLocation.getNorthWest()
            );
            //if (Steps.size() == 2) {
            //Log.d("t", "0x89DA   " + currentLocation.getName() + "  Dir:  " + directions);
            //}
            switch (getRand(excludedNumbers)) {
                case 1:
                    moveAndAddToHistory(currentLocation.getNorth());
                    break;
                case 2:
                    moveAndAddToHistory(currentLocation.getNorthWest());
                    break;
                case 3:
                    moveAndAddToHistory(currentLocation.getNorthEast());
                    break;
                case 4:
                    moveAndAddToHistory(currentLocation.getEast());
                    break;
                case 5:
                    moveAndAddToHistory(currentLocation.getSouthEast());
                    break;
                case 6:
                    moveAndAddToHistory(currentLocation.getSouth());
                    break;
                case 7:
                    moveAndAddToHistory(currentLocation.getSouthWest());
                    break;
                case 8:
                    moveAndAddToHistory(currentLocation.getWest());
                    break;
                default:
            }
            exeCount++;
            if (exeCount > 30){
                exeCount = 0;
                return null;
            }

        }
        if (!directions.stream().noneMatch(direction -> direction.equals(End))){
            Steps.add(currentLocation.getName());
        }

        Steps.add(End);
        return Steps;
    }


    public Integer getRand (List<Integer> excludedNumbers){
        Random random = new Random();
        int randomNumber;
        do {
            randomNumber = random.nextInt(8) + 1;
        } while (excludedNumbers.contains(randomNumber));
        return randomNumber;
    }
    private void moveAndAddToHistory(String direction) {
        if (!direction.equals("Null") && !Steps.contains(direction)) {
            Steps.add(currentLocation.getName());
            currentLocation = getLocation(direction);
            excludedNumbers.clear();
        }
    }
    private Location getLocation(String loc){
        for (Location location : mLocations) {
            String l = location.getName();
            if (loc.equals(l)){
                return location;
            }
        }
        return null;
    }


}
