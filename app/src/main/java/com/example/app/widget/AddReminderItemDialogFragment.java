package com.example.app.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.model.RemindItem;

/**
 * Created by wpc on 5/26/14.
 */
public class AddReminderItemDialogFragment extends DialogFragment {

    public static final String TAG_NAME_FOR_FRAGMENT = "AddReminderItemDialogFragment";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle(R.string.add_item_dialog_title);
        builder.setView(inflater.inflate(R.layout.fragment_add_dialog, null))
                .setPositiveButton(R.string.save_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText title = (EditText)((AlertDialog)dialog).findViewById(R.id.title_editText);
                        EditText note = (EditText)((AlertDialog)dialog).findViewById(R.id.note_editText);

                        RemindItem item = new RemindItem(title.getText().toString(),note.getText().toString());
                        item.save();

                        MainActivity.PlaceholderFragment fragment = (MainActivity.PlaceholderFragment)getFragmentManager().findFragmentById(R.id.container);
                        fragment.getAdapter().notifyDataSetChanged();
                    }
                })
                .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddReminderItemDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

}
