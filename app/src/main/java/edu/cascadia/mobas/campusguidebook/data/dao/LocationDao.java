package edu.cascadia.mobas.campusguidebook.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.data.model.Location;


@androidx.room.Dao
public interface LocationDao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(Location location);

    // below method is use to update
    // the data in our database.
    @Update
    void update(Location location);

    // below line is use to delete a
    // specific Event in our database.
    @Delete
    void delete(Location location);

    @Query("SELECT * FROM Location_Table")
    List<Location> getAll();

    @Query("SELECT * FROM Location_Table WHERE id=:id")
    LiveData<Location> getById(long id);

    @Query("SELECT COUNT(*) FROM Location_Table")
    int getCount();

}
