package com.example.hazal.myagenda.DatabaseAndClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.location.Address;
import android.location.Geocoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import java.sql.SimpleDateFormat;

/**
 * Created by ha3al on 6/25/16.
 */

public class Database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyAgendaDb";

    public static final String TABLE_USER = "User";
    public static final String TABLE_NOTE = "Note";

    // This columns for User Table
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String USER_EMAIL = "user_email";
    public static final String PASSWORD = "password";

    // This columns for Note Table
    public static final String NOTE_ID = "note_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String CONTACT_NO = "contact_no";
    public static final String NOTE_EMAIL = "note_email";
    public static final String CREATED_AT = "created_at";
    public static final String LOCATION = "location";

    public static final String TABLE_SETTING = "Setting";


    // This columns for Setting Table
    public static final String COLOR_ID = "color_id";
    public static final String COLOR_HEX = "color_hex";
    public static final String COLOR_FONT = "color_font";


    Context context;
    public Database(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        //context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_NOTE = "CREATE TABLE " + TABLE_NOTE + " ("
                + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + CONTACT_NO + " TEXT, "
                + NOTE_EMAIL + " TEXT, "
                + CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + LOCATION + " TEXT )";

        String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + " TEXT, "
                + USER_EMAIL + " TEXT, "
                + PASSWORD + " TEXT )";

        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_NOTE);

        String CREATE_TABLE_SETTING = "CREATE TABLE " + TABLE_SETTING + " ( "
                + COLOR_ID + " INTEGER, "
                + COLOR_HEX + " TEXT, "
                + COLOR_FONT + " INTEGER );";


        db.execSQL(CREATE_TABLE_SETTING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    public void defaultSetting(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+TABLE_SETTING+" VALUES (1, 'FFFFA500', 18);");
    }

    public Settings getSettings() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_SETTING + " WHERE " + COLOR_ID + " = " + 1;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();

        Settings settings = new Settings(cursor.getString(1), Integer.parseInt(cursor.getString(2)));

        db.close();
        cursor.close();
        return settings;
    }

    public void setSettings(Settings settings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLOR_HEX, settings.getColorHex());
        values.put(COLOR_FONT, settings.getColorFont());

        db.update(TABLE_SETTING, values, COLOR_ID + " = ?", new String[]{String.valueOf(1)});
        db.close();
    }

    private String formatDateTime(String sa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            String dt = sdf.parse(sa).toString(); //replace 4 with the column index
            /// this will return Sunday 12:13

            String day= String.valueOf(dt.split(" ")[0]);
            String hour = String.valueOf(Integer.parseInt(dt.split(" ")[3].split(":")[0]) +3);
            String minute = String.valueOf(dt.split(" ")[3].split(":")[1]);

            return day + " " + hour + ":" + minute;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "time!";
    }

    GPSTracker gps; //++

    //++++
    public String konumuAl(){
        //// This is for gps
        gps = new GPSTracker(context);
        // check if GPS enabled
        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            // \n is for new line
            String cityName=null;
            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(latitude, longitude, 1);
                if (addresses.size() > 0)
                    cityName = addresses.get(0).getThoroughfare()+"-"+addresses.get(0).getAdminArea()+"-"+addresses.get(0).getCountryName();
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                //Toast.makeText(context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                return cityName; // bize anlık konumu verecek

            }catch (Exception e){
                return ""; // Hata olursa konumu boş eklesin
            }
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();

            return ""; // Hata olursa konumu boş eklesin
        }
    }
    /**
     * Bu kısım Note classı için
     */

    // insert note to note table
    public void addNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, note.getTitle());
        values.put(DESCRIPTION, note.getDescription());
        values.put(CONTACT_NO, note.getContactNO());
        values.put(NOTE_EMAIL, note.getEmail());
        values.put(LOCATION, konumuAl());

        db.insert(TABLE_NOTE, null, values);
        db.close();
    }

    // delete note
    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTE, NOTE_ID + " = ?", new String[]{String.valueOf(note.getNoteID())});
        db.close();
    }

    // update note
    public void updateNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, note.getTitle());
        values.put(DESCRIPTION, note.getDescription());
        values.put(CONTACT_NO, note.getContactNO());
        values.put(NOTE_EMAIL, note.getEmail());
        values.put(LOCATION, konumuAl());


        db.update(TABLE_NOTE, values, NOTE_ID + " = ?", new String[]{String.valueOf(note.getNoteID())});
        db.close();
    }

    // get single notes detail
    public Note getSingleNoteDetailById(int noteID) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NOTE + " WHERE " + NOTE_ID + " = " + noteID;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();

        Note note = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),formatDateTime(cursor.getString(5)),cursor.getString(6));

        db.close();
        cursor.close();
        return note;
    }

    public List<Note> getAllNote() {
        List<Note> noteList = new ArrayList<>();
        String selectQery = "SELECT * FROM " + TABLE_NOTE + " ORDER BY " + CREATED_AT + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setNoteID(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setDescription(cursor.getString(2));
                note.setContactNO(cursor.getString(3));
                note.setEmail(cursor.getString(4));
                note.setCreated_at(formatDateTime(cursor.getString(5)));
                note.setLocation(cursor.getString(6));
                noteList.add(note);

            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return noteList;
    }


    /**
     * Bu kısım User classı için
     */

    // insert user to user table
    public void registerUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(USER_EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    // update user
    public void updateUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(USER_EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());

        db.update(TABLE_USER, values, USER_ID + " = ?", new String[]{String.valueOf(user.getUserID())});
        db.close();
    }

    // get single users detail
    public User getUserDetail(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + USERNAME + " = '" + username + "' AND " + PASSWORD + " = '" + password + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null)
            cursor.moveToFirst();


        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        db.close();
        cursor.close();
        return user;
    }
}
