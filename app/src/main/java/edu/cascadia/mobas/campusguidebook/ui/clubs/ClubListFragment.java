package edu.cascadia.mobas.campusguidebook.ui.clubs;

import androidx.annotation.NonNull;
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
import edu.cascadia.mobas.campusguidebook.viewmodel.ActivityViewModel;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// ClubsListFragment
// Displays the list of all clubs
@RequiresApi(api = Build.VERSION_CODES.O)
public class ClubListFragment extends Fragment {

    private static final String TAG = "ClubListFragment";
    private ActivityViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LiveData<List<ClubUIItem>> mClubList = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this.getActivity()).get(ActivityViewModel.class);
        mClubList = mViewModel.getAllClubs();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_club_list, container, false);
        viewRoot.setTag(TAG);

        // RecyclerView setup
        mRecyclerView = viewRoot.findViewById(R.id.club_list_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));
        mClubList = mViewModel.getAllClubs();
        mListAdapter = new ListAdapter(mClubUiList.getValue(), mViewModel);
        mRecyclerView.setAdapter(mListAdapter);

        // Respond to changes in LiveData
        mClubList.observe(this.getViewLifecycleOwner(), (mClubList) -> mListAdapter.setList(mClubList));

        // return the inflated root view
        return viewRoot;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}