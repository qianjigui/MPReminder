package com.example.app.widget;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.model.RemindItem;

import java.util.List;

/**
 * Created by wpc on 5/26/14.
 */
public class RItemAdapter extends BaseAdapter{
    private Context context;
    private List<RemindItem> items;
    private LayoutInflater inflater;
    public RItemAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(vi==null){
            vi = inflater.inflate(R.layout.ritem_row, null);
        }
        RemindItem item = (RemindItem) getItem(position);
        TextView tvTitleTV = (TextView) vi.findViewById(R.id.riTitleTV);
        TextView tvNoteTV = (TextView) vi.findViewById(R.id.riNoteTV);

        tvTitleTV.setText(item.getTitle());
        tvNoteTV.setText(item.getNote());

        if(item.isFinish()){
            tvTitleTV.setPaintFlags(tvTitleTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvNoteTV.setPaintFlags(tvNoteTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        return vi;
    }

    @Override
    public void notifyDataSetChanged() {
        this.items = RemindItem.all();
        super.notifyDataSetChanged();
    }
}
