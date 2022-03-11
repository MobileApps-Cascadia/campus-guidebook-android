package edu.cascadia.mobas.campusguidebook.data.model;


import androidx.room.Ignore;

import java.time.ZonedDateTime;
import java.util.Map;

public interface IEntity {
    public long getId();
    public String getName();
    public String getDetails();
    public String getImageUri();
    public Map<String, String> getProperties();
    public ZonedDateTime getLastUpdated();
    public int getUploadStatus();
    @Ignore public abstract String getEntityName();
}
