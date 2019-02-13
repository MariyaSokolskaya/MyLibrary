package com.example.admin215.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    LinkedList <Book> listBooks = new LinkedList<Book>();
   // ArrayAdapter <Book> adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listBooks.add(new Book("Oruell", "1984", "Antiutopiya", 1992, R.drawable.oruell));
        listBooks.add(new Book("Freid", "История фобий пятилетнего мальчика", "Психология, научная " +
                                "литература", 1955, R.drawable.fraid));
        listBooks.add(new Book("Azimov", "Основание", "Научная фантастика", 1986, R.drawable.azimov));
        listBooks.add(new Book("Rouling", "Гарри Поттер", "Фентези", 2010, R.drawable.harry));

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < listBooks.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("author", listBooks.get(i).author);
            map.put("name", listBooks.get(i).name);
            map.put("year", listBooks.get(i).year);
            map.put("genre", listBooks.get(i).genre);
            map.put("cover", listBooks.get(i).coverID);
            dataList.add(map);
        }
        String [] from = {"author", "name", "year", "genre", "cover"};
        int [] to = {R.id.author, R.id.name, R.id.year, R.id.genre, R.id.cover};
        SimpleAdapter adapter = new SimpleAdapter(this, dataList, R.layout.item_list,from, to);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
/*        adapter = new ArrayAdapter(this, R.layout.item_list, R.id.itemlist, listBooks);
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
