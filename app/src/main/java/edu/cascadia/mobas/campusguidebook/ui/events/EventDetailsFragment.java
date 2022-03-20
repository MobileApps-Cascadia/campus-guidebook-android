package edu.cascadia.mobas.campusguidebook.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.LinkedHashMap;

import edu.cascadia.mobas.campusguidebook.data.model.Event;
import edu.cascadia.mobas.campusguidebook.ui.BaseDetailsFragment;

public class EventDetailsFragment extends BaseDetailsFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        LinkedHashMap<String, String> properties = new LinkedHashMap<>();
        Event event = (Event) getItem();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("eeee, MMMM d h:mm a");
        ZonedDateTime dateTime = event.getDateTime();
        String dateTimeString = dateTime.format(dateTimeFormatter);
        properties.put("When", dateTimeString);
        properties.put("Where", event.getLocation());
        properties.putAll(event.getProperties());
        properties.forEach(this::addDetail);

        return root;
    }
}