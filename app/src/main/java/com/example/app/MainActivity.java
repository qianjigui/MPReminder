package com.example.app;

import android.app.Activity;
import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.app.model.MPRemindDbHelper;
import com.example.app.model.RemindItem;
import com.example.app.widget.AddReminderItemDialogFragment;
import com.example.app.widget.RItemAdapter;
import com.example.app.widget.ReminderItemDetailDialogFragment;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MPRemindDbHelper.init(getApplicationContext());
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_item:
                DialogFragment dialog = new AddReminderItemDialogFragment();
                dialog.show(getFragmentManager(),AddReminderItemDialogFragment.TAG_NAME_FOR_FRAGMENT);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private BaseAdapter adapter;

        public BaseAdapter getAdapter() {
            return this.adapter;
        }

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            adapter = new RItemAdapter(getActivity()
                    .getApplicationContext());
            adapter.notifyDataSetChanged();

            ListView lv = (ListView) rootView.findViewById(R.id.reminderList);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    view.animate().scaleX(1.25f).scaleY(1.25f);
                    RemindItem item = (RemindItem) adapter.getItem(position);
                    DialogFragment dialogFragment = new ReminderItemDetailDialogFragment(item, view);
                    dialogFragment.show(getFragmentManager(), ReminderItemDetailDialogFragment.TAG_NAME_FOR_FRAGMENT);
                }
            });
            return rootView;
        }

    }

}
