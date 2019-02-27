package com.example.admin215.mylibrary;

/**
 * Created by Admin215 on 11.02.2019.
 */

public class Book {
    String author;
    String name;
    int year;
    String genre;
   // int coverID;

    public Book(String author,  String name, String genre, int year /*, int coverID*/) {
        this.author = author;
        this.genre = genre;
        this.name = name;
        this.year = year;
        //this.coverID = coverID;
    }

    @Override
    public String toString() {
        return "Author: " + author + "\n" +
                "Name: " + name + "\n" +
                "Genre: " + genre + "\n" +
                "Year: " + year;
    }
}
