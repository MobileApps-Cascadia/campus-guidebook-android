package edu.cascadia.mobas.campusguidebook.ui.clubs;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.Club;
import edu.cascadia.mobas.campusguidebook.viewmodel.MainActivityViewModel;

public class DetailsFragment extends Fragment {

    private Club club;
    private MainActivityViewModel viewModel;

    ImageView image;
    Button viewDateTime;
    Button viewLocation;
    TextView viewHeading;
    TextView viewDescription;
    LinearLayout propertyContainer1;
    LinearLayout propertyContainer2;
    LinearLayout propertyContainer3;
    TextView propertyLabel1;
    TextView propertyLabel2;
    TextView propertyLabel3;
    TextView propertyValue1;
    TextView propertyValue2;
    TextView propertyValue3;

    public DetailsFragment() {
        viewModel = new ViewModelProvider(this.getActivity()).get(MainActivityViewModel.class);
        club = (Club) viewModel.getDetailsItem();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_view_details, container, false);

        image = view.findViewById(R.id.imageview_details);
        viewDateTime = view.findViewById(R.id.button_datetime);
        viewLocation = view.findViewById(R.id.button_location);
        viewHeading = view.findViewById(R.id.textview_heading);
        viewDescription = view.findViewById(R.id.textview_description);
        propertyContainer1 = view.findViewById(R.id.layout_property_1);
        propertyContainer2 = view.findViewById(R.id.layout_property_2);
        propertyContainer3 = view.findViewById(R.id.layout_property_3);
        propertyLabel1 = view.findViewById(R.id.textview_property_1_label);
        propertyLabel2 = view.findViewById(R.id.textview_property_2_label);
        propertyLabel3 = view.findViewById(R.id.textview_property_3_label);
        propertyValue1 = view.findViewById(R.id.textview_property_1_value);
        propertyValue2 = view.findViewById(R.id.textview_property_2_value);
        propertyValue3 = view.findViewById(R.id.textview_property_3_value);

        // set the image
        LiveData<Drawable> drawableLiveData = viewModel.getImageFromUri(club.getImageUri());
        drawableLiveData.observe(this.getViewLifecycleOwner(), drawable -> {
            image.setImageDrawable(drawable);
        });

        // set the heading and description
        viewHeading.setText(club.getName());
        viewDescription.setText(club.getDetails());

        // set the properties
        int count = club.getProperties().size();
        Map<String,String> properties = new LinkedHashMap<>();

        String contact = club.getProperties().get("contact");
        if (contact != null) { properties.put("Contact", contact); }

        String meetings = club.getProperties().get("meetings");
        if (meetings != null) { properties.put("Meetings", meetings); }

        String location = club.getProperties().get("location");

        viewLocation.setText(location);
        viewDateTime.setText(meetings);

        propertyLabel1.setText("Contact");
        propertyValue1.setText(contact);
        propertyContainer1.setVisibility(View.VISIBLE);
        if (club.getProperties().containsKey("advisor")) {
            propertyLabel2.setText("Advisor");
            propertyValue2.setText(club.getProperties().get("advisor"));
            propertyContainer2.setVisibility(View.VISIBLE);
        } else {
            propertyContainer2.setVisibility(View.GONE);
        }
        propertyContainer3.setVisibility(View.GONE);

        return view;
    }
}
