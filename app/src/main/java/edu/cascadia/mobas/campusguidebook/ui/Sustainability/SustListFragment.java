package edu.cascadia.mobas.campusguidebook.ui.Sustainability;

import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import java.util.List;


import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.Sustainability;
import edu.cascadia.mobas.campusguidebook.ui.events.EventsListFragment;


public class SustListFragment extends EventsListFragment<Sustainability> {
    @Override
    public LiveData<List<Sustainability>> getList() {
        return getViewModel().getAllSustainability();
    }

    @Override
    public void itemClicked(Sustainability item) {
        getViewModel().setDetailsItem(item);
        Navigation.findNavController(getRootView()).navigate(R.id.nav_sust_details);
    }
}
