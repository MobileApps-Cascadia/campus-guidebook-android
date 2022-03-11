package edu.cascadia.mobas.campusguidebook.ui.events;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.time.ZonedDateTime;
import java.util.Calendar;

import edu.cascadia.mobas.campusguidebook.data.typeconverter.ZonedDateTimeConverter;

public class DatePickerFragment  extends DialogFragment {

    private Bundle mBundle;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ZonedDateTime zonedDateTime = ZonedDateTimeConverter.toZonedDateTime(mBundle.getString("datetime"));

        //gets the date from bundle
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getTargetFragment(),
                zonedDateTime.getYear(), zonedDateTime.getMonthValue(), zonedDateTime.getDayOfMonth());
    }

    public DatePickerFragment(Bundle savedInstanceState) {
        super();
        //gets bundle from savedinstance
        mBundle = savedInstanceState;
    }
}
