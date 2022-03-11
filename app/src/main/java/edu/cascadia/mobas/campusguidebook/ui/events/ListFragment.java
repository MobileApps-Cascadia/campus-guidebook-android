package edu.cascadia.mobas.campusguidebook.ui.events;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.Event;
import edu.cascadia.mobas.campusguidebook.databinding.EventListBinding;
import edu.cascadia.mobas.campusguidebook.viewmodel.EventListViewModel;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


// EventListFragment
// Displays the list of all Events
public class ListFragment extends Fragment {

    private static final String TAG = "EventListFragment";
    private EventListViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LiveData<List<UIItem>> mEventList = null;
    private EventListBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EventListViewModel.class);
        mEventList = mViewModel.getAllEvents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = EventListBinding.inflate(inflater, container,false);

        // RecyclerView setup
        mRecyclerView = mBinding.recyclerView;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));
        mListAdapter = new ListAdapter(mEventList.getValue(), mViewModel);
        mRecyclerView.setAdapter(mListAdapter);

        // Respond to changes in LiveData
        mEventList.observe(this.getViewLifecycleOwner(), (mEventList) -> mListAdapter.setList(mEventList));

        FloatingActionButton addEventBtn = mBinding.getRoot().findViewById(R.id.floatingActionButton);
        addEventBtn.setOnClickListener(view -> {
            Navigation.findNavController(addEventBtn).navigate(R.id.addEventFragment);
        });

        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}