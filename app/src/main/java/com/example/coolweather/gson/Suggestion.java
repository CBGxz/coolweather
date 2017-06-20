package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.Streams;

/**
 * Created by 13030 on 2017/6/15.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public  CarWash carWash;

    public Sport sport;
    public class Comfort{
        @SerializedName("txt")
        public  String info;
    }

    public  class CarWash{

        @SerializedName("txt")
        public  String info;
    }
    public  class Sport{
        @SerializedName("txt")
        public String info;
    }
}
