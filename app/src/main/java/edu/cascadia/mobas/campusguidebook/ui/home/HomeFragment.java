package edu.cascadia.mobas.campusguidebook.ui.home;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Random;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.Location;
import edu.cascadia.mobas.campusguidebook.databinding.HomeBinding;
import edu.cascadia.mobas.campusguidebook.ui.navigation.ShortestPath;
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

                List<String> path = calculate("Parking 1","15 Min Parking 1");
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






    public List<String> calculate (String Start, String End) {
        Integer Distance = 0;
        Location currentLocation = getLocation(Start);
        List<String> History = new ArrayList<>();
        List<String> Steps = new ArrayList<>();
        Steps.add(Start);
        //Choosing a random direction to look at and keeping history of directions chosen.
        Random random = new Random();
        int randomNumber = random.nextInt(8) + 1;
        List<Integer> randHistory = new ArrayList<>();
        if(currentLocation != null) {
            while (!currentLocation.getNorth().equals(End) && !currentLocation.getNorthEast().equals(End) && !currentLocation.getEast().equals(End) && !currentLocation.getSouthEast().equals(End) && !currentLocation.getSouth().equals(End) && !currentLocation.getSouthWest().equals(End) && !currentLocation.getWest().equals(End)) {
                Log.d("t", "0x45B" + Steps);

                while(!randHistory.contains(randomNumber)) {
                    randomNumber = random.nextInt(8) + 1;
                }
                randHistory.add(randomNumber);

                if (!currentLocation.getNorth().equals("Null") && !History.contains(currentLocation.getNorth())) {
                    Steps.add(currentLocation.getName());
                    History.add(currentLocation.getName());
                    currentLocation = getLocation(currentLocation.getNorth());
                    continue;
                }
                //Log.d("t", "0x81A2 - " + nw.equals(From));
                if (!currentLocation.getNorthWest().equals("Null") && !History.contains(currentLocation.getNorthWest())) {
                    //Log.d("t", "0x81A2 - Trigger NW");
                    Steps.add(currentLocation.getName());
                    History.add(currentLocation.getName());
                    currentLocation = getLocation(currentLocation.getNorthWest());
                    continue;
                }
                if (!currentLocation.getNorthEast().equals("Null") && !History.contains(currentLocation.getNorthEast())) {
                    Steps.add(currentLocation.getName());
                    History.add(currentLocation.getName());
                    currentLocation = getLocation(currentLocation.getNorthEast());
                    continue;
                }
                if (!currentLocation.getEast().equals("Null") && !History.contains(currentLocation.getEast())) {
                    Steps.add(currentLocation.getName());
                    History.add(currentLocation.getName());
                    currentLocation = getLocation(currentLocation.getEast());
                    continue;
                }
                if (!currentLocation.getSouthEast().equals("Null") && !History.contains(currentLocation.getSouthEast())) {
                    Steps.add(currentLocation.getName());
                    History.add(currentLocation.getName());
                    currentLocation = getLocation(currentLocation.getSouthEast());
                    continue;
                }
                if (!currentLocation.getSouth().equals("Null") && !History.contains(currentLocation.getSouth())) {
                    Steps.add(currentLocation.getName());
                    History.add(currentLocation.getName());
                    currentLocation = getLocation(currentLocation.getSouth());
                    continue;
                }
                if (!currentLocation.getSouthWest().equals("Null") && !History.contains(currentLocation.getSouthWest())) {
                    Steps.add(currentLocation.getName());
                    History.add(currentLocation.getName());
                    currentLocation = getLocation(currentLocation.getSouthWest());
                    continue;
                }
                if (!currentLocation.getWest().equals("Null") && !History.contains(currentLocation.getWest())) {
                    Steps.add(currentLocation.getName());
                    History.add(currentLocation.getName());
                    currentLocation = getLocation(currentLocation.getWest());
                }
            }
        }
        return Steps;
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
