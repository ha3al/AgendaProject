package com.example.hazal.myagenda.DatabaseAndClasses;

/**
 * Created by ha3al on 6/25/16.
 */

public class Note {
    int noteID;
    String title;
    String description;
    String contactNO;
    String email;
    String created_at;// zaman için
    String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Note() {
    }

    // Cons without id
    public Note(String title, String description, String contactNO, String email) {
        this.title = title;
        this.description = description;
        this.contactNO = contactNO;
        this.email = email;
    }

    public Note(int noteID, String title, String description, String contactNO, String email, String created_at, String location) {
        this.noteID = noteID;
        this.title = title;
        this.description = description;
        this.contactNO = contactNO;
        this.email = email;
        this.created_at = created_at;
        this.location = location;
    }


    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactNO() {
        return contactNO;
    }

    public void setContactNO(String contactNO) {
        this.contactNO = contactNO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
