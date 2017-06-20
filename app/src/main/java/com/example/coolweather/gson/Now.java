package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 13030 on 2017/6/15.
 */

public class Now {
    @SerializedName("tmp")
    public  String temperature;

    @SerializedName("cond")
    public More more;

    public Wind wind;

    public  class More{
        @SerializedName("text")
        public String info;

        public String code;
    }

    private  class Wind{
        @SerializedName("dir")
        public  String direction;

        @SerializedName("sc")
        public String scale;
    }
}
