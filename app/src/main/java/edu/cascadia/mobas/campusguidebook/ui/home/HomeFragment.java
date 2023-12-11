package edu.cascadia.mobas.campusguidebook.ui.home;

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


public class HomeFragment extends Fragment {

    private HomeBinding binding;

    private MainActivityViewModel mViewModel;

    protected MainActivityViewModel getViewModel() {
        return mViewModel;
    }
    private List<Location> mLocations;





    // All UI behavior has moved from MainActivity to fragments
    // View initialization logic goes in onCreateView
    @SuppressLint("StaticFieldLeak")
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = HomeBinding.inflate(inflater, container, false);
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
                //TODO: For Testing:

                //List<String> path = calculate("Parking 1","15 Min Parking 1");
                List<String> path = calculate("Parking 1","Bus stop 1");
                //List<String> path = calculate("Parking 1","CC01 Entry LL");
                Integer i = 0;
                for (String item : path) {
                    i++;
                    Log.d("Nav", "0xB45 - Nav step " + i + ":  " + item);
                }
            }
        }.execute();
        return binding.getRoot();
    }

    // Initialization logic beyond view initialization goes on onViewCreated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Initialize properties of individual views, such as onClickListeners
        binding.cardViewHomeEvents.setOnClickListener(CardView -> {
            Navigation.findNavController(binding.cardViewHomeEvents)
                    .navigate(R.id.action_nav_home_to_nav_events);
        });
        binding.cardViewHomeInfo.setOnClickListener(CardView -> {
            Navigation.findNavController(binding.cardViewHomeInfo)
                    .navigate(R.id.nav_sust_list);
        });
        binding.cardViewHomeClubs.setOnClickListener(CardView -> {
                Navigation.findNavController(binding.cardViewHomeClubs)
                        .navigate(R.id.action_nav_home_to_nav_clubs);
        });


        
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
