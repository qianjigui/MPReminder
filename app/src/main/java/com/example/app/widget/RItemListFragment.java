package com.example.app.widget;

import android.app.DialogFragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.app.R;
import com.example.app.model.RemindItem;

/**
 * Created by wpc on 5/29/14.
 */
public class RItemListFragment extends ListFragment {

    public static final String TAG_NAME_FOR_FRAGMENT = "RItemListFragment";

    private static final int NO_CLICK_INDEX = -100;
    private static final String CLICK_INDEX_STATE_NAME = "CLICK_INDEX_STATE_NAME";

    private int clickIndex;

    public RItemListFragment() {
        clickIndex = NO_CLICK_INDEX;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RItemAdapter adapter = new RItemAdapter(getActivity().getApplicationContext());
        adapter.notifyDataSetChanged();
        setListAdapter(adapter);
        if (savedInstanceState != null) {
            int i = savedInstanceState.getInt(CLICK_INDEX_STATE_NAME);
            if (i != NO_CLICK_INDEX) {
                clickIndex = i;
                adapter.toClickIndex(i);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ritem_list, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        if(true){
            clickIndex = position;
            RItemAdapter adapter = (RItemAdapter)getListAdapter();
            adapter.toClickIndex(clickIndex);
            DialogFragment dialogFragment = new ReminderItemDetailDialogFragment();
            dialogFragment.show(getFragmentManager(), ReminderItemDetailDialogFragment.TAG_NAME_FOR_FRAGMENT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CLICK_INDEX_STATE_NAME, clickIndex);
    }
}
