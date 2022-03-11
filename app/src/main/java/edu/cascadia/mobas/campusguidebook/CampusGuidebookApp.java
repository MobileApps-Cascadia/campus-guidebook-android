package edu.cascadia.mobas.campusguidebook;

import android.app.Application;
import android.os.Build;
import androidx.annotation.RequiresApi;

import edu.cascadia.mobas.campusguidebook.application.AppExecutors;
import edu.cascadia.mobas.campusguidebook.data.database.AppDatabase;
import edu.cascadia.mobas.campusguidebook.data.repository.AppRepository;
import edu.cascadia.mobas.campusguidebook.data.repository.ImageRepository;

// Top level class to implement inject dependencies
// and make database access through viewmodels/repositories
// available everywhere it is needed in the app

@RequiresApi(api = Build.VERSION_CODES.O)
public class CampusGuidebookApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
    }

    // return an instance of the database
    public AppDatabase getAppDatabase() {
        return AppDatabase.getInstance(this, getAppExecutors());
    }

    // return an instance of the repository
    public AppRepository getAppRepository() {
        return AppRepository.getInstance(getAppDatabase(), getAppExecutors());
    }

    public ImageRepository getImageRepository() {
        return ImageRepository.getInstance(this, getAppDatabase(), getAppExecutors() );
    }

    // make this executor pool available
    public AppExecutors getAppExecutors() {
        return mAppExecutors;
    }
}