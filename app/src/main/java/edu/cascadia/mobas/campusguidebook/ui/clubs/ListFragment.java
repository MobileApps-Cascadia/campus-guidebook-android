package edu.cascadia.mobas.campusguidebook.ui.clubs;

import android.util.Log;

import androidx.lifecycle.LiveData;
import java.util.List;
import edu.cascadia.mobas.campusguidebook.data.model.Club;
import edu.cascadia.mobas.campusguidebook.ui.BaseListFragment;

public class ListFragment extends BaseListFragment<Club> {
    @Override
    public LiveData<List<Club>> getList() {
        return getViewModel().getAllClubs();
    }

    @Override
    protected void itemClicked(Club item) {
        Log.d("Clicked", item.getName());

    }
}
