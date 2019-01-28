package com.example.ranas.a2019scoutingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SandstormActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            TextView moves = findViewById(R.id.moves);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    MainActivity.robotMovesSS = moves.getText().toString();
                    Intent SS = new Intent();
                    SS.setClass(getApplicationContext(), SandstormActivity.class);
                    startActivity(SS);
                    return true;
                case R.id.navigation_dashboard:
                    MainActivity.robotMovesSS = moves.getText().toString();
                    Intent TO = new Intent();
                    TO.setClass(getApplicationContext(), TeleOpActivity.class);
                    startActivity(TO);
                    return true;
//                case R.id.navigation_notifications:
//                    MainActivity.robotMovesSS = moves.getText().toString();
//                    Intent EG = new Intent();
//                    EG.setClass(getApplicationContext(), EndgameActivity.class);
//                    startActivity(EG);
//                    return true;
                case R.id.navigation_header_container:
                    Intent N = new Intent();
                    N.setClass(getApplicationContext(), NotesActivity.class);
                    startActivity(N);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandstorm);

        Button H = findViewById(R.id.H);
        Button C = findViewById(R.id.C);
        Button Null = findViewById(R.id.Null);
        TextView moves = findViewById(R.id.moves);
        Button l1 = findViewById(R.id.l1);
        Button l2 = findViewById(R.id.l2);
        Button r1 = findViewById(R.id.r1);
        Button r2 = findViewById(R.id.r2);
        Button m1 = findViewById(R.id.m1);
        TextView P = findViewById(R.id.P);

        P.setText(Integer.toString(MainActivity.penaltiesSS + MainActivity.penaltiesTO));
        moves.setText(MainActivity.robotMovesSS);
        H.setVisibility(View.INVISIBLE);
        C.setVisibility(View.INVISIBLE);
        Null.setVisibility(View.INVISIBLE);

        Log.d("Stuff", MainActivity.ssPos);

        if(MainActivity.ssPos.equals("L1")){
            selectPos(l1);
        }
        if(MainActivity.ssPos.equals("R1")){
            selectPos(r1);
        }
        if(MainActivity.ssPos.equals("L2")){
            selectPos(l2);
        }
        if(MainActivity.ssPos.equals("R2")){
            selectPos(r2);
        }
        if(MainActivity.ssPos.equals("M1")){
            selectPos(m1);
        }
