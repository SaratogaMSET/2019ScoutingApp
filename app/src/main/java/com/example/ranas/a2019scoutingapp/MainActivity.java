package com.example.ranas.a2019scoutingapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.Stack;

import static com.example.ranas.a2019scoutingapp.Vars.CargoshipScoredSS;
import static com.example.ranas.a2019scoutingapp.Vars.CargoshipScoredTO;
import static com.example.ranas.a2019scoutingapp.Vars.myMatchNumber;
import static com.example.ranas.a2019scoutingapp.Vars.myScouterName;
import static com.example.ranas.a2019scoutingapp.Vars.rocketScoredSS;
import static com.example.ranas.a2019scoutingapp.Vars.rocketScoredTO;
import static com.example.ranas.a2019scoutingapp.Vars.tabNum;

public class MainActivity extends AppCompatActivity {
    //main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText teamNumber = findViewById(R.id.teamNumber);
        final EditText matchNumber = findViewById(R.id.matchNumber);
        final EditText scouterName = findViewById(R.id.scouterName);
        final Button scoutMatch = findViewById(R.id.scoutMatch);
        final Button red = findViewById(R.id.red);
        final Button blue = findViewById(R.id.blue);
        final TextView Path = findViewById(R.id.Path);
        final Button button = findViewById(R.id.button);

        //get the spinner from the xml.
        Vars.dropdown = findViewById(R.id.spinner);
        //create a list of items for the spinner.
        String[] items = {"R1", "R2", "R3", "B1", "B2", "B3"};
        //create an adapter to describe how the items are displayed, adapters are used in several places    in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        Vars.dropdown.setAdapter(adapter);

        Vars.slots = "";

        if(tabNum.equals("R1"))
            Vars.dropdown.setSelection(0);
        if(tabNum.equals("R2"))
            Vars.dropdown.setSelection(1);
        if(tabNum.equals("R3"))
            Vars.dropdown.setSelection(2);
        if(tabNum.equals("B1"))
            Vars.dropdown.setSelection(3);
        if(tabNum.equals("B2"))
            Vars.dropdown.setSelection(4);
        if(tabNum.equals("B3"))
            Vars.dropdown.setSelection(5);

        //main activity
        Vars.myTeamNumber = "";
        Vars.myMatchNumber = Integer.toString(Integer.valueOf(myMatchNumber)+1);
        matchNumber.setText(myMatchNumber);
        View v = (View) button;
        refresh(v);
        scouterName.setText(myScouterName);
        //alliance = "";

        //sandstorm
        Vars.ssPos = "L1";
        for(int x = 0; x < 20; x++){
            Vars.rocketScoredSS[x] = 0;
        }
        for(int x = 0; x < 2; x++){
            Vars.CargoshipScoredSS[x] = 0;
        }
        Vars.startedWithSS = "NaN";
        Vars.robotMovesSS = "Robot moves: ";
        Vars.penalties = 0;
        Vars.groundC = 0;
        Vars.groundH = 0;

        //teleop
        for(int x = 0; x < 20; x++){
            Vars.rocketScoredTO[x] = 0;
        }
        for(int x = 0; x < 2; x++){
            Vars.CargoshipScoredTO[x] = 0;
        }
//        Vars.robotMovesTO = "Robot moves: ";

        //endgame
//        for(int x = 0; x < 20; x++){
//            rocketScoredEG[x] = 0;
//        }
//        for(int x = 0; x < 2; x++){
//            CargoshipScoredEG[x] = 0;
//        }
//        robotMovesEG = "Robot moves: ";
//        penaltiesEG = 0;
        Vars.driving = "";
        Vars.accuracy = "";
        Vars.defense = "";
        Vars.unsupportedClimb = 0;
        Vars.myNotes = "";
        Vars.checked = false;


        Path.setText("Path is: " + getIntent().getStringExtra("path"));

        scoutMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Vars.alliance.matches("") && !scouterName.getText().toString().matches("") && !teamNumber.getText().toString().matches("") && !matchNumber.getText().toString().matches("") ){
                    Intent goToAutonomous = new Intent();
                    goToAutonomous.setClass(getApplicationContext(), SandstormActivity.class);
                    Vars.myTeamNumber = teamNumber.getText().toString();
                    myMatchNumber = matchNumber.getText().toString();
                    myScouterName = scouterName.getText().toString();
                    startActivity(goToAutonomous);
                }
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blue.setBackgroundColor(Color.GRAY);
                red.setBackgroundColor(Color.RED);
                Vars.alliance = "red";
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blue.setBackgroundColor(Color.BLUE);
                red.setBackgroundColor(Color.GRAY);
                Vars.alliance = "blue";
            }
        });
    }



    public void refresh(View v){

        final EditText teamNumber = findViewById(R.id.teamNumber);
        final EditText matchNumber = findViewById(R.id.matchNumber);
        final Button red = findViewById(R.id.red);
        final Button blue = findViewById(R.id.blue);

        //Toast.makeText(getApplicationContext(), "refreshing..." + matchNumber.getText(), Toast.LENGTH_SHORT).show();
        Log.d("myRefresh", "refreshing..." + matchNumber.getText());
        try {
            Matches matchList = new Matches(getResources().getString(R.string.matches));
            int[][] list = matchList.getMatchList();
            if (matchNumber.getText().toString().length() > 0 && Integer.valueOf(matchNumber.getText().toString()) - 1 <= matchList.ctr) {
                //teamNumber.setText(Integer.toString(list[Integer.valueOf(matchNumber.getText().toString()) - 1][tabNum]));
                if (Vars.dropdown.getSelectedItem().toString().equals("R1") || Vars.dropdown.getSelectedItem().toString().equals("R2") || Vars.dropdown.getSelectedItem().toString().equals("R3")){
                    String s = Vars.dropdown.getSelectedItem().toString();
                    tabNum = s;
                    blue.setBackgroundColor(Color.GRAY);
                    red.setBackgroundColor(Color.RED);
                    Vars.alliance = "red";
                    int x = Integer.valueOf(matchNumber.getText().toString()) - 1;
                    int y = Integer.valueOf(s.charAt(1))-49;
                    teamNumber.setText(Integer.toString(list[x][y]));
                } else {
                    String s = Vars.dropdown.getSelectedItem().toString();
                    tabNum = s;
                    blue.setBackgroundColor(Color.BLUE);
                    red.setBackgroundColor(Color.GRAY);
                    Vars.alliance = "blue";
                    int x = Integer.valueOf(matchNumber.getText().toString()) - 1;
                    int y = Integer.valueOf(s.charAt(1))-46;
                    teamNumber.setText(Integer.toString(list[x][y]));
                }
                return;
            }
            //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            Log.d("myRefresh", "error..." + matchNumber.getText());
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(getApplicationContext(), "exception", Toast.LENGTH_SHORT).show();
            Log.d("myRefresh", e.toString() + matchNumber.getText());
        }
    }
}
