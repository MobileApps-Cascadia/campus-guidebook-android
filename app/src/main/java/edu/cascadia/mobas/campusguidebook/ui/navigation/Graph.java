package edu.cascadia.mobas.campusguidebook.ui.navigation;

public class Graph {
    String name = null;
    Graph north = null;
    Graph northeast = null;
    Graph east = null;
    Graph southeast = null;
    Graph south = null;
    Graph southwest = null;
    Graph west = null;
    Graph northwest = null;

    Graph up = null;
    Graph down = null;
    int level = -1;

    public void initGraph(String Name, Graph north, Graph northeast, Graph east, Graph southeast, Graph south, Graph southwest, Graph west, Graph northwest) {
        this.name = Name;
        this.north = north;
        this.northeast = northeast;
        this.east = east;
        this.southeast = southeast;
        this.south = south;
        this.southwest = southwest;
        this.west = west;
        this.northwest = northwest;
    }

    // Getter methods
    public String getName() {
        return name;
    }
    public Graph getNorth() {
        return north;
    }

    public Graph getNortheast() {
        return northeast;
    }

    public Graph getEast() {
        return east;
    }

    public Graph getSoutheast() {
        return southeast;
    }

    public Graph getSouth() {
        return south;
    }

    public Graph getSouthwest() {
        return southwest;
    }

    public Graph getWest() {
        return west;
    }

    public Graph getNorthwest() {
        return northwest;
    }
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    public void setNorth(Graph north) {
        this.north = north;
    }

    public void setNortheast(Graph northeast) {
        this.northeast = northeast;
    }

    public void setEast(Graph east) {
        this.east = east;
    }

    public void setSoutheast(Graph southeast) {
        this.southeast = southeast;
    }

    public void setSouth(Graph south) {
        this.south = south;
    }

    public void setSouthwest(Graph southwest) {
        this.southwest = southwest;
    }

    public void setWest(Graph west) {
        this.west = west;
    }

    public void setNorthwest(Graph northwest) {
        this.northwest = northwest;
    }

}
