package edu.cascadia.mobas.campusguidebook.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import edu.cascadia.mobas.campusguidebook.application.AppConfig;

import edu.cascadia.mobas.campusguidebook.data.typeconverter.PropertyListTypeConverter;


@Entity(tableName = "Club_Table")
public class Club implements IEntity{

    // returns the name of the class to allow generic fragments to utilize navigation
    @Override @Ignore
    public final String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id = 0;

    @NonNull
    @ColumnInfo(name = "name")
    private String name = "";

    @ColumnInfo(name = "details")
    private String details;

    @ColumnInfo(name = "image_uri")
    private String imageUri;

    @ColumnInfo(name = "properties")
    private Map<String, String> properties;

    @ColumnInfo(name = "last_updated")
    private ZonedDateTime lastUpdated;

    @ColumnInfo(name = "upload_status")
    private int uploadStatus; // TODO: change to enum using TypeConverter

    // Constructor
    public Club(long id, @NonNull String name, String details, String imageUri,
                ZonedDateTime lastUpdated, Map<String, String> properties) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.imageUri = imageUri;
        this.lastUpdated = (lastUpdated == null ? ZonedDateTime.now(AppConfig.TIMEZONE) : lastUpdated);
        this.properties = (properties == null ? new HashMap<String, String>() : properties);
        this.uploadStatus = 0;
    }

    // Convenience constructor which takes a JSON string for properties
    @Ignore
    public Club(long id, @NonNull String name, String details, String imageUri,
                ZonedDateTime lastUpdated, String jsonProperties) {
        this(id, name, details, imageUri, lastUpdated, PropertyListTypeConverter.toMap(jsonProperties));
    }


    // getter and setter methods.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String description) {
        this.details = description;
    }

    public String getImageUri() {
        return this.imageUri;
    }

    @Override
    public Map<String, String> getProperties() { return this.properties; }

    public void setProperties(Map<String, String> properties) { this.properties = properties; }

    public void setImageUri(String uri) {
        this.imageUri = uri;
    }

    public ZonedDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    @Override
    public int getUploadStatus() {
        return 0;
    }

    public void setLastUpdated(ZonedDateTime updatedOn) {
        this.lastUpdated = updatedOn;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }
}
