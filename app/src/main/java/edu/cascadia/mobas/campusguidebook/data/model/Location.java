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


@Entity(tableName = "Location_Table")
public class Location {

    // returns the name of the class to allow generic fragments to utilize navigation
    @Ignore
    public final String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id = 0;

    @NonNull
    @ColumnInfo(name = "name")
    private String name = "";

    @ColumnInfo(name = "North")
    private String North;
    @ColumnInfo(name = "NorthEast")
    private String NorthEast;
    @ColumnInfo(name = "East")
    private String East;
    @ColumnInfo(name = "SouthEast")
    private String SouthEast;
    @ColumnInfo(name = "South")
    private String South;
    @ColumnInfo(name = "SouthWest")
    private String SouthWest;
    @ColumnInfo(name = "West")
    private String West;
    @ColumnInfo(name = "NorthWest")
    private String NorthWest;
    @ColumnInfo(name = "FloorNo")
    private Integer FloorNo;
    @ColumnInfo(name = "Up")
    private String Up;
    @ColumnInfo(name = "Down")
    private String Down;



    // Constructor
    public Location(long id, @NonNull String name, String North, String NorthEast, String East, String SouthEast, String South, String SouthWest, String West, String NorthWest, Integer FloorNo, String Up, String Down) {
        this.id = id;
        this.name = name;
        this.North = North;
        this.NorthEast = NorthEast;
        this.East = East;
        this.SouthEast = SouthEast;
        this.South = South;
        this.SouthWest = SouthWest;
        this.West = West;
        this.NorthWest = NorthWest;
        this.FloorNo = FloorNo;
        this.Up = Up;
        this.Down = Down;

    }

    // Convenience constructor which takes a JSON string for properties
    //@Ignore
    //public Locations(long id, @NonNull String name, String North, String NorthEast, String SouthEast, String South, String SouthWest, String West, String NorthWest, Integer FloorNo, String Up, String Down) {
    //    this(long id, name, North, NorthEast, SouthEast, South, SouthWest, West, NorthWest, FloorNo, Up, Down, PropertyListTypeConverter.toMap(jsonProperties));
    //}

    // getter and setter methods.
//Getters:
    public long getId() { return id; }
    @NonNull public String getName() { return name; }
    public String getNorth() { return this.North; }
    public String getNorthEast() { return this.NorthEast; }
    public String getEast() { return this.East; }
    public String getSouthEast() { return this.SouthEast; }
    public String getSouth() { return this.South; }
    public String getSouthWest() { return this.SouthWest; }
    public String getWest() { return this.West; }
    public String getNorthWest() { return this.NorthWest; }
    public Integer getFloorNo() { return this.FloorNo; }
    public String getUp() { return this.Up; }
    public String getDown() { return this.Down; }


    //Setters:
    public void setNorth(String North) { this.North = North; }
    public void setNorthEast(String NorthEast) { this.NorthEast = NorthEast; }
    public void setEast(String East) { this.East = East; }
    public void setSouthEast(String SouthEast) { this.SouthEast = SouthEast; }
    public void setSouth(String South) { this.South = South; }
    public void setSouthWest(String SouthWest) { this.SouthWest = SouthWest; }
    public void setWest(String West) { this.West = West; }
    public void setNorthWest(String NorthWest) { this.NorthWest = NorthWest; }
    public void setFloorNo(Integer FloorNo) { this.FloorNo = FloorNo; }
    public void setUp(String Up) { this.Up = Up; }
    public void setDown(String Down) { this.Down = Down; }

    public void insert(Location location) {
    }
}
