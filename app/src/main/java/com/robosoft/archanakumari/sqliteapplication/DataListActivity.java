package com.robosoft.archanakumari.sqliteapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class DataListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DatabaseAdapter databaseAdapter;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        listView = (ListView) findViewById(R.id.listview);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.rowlayout);
        listView.setAdapter(listDataAdapter);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        sqLiteDatabase = databaseAdapter.helper.getReadableDatabase();
        cursor = databaseAdapter.getAllData(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String password = cursor.getString(2);
                DataProvider dataProvider = new DataProvider(id,name,password);
                listDataAdapter.add(dataProvider);

            }while(cursor.moveToNext());
        }

    }

}
