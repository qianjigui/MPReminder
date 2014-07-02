package com.example.app.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.model.RemindItem;

/**
 * Created by wpc on 5/28/14.
 */
public class ReminderItemDetailDialogFragment extends DialogFragment {

    public static final String TAG_NAME_FOR_FRAGMENT = "ReminderItemDetailDialogFragment";

    private RemindItem item;
    private RItemAdapter adapter;

    public ReminderItemDetailDialogFragment() {
    }

    public static final String CLICK_ITEM_POSITION_INDEX = "CLICK_ITEM_POSITION_INDEX";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        RItemListFragment fr = (RItemListFragment) getFragmentManager().findFragmentById(R.id.container);
        adapter = (RItemAdapter) fr.getListAdapter();
        item = (RemindItem) adapter.getClickedItem();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle(item.getTitle());
        View view = inflater.inflate(R.layout.fragment_detail_dialog, null);
        TextView tv = (TextView) view.findViewById(R.id.item_note);
        tv.setText(item.getNote());
        builder.setView(view)
                .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.clearClick();
                    }
                });
        return builder.create();
    }

}
