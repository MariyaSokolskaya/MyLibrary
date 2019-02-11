package com.example.admin215.mylibrary;

/**
 * Created by Admin215 on 11.02.2019.
 */

public class Book {
    String author;
    String name;
    int year;
    String genre;

    public Book(String author,  String name, String genre, int year) {
        this.author = author;
        this.genre = genre;
        this.name = name;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Author: " + author + "\n" +
                "Name: " + name + "\n" +
                "Genre: " + genre + "\n" +
                "Year: " + year;
    }
}
