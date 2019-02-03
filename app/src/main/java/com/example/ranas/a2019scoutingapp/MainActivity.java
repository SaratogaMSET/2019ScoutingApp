package com.example.ranas.a2019scoutingapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //main
    public static String myTeamNumber = "";
    public static String myMatchNumber = "";
    public static String myScouterName = "";
    public static String alliance = "";

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


    //endgame
    public static String driving = "";
    public static String accuracy = "";
    public static String defense = "";
    public static int unsupportedClimb = 5;
    public static int support = 0;
    public static String myNotes;
    public static boolean checked;

    //notes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //main activity
        myTeamNumber = "";
        myMatchNumber = "";
        myScouterName = "";
        alliance = "";

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

        final EditText teamNumber = findViewById(R.id.teamNumber);
        final EditText matchNumber = findViewById(R.id.matchNumber);
        final EditText scouterName = findViewById(R.id.scouterName);
        final Button scoutMatch = findViewById(R.id.scoutMatch);
        final Button red = findViewById(R.id.red);
        final Button blue = findViewById(R.id.blue);
        final TextView Path = findViewById(R.id.Path);
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
            int tabNum = 0;
            if (matchNumber.getText().toString().length() > 0 && Integer.valueOf(matchNumber.getText().toString()) - 1 <= matchList.ctr) {
                teamNumber.setText(Integer.toString(list[Integer.valueOf(matchNumber.getText().toString()) - 1][tabNum]));
                if (tabNum <= 2){
                    blue.setBackgroundColor(Color.GRAY);
                    red.setBackgroundColor(Color.RED);
                    alliance = "red";

                } else {
                    blue.setBackgroundColor(Color.BLUE);
                    red.setBackgroundColor(Color.GRAY);
                    alliance = "blue";

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
