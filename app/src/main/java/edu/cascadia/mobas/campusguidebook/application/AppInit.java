package edu.cascadia.mobas.campusguidebook.application;

import android.os.Build;

import androidx.annotation.RequiresApi;

import edu.cascadia.mobas.campusguidebook.CampusGuidebookApp;
import edu.cascadia.mobas.campusguidebook.data.database.AppDatabase;
import edu.cascadia.mobas.campusguidebook.data.repository.AppRepository;
import edu.cascadia.mobas.campusguidebook.data.repository.ImageRepository;
@RequiresApi(api = Build.VERSION_CODES.O)

public class AppInit {

    // Make app and its context available for initializers
    private CampusGuidebookApp mApplication;

    // List of initialization tasks (private command pattern classes below)
    private final Runnable[] initializationTasks = {
            new InitializeRepositories(),
            new PreloadImageCache()
    };

    private AppInit() {
    }

    public AppInit(CampusGuidebookApp app) {
        this.mApplication = app;
    }

    // run all the initialization tasks
    public void initialize() {

        // Use an executor to Iterate through list of initialization tasks and execute
        // for now, tasks are executed on a single background thread, in sequence

        mApplication.getAppExecutors().diskIO().execute(() -> {
            for (Runnable task : initializationTasks) {
                task.run();
            }
        });
    }

    // Action to initialize the database
    private class InitializeDatabase implements Runnable {
        public void run() {
            AppDatabase database = mApplication.getAppDatabase();
        }
    }

    // Action to load repositories for the first time to initialize singletons
    private class InitializeRepositories implements Runnable {
        public void run() {
            AppRepository repo = mApplication.getAppRepository();
            ImageRepository imageRepository = mApplication.getImageRepository();
        }
    }

    // TODO: Implement PreloadImageCache
    // Action to preload image cache
    private class PreloadImageCache implements Runnable {
        public void run() {
            return;
        }
    }
}