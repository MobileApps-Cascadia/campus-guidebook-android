package edu.cascadia.mobas.campusguidebook.ui.Sustainability;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.IEntity;
import edu.cascadia.mobas.campusguidebook.ui.Sustainability.SustsListFragment;

public class SustsListAdapter<T extends IEntity> extends RecyclerView.Adapter<SustsListAdapter.ViewHolder> {

    private List<T> mList;
    private LifecycleOwner mLifecycleOwner;
    private final SustsListFragment<T> mSustsListFragment;

    // ClubListAdapter constructor and methods
    // Initialize this adapter with a reference to the datasource to be used.
    public SustsListAdapter(List<T> list, SustsListFragment<T> fragment) {
        mList = list;
        this.mSustsListFragment = fragment;
    }

    // TODO: Investigate using SwitchMap to modify instead of completely replacing the list
    // Updates the list used by the RecyclerView
    public void setList(List<T> newList) {
        if (newList == null) {
            this.mList = new ArrayList<>();
        } else {
            this.mList = newList;
        }
        notifyDataSetChanged();
    }

    // Used by the layout manager to return the ViewHolder for a row containing club info
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {

        // get the parent LifecycleOwner if this is the first time
        if (mLifecycleOwner == null) {
            mLifecycleOwner = (LifecycleOwner) parentViewGroup.getContext();
        }

        // Create a new view containing all the views for the UI of the list item
        View view = LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.list_view_item_sust, parentViewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(v -> {
            T item = mList.get(holder.getAdapterPosition());
            mSustsListFragment.itemClicked(item);

        });
        return holder;
    }

    // Used by the layout manager to change the displayed values in a bound ViewHolder
    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        // Get the data for a particular row within the list; return if null
        T item = mList.get(position);
        viewHolder.textView.setText(item.getName());

        Map<String, String> Properties = item.getProperties();

        // get the drawable image as livedata and add an observer
        String imageUri = item.getImageUri();
        LiveData<Drawable> drawableLiveData = mSustsListFragment.getImage(imageUri);
        drawableLiveData.observe(mLifecycleOwner, viewHolder.imageView::setImageDrawable);
    }

    // Used by the layout manager to determine the number of clubs in the list
    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        } else {
            return mList.size();
        }
    }

    // ViewHolder provides access to the individual views in a row of the RecyclerView's row
    // This internal static class is supplied in the diamond brackets in the class declaration
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Provide variables to hold references to each View in the list item fragment
        public final CardView cardView;
        public final ImageView imageView;
        public final TextView textView;

        // The constructor takes a parent view (a layout defined in xml) on the list item fragment
        // and allows us to get references to the views it contains
        public ViewHolder(View parentView) {
            super(parentView);
            // look up and assign the views in the club list item fragment to our variables
            cardView = parentView.findViewById(R.id.cardview_sust_list_item);
            imageView = parentView.findViewById(R.id.imageview_sust_list_item_image);
            textView = parentView.findViewById(R.id.textView_sust_list_item_text);
        }
    }  // End of static ViewHolder class

}
