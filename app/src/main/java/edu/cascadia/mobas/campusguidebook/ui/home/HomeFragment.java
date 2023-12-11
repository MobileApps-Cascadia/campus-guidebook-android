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

    // All UI behavior has moved from MainActivity to fragments
    // View initialization logic goes in onCreateView
    @SuppressLint("StaticFieldLeak")
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = HomeBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this.requireActivity()).get(MainActivityViewModel.class);


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
        binding.cardViewHomeUNUSED4.setOnClickListener(CardView -> {
            Navigation.findNavController(binding.cardViewHomeUNUSED4)
                    .navigate(R.id.action_nav_home_to_to_navigation_loading);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
