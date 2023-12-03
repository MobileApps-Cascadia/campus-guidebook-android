package edu.cascadia.mobas.campusguidebook.ui.clubs;

import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.Club;
import edu.cascadia.mobas.campusguidebook.ui.events.EventsListFragment;

public class ClubListFragment extends EventsListFragment<Club> {

    @Override
    public LiveData<List<Club>> getList() {
        return getViewModel().getAllClubs();
    }

    @Override
    public void itemClicked(Club item) {
        getViewModel().setDetailsItem(item);
        Navigation.findNavController(getRootView()).navigate(R.id.nav_club_details);
    }
}