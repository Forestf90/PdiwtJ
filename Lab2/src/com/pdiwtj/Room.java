package com.pdiwtj;

public class Room {

    private String name;
    private int x;
    private int y;
    private int z;

    private float floorCost;
    private float wallCost;

    public Room(String n,int xx, int yy, int zz, float floor, float wall){

        this.name=n;
        this.x = xx;
        this.y=yy;
        this.z =zz;
        this.floorCost=floor;
        this.wallCost =wall;

    }
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

    public void wallObj(){

        float xx = x*z*2;
        float yy = x*y*2;
        float xd = xx+yy;
        System.out.println("Powierzchnia scian: "+xd+" m2");
    }

    public void floorObj(){

        float xd = x*z;
        System.out.println("Powierzchnia podlogi: "+xd+" m2");
    }
    public void obj(){

        System.out.println("Objetosc pokoju :"+x*y*z+" m3");
    }

    public void wallPrice(){
        float xx = x*z*2;
        float yy = x*y*2;
        float xd = xx+yy;
        xd =xd *wallCost;
        System.out.println("Koszt pomalowania scian: "+String.format("%.2f",xd)+" zl");
    }

    public void floorPrice(){
        float xd = x*z*floorCost;
        System.out.println("Koszt wykonania podlogi: "+String.format("%.2f",xd)+" zl");
    }

    public void display(){
        System.out.println(name);
        obj();
        wallObj();
        floorObj();
        wallPrice();
        floorPrice();
    }
}
