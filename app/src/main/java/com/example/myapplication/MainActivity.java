package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {
    ListView mainListView;
    ArrayList<String> settingNames;
    ArrayList<Integer> settingValues;
    ArrayList<String> settingUnits;
    ArrayList<String> displayItemsForListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListView = findViewById(R.id.settingsListView);

        settingNames = new ArrayList<>();
        settingNames.add("Jasność Ekranu");
        settingNames.add("Głośność Dźwięków");
        settingNames.add("Czas Automatycznej Blokady");

        settingValues = new ArrayList<>();
        settingValues.add(50);
        settingValues.add(80);
        settingValues.add(30);

        settingUnits = new ArrayList<>();
        settingUnits.add("%");
        settingUnits.add("%");
        settingUnits.add("s");

        displayItemsForListView = new ArrayList<>();
        for (int i = 0; i < settingNames.size(); i++) {
            String temp = settingNames.get(i) + ":" + settingValues.get(i) + settingUnits.get(i);
            displayItemsForListView.add(temp);
        }

        ArrayAdapter<String> customAdapter = new ArrayAdapter<>(
                this,
                R.layout.list_item_setting,
                R.id.customTextView,
                displayItemsForListView
        );

        mainListView.setAdapter(customAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = displayItemsForListView.get(position);
                Toast.makeText(MainActivity.this, "Wybrano: " + selectedItem + ". Idealny wybór!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}