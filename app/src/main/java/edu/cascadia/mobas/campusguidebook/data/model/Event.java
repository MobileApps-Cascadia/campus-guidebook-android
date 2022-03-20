package edu.cascadia.mobas.campusguidebook.data.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;
import java.util.Map;

import edu.cascadia.mobas.campusguidebook.application.AppConfig;
import edu.cascadia.mobas.campusguidebook.data.typeconverter.PropertyListTypeConverter;
import edu.cascadia.mobas.campusguidebook.data.typeconverter.ZonedDateTimeConverter;

@Entity(tableName = "Event_Table")
public class Event implements IEntity {
    // returns the name of the class to allow generic fragments to utilize navigation
    @Override @Ignore
    public final String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "date_time")
    private ZonedDateTime dateTime;

    @NonNull
    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "image_uri")
    private String imageUri;

    @ColumnInfo(name = "properties")
    private Map<String, String> properties;

    @ColumnInfo(name = "date_updated")
    private ZonedDateTime lastUpdated;

    @ColumnInfo(name = "upload_status")
    private int uploadStatus;

    public Event(long id, String name, String description, String location, ZonedDateTime dateTime,
                 String imageUri, Map<String,String> properties, ZonedDateTime lastUpdated,
                 int uploadStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.imageUri = imageUri;
        this.properties = properties;
        this.lastUpdated = lastUpdated;
        this.uploadStatus = uploadStatus;
    }

    @Ignore
    public Event(long id, String name, String description, String location, ZonedDateTime dateTime,
                 String imageUri, String properties) {
        this(id, name, description, location, dateTime, imageUri,
                PropertyListTypeConverter.jsonStrToMap(properties),
                ZonedDateTime.now(AppConfig.TIMEZONE), 0);
    }

    // getter and setter methods
    public void setId(int ID) { this.id = ID; }
    public void setName(String eventName) { this.name = name; }
    public void setLocation(String location) { this.properties.put("Location", location); }
    public void setDateTime(ZonedDateTime dateTime){ this.properties.put("Date/Time", ZonedDateTimeConverter.fromZonedDateTime(dateTime)); }
    public void setImageUri(String uri) { this.imageUri = uri; }
    public void setProperties(Map<String, String> properties) { this.properties = properties; };

    @Override public long getId() { return this.id; }
    @Override public String getName() { return this.name; }
    @Override public String getDescription() { return this.description; }
    @Override public String getImageUri() { return this.imageUri; }
    @Override public Map<String, String> getProperties() { return this.properties; }
    @Override public ZonedDateTime getLastUpdated() { return this.lastUpdated; }
    @Override public int getUploadStatus() { return 0; }
    public String getLocation() { return this.location; }
    public ZonedDateTime getDateTime(){ return this.dateTime; }
}

