package edu.cascadia.mobas.campusguidebook.ui.events;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import edu.cascadia.mobas.campusguidebook.application.AppConfig;
import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.typeconverter.ZonedDateTimeConverter;
import edu.cascadia.mobas.campusguidebook.viewmodel.MainActivityViewModel;


public class AddEventFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    //initialize textviews and buttons
    private MainActivityViewModel mViewModel;
    private EditText mEditTextEventName;
    private EditText mEditTextEventDescription;
    private EditText mEditTextEventLocation;
    private Button mBtnPickDateTime;
    private Button mAddNewEventBtn;
    private TextView mTextViewDateTime;
    private ZonedDateTime mZonedDateTime;

    public AddEventFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize viewmodel and zoneddatetime
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mZonedDateTime = ZonedDateTime.now(AppConfig.TIMEZONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.add_event, container, false);

        //initialize textviews and buttons
        mEditTextEventName = root.findViewById(R.id.editTextEventName);
        mEditTextEventDescription = root.findViewById(R.id.editTextEventDescription);
        mEditTextEventLocation = root.findViewById(R.id.editTextEventLocation);
        mBtnPickDateTime = root.findViewById(R.id.btnPickDateTime);
        mAddNewEventBtn = root.findViewById(R.id.addNewEventBtn);
        mTextViewDateTime = root.findViewById(R.id.textViewDateTime);

        // call listener on buttons
        mBtnPickDateTime.setOnClickListener(this);
        mAddNewEventBtn.setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()){
            case R.id.btnPickDateTime: {

                // creating a bundle and passing datetime
                Bundle bundle = new Bundle();
                bundle.putString("datetime", ZonedDateTimeConverter.fromZonedDateTime(mZonedDateTime));

                //create the dialog for the date
                DialogFragment dialogFragment = new DatePickerFragment(bundle);
                dialogFragment.setTargetFragment(AddEventFragment.this, 0);
                dialogFragment.show(getFragmentManager(), "date picker");
                break;
            }
            case R.id.addNewEventBtn: {
                if (AddEvent()){
                    //show a succesful message to the user when adding an event and navigate to event page
                    Toast.makeText(this.getContext(), R.string.successfully_added_event, Toast.LENGTH_LONG).show();
                    Navigation.findNavController(this.getView()).navigate(R.id.nav_events);
                }
                else
                    //show error message to an event not being able to be added
                    Toast.makeText(this.getContext(), R.string.error_adding_event, Toast.LENGTH_LONG).show();
                break;
            }
        }

    }

    public boolean AddEvent() {
        //adds event through ViewModel in MainActivityViewModel
        //passes the information
        return mViewModel.addNewEvent(mEditTextEventName.getText().toString(),
                mEditTextEventDescription.getText().toString(),
                mEditTextEventLocation.getText().toString(), mZonedDateTime);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        //sets the date and gets hour and minute
        mZonedDateTime = ZonedDateTime.of(year, month, dayOfMonth, mZonedDateTime.getHour(), mZonedDateTime.getMinute(), 0, 0, AppConfig.TIMEZONE);

        // creating a bundle and passing datetime
        Bundle bundle = new Bundle();
        bundle.putString("datetime", ZonedDateTimeConverter.fromZonedDateTime(mZonedDateTime));

        //create the dialog for the time and shows the time dialog
        DialogFragment dialogTimeFragment = new TimePickerFragment(bundle);
        dialogTimeFragment.setTargetFragment(AddEventFragment.this, 0);
        dialogTimeFragment.show(getFragmentManager(), "time picker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //gets date that was chosen and sets the time
        mZonedDateTime = ZonedDateTime.of(mZonedDateTime.getYear(), mZonedDateTime.getMonthValue(), mZonedDateTime.getDayOfMonth(), hourOfDay, minute, 0, 0, AppConfig.TIMEZONE);

        //formats the date and time to a nicer look
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MM/dd/yyyy h:mm a");
        String dateTime = mZonedDateTime.format(formatter);

        //sets the text of date and time in the view
        mTextViewDateTime.setText(dateTime);
    }



}