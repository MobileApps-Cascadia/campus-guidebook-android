package edu.cascadia.mobas.campusguidebook.ui.Sustainability;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.cascadia.mobas.campusguidebook.R;
import edu.cascadia.mobas.campusguidebook.data.model.IEntity;
import edu.cascadia.mobas.campusguidebook.ui.Susts.SustsListAdapter;
import edu.cascadia.mobas.campusguidebook.viewmodel.MainActivityViewModel;

// ListFragment
// Base class for displaying a recyclerview for the provided type
public abstract class SustsListFragment<T extends IEntity> extends Fragment {

    protected String TAG = "ListFragment";
    private MainActivityViewModel mViewModel;
    private LiveData<List<T>> mList = new MutableLiveData<>();
    private FloatingActionButton mFloatingActionButton;
    private View mRoot;

    protected abstract LiveData<List<T>> getList();

    public abstract void itemClicked(T item);

    protected void onClickFloatingActionButton() {

    }

    protected MainActivityViewModel getViewModel() {
        return mViewModel;
    }

    public LiveData<Drawable> getImage(String uri) {
        return getViewModel().getImageFromUri(uri);
    }

    protected View getRootView() {
        return mRoot;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this.requireActivity()).get(MainActivityViewModel.class);
        mList = getList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.list_view_sust, container, false);
        viewRoot.setTag(TAG);

        // RecyclerView setup
        RecyclerView recyclerView = viewRoot.findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));
        SustsListAdapter<T> adapter = new SustsListAdapter<T>(mList.getValue(), this);
        recyclerView.setAdapter(adapter);

        // get floatingActionButton
        mFloatingActionButton = viewRoot.findViewById(R.id.floatingActionButton);
        //mFloatingActionButton.setOnClickListener(v -> this.onClickFloatingActionButton());
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFloatingActionButton();
            }
        });

        // respond to changes in livedata
        mList.observe(this.getViewLifecycleOwner(), adapter::setList);

        //exposing the root view for the fragment
        mRoot = viewRoot;
        return viewRoot;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRoot = null;
        mFloatingActionButton = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mList = null;
        mViewModel = null;
    }

    protected FloatingActionButton getFloatingActionButton() {
        return mFloatingActionButton;
    }

    protected void setTag(String tag) {
        this.TAG = tag;
    }

}
