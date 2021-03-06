package com.example.final_project;

public class ModelClass {
    public String getName() {
        return name;
    }

    public ModelClass(String name, String location, String divider, int imageView1) {
        this.name = name;
        this.location = location;
        this.divider = divider;
        this.imageView1 = imageView1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDivider() {
        return divider;
    }

    public void setDivider(String divider) {
        this.divider = divider;
    }

    public int getImageView1() {
        return imageView1;
    }

    public void setImageView1(int imageView1) {
        this.imageView1 = imageView1;
    }

    private String name;
    private String location;
    private String divider;
    private int imageView1;

}
