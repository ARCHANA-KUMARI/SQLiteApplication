package com.robosoft.archanakumari.sqliteapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseAdapter databaseAdapter;
    EditText username, password, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.usernameedit);
        password = (EditText) findViewById(R.id.passedit);
        name = (EditText) findViewById(R.id.edit);
        databaseAdapter = new DatabaseAdapter(this);


    }

    public void addUser(View view) {

        String user = username.getText().toString();
        String pass = password.getText().toString();
        long id = databaseAdapter.insertData(user, pass);
        if (id < 0) {
            Message.message(this, "Unsuccessfully");
        } else {
            Message.message(this, "Data is inserted successfully");
        }


        //ContentValues contentValues = new ContentValues();
    }

    public void viewAllDetails(View view) {

        String data = databaseAdapter.getAllData();
        Message.message(this, data);
        //Display list into another activity class
       /*  Intent intent = new Intent(this,DataListActivity.class);
        startActivity(intent);*/
    }

    public void getDetails(View view) {

        String s1 = String.valueOf(name.getText());
        //Archana Kumari
        String substring1 = s1.substring(0, s1.indexOf(" "));
        String substring2 = s1.substring(s1.indexOf(" ") + 1);
        String s3 = databaseAdapter.getData(substring1, substring2);
        Message.message(this, s3);
    }
    public void toUpdate(View view){

        databaseAdapter.updateName("astha", "Aradhana");
    }

    public void toDelete(View view){

        int noOfRows = databaseAdapter.deleteRow();
        Message.message(this," "+noOfRows);
    }
    //just for check
    public void getMessag(){}
    //Line two added in Main Activity

}
