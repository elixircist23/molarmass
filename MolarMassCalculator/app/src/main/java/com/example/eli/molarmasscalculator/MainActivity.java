package com.example.eli.molarmasscalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import java.util.*;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] elementsArray = {"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P"};

        Double[] massesArray = {1.0079, 4.002602, 6.941, 9.012182, 10.811, 12.0107, 14.00674,
                15.9994, 18.9984032, 20.1797, 22.989770, 24.3050, 26.981538, 28.0855, 30.973761};

        final ArrayList<String> secondArray = new ArrayList<String>();


        ListAdapter Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elementsArray);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(Adapter);

        final ListAdapter Adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, secondArray);
        final ListView listView2 = (ListView) findViewById(R.id.listView2);
        listView2.setAdapter(Adapter2);



        final Map<String, Integer> map = new HashMap<String, Integer>();


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        String test = String.valueOf(parent.getItemAtPosition(position));
                        TextView addText = (TextView) findViewById(R.id.textView);
                        addText.setText(test);

                        if(map.containsKey(test)){
                            secondArray.clear();
                            map.put(test, map.get(test) + 1);
                            listView2.invalidateViews();
                            Collections.sort(secondArray);
                            for(String i : map.keySet()){
                                secondArray.add(i + map.get(i));
                            }

                        }
                        else{
                            map.put(test, 1);
                            secondArray.clear();
                            listView2.invalidateViews();
                            Collections.sort(secondArray);
                            for(String i : map.keySet()){
                                secondArray.add(i + map.get(i));
                            }

                        }

                    }
                }
        );
    }
}
