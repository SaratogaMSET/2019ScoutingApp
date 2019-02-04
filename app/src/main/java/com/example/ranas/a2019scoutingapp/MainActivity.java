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

public class MainActivity extends AppCompatActivity {

    //main
    public static String myTeamNumber = "";
    public static String myMatchNumber = "0";
    public static String myScouterName = "";
    public static String alliance = "";
    public static String tabNum = "";


    //sandstorm
    public static String ssPos;
    public static int [] rocketScoredSS = new int[20];
    public static int [] CargoshipScoredSS = new int[2];
    public static String startedWithSS;
    public static String robotMovesSS;
    public static int penaltiesSS;
    public static int groundC, groundH;

    //teleop
    public static int [] rocketScoredTO = new int[20];
    public static int [] CargoshipScoredTO = new int[2];
    public static String robotMovesTO;
    public static int penaltiesTO;


    //endgame/notes
    public static String driving = "";
    public static String accuracy = "";
    public static String defense = "";
    public static int unsupportedClimb = 5;
    public static int support = 0;
    public static String myNotes;
    public static boolean checked;

    public static Spinner dropdown;
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
        dropdown = findViewById(R.id.spinner);
        //create a list of items for the spinner.
        String[] items = {"R1", "R2", "R3", "B1", "B2", "B3"};
        //create an adapter to describe how the items are displayed, adapters are used in several places    in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        if(tabNum.equals("R1"))
            dropdown.setSelection(0);
        if(tabNum.equals("R2"))
            dropdown.setSelection(1);
        if(tabNum.equals("R3"))
            dropdown.setSelection(2);
        if(tabNum.equals("B1"))
            dropdown.setSelection(3);
        if(tabNum.equals("B2"))
            dropdown.setSelection(4);
        if(tabNum.equals("B3"))
            dropdown.setSelection(5);

        //main activity
        myTeamNumber = "";
        myMatchNumber = Integer.toString(Integer.valueOf(myMatchNumber)+1);
        matchNumber.setText(myMatchNumber);
        View v = (View) button;
        refresh(v);
        scouterName.setText(myScouterName);
        //alliance = "";

        //sandstorm
        ssPos = "L1";
        for(int x = 0; x < 20; x++){
            rocketScoredSS[x] = 0;
        }
        for(int x = 0; x < 2; x++){
            CargoshipScoredSS[x] = 0;
        }
        startedWithSS = "";
        robotMovesSS = "Robot moves: ";
        penaltiesSS = 0;
        groundC = 0;
        groundH = 0;

        //teleop
        for(int x = 0; x < 20; x++){
            rocketScoredTO[x] = 0;
        }
        for(int x = 0; x < 2; x++){
            CargoshipScoredTO[x] = 0;
        }
        robotMovesTO = "Robot moves: ";
        penaltiesTO = 0;

        //endgame
//        for(int x = 0; x < 20; x++){
//            rocketScoredEG[x] = 0;
//        }
//        for(int x = 0; x < 2; x++){
//            CargoshipScoredEG[x] = 0;
//        }
//        robotMovesEG = "Robot moves: ";
//        penaltiesEG = 0;
        driving = "";
        accuracy = "";
        defense = "";
        unsupportedClimb = 0;
        myNotes = "";
        checked = false;


        Path.setText("Path is: " + getIntent().getStringExtra("path"));


        scoutMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!alliance.matches("") && !scouterName.getText().toString().matches("") && !teamNumber.getText().toString().matches("") && !matchNumber.getText().toString().matches("") ){
                    Intent goToAutonomous = new Intent();
                    goToAutonomous.setClass(getApplicationContext(), SandstormActivity.class);
                    myTeamNumber = teamNumber.getText().toString();
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
            alliance = "red";
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            blue.setBackgroundColor(Color.BLUE);
            red.setBackgroundColor(Color.GRAY);
            alliance = "blue";
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
                if (dropdown.getSelectedItem().toString().equals("R1") || dropdown.getSelectedItem().toString().equals("R2") || dropdown.getSelectedItem().toString().equals("R3")){
                    String s = dropdown.getSelectedItem().toString();
                    tabNum = s;
                    blue.setBackgroundColor(Color.GRAY);
                    red.setBackgroundColor(Color.RED);
                    alliance = "red";
                    int x = Integer.valueOf(matchNumber.getText().toString()) - 1;
                    int y = Integer.valueOf(s.charAt(1))-49;
                    teamNumber.setText(Integer.toString(list[x][y]));
                } else {
                    String s = dropdown.getSelectedItem().toString();
                    tabNum = s;
                    blue.setBackgroundColor(Color.BLUE);
                    red.setBackgroundColor(Color.GRAY);
                    alliance = "blue";
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
