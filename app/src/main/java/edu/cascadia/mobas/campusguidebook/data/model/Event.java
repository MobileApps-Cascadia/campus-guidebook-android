package edu.cascadia.mobas.campusguidebook.data.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.OffsetDateTime;

@Entity(tableName = "Event_Table")
public class Event {
    // below line is to auto increment
    // id for each Event.
    @PrimaryKey(autoGenerate = true)

    // variable for our id.
    private int id;

    // below line is a variable
    // for Event name.
    @ColumnInfo(name = "event_name")
    private String eventName;

    // below line is use for
    // Event Description.
    @ColumnInfo(name = "description")
    private String description;

    // below line is use
    // for Event Location.
    @ColumnInfo(name = "location")
    private String location;

    // below line is use
    // for Event Date and Time.
    @ColumnInfo(name = "date_time")
    private OffsetDateTime dateTime;

    // below line we are creating constructor class.
    // inside constructor class we are not passing
    // our id because it is incrementing automatically
    public Event(String eventName, String description, String location, OffsetDateTime dateTime) {
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
    }

    // on below line we are creating
    // getter and setter methods that
    // will auto-generated by Room

    public int getId() {
        return this.id;
    }
    public void setId(int ID) {
        this.id = ID;
    }

    public String getEventName() { return this.eventName; }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() { return this.description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() { return this.location; }
    public void setLocation(String location) {
        this.location = location;
    }

    public OffsetDateTime getDateTime(){ return this.dateTime; }
    public void setDateTime(OffsetDateTime dateTime){
        this.dateTime = dateTime;
    }

}
