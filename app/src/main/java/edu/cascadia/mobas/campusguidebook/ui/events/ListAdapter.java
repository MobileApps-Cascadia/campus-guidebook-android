package edu.cascadia.mobas.campusguidebook.ui.events;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.Event;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Event> mEventList;
    private Context mContext;

    // Viewholder provides access to the individual views in a row of the RecyclerView's row
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView EventCardView;
        private final ImageView EventImageView;
        private final TextView EventTextView;

        public ViewHolder(View parentView) {
            super(parentView);
            // TODO: Define click listener(s) for the ViewHolder's Views
            EventCardView = (CardView) parentView.findViewById(R.id.cardView_event_list);
            EventImageView = (ImageView) parentView.findViewById(R.id.imageView_event_list);
            EventTextView = (TextView) parentView.findViewById(R.id.textView_event_title);
        }

        // These methods return references to the views on the current row
        public TextView getTextView() {
            return EventTextView;
        }

        public ImageView getImageView() {
            return EventImageView;
        }

        public CardView getCardView() {
            return EventCardView;
        }

    }

    // Updates the list of Events used
    public void setData(List<Event> newEventList) {
        this.mEventList = newEventList;
        notifyDataSetChanged();
    }

    // Initialize the datasource used by the Adapter.
    public ListAdapter(List<Event> EventList) {
        mEventList = EventList;
    }

    // Used by the layout manager to return a ViewHolder for a row containing Event info
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // Used by the layout manager to change the displayed values in a bound ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get the data for a particular row within the list; return if null
        Event EventItem = mEventList.get(position);
        if (EventItem == null) {
            return;
        }

        String EventName = EventItem.getEventName();

        // display the data from EventItem in the Views provided by the ViewHolder
        TextView tv = viewHolder.getTextView();
        if (tv == null) {
            return;
        }
        tv.setText(EventName);
    }

    // Used by the layout manager to determine the number of Events in the list
    @Override
    public int getItemCount() {
        if (mEventList == null) {
            return 0;
        } else {
            return mEventList.size();
        }
    }

}

