package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.io.StringWriter;

/**
 * Created by 13030 on 2017/6/15.
 */

public class Forecast {
    public String data;
    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature {

        public String max;

        public String min;


    }

    public class More {

        @SerializedName("txt_d")
        public String info;

    }
}
