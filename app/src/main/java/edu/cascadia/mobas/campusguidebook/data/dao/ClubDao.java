package edu.cascadia.mobas.campusguidebook.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.data.model.Club;
import edu.cascadia.mobas.campusguidebook.data.model.User;


@androidx.room.Dao
public interface ClubDao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(Club club);

    // below method is use to update
    // the data in our database.
    @Update
    void update(Club club);

    // below line is use to delete a
    // specific Event in our database.
    @Delete
    void delete(Club club);

    @Query("SELECT * FROM Club_Table")
    LiveData<List<Club>> getAll();

    @Query("SELECT * FROM Club_Table WHERE id=:id")
    LiveData<Club> getById(long id);

    @Query("SELECT COUNT(*) FROM Club_Table")
    int getCount();

}
