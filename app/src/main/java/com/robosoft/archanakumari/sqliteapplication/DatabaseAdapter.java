package com.robosoft.archanakumari.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by archanakumari on 25/12/15.
 */
public class DatabaseAdapter {

     Helper helper;
    DatabaseAdapter(Context context){

        helper = new Helper(context);
    }
    public long  insertData(String name ,String password){
        SQLiteDatabase sqLiteDatabase  = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.NAME,name);
        contentValues.put(Helper.PASSWORD,password);
        long id = sqLiteDatabase.insert(Helper.TABLE_NAME,null,contentValues);
        return id;
    }
   public String  getAllData(){

         StringBuffer stringBuffer = new StringBuffer();
        //Select UID,NAME and PASSWORD
        String columns[] = {Helper.UID,Helper.NAME,Helper.PASSWORD};
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.query(Helper.TABLE_NAME, columns, null, null, null, null, null);
        while(cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String password = cursor.getString(2);
            stringBuffer.append(id+" "+name+" "+password+"\n");
        }
        return stringBuffer.toString();

    }
    //This getAllData is for displaying data as listView
  public Cursor getAllData(SQLiteDatabase sqLiteDatabase){

      StringBuffer stringBuffer = new StringBuffer();
      //Select UID,NAME and PASSWORD
      String columns[] = {Helper.UID,Helper.NAME,Helper.PASSWORD};
    //  SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
      Cursor cursor =  sqLiteDatabase.query(Helper.TABLE_NAME, columns, null, null, null, null, null);
     /* while(cursor.moveToNext()){

          int id = cursor.getInt(0);
          String name = cursor.getString(1);
          String password = cursor.getString(2);
          stringBuffer.append(id+" "+name+" "+password+"\n");
      }*/
      return cursor;

  }
    public String getData(String name,String password){

        //SELECT -id from TABLE_NAME WHERE name =? AND password=?;

        StringBuffer stringBuffer = new StringBuffer();

        String columns[] = {Helper.UID};
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        //Select UID,NAME and PASSWORD
      //  Cursor cursor =  sqLiteDatabase.query(Helper.TABLE_NAME,columns, Helper.NAME+" = '" + name +"' ",null, null, null, null);
        String selectionArgs[] = {name,password};
        Cursor cursor = sqLiteDatabase.query(Helper.TABLE_NAME, columns, Helper.NAME + " =? AND " + Helper.PASSWORD + " =?", selectionArgs, null, null, null, null);
        while(cursor.moveToNext()){

             int indexofId = cursor.getColumnIndex(Helper.UID);
             int personId = cursor.getInt(indexofId);

            //String personName = cursor.getString(index2);
          //  String personPassword = cursor.getString(index3);
            stringBuffer.append(personId+"\n");


        }
        return stringBuffer.toString();
    }

    public int updateName(String oldName,String newName){

        //Update TABLE_NAME set NAME = 'SINGH' where NAME = 'ARCHANA'
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.NAME,newName);
        String whereArgs[] = {oldName};
        int noOfRows = sqLiteDatabase.update(Helper.TABLE_NAME,contentValues,Helper.NAME+" =? ",whereArgs);
        return noOfRows;
    }
    public int deleteRow(){

        //Delete * from TABLE_NAME where NAME = "archana"
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String whereArgs[] = {"archana"};
        int noOfRows = sqLiteDatabase.delete(Helper.TABLE_NAME,Helper.NAME+ "=?",whereArgs);
        return noOfRows;


    }
   static public class Helper  extends SQLiteOpenHelper{

       private static final String DATABASE_NAME = "databasename";
        private static final String TABLE_NAME = "Employee";
        private static final int DATABASE_VERSION = 11;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " VARCHAR(255)," + PASSWORD + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        Context context;


        public Helper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Message.message(context, "Constructor is called");

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            Message.message(context, "onCreate is called");
            try {
                db.execSQL(CREATE_TABLE);
            } catch (SQLException exception) {
                Message.message(context, "" + exception);
                exception.printStackTrace();
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Message.message(context, "onUpgrade is called");
            try {
               db.execSQL(DROP_TABLE);
               onCreate(db);
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }

    }
}
