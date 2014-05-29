package com.example.app.widget;

import android.app.DialogFragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
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

    public static final String TAG_NAME_FOR_FRAGMENT="RItemListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new RItemAdapter(getActivity().getApplicationContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ritem_list, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        view.animate().scaleX(1.25f).scaleY(1.25f);
        RemindItem item = (RemindItem) getListAdapter().getItem(position);
        DialogFragment dialogFragment = new ReminderItemDetailDialogFragment(item, view);
        dialogFragment.show(getFragmentManager(), ReminderItemDetailDialogFragment.TAG_NAME_FOR_FRAGMENT);
    }
}
