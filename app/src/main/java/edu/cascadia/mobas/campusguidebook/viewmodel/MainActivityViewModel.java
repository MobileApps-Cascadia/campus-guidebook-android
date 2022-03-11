package edu.cascadia.mobas.campusguidebook.viewmodel;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cascadia.mobas.campusguidebook.application.AppExecutors;
import edu.cascadia.mobas.campusguidebook.CampusGuidebookApp;
import edu.cascadia.mobas.campusguidebook.data.model.Club;
import edu.cascadia.mobas.campusguidebook.data.model.Event;
import edu.cascadia.mobas.campusguidebook.data.model.IEntity;
import edu.cascadia.mobas.campusguidebook.data.model.Sustainability;
import edu.cascadia.mobas.campusguidebook.data.repository.AppRepository;
import edu.cascadia.mobas.campusguidebook.data.repository.ImageRepository;

public class MainActivityViewModel extends AndroidViewModel {

    // provide a place to "remember" the last item clicked for each entity
    private final Map<String, IEntity> destinationMap = new HashMap<>();
    // private final LiveData<IEntity> listClickNotifier = new MutableLiveData<IEntity>();

    private final AppRepository mAppRepository;
    private final ImageRepository mImageRepository;
    private final AppExecutors mAppExecutors;
    private final CampusGuidebookApp app;
    private IEntity detailsItem;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        app = (CampusGuidebookApp) application;
        mAppRepository = app.getAppRepository();
        mImageRepository = app.getImageRepository();
        mAppExecutors = app.getAppExecutors();
    }


    public IEntity getDetailsItem() {
        return detailsItem;
    }

    public <T extends IEntity> void setDetailsItem(T item) {
        detailsItem = item;
    }

    public void navigateToDetails() {}

    public LiveData<List<Event>> getAllEvents() {
        return mAppRepository.getAllEvents();
    }

    public LiveData<List<Club>> getAllClubs() {
        return mAppRepository.getAllClubs();
    }

    public LiveData<List<Sustainability>> getAllSustainability() {
        return mAppRepository.getAllSustainability();
    }

    public LiveData<Drawable> getImageFromUri(String uri) {
        return mImageRepository.getImage(uri);
    }

    public boolean addNewEvent(String eventName, String eventDescription, String eventLocation, ZonedDateTime eventDateTime, String imageUri) {
        return mAppRepository.addNewEvent(eventName, eventDescription, eventLocation, eventDateTime, imageUri);
    }
}
