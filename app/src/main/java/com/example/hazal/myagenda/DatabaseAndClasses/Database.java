package com.example.hazal.myagenda.DatabaseAndClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import java.sql.SimpleDateFormat;

/**
 * Author : l50 - Özcan YARIMDÜNYA (@ozcaan11)
 * Date   : 23.06.2016 - 12:15
 */

public class Database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AgendaDb";

    public static final String TABLE_USER = "User";
    public static final String TABLE_NOTE = "Note";

    // This columns for User Table
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String USER_EMAIL = "user_email";
    public static final String PASSWORD = "password";

    // This columns for User Table
    public static final String NOTE_ID = "note_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String CONTACT_NO = "contact_no";
    public static final String NOTE_EMAIL = "note_email";
    public static final String CREATED_AT = "created_at";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_NOTE = "CREATE TABLE " + TABLE_NOTE + " ("
                + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + CONTACT_NO + " TEXT, "
                + NOTE_EMAIL + " TEXT, "
                + CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP )";

        String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + " TEXT, "
                + USER_EMAIL + " TEXT, "
                + PASSWORD + " TEXT )";

        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    private String formatDateTime(String sa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            String dt = sdf.parse(sa).toString(); //replace 4 with the column index
            /// this will return Sunday 12:13
            return dt.split(" ")[0] + " " + dt.split(" ")[3].split(":")[0] + ":" + dt.split(" ")[3].split(":")[1];

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "time!";
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

        Note note = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),formatDateTime(cursor.getString(5)));

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
