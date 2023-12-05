package edu.cascadia.mobas.campusguidebook.ui.navigation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.IEntity;
import edu.cascadia.mobas.campusguidebook.ui.events.EventsListAdapter;
import edu.cascadia.mobas.campusguidebook.viewmodel.MainActivityViewModel;

// ListFragment
// Base class for displaying a recyclerview for the provided type
public abstract class NavigationFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.list_view_events, container, false);

        //TODO: get best path
        //TODO: form list of steps
        //TODO: present overview as first step
        //TODO: on next, start ticking through steps
            //TODO: new method for steps to human text before displaying


        //TODO: Trigger MapGraph build
        //TODO: Run Nav alg
        //TODO: send best path to nav frag


        return viewRoot;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }


}
