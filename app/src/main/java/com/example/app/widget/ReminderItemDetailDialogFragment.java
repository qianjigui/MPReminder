package com.example.app.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
    private View view;

    public ReminderItemDetailDialogFragment(RemindItem item,View view) {
        this.item = item;
        this.view = view;
    }

    public View getItemView(){
        return view;
    }

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
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
                        ReminderItemDetailDialogFragment f =(ReminderItemDetailDialogFragment)getFragmentManager().findFragmentByTag(TAG_NAME_FOR_FRAGMENT);
                        f.getItemView().animate().scaleX(1f).scaleY(1f);
                    }
                });
        return builder.create();
    }
}
