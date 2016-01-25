//package com.example.arturo.supermarket;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.util.ArrayList;
//
///**
// * Created by casunley on 5/3/2015.
// */
//public class DatabaseHandler extends SQLiteOpenHelper {
//
//    //Static Things
//    //DB Version
//    private static final int DATABASE_VERSION = 1;
//
//    //DaB Name
//    private static final String DATABASE_NAME = "userSupermarketlist";
//
//    //Places table name
//    private static final String TABLE_SUPERMARKET = "supermarket";
//
//    //Places Table Columns names
//    private static final String KEY_ID = "id";
//    private static final String KEY_USERNAME = "username";
//    private static final String KEY_PASSWORD = "password";
//    private static final String KEY_EMAIL = "email";
//    private static final String KEY_ARRAY = "array";
//
//    public DatabaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_PLACES_TABLE = "CREATE TABLE " + TABLE_SUPERMARKET  + "("
//                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT," + KEY_PASSWORD + " TEXT, "
//                + KEY_EMAIL + " TEXT," +KEY_ARRAY+" TEXT)";
//        db.execSQL(CREATE_PLACES_TABLE);
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        //Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SUPERMARKET);
//
//        //Create tables again
//        onCreate(db);
//    }
//
//    /**
//     * CRUD (Create, Read, Update, Delete) Operations
//     */
//
//    //Adding new place
//    void addPlace (userClass users) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        //Log.e("hola", "This one won't");
//        ContentValues values = new ContentValues();
//        values.put(KEY_USERNAME, users.getUserName());
//        values.put(KEY_PASSWORD, users.getPassword());
//        values.put(KEY_EMAIL, users.getEmail());
//        values.put(KEY_ARRAY, users.getStringArray());
//
//        //Insert row
//        long id = db.insert(TABLE_SUPERMARKET, null, values);
//        Log.e("ID from the DB","  ID: "+id);
//        db.close();
//    }
//
//    //Grab a single contact
//    public userClass getUserClass(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_SUPERMARKET, new String[] {KEY_ID, KEY_USERNAME, KEY_PASSWORD,
//                        KEY_EMAIL,KEY_ARRAY}, KEY_ID + "=?",
//                new String[] { String.valueOf(id)}, null, null, null, null);
//        if(cursor != null)
//            cursor.moveToFirst();
//
//        userClass users = new userClass(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
//                cursor.getString(2), cursor.getString(3), cursor.getString(4));
//
//        return users;
//    }
//
//    public ArrayList<userClass> getAllPlaces(){
//        ArrayList<userClass> usersList = new ArrayList<userClass>();
//        //Select ALL Query
//        String selectQuery = "SELECT  * FROM " + TABLE_SUPERMARKET;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        Log.e("hola", "This one will appear");
//        //loop through all rows and add to list
//        if (cursor.moveToFirst()){
//            do {
//                userClass users = new userClass();
//                users.set_id(Integer.parseInt(cursor.getString(0)));
//                users.setUserName(cursor.getString(1));
//                users.setPassword(cursor.getString(2));
//                users.setEmail(cursor.getString(3));
//                users.setSArray(cursor.getString(4));//Wants StrArray instead of string
//                //Add place to list
//                usersList.add(users);
//            } while(cursor.moveToNext());
//        }
//        return usersList;
//    }
//
//    //Update single place
//    public int updateUser(userClass users) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_USERNAME, users.getUserName());
//        values.put(KEY_PASSWORD, users.getPassword());
//        //values.put(KEY_PHONE_NUMBER, place.getPhone());
//        values.put(KEY_EMAIL, users.getEmail());
//        values.put(KEY_ARRAY, users.getsArray());
//
//        //Update the row
//        return db.update(TABLE_SUPERMARKET, values, KEY_ID + " = ?",
//                new String[] {String.valueOf(users.get_id())});
//    }
//
//    //Delete single place
//    public void deleteUser(userClass user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_SUPERMARKET, KEY_ID + " + ?",
//                new String[] { String.valueOf(user.get_id())});
//        db.close();
//    }
//
//    //Get count of places
//    public int getUsersCount(){
//        String countQuery = "SELECT * FROM " + TABLE_SUPERMARKET;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        //Return the count
//        return cursor.getCount();
//    }
//
//}
