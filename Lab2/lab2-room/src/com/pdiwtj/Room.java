package com.pdiwtj;

public class Room {

    private String name;
    private int x;
    private int y;
    private int z;

    private float floorCost;
    private float wallCost;

    public Room(String n, int xx, int yy, int zz, float floor, float wall) {
        this.name = n;
        this.x = xx;
        this.y = yy;
        this.z = zz;
        this.floorCost = floor;
        this.wallCost = wall;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public float getFloorCost() {
        return floorCost;
    }

    public void setFloorCost(float floorCost) {
        this.floorCost = floorCost;
    }

    public float getWallCost() {
        return wallCost;
    }

    public void setWallCost(float wallCost) {
        this.wallCost = wallCost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public float wallObj() {
        return (x * z * 2) + (z * y * 2);
    }

    public float floorObj() {
        return x * y;
    }

    public int obj() {
        return x * y * z;
    }

    public float wallPrice() {
        return ((x * z * 2) + (z * y * 2)) * wallCost;
    }

    public float floorPrice() {
        return x * z * floorCost;
    }

    public void display() {
        System.out.println(name);
        System.out.println("Objetosc pokoju :" + obj() + " m3");
        System.out.println("Powierzchnia scian: " + wallObj() + " m2");
        System.out.println("Powierzchnia podlogi: " + floorObj() + " m2");
        System.out.println("Koszt pomalowania scian: " + String.format("%.2f", wallPrice()) + " zl");
        System.out.println("Koszt wykonania podlogi: " + String.format("%.2f", floorPrice()) + " zl");
    }
}
