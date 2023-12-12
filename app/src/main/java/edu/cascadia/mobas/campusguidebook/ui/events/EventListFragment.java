package edu.cascadia.mobas.campusguidebook.ui.events;

import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.Event;

public class EventListFragment extends EventsListFragment<Event> {

    @Override
    public LiveData<List<Event>> getList() {
        return getViewModel().getAllEvents();
    }

    @Override
    public void itemClicked(Event item) {
        getViewModel().setDetailsItem(item);
        Navigation.findNavController(getRootView()).navigate(R.id.nav_club_details);
    }
}