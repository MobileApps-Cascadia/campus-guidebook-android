package edu.cascadia.mobas.campusguidebook.data.repository;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.collection.LruCache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import edu.cascadia.mobas.campusguidebook.application.AppConfig;
import edu.cascadia.mobas.campusguidebook.application.AppExecutors;
import edu.cascadia.mobas.campusguidebook.CampusGuidebookApp;
import edu.cascadia.mobas.campusguidebook.data.database.AppDatabase;

@RequiresApi(android.os.Build.VERSION_CODES.O)
public class ImageRepository {

    // singleton static class storage
    private static ImageRepository sImageRepository = null;

    // in memory cache for loaded images not in res/drawables
    private LruCache<String, Drawable> mMemCache = new LruCache<String, Drawable>(AppConfig.IMAGE_MEMORY_CACHE_ENTRIES);

    // for maintaining the LRU behavior of cached files
    private LruCache<String, String> fileCache;

    // for executing disk and web requests on other threads
    private AppExecutors mAppExecutors;

    // needed to access drawables
    private Context mContext;

    // image will be instantly used when needed
    private Drawable defaultImage;

    // lookups for images in res/drawables
    private static Map<String, Integer> sResImageMap;

    // private default constructor to enforce singleton
    private ImageRepository() {}

    // private standard constructor takes AppDatabase and AppExecutors to initialize singleton
    private ImageRepository(Context context)  {
        if (sImageRepository != null) { return; }
        mAppExecutors = ((CampusGuidebookApp)context.getApplicationContext()).getAppExecutors();
        mContext = context.getApplicationContext();
        defaultImage = AppCompatResources.getDrawable(context, AppConfig.DEFAULT_IMAGE);
        mMemCache = new LruCache<String, Drawable>(32);
    }

    // returns the singleton instance of this repository, creating it if not exists
    public static ImageRepository getInstance(Context context, AppDatabase database, AppExecutors executors) {
        if (sImageRepository == null) {
            synchronized (ImageRepository.class) {
                sResImageMap = buildResourceMap(context);
                sImageRepository = new ImageRepository(context);
            }
        }
        return sImageRepository;
    }

    // returns the singleton instance only if it already exists
    @Nullable
    public static ImageRepository getInstance() {
        return sImageRepository;
    }

    // returns an Drawable image wrapped in LiveData
    @NonNull
    public LiveData<Drawable> getImage(@Nullable final String uri) {
        final MutableLiveData<Drawable> liveImage = new MutableLiveData<Drawable>();

        // return the default image if the uri is empty or null
        if (uri == null || uri.equals("")) {
            liveImage.postValue(defaultImage);
            return liveImage;
        }

        // returns a livedata drawable from resources if it exists
        if (sResImageMap.containsKey(uri)) {
            liveImage.postValue(AppCompatResources.getDrawable(mContext, sResImageMap.get(uri)));
            return liveImage;
        }

        // returns the drawable from the memory cache, if present
        Drawable image = mMemCache.get(uri);
        if (image != null) {
            liveImage.postValue(image);
            return liveImage;
        }

        // The rest of the possible locations will be searched asynchronously
        // while the default image is returned initially
        if (new File(mContext.getFilesDir(),uri).exists()) {
            liveImage.postValue(defaultImage);
            mAppExecutors.diskIO().execute( () -> {

            });
        }

        // Get the image from the web api
        liveImage.postValue(defaultImage);
        // use a different thread to change the livedata when the image is loaded
        mAppExecutors.networkIO().execute( () -> {
            try {
                // Establish a connection to the webserver
                HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();
                connection.connect();
                // Get the image file from there
                InputStream inputStream = connection.getInputStream();
                Drawable webImage = new BitmapDrawable(mContext.getResources(), BitmapFactory.decodeStream(inputStream));
                liveImage.postValue(webImage);
                mMemCache.put(uri, webImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // returns instantly with default livedata, updated when thread above finishes
        return liveImage;
    }

    // Obtain the user readable names for drawables in res/drawable
    private static Map<String, Integer> buildResourceMap(Context context) {

        // Use TreeMap for O(log n) lookups
        Map<String, Integer> map = new TreeMap<String, Integer>();

        // iterate through drawables and add each to the map by name
        Field[] drawables = edu.cascadia.mobas.campusguidebook.R.drawable.class.getFields();
        for(Field field : drawables) {
            try {
                map.put(field.getName(), field.getInt(null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    // Adds local files to LRU File Cache
    private LruCache<String, String> buildFileCache() {
        LruCache<String, String> fileCache = new LruCache<String, String>(AppConfig.IMAGE_FILE_CACHE_ENTRIES);
        // TODO: Implement file cache
        return fileCache;
    }
}


