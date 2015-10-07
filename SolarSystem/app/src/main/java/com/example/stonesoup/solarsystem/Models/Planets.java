package com.example.stonesoup.solarsystem.Models;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import java.io.Serializable;

/**
 * this class describes the Plantes and inherit Planet
 */
public class Planets extends Planet implements Parcelable {

    private long DistanceFromSun;
    private String LengthOfDate;
    private String typeOfPlanet;
    private String ImageParce;
    private byte[] data;

    public Planets(String Name, String Description, String Mass,double Gravity, double radious, long DistanceFromSun
        , String LengthOfDate, String typeOfPlanet, String Age, ParseFile ImagePic){
        this.Name = Name;
        this.Description = Description;
        this.Mass = Mass;
        this.Gravity = Gravity;
        this.radius = radious;
        this.DistanceFromSun = DistanceFromSun;
        this.LengthOfDate = LengthOfDate;
        this.typeOfPlanet = typeOfPlanet;
        this.Age = Age;
        this.imagePic = ImagePic;

        imagePic.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                data = bytes;
            }
        });
    }

    public static final Parcelable.Creator<Planets> CREATOR = new Parcelable.Creator<Planets>() {

        public Planets createFromParcel(Parcel in) {
            return new Planets(in);

        }

        public Planets[] newArray(int size){
            return new Planets[size];
        }
    };

    private Planets(Parcel in){
        Name = in.readString();
        Description = in.readString();
        Mass = in.readString();
        Gravity = in.readDouble();
        radius = in.readDouble();
        DistanceFromSun = in.readLong();
        LengthOfDate = in.readString();
        typeOfPlanet = in.readString();
        Age = in.readString();
        in.readByteArray(data);
    }



        @Override
    public int describeContents() {
        return 0;
    }

         @Override
    public void writeToParcel(Parcel dest, int flags) {
             dest.writeString(Name);
        dest.writeString(Description);
        dest.writeString(Mass);
        dest.writeDouble(Gravity);
        dest.writeDouble(radius);
        dest.writeLong(DistanceFromSun);
        dest.writeString(LengthOfDate);
        dest.writeString(typeOfPlanet);
        dest.writeString(Age);
        dest.writeByteArray(data);
    }

    public String getLengthOfDate() {
        return LengthOfDate;
    }

    public String getTypeOfPlanet() {
        return typeOfPlanet;
    }

    public byte[] getData() {
        return data;
    }

    public long getDistanceFromSun() {

        return DistanceFromSun;
    }

    public String getName(){
        return this.Name;
    }

    public Double getRadius(){
        return this.radius;
    }

    public String getAge(){
        return this.Age;
    }
    public double getGravity(){
        return this.Gravity;
    }

    public String getMass(){
        return this.Mass;
    }

    public String getDescription(){
        return this.Description;
    }
}
