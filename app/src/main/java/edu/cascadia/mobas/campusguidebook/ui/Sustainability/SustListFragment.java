package edu.cascadia.mobas.campusguidebook.ui.Sustainability;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.ui.clubs.ClubListAdapter;
import edu.cascadia.mobas.campusguidebook.ui.clubs.ClubUIItem;
import edu.cascadia.mobas.campusguidebook.viewmodel.ClubListViewModel;

// ClubsListFragment
// Displays the list of all clubs
@RequiresApi(api = Build.VERSION_CODES.O)
public class SustListFragment extends Fragment {

    private static final String TAG = "ClubListFragment";
    private ClubListViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private ClubListAdapter mClubListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LiveData<List<ClubUIItem>> mClubList = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ClubListViewModel.class);
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
        mClubListAdapter = new ClubListAdapter(mClubList.getValue(), mViewModel);
        mRecyclerView.setAdapter(mClubListAdapter);

        // Respond to changes in LiveData
        mClubList.observe(this.getViewLifecycleOwner(), (mClubList) -> mClubListAdapter.setList(mClubList));

        // return the inflated root view
        return viewRoot;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}