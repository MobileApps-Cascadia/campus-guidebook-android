package edu.cascadia.mobas.campusguidebook;

import android.os.Bundle;
import android.view.Menu;



import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;


public class MainActivity extends FragmentActivity {
    // Get the NavController
    private NavController navController;
//    private final MediatorLiveData<List<UIItem>> mLiveClubList = new MediatorLiveData<List<ClubUIItem>>();

    // No need to load the start destination, handled automatically by the Navigation component
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
