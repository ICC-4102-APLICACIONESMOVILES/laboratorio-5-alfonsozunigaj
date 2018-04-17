package com.example.alfonso.lab01;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Date;


@Entity
public class Form {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "last_name")
    private String date;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "number_questions")
    private int numberQuestions;

    public Form() {
        name = "";
        date = "";
        category = "";
        description = "";
        numberQuestions = 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName (String name) { this.name = name; }
    public String getDate() { return date; }
    public void setDate (String date) { this.date = date; }
    public String getCategory() { return category; }
    public void setCategory (String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription (String description) { this.description = description; }
    public int getNumberQuestions() { return numberQuestions; }
    public void setNumberQuestions (int numberQuestions) { this.numberQuestions = numberQuestions; }

}
