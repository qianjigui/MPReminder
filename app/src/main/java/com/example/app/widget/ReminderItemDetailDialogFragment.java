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
    private int viewIndex;

    public ReminderItemDetailDialogFragment(RemindItem item,int viewIndex) {
        this.item = item;
        this.viewIndex = viewIndex;
    }

    public ReminderItemDetailDialogFragment(){
    }

    public View getRItemListItemViewByIndex(int index){
        RItemListFragment fr = (RItemListFragment)getFragmentManager().findFragmentById(R.id.container);
        View v = null;
        if(fr!=null){
            ListView lv = fr.getListView();
            v = lv.getChildAt(index-lv.getFirstVisiblePosition());
        }
        return v;
    }

    public static final String CLICK_ITEM_POSITION_INDEX  = "CLICK_ITEM_POSITION_INDEX";

    public int getItemViewIndex(){
        return viewIndex;
    }

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            int i = savedInstanceState.getInt(CLICK_ITEM_POSITION_INDEX);
            if(item==null){
                viewIndex = i;
                RItemListFragment fr = (RItemListFragment)getFragmentManager().findFragmentById(R.id.container);
                item = (RemindItem)fr.getListAdapter().getItem(i);
            }
        }
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
                        View v = getRItemListItemViewByIndex(viewIndex);
                        if(v!=null){
                            v.animate().scaleX(1f).scaleY(1f);
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CLICK_ITEM_POSITION_INDEX, viewIndex);
    }
}
