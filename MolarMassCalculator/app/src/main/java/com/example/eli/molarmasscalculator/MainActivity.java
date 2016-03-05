package com.example.eli.molarmasscalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.*;
import android.widget.Toast;
import android.view.View;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.*;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] elementsArray = {"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P"};
        Double[] massesArray = {1.0079, 4.002602, 6.941, 9.012182, 10.811, 12.0107, 14.00674,
                15.9994, 18.9984032, 20.1797, 22.989770, 24.3050, 26.981538, 28.0855, 30.973761};

        final int[] numTimesSelected = new int[15];

        ListAdapter Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elementsArray);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(Adapter);

        final ArrayList<String> addedItems = new ArrayList<String>();

        ListAdapter addedAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addedItems);
        ListView addedListView = (ListView)findViewById(R.id.listView2);
        addedListView.setAdapter(addedAdapter);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String test = String.valueOf(parent.getItemAtPosition(position));
                        numTimesSelected[position] += 1;
                        addedItems.add(test + ": " + numTimesSelected[position]);
                        addedListView.setAdapter(addedAdapter);


                        TextView addText = (TextView) findViewById(R.id.textView);
                        addText.setText(test + numTimesSelected[position]);
                    }
                }
        );
    }
}