//        if(MainActivity.startedWithSS == "C"){
//            C.setBackgroundColor(Color.GREEN);
//        }else if(MainActivity.startedWithSS == "H"){
//            H.setBackgroundColor((Color.GREEN));
//        } else if (MainActivity.startedWithSS == "--"){
//            Null.setBackgroundColor(Color.GREEN);
//        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void penalty(View v){
        Button b = (Button) v;
        if(b.getId() == R.id.p1SS)
            MainActivity.penaltiesSS++;
        else
            if(MainActivity.penaltiesSS > 0)
                MainActivity.penaltiesSS--;

        TextView P = findViewById(R.id.P);
        P.setText(Integer.toString(MainActivity.penaltiesSS + MainActivity.penaltiesTO));
    }

    @SuppressLint("ResourceType")
    //TODO fix something?!
    public void add (View v){
        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;
        int id = b.getId();

        if(b.getId() == R.id.SS_H3RR){
            moves.setText(moves.getText().toString() + "H3RR ");
            MainActivity.rocketScoredSS[0]++;
        }
        if(b.getId() == R.id.SS_C1L){
            moves.setText(moves.getText().toString() + "C1L ");
            MainActivity.rocketScoredSS[1]++;
        }
        if(b.getId() == R.id.SS_C1R){
            moves.setText(moves.getText().toString() + "C1R ");
            MainActivity.rocketScoredSS[2]++;
        }
        if(b.getId() == R.id.SS_C2L){
            moves.setText(moves.getText().toString() + "C2L ");
            MainActivity.rocketScoredSS[3]++;
        }
        if(b.getId() == R.id.SS_C2R){
            moves.setText(moves.getText().toString() + "C2R ");
            MainActivity.rocketScoredSS[4]++;
        }
        if(b.getId() == R.id.SS_C3L){
            moves.setText(moves.getText().toString() + "C3L ");
            MainActivity.rocketScoredSS[5]++;
        }
        if(b.getId() == R.id.SS_C3R){
            moves.setText(moves.getText().toString() + "C3R ");
            MainActivity.rocketScoredSS[6]++;
        }
        if(b.getId() == R.id.SS_H1LL){
            moves.setText(moves.getText().toString() + "H1LL ");
            MainActivity.rocketScoredSS[9]++;
        }
        if(b.getId() == R.id.SS_H1LR){
            moves.setText(moves.getText().toString() + "H1LR ");
            MainActivity.rocketScoredSS[10]++;
        }
        if(b.getId() == R.id.SS_H1RL){
            moves.setText(moves.getText().toString() + "H1RL ");
            MainActivity.rocketScoredSS[11]++;
        }
        if(b.getId() == R.id.SS_H1RR){
            moves.setText(moves.getText().toString() + "H1RR ");
            MainActivity.rocketScoredSS[12]++;
        }
        if(b.getId() == R.id.SS_H2LL){
            moves.setText(moves.getText().toString() + "H2LL ");
            MainActivity.rocketScoredSS[13]++;
        }
        if(b.getId() == R.id.SS_H2LR){
            moves.setText(moves.getText().toString() + "H2LR ");
            MainActivity.rocketScoredSS[14]++;
        }
        if(b.getId() == R.id.SS_H2RL){
            moves.setText(moves.getText().toString() + "H2RL ");
            MainActivity.rocketScoredSS[15]++;
        }
        if(b.getId() == R.id.SS_H2RR){
            moves.setText(moves.getText().toString() + "H2RR ");
            MainActivity.rocketScoredSS[16]++;
        }
        if(b.getId() == R.id.SS_H3LL){
            moves.setText(moves.getText().toString() + "H3LL ");
            MainActivity.rocketScoredSS[17]++;
        }
        if(b.getId() == R.id.SS_H3LR){
            moves.setText(moves.getText().toString() + "H3LR ");
            MainActivity.rocketScoredSS[18]++;
        }
        if(b.getId() == R.id.SS_H3RL){
            moves.setText(moves.getText().toString() + "H3RL ");
            MainActivity.rocketScoredSS[19]++;
        }

        //moves.setText(moves.getText().toString() + Integer.toString((b.getId()%20)) + " ");
    }

    public void loaded (View v){
//        Button b = (Button) v;
//        Button H = findViewById(R.id.H);
//        Button C = findViewById(R.id.C);
//        Button Null = findViewById(R.id.Null);
//        if(b.getId() == R.id.C){
//            b.setBackgroundColor(Color.GREEN);
//            H.setBackgroundColor(Color.rgb(255,187,51));
//            Null.setBackgroundColor(Color.rgb(192,192,192));
//            MainActivity.startedWithSS = "C";
//        }
//        if(b.getId() == R.id.H){
//            b.setBackgroundColor(Color.GREEN);
//            C.setBackgroundColor(Color.rgb(255,136,0));
//            Null.setBackgroundColor(Color.rgb(192,192,192));
//            MainActivity.startedWithSS = "H";
//        }
//        if(b.getId() == R.id.Null){
//            b.setBackgroundColor(Color.GREEN);
//            H.setBackgroundColor(Color.rgb(255,187,51));
//            C.setBackgroundColor(Color.rgb(255,136,0));
//            MainActivity.startedWithSS = "--";
//        }
    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        TextView moves = findViewById(R.id.moves);

        moves.setText(moves.getText().toString() + b.getText().toString() + " ");
        if(b.getText().toString().equals("C")){
            MainActivity.CargoshipScoredSS[0]++;
        }
        if(b.getText().toString().equals("H")){
            MainActivity.CargoshipScoredSS[1]++;
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
//        Button H = findViewById(R.id.H);
//        Button C = findViewById(R.id.C);
//        Button Null = findViewById(R.id.Null);

        Button b = (Button) v;
        MainActivity.ssPos = b.getText().toString();

        if(v.getId() == R.id.l1){
            l2.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
//            H.setX(l1.getX()+ 140);
//            H.setY(l1.getY());
//            C.setX(H.getX() + 85);
//            C.setY(H.getY());
//            Null.setX(C.getX() + 85);
//            Null.setY(C.getY());
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.l2){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
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
        if(v.getId() == R.id.r2){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
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
        if(v.getId() == R.id.r1){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
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
        if(v.getId() == R.id.m1){
            l1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
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
    }

}
