package edu.cascadia.mobas.campusguidebook.data.typeconverter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PropertyListTypeConverter {
    private static final Gson sGson = new Gson();
    private static final Type sType = new TypeToken<Map<String,String>>(){}.getType();

    @TypeConverter
    public static String toJson(@Nullable Map<String, String> properties) {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        return sGson.toJson(properties);
    }

    @TypeConverter
    public static Map<String, String> toMap(@Nullable String json) {
        if (json == null) {
            return new HashMap<String, String>();
        } else {
            return jsonStrToMap(json);
        }
    }

    public static Map<String, String> jsonStrToMap(String jsonStr) {
        return sGson.fromJson(jsonStr, sType);
    }
}