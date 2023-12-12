package edu.cascadia.mobas.campusguidebook.ui.navigation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;




import java.util.ArrayList;
import java.util.List;

import edu.cascadia.mobas.campusguidebook.R;


// ListFragment
// Base class for displaying a recyclerview for the provided type
public class NavigationFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.navigation, container, false);
        Bundle args = getArguments();
        List<String> path = new ArrayList<>(args.getStringArrayList("NavResults"));

        TextView text = viewRoot.findViewById(R.id.textView3_nav);
        text.setText(String.join("\n", path));

        return viewRoot;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }


}
