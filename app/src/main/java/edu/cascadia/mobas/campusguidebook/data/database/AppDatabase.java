package edu.cascadia.mobas.campusguidebook.data.database;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.TypeConverters;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import edu.cascadia.mobas.campusguidebook.application.AppConfig;
import edu.cascadia.mobas.campusguidebook.data.SampleData;
import edu.cascadia.mobas.campusguidebook.data.dao.*;
import edu.cascadia.mobas.campusguidebook.data.model.*;
import edu.cascadia.mobas.campusguidebook.data.typeconverter.*;
import edu.cascadia.mobas.campusguidebook.application.AppExecutors;



// adding annotation for our database entities and db version.
@Database(entities = {
        Event.class,
        Club.class,
        Sustainability.class,
        User.class}, version = 1)
@TypeConverters({ZonedDateTimeConverter.class, PropertyListTypeConverter.class})
@RequiresApi(api = Build.VERSION_CODES.O)
public abstract class AppDatabase extends RoomDatabase {

    // below line is to create instance
    // for our singleton database class.
    private static AppDatabase sAppDatabase;

    // below line is to save the executor pool
    // to create database and supply repositories

    // below line is to create`
    // abstract variable for dao.
    public abstract EventDao EventDao();
    public abstract ClubDao ClubDao();
    public abstract SustainabilityDao SustainabilityDao();
    public abstract UserDao UserDao();

    // on below line we are getting instance for our database.
    public static AppDatabase getInstance(final Application application, final AppExecutors appExecutors) {
        // return existing singleton instance
        // unless the instance is null
        if (sAppDatabase == null) {
            // otherwise block access until database is built
            synchronized (AppDatabase.class) {
                // if the instance is still null under synchronization,
                // we are creating a new instance
                if (sAppDatabase == null) {
                    sAppDatabase =
                            // for creating a instance for our database
                            // we are creating a database builder and passing
                            // our database class with our database name.
                            Room.databaseBuilder(application.getApplicationContext(),
                                    AppDatabase.class, AppConfig.DATABASE_FILENAME)
                                    // below line adds callback (only logs its execution)
                                    .addCallback(new RoomDatabase.Callback() {
                                        @Override
                                        public void onCreate(@NonNull SupportSQLiteDatabase sqLiteDatabase) {
                                            super.onCreate(sqLiteDatabase);
                                            Log.d("AppDatabase", "RoomDatabase.Callback Executed");
                                        }
                                    })
                                    .build();  // end of RoomDatabaseBuilder
                    // TODO: REPLACE THE FOLLOWING BEFORE RELEASE
                    SampleData.addAll(sAppDatabase, appExecutors);
                }
            } // End of synchronized block
        }
        return sAppDatabase;
    } // End of getInstance

}