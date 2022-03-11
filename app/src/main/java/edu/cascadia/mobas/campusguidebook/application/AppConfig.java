package edu.cascadia.mobas.campusguidebook.application;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.ZoneId;
import java.util.TimeZone;

import edu.cascadia.mobas.campusguidebook.R;

@RequiresApi(api = Build.VERSION_CODES.O)

public class AppConfig {
    // Cascadia College local time zone
    public static final ZoneId TIMEZONE = TimeZone.getTimeZone("America/Los_Angeles").toZoneId();

    // Database
    public static final String DATABASE_FILENAME = "CampusGuidebook.sqlite";

    // Image repository
    public static final int DEFAULT_IMAGE = R.drawable.default_image;
    public static final int IMAGE_FILE_CACHE_ENTRIES = 32;
    public static final int IMAGE_MEMORY_CACHE_ENTRIES = 32;

    // Splash screen display time in ms
    public static final int SPLASH_DISPLAY_TIME = 1500;
}
