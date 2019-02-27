package com.example.admin215.mylibrary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    LinkedList <Book> listBooks = new LinkedList<Book>();
   // ArrayAdapter <Book> adapter;
    ListView listView;
    OpenHelper dbHelper;
    SQLiteDatabase sdb;
    public static final String DATABASE_NAME = "mylibrary.db";
    public static final Integer DATABASE_VERSION = 1;

    EditText title, author, year, genre;
    Button saveButton, showAllButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (EditText) findViewById(R.id.title_book);
        author = (EditText) findViewById(R.id.author_book);
        year = (EditText) findViewById(R.id.year_book);
        genre = (EditText) findViewById(R.id.genre_book);
        saveButton = (Button) findViewById(R.id.save_button);
        showAllButton = (Button) findViewById(R.id.show_all);

        dbHelper = new OpenHelper(getBaseContext(), DATABASE_NAME, null, DATABASE_VERSION);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!title.getText().toString().equals("")) {
                    sdb = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(OpenHelper.TITLE_COLUMN, title.getText().toString());
                    values.put(OpenHelper.AUTHOR_COLUMN, author.getText().toString());
                    values.put(OpenHelper.GENRE_COLUMN, genre.getText().toString());
                    values.put(OpenHelper.YEAR_COLUMN, Integer.parseInt(year.getText().toString()));
                    sdb.insert(OpenHelper.TABLENAME, null, values);
                    sdb.close();
                    title.setText("");
                    author.setText("");
                    year.setText("");
                    genre.setText("");
                } else {
                    title.setText("Впишите название!!!");
                }
            }
        });

        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdb = dbHelper.getReadableDatabase();
                Cursor response = sdb.query(OpenHelper.TABLENAME, new String[]
                        {OpenHelper.AUTHOR_COLUMN, OpenHelper.TITLE_COLUMN, OpenHelper.GENRE_COLUMN,
                        OpenHelper.YEAR_COLUMN}, null, null, null, null, null);
                String strQuery = "SELECT " + OpenHelper.TITLE_COLUMN + " FROM " +
                        OpenHelper.TABLENAME + " WHERE " + OpenHelper.YEAR_COLUMN + ">2000;";
                Cursor resp = sdb.rawQuery(strQuery, null);
                response.moveToFirst();
                while (response.moveToNext()){
                    String aStr="", tStr="", gStr="";
                    int year;
                    aStr = response.getString(response.getColumnIndex(OpenHelper.AUTHOR_COLUMN));
                    tStr = response.getString(response.getColumnIndex(OpenHelper.TITLE_COLUMN));
                    gStr = response.getString(response.getColumnIndex(OpenHelper.GENRE_COLUMN));
                    year = response.getInt(response.getColumnIndex(OpenHelper.YEAR_COLUMN));
                    Book newBook = new Book(aStr, tStr, gStr, year);
                    listBooks.add(newBook);
                }
                response.close();
                ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
                for (int i = 0; i < listBooks.size(); i++) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("author", listBooks.get(i).author);
                    map.put("name", listBooks.get(i).name);
                    map.put("year", listBooks.get(i).year);
                    map.put("genre", listBooks.get(i).genre);
                    // map.put("cover", listBooks.get(i).coverID);
                    dataList.add(map);
                }
                String [] from = {"author", "name", "year", "genre"/*, "cover"*/};
                int [] to = {R.id.author, R.id.name, R.id.year, R.id.genre/*, R.id.cover*/};
                SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), dataList, R.layout.item_list,from, to);
                listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(adapter);
                listBooks.clear();
            }
        });

       /* listBooks.add(new Book("Oruell", "1984", "Antiutopiya", 1992, R.drawable.oruell));
        listBooks.add(new Book("Freid", "История фобий пятилетнего мальчика", "Психология, научная " +
                                "литература", 1955, R.drawable.fraid));
        listBooks.add(new Book("Azimov", "Основание", "Научная фантастика", 1986, R.drawable.azimov));
        listBooks.add(new Book("Rouling", "Гарри Поттер", "Фентези", 2010, R.drawable.harry));
        */

/*      adapter = new ArrayAdapter(this, R.layout.item_list, R.id.itemlist, listBooks);
        listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Position: " + Integer.valueOf(position).toString(),
                                Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
