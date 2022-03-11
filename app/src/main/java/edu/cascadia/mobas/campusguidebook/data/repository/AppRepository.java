package edu.cascadia.mobas.campusguidebook.data.repository;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import java.time.ZonedDateTime;
import java.util.List;
import edu.cascadia.mobas.campusguidebook.application.AppExecutors;
import edu.cascadia.mobas.campusguidebook.data.database.AppDatabase;
import edu.cascadia.mobas.campusguidebook.data.model.*;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AppRepository {

    // instance variable for singleton
    private static AppRepository sAppRepository = null;
    private AppDatabase mAppDatabase = null;
    private AppExecutors mAppExecutors = null;

    // private constructor to enforce singleton pattern
    private AppRepository() {}

    private AppRepository(AppDatabase db, AppExecutors executors) {
        if (sAppRepository == null) {
            mAppDatabase = db;
            mAppExecutors = executors;
            sAppRepository = this;
        }
    }

    // returns the AppRepository instance, creating if necessary
    public static AppRepository getInstance(AppDatabase db, AppExecutors executors) {
        if (sAppRepository == null) {
            sAppRepository = new AppRepository(db, executors);
        }
        return sAppRepository;
    }

    // METHODS TO RETURN LIVEDATA FROM APPDATABASE
    // returns a list of all events
    public LiveData<List<Event>> getAllEvents() {
        return mAppDatabase.EventDao().getAllEvents();
    }

    // returns a single event by ID
    public LiveData<Event> getEvent(int eventId) { return mAppDatabase.EventDao().getEventByID(eventId); }

    // returns a list of all users
    public LiveData<List<User>> getAllUsers() {
        return mAppDatabase.UserDao().getAllUsers();
    }

    // returns a list of all clubs
    public LiveData<List<Club>> getAllClubs() {
        return mAppDatabase.ClubDao().getAll();
    }

    // returns a list of all sustainability
    public LiveData<List<Sustainability>> getAllSustainability() {
        return mAppDatabase.SustainabilityDao().getAllSustainability();
    }

    public boolean addNewEvent(String eventName, String eventDescription, String eventLocation, ZonedDateTime eventDateTime, String imageUri) {
        Event event = new Event(eventName, eventDescription, eventLocation, eventDateTime, imageUri);
        try {
            this.insert(event);
            return true;
        } catch (Exception e) {
            Log.e("Error adding new event: ", e.getMessage() );
            return false;
        }
    }

    // Additional Event CRUD operations
    public void insert(Event... events) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (Event event:events) {
                mAppDatabase.EventDao().insert(event);
            }
        }));
    }

    public void update(Event... events) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (Event event:events) {
                mAppDatabase.EventDao().update(event);
            }
        }));
    }

    public void delete(Event... events) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (Event event : events) {
                mAppDatabase.EventDao().delete(event);
            }
        }));
    }

    // Additional Club CRUD operations
    public void insert(Club... clubs) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (Club club : clubs) {
                mAppDatabase.ClubDao().insert(club);
            }
        }));
    }

    public void update(Club... clubs) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (Club club : clubs) {
                mAppDatabase.ClubDao().update(club);
            }
        }));
    }

    public void delete(Club... clubs) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (Club club : clubs) {
                mAppDatabase.ClubDao().delete(club);
            }
        }));
    }

    // Additional Sustainability CRUD operations
    public void insert(Sustainability... sustainabilities) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (Sustainability sustainability : sustainabilities) {
                mAppDatabase.SustainabilityDao().insert(sustainability);
            }
        }));
    }

    public void update(Sustainability... sustainabilities) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (Sustainability sustainability : sustainabilities) {
                mAppDatabase.SustainabilityDao().update(sustainability);
            }
        }));
    }

    public void delete(Sustainability... sustainabilities) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (Sustainability sustainability : sustainabilities) {
                mAppDatabase.SustainabilityDao().delete(sustainability);
            }
        }));
    }

    // Additional User CRUD operations
    public void insert(User... users) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (User user : users) {
                mAppDatabase.UserDao().insert(user);
            }
        }));
    }

    public void update(User... users) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (User user : users) {
                mAppDatabase.UserDao().update(user);
            }
        }));
    }

    public void delete(User... users) {
        mAppExecutors.diskIO().execute( () -> mAppDatabase.runInTransaction( () -> {
            for (User user : users) {
                mAppDatabase.UserDao().delete(user);
            }
        }));
    }
}
