package edu.cascadia.mobas.campusguidebook.ui.home;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        String From = null;
        List<String> Steps = new ArrayList<>();
        Steps.add(Start);
        while (currentLocation.getNorth() != End || currentLocation.getNorthEast() != End || currentLocation.getEast() != End || currentLocation.getSouthEast() != End || currentLocation.getSouth() != End || currentLocation.getSouthWest() != End || currentLocation.getWest() != End) {
            Log.d("t", "0x45B" + Steps);
            if (currentLocation.getNorth() != null && currentLocation.getNorth() != From) {
                From = currentLocation.getName();
                Steps.add(currentLocation.getName());
                currentLocation = getLocation(currentLocation.getNorth());
                continue;
            }
            String nw = currentLocation.getNorthWest();
            //Log.d("t", "0x45B" + nw.equals(From));
            if (nw != null && !nw.equals(From)) {
                From = currentLocation.getName();
                Steps.add(nw);
                currentLocation = getLocation(currentLocation.getNorthWest());
            }
            if (currentLocation.getNorthEast() != null && !currentLocation.getNorthEast().equals(From)) {
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
            String l = location.getName();
            if (loc.equals(l)){
                return location;
            }
        }
        return null;
    }
}
