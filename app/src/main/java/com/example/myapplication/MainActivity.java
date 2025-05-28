package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.TextView;
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
    TextView editingLabelTextView;
    TextView seekBarValueTextView;
    SeekBar valueSeekBar;
    int selectedItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListView = findViewById(R.id.settingsListView);
        editingLabelTextView = findViewById(R.id.editingLabelTextView);
        seekBarValueTextView = findViewById(R.id.seekBarValueTextView);
        valueSeekBar = findViewById(R.id.valueSeekBar);

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
                selectedItemPosition = position;
                String selectedItem = displayItemsForListView.get(position);
                Toast.makeText(MainActivity.this, "Wybrano: " + selectedItem + ". Idealny wybór!", Toast.LENGTH_SHORT).show();
                String selected[] = selectedItem.split(":");
                editingLabelTextView.setText(selected[0]);
                seekBarValueTextView.setText("Wartość: " + selected[1]);
                valueSeekBar.setProgress(Integer.parseInt(selected[1].substring(0,selected[1].length()-1)));
                valueSeekBar.setEnabled(true);
            }
        });

        valueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                if(fromUser && selectedItemPosition != -1){
                    seekBarValueTextView.setText("Wartość: " + progressValue);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(selectedItemPosition != -1){
                    int actualValue = valueSeekBar.getProgress();
                    String temp = settingNames.get(selectedItemPosition) + ":" + actualValue + settingUnits.get(selectedItemPosition);
                    displayItemsForListView.set(selectedItemPosition, temp);
                    customAdapter.notifyDataSetChanged();
                }
            }
        });


    }
}