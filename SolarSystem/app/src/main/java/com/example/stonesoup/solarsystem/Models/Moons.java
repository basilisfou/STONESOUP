package com.example.stonesoup.solarsystem.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;

/**
 * this class describes the moons of the solar system and inherit Planet
 */
public class Moons extends Planet implements Parcelable {

    private long distanceFromParent;
    private double mass;
    private String motherPlanet;
    private byte[] data;

    public Moons(String Name, String Description, long distanceFromParent, double radious, double gravity,  double mass, String motherPlanet
                 ,ParseFile imagePic ){
        this.Name = Name;
        this.Description = Description;
        this.distanceFromParent = distanceFromParent;
        this.radius = radious;
        this.Gravity = gravity;
        this.mass = mass;
        this.motherPlanet = motherPlanet;
        this.imagePic = imagePic;
        this.imagePic.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                data = bytes;
            }
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Description);
        dest.writeLong(distanceFromParent);
        dest.writeDouble(radius);
        dest.writeDouble(Gravity);
        dest.writeDouble(mass);
        dest.writeString(motherPlanet);
        dest.writeByteArray(data);
    }

    public static final Parcelable.Creator<Moons> CREATOR = new Parcelable.Creator<Moons>() {

        public Moons createFromParcel(Parcel in) {
            return new Moons(in);

        }

        public Moons[] newArray(int size){
            return new Moons[size];
        }
    };

    private Moons(Parcel in){
        Name = in.readString();
        Description = in.readString();
        distanceFromParent = in.readLong();
        radius = in.readDouble();
        Gravity = in.readDouble();
        mass = in.readDouble();
        Gravity = in.readDouble();
        motherPlanet = in.readString();
        in.readByteArray(data);
    }

    public long getDistanceFromParent() {
        return distanceFromParent;
    }

    public double getMass() {
        return mass;
    }

    public String getMotherPlanet() {
        return motherPlanet;
    }

    public byte[] getData() {
        return data;
    }

    public String getName(){
        return Name;
    }

    public double getRadious(){
        return radius;
    }

    public double getGravity(){
        return Gravity;
    }

    public String getDescription(){return Description;}
}
