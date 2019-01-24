package com.example.ranas.a2019scoutingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

public class EndgameActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            TextView moves = findViewById(R.id.moves);
            MainActivity.robotMovesEG = moves.getText().toString();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent SS = new Intent();
                    SS.setClass(getApplicationContext(), SandstormActivity.class);
                    startActivity(SS);
                    return true;
                case R.id.navigation_dashboard:
                    Intent TO = new Intent();
                    TO.setClass(getApplicationContext(), TeleOpActivity.class);
                    startActivity(TO);
                    return true;
                case R.id.navigation_notifications:
                    Intent EG = new Intent();
                    EG.setClass(getApplicationContext(), EndgameActivity.class);
                    startActivity(EG);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        TextView moves = findViewById(R.id.moves);
        Button l1 = findViewById(R.id.l1);
        Button l2 = findViewById(R.id.l2);
        Button r1 = findViewById(R.id.r1);
        Button r2 = findViewById(R.id.r2);
        Button m1 = findViewById(R.id.m1);

        moves.setText(MainActivity.robotMovesEG);


        Log.d("Stuff", MainActivity.egPos);

        if(MainActivity.egPos == "L1"){
            selectPos(l1);
//            l1.setBackgroundColor(Color.GREEN);
//            H.setX(l1.getX()+140);
//            H.setY(l1.getY());
//            C.setX(H.getX() + 85);
//            C.setY(H.getY());
//            Null.setX(C.getX() + 85);
//            Null.setY(C.getY());
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }
        if(MainActivity.egPos == "R1"){
            selectPos(r1);
//            r1.setBackgroundColor(Color.GREEN);
//            H.setX(r1.getX()+140);
//            H.setY(r1.getY());
//            C.setX(H.getX() + 85);
//            C.setY(H.getY());
//            Null.setX(C.getX() + 85);
//            Null.setY(C.getY());
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }
        if(MainActivity.egPos == "L2"){
            selectPos(l2);
//            l2.setBackgroundColor(Color.GREEN);
//            H.setX(l1.getX()+140);
//            H.setY(l1.getY());
//            C.setX(H.getX() + 85);
//            C.setY(H.getY());
//            Null.setX(C.getX() + 85);
//            Null.setY(C.getY());
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }
        if(MainActivity.egPos == "R2"){
            selectPos(r2);
//            r2.setBackgroundColor(Color.GREEN);
//            H.setX(r1.getX()+140);
//            H.setY(r1.getY());
//            C.setX(H.getX() + 85);
//            C.setY(H.getY());
//            Null.setX(C.getX() + 85);
//            Null.setY(C.getY());
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }
        if(MainActivity.egPos == "M1") {
            selectPos(m1);
//            m1.setBackgroundColor(Color.GREEN);
//            H.setX(m1.getX()+140);
//            H.setY(m1.getY());
//            C.setX(H.getX() + 85);
//            C.setY(H.getY());
//            Null.setX(C.getX() + 85);
//            Null.setY(C.getY());
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void send (View v){
        // ready to put all the data

//        File directory = getExternalFilesDir(null);
//        Log.d("sendScoutReports", "Directory is " + directory.getAbsolutePath());
//        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
//                Settings.Secure.ANDROID_ID);
//
//        String filename = "ScoutingData_" + android_id + ".txt";
//
//        try {
//            File entry = new File(directory, filename);
//            if(!entry.exists()){
//                entry.createNewFile();
//                Toast.makeText(getApplicationContext(), "created file", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "file exists", Toast.LENGTH_LONG).show();
//            }
//            JSONObject item = new JSONObject();
//            //Toast.makeText(getApplicationContext(), "Created JSON", Toast.LENGTH_LONG).show();
//
//            item.put("Team Number", Integer.valueOf(MainActivity.myTeamNumber));
//            item.put("Match Number", Integer.valueOf(MainActivity.myMatchNumber));
//            item.put("Scouter", MainActivity.myScouterName);
//            item.put("Alliance", MainActivity.alliance);
//            item.put("Crossed auto line?", (MainActivity.ifCrossedAutoLine));
//            item.put("Starting position", (MainActivity.startingPos));
//            item.put("Cubes in switch autonomous", (MainActivity.cubesInSwitch));
//            item.put("Cubes in scale autonomous", (MainActivity.cubesInScale));
//            item.put("Can stack in scale teleop?", (MainActivity.stackCubesInScaleTO));
//            item.put("Cubes in scale teleop", (MainActivity.numCubesInScale));
//            item.put("Cubes in switch teleop", (MainActivity.numCubesInswitch));
//            item.put("Cubes in exchange teleop", (MainActivity.numCubesInExchange));
//            item.put("Penalties", (MainActivity.numPenalties));
//            item.put("Carried", MainActivity.carried);
//            item.put("Itself", MainActivity.itself);
//            item.put("Robots carried", (MainActivity.numCarried));
//            item.put("Driving", MainActivity.driving);
//            item.put("Cubing", MainActivity.cubing);
//            item.put("Defense", MainActivity.defense);
//            item.put("Notes", myNotes.getText().toString());
//
//
//            FileOutputStream fos = new FileOutputStream(entry, true);
////                fos.write(("Team#: " + MainActivity.myTeamNumber + "\n").getBytes());
////                fos.write(("Match#: " + MainActivity.myMatchNumber + "\n").getBytes());
////                fos.write(("Scouter: " + MainActivity.myScouterName + "\n").getBytes());
////                fos.write(("Alliance: " + MainActivity.alliance + "\n").getBytes());
////                fos.write(("CrossedAutoline: " + String.valueOf(MainActivity.ifCrossedAutoLine) + "\n").getBytes());
////                fos.write(("StartingPosition: " + String.valueOf(MainActivity.startingPos) + "\n").getBytes());
////                fos.write(("CubesInSwitchAutonomous: " + String.valueOf(MainActivity.cubesInSwitch) + "\n").getBytes());
////                fos.write(("CubesInScaleAutonomous: " + String.valueOf(MainActivity.cubesInScale) + "\n").getBytes());
////                fos.write(("CanStackInScaleTeleop?: " + String.valueOf(MainActivity.stackCubesInScaleTO) + "\n").getBytes());
////                fos.write(("CubesInScaleTeleop: " + String.valueOf(MainActivity.numCubesInScale) + "\n").getBytes());
////                fos.write(("CubesInSwitchTeleop: " + String.valueOf(MainActivity.numCubesInswitch) + "\n").getBytes());
////                fos.write(("CubesInExchangeTeleop: " + String.valueOf(MainActivity.numCubesInExchange) + "\n").getBytes());
////                fos.write(("Penalties: " + String.valueOf(MainActivity.numPenalties) + "\n").getBytes());
////                fos.write(("Levitate: " + MainActivity.goingUp + "\n").getBytes());
////                fos.write(("RobotsCarried: " + String.valueOf(MainActivity.numCarried) + "\n").getBytes());
////                fos.write(("Driving: " + MainActivity.driving + "\n").getBytes());
////                fos.write(("Cubing: " + MainActivity.cubing + "\n").getBytes());
////                fos.write(("Defense: " + MainActivity.defense + "\n").getBytes());
////                fos.write(("Notes: " + myNotes.getText().toString() + "\n").getBytes());
//            fos.write(item.toString(4).getBytes());
//            fos.write(",\n".getBytes());
//            fos.close();
//
//            Toast.makeText(getApplicationContext(), "Thanks for scouting match number " + MainActivity.myMatchNumber + "!", Toast.LENGTH_LONG).show();
//            Intent returnHome = new Intent();
//            returnHome.setClass(getApplicationContext(), MainActivity.class);
//            returnHome.putExtra("path", directory.getAbsolutePath());
//            startActivity(returnHome);
//        } catch(Exception e) {
//            Toast.makeText(getApplicationContext(), "try again", Toast.LENGTH_LONG).show();
//        }

//            File scout = new File("/Scouting/tests/myScout.txt");
//            scout.createNewFile();
//            FileOutputStream fos = new FileOutputStream(scout);
//            fos.write("HelloWorld".getBytes());
//            fos.close();
    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        TextView moves = findViewById(R.id.moves);

        moves.setText(moves.getText().toString() + b.getText().toString() + " ");
        if(b.getText().toString() == "C"){
            MainActivity.CargoshipScoredEG[0]++;
        }
        if(b.getText().toString() == "H"){
            MainActivity.CargoshipScoredEG[1]++;
        }
    }

    public void undo (View v){
        TextView moves = findViewById(R.id.moves);

        while(true){
            if(moves.getText().toString().charAt(moves.getText().toString().length()-2) == ':'){
                break;
            }
            String str = moves.getText().toString();
            str = str.substring(0, str.length() - 1);
            moves.setText(str);

            int x = moves.getText().toString().length()-1;

            if(moves.getText().toString().charAt(x) == ' ') {
                break;
            }
        }
    }

    public void selectPos(View v){
        Button l1 = findViewById(R.id.l1);
        Button l2 = findViewById(R.id.l2);
        Button r1 = findViewById(R.id.r1);
        Button r2 = findViewById(R.id.r2);
        Button m1 = findViewById(R.id.m1);
        v.setBackgroundColor(Color.GREEN);


        Button b = (Button) v;
        MainActivity.egPos = b.getText().toString();

        if(v.getId() == R.id.l1){
            l2.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);

        }
        if(v.getId() == R.id.l2){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);

        }
        if(v.getId() == R.id.r2){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);


        }
        if(v.getId() == R.id.r1){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);

        }
        if(v.getId() == R.id.m1){
            l1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);

        }
    }

    @SuppressLint("ResourceType")
    public void add (View v) {
        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;
        if ((b.getId() % 20) == 0) {
            moves.setText(moves.getText().toString() + "H3RR ");
            MainActivity.rocketScoredEG[0]++;
        }
        if ((b.getId() % 20) == 1) {
            moves.setText(moves.getText().toString() + "C1L ");
            MainActivity.rocketScoredEG[1]++;
        }
        if ((b.getId() % 20) == 2) {
            moves.setText(moves.getText().toString() + "C1R ");
            MainActivity.rocketScoredEG[2]++;
        }
        if ((b.getId() % 20) == 3) {
            moves.setText(moves.getText().toString() + "C2L ");
            MainActivity.rocketScoredEG[3]++;
        }
        if ((b.getId() % 20) == 4) {
            moves.setText(moves.getText().toString() + "C2R ");
            MainActivity.rocketScoredEG[4]++;
        }
        if ((b.getId() % 20) == 5) {
            moves.setText(moves.getText().toString() + "C3L ");
            MainActivity.rocketScoredEG[5]++;
        }
        if ((b.getId() % 20) == 6) {
            moves.setText(moves.getText().toString() + "C3R ");
            MainActivity.rocketScoredEG[6]++;
        }
//        if((b.getId()%20) == 7){
//            moves.setText(moves.getText().toString() + "C1L ");
//        }
//        if((b.getId()%20) == 8){
//            moves.setText(moves.getText().toString() + "C1L ");
//        }
        if ((b.getId() % 20) == 9) {
            moves.setText(moves.getText().toString() + "H1LL ");
            MainActivity.rocketScoredEG[9]++;
        }
        if ((b.getId() % 20) == 10) {
            moves.setText(moves.getText().toString() + "H1LR ");
            MainActivity.rocketScoredEG[10]++;
        }
        if ((b.getId() % 20) == 11) {
            moves.setText(moves.getText().toString() + "H1RL ");
            MainActivity.rocketScoredEG[11]++;
        }
        if ((b.getId() % 20) == 12) {
            moves.setText(moves.getText().toString() + "H1RR ");
            MainActivity.rocketScoredEG[12]++;
        }
        if ((b.getId() % 20) == 13) {
            moves.setText(moves.getText().toString() + "H2LL ");
            MainActivity.rocketScoredEG[13]++;
        }
        if ((b.getId() % 20) == 14) {
            moves.setText(moves.getText().toString() + "H2LR ");
            MainActivity.rocketScoredEG[14]++;
        }
        if ((b.getId() % 20) == 15) {
            moves.setText(moves.getText().toString() + "H2RL ");
            MainActivity.rocketScoredEG[15]++;
        }
        if ((b.getId() % 20) == 16) {
            moves.setText(moves.getText().toString() + "H2RR ");
            MainActivity.rocketScoredEG[16]++;
        }
        if ((b.getId() % 20) == 17) {
            moves.setText(moves.getText().toString() + "H3LL ");
            MainActivity.rocketScoredEG[17]++;
        }
        if ((b.getId() % 20) == 18) {
            moves.setText(moves.getText().toString() + "H3LR ");
            MainActivity.rocketScoredEG[18]++;
        }
        if ((b.getId() % 20) == 19) {
            moves.setText(moves.getText().toString() + "H3RL ");
            MainActivity.rocketScoredEG[19]++;
        }

    }
}
