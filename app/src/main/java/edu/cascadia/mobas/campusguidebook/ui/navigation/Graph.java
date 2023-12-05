package edu.cascadia.mobas.campusguidebook.ui.navigation;

public class Graph {
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

    public void initGraphs(Graph north, Graph northeast, Graph east, Graph southeast, Graph south, Graph southwest, Graph west, Graph northwest) {
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

}
