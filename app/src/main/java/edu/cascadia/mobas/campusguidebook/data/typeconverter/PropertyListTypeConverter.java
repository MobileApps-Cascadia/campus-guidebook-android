package edu.cascadia.mobas.campusguidebook.data.typeconverter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertyListTypeConverter {
    private static final Gson sGson = new Gson();
    private static final Type sType = new TypeToken<Map<String,String>>(){}.getType();

    @TypeConverter
    public static String toJson(@Nullable Map<String, String> properties) {
        return sGson.toJson(properties, LinkedHashMap.class);
    }

    @TypeConverter
    public static Map<String, String> toMap(@Nullable String json) {
        if (json == null || json == "") {
            return new LinkedHashMap<String, String>();
        } else {
            return jsonStrToMap(json);
        }
    }

    public static Map<String, String> jsonStrToMap(String jsonStr) {
        return sGson.fromJson(jsonStr, sType);
    }
}
