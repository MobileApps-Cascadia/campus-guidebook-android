package edu.cascadia.mobas.campusguidebook.data.typeconverter;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeConverter {

    @TypeConverter
    public static ZonedDateTime toZonedDateTime(@Nullable String dateTimeString) {

        // null input returns null output
        if (dateTimeString == null) return null;

        // Convert a valid ISO8601 String to an ZonedDateTime
        try {
            return ZonedDateTime.parse(dateTimeString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        } catch (Exception e){
            Log.e("ZonedDateTimeConverter", "Invalid ZonedDateTime format: " + dateTimeString);
            return null;
        }
    }

    @TypeConverter
    public static String fromZonedDateTime(@Nullable ZonedDateTime zonedDateTime) {

        // null input returns null output
        if (zonedDateTime == null) {
            return null;
        } else {
            return zonedDateTime.toString();
        }
    }
}
