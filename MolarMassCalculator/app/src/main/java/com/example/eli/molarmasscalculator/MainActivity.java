package com.example.eli.molarmasscalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import java.util.*;
import android.content.Context;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //array of all elements
        final String[] elementsArray = {"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P",
                                    "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu",
                                    "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc",
                                    "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La",
                                    "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu",
                                    "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At",
                                    "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es",
                                    "Fm", "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Uut",
                                    "Fl", "Uup", "Lv", "UUs", "Uuo"};

        //array of all masses to match elements
        final Double[] massesArray = {1.0079, 4.002602, 6.941, 9.012182, 10.811, 12.0107, 14.00674,15.9994,
                18.9984032, 20.1797, 22.989770, 24.3050, 26.981538, 28.0855, 30.973761, 32.065, 35.453, 39.948,
                39.0983, 40.078, 44.9559, 47.867, 50.9415, 51.9961, 54.938, 55.845, 58.9332, 58.6934, 63.546,
                65.39, 69.723, 72.64, 74.9216, 78.96, 79.904, 83.8, 85.4678, 87.62, 88.9059, 91.224, 92.9064,
                95.94, 98.0, 101.07, 102.9055, 106.42, 107.8682, 112.411, 114.818, 118.71, 121.76, 127.6,
                126.9045, 131.293, 132.9055, 137.327, 138.9055, 140.116, 140.9077, 144.24, 145.0, 150.36,
                151.964, 157.25, 158.9253, 162.5, 164.9303, 167.259, 168.9342, 173.04, 174.967, 178.49, 180.9479,
                183.84, 186.207, 190.23, 192.217, 195.078, 196.9665, 200.59, 204.3833, 207.2, 208.9804, 209.0, 210.0,
                222.0, 223.0, 226.0, 227.0, 232.0381, 231.0359, 238.0289, 237.0, 244.0, 243.0, 247.0, 247.0, 251.0,
                252.0, 257.0, 258.0, 259.0, 262.0, 266.0, 268.0, 269.0, 270.0, 269.0, 278.0, 281.0, 282.0, 285.0,
                286.0, 289.0, 289.0, 293.0, 294.0, 294.0};


        //array to hold values clicked
        final ArrayList<String> secondArrayString = new ArrayList<String>();

        //array to hold element states that have been clicked
        final ArrayList<Element> secondArrayState = new ArrayList<Element>();

        //making clear button
        final Button b = (Button) findViewById(R.id.button);

        //total mass textview
        final TextView addText2 = (TextView) findViewById(R.id.textView2);

        //hash to keep all clicked values
        final Map<String, Integer> map = new HashMap<String, Integer>();

        //total mass object
        final Mass totalMass = new Mass(0);

        //plugging the elements into the listview and making an adapter for it
        ListAdapter Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elementsArray);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(Adapter);

        //adapter for the second listview (clicked elements, how many times clicked);
        final ListAdapter Adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, secondArrayString);
        final ListView listView2 = (ListView) findViewById(R.id.listView2);
        listView2.setAdapter(Adapter2);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //get the position of the click on listview
                            //set the text to the elements name
                            //make new Element object that holds element and mass
                        String clickedElement = String.valueOf(parent.getItemAtPosition(position));
                        Element clickedElementState = new Element(clickedElement, massesArray[position]);
                        TextView addText = (TextView) findViewById(R.id.textView);
                        addText.setText(clickedElementState.getE());

                        //setting total mass text
                        addText2.setText(String.format("Total: %.5f", totalMass.getM()));

                        //if the clicked item is already in the list update the value of it by 1
                        //updating the list so clear the array
                        secondArrayState.clear();
                        secondArrayString.clear();
                        if(map.containsKey(clickedElementState.getE())){

                            //put the new updated value of the item in the map
                            map.put(clickedElementState.getE(), map.get(clickedElementState.getE()) + 1);

                        }
                        //if its not already in the list, add a new one
                        else{
                            map.put(clickedElementState.getE(), 1);
                        }

                        //update the total mass
                        totalMass.setM(totalMass.getM() + clickedElementState.getM());
                        //update text
                        addText2.setText(String.format("Total: %.5f", totalMass.getM()));
                        //update listview
                        listView2.invalidateViews();
                        //update secondArrayState
                        for(String i : map.keySet()){
                            secondArrayState.add(new Element(i, massesArray[position]));
                            secondArrayString.add(i + map.get(i));
                        }
                    }
                }
        );

        listView2.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {

                        //get clicked item from secondArray
                        Element clickedElement = secondArrayState.get(position);

                        //updating mass variable
                        totalMass.setM(totalMass.getM() - clickedElement.getM());
                        //setting the text accordingly
                        addText2.setText(String.format("Total: %.5f", totalMass.getM()));

                        //call map to subtract 1 from the value with our obtained key
                            //if key-1 = 0, then remove it from the list
                        if((map.get(clickedElement.getE()) - 1 == 0)) {
                            map.remove(clickedElement.getE());
                            secondArrayString.remove(position);
                            secondArrayState.remove(position);
                        }
                        //removing if = 0
                        else{
                            map.put(clickedElement.getE(), map.get(clickedElement.getE()) - 1);
                        }

                        //'refreshing' the array we use to write to the listview
                            //using updated values from the map
                        secondArrayString.clear();
                        secondArrayState.clear();

                        //updating both arrays
                        for(String i: map.keySet()){

                            //we have to find the current elements mass somehow
                                //a little dirty looking but alright
                            String name = i;
                            int index = -1;
                            for (int j=0; j < elementsArray.length; j++) {
                                if (elementsArray[j].equals(name)) {
                                    index = j;
                                    break;
                                }
                            }

                            //updating arrays
                            secondArrayString.add(i + map.get(i));
                            secondArrayState.add(new Element(i, massesArray[index]));
                        }


                        //notify listview of update
                        listView2.invalidateViews();

                    }

                    ;
                }
        );

        //listener for clear button
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set mass to 0;
                totalMass.setM(0);
                //update the textview
                addText2.setText(String.format("Total: %.5f", totalMass.getM()));
                //clear the array
                secondArrayString.clear();
                //update the listview
                listView2.invalidateViews();
                //clear the map
                map.clear();


            }
        });

    }
}
