package com.example.admin215.mylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    LinkedList <Book> listBooks = new LinkedList<Book>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listBooks.add(new Book("Oruell", "1984", "Antiutopiya", 1992));
        listBooks.add(new Book("Freid", "История фобий пятилетнего мальчика", "Психология, научная " +
                                "литература", 1955));
        listBooks.add(new Book("Azimov", "Основание", "Научная фантастика", 1986));
        listBooks.add(new Book("Rouling", "Гарри Поттер", "Фентези", 2010));
    }
}
