package com.robosoft.archanakumari.sqliteapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archanakumari on 28/12/15.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.rowlayout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.textuserid = (TextView) row.findViewById(R.id.userid);
            layoutHandler.textusername = (TextView) row.findViewById(R.id.name);
            layoutHandler.textuserpassword = (TextView) row.findViewById(R.id.password);
            row.setTag(layoutHandler);

        }
        else
        {layoutHandler = (LayoutHandler) row.getTag();}
        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.textuserid.setText(""+dataProvider.getId());
        layoutHandler.textusername.setText(dataProvider.getName());
        layoutHandler.textuserpassword.setText(dataProvider.getPassword());
        return row;
    }
    static class LayoutHandler
    {
        TextView textuserid;
        TextView textusername;
        TextView textuserpassword;

    }
}
