package com.example.hazal.myagenda.DatabaseAndClasses;

/**
 * Author : l50 - Özcan YARIMDÜNYA (@ozcaan11)
 * Date   : 23.06.2016 - 14:57
 */

public class Note {
    int noteID;
    String title;
    String description;
    String contactNO;
    String email;
    String created_at; // zaman için

    public Note() {
    }

    // Constructor with id
    public Note(int noteID, String title, String description, String contactNO, String email) {
        this.noteID = noteID;
        this.title = title;
        this.description = description;
        this.contactNO = contactNO;
        this.email = email;
    }

    // Cons without id
    public Note(String title, String description, String contactNO, String email) {
        this.title = title;
        this.description = description;
        this.contactNO = contactNO;
        this.email = email;
    }

    public Note(int noteID, String title, String description, String contactNO, String email, String created_at) {
        this.noteID = noteID;
        this.title = title;
        this.description = description;
        this.contactNO = contactNO;
        this.email = email;
        this.created_at = created_at;
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
