package com.packet.utils;

import org.json.JSONException;
import org.json.JSONObject;
import java.lang.String;

import java.util.HashMap;


/**
 * Created by mihai on 11/23/2014.
 *
 * This call helps parsing all JSON data
 */
public class JSONWeatherParser {
    public   HashMap<String,Object> getWeather(String data) throws JSONException
    {
        System.out.println(data);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        JSONObject jObj = new JSONObject(data);

        JSONObject mainObj = getObject("main",jObj);
        hashMap.put("temperature",getString("temp",mainObj,273.15));

        JSONObject wObj = getObject("wind",jObj);
        hashMap.put("windSpeed",getString("speed",wObj,null));
        return hashMap;

    }
    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException
    {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }
    private static String getString(String tagName, JSONObject jObj, Double ref) throws JSONException
    {
       if(ref == null)
           return String.format("%.2f", (jObj.getDouble(tagName)));
        else
           return String.format("%.2f", (jObj.getDouble(tagName)-ref));
    }
}
