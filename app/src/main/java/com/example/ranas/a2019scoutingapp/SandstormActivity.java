package com.example.ranas.a2019scoutingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.ranas.a2019scoutingapp.Vars.stackCSMovesSS;
import static com.example.ranas.a2019scoutingapp.Vars.stackMovesSS;

public class SandstormActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            TextView moves = findViewById(R.id.moves);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Vars.robotMovesSS = moves.getText().toString();
                    Intent SS = new Intent();
                    SS.setClass(getApplicationContext(), SandstormActivity.class);
                    startActivity(SS);
                    return true;
                case R.id.navigation_dashboard:
                    Vars.robotMovesSS = moves.getText().toString();
                    Intent TO = new Intent();
                    TO.setClass(getApplicationContext(), TeleOpActivity.class);
                    startActivity(TO);
                    return true;
//                case R.id.navigation_notifications:
//                    Vars.robotMovesSS = moves.getText().toString();
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
        check();

//        Button H = findViewById(R.id.H);
//        Button C = findViewById(R.id.C);
//        Button Null = findViewById(R.id.Null);
        final TextView moves = findViewById(R.id.moves);
        Button l1 = findViewById(R.id.l1);
        Button l2 = findViewById(R.id.l2);
        Button r1 = findViewById(R.id.r1);
        Button r2 = findViewById(R.id.r2);
        Button m1 = findViewById(R.id.m1);
        TextView P = findViewById(R.id.P);
        Button b = findViewById(R.id.button3);

        ImageView i = findViewById(R.id.imageView);

        if (Vars.alliance.equals("red")) {
            if (Vars.counter % 2 == 1) {
                i.setImageResource(R.drawable.fieldredbetterflipped);
            } else {
                i.setImageResource(R.drawable.fieldredbetter);
            }
        } else {
            if (Vars.counter % 2 == 1) {
                i.setImageResource(R.drawable.fieldbluebetterflipped);
            } else {
                i.setImageResource(R.drawable.fieldbluebetter);
            }
        }

        if (Vars.counter % 2 == 1){
            Vars.counter--;
            flip(b);
        }


        P.setText(Integer.toString(Vars.penalties));
        moves.setText(Vars.robotMovesSS);
//        H.setVisibility(View.INVISIBLE);
//        C.setVisibility(View.INVISIBLE);
//        Null.setVisibility(View.INVISIBLE);

        Log.d("Stuff", Vars.ssPos);

        if(Vars.ssPos.equals("L1")){
            selectPos(l1);
        }
        if(Vars.ssPos.equals("R1")){
            selectPos(r1);
        }
        if(Vars.ssPos.equals("L2")){
            selectPos(l2);
        }
        if(Vars.ssPos.equals("R2")){
            selectPos(r2);
        }
        if(Vars.ssPos.equals("M1")){
            selectPos(m1);
        }
//        if(Vars.startedWithSS == "Cargo"){
//            C.setBackgroundColor(Color.GREEN);
//        }else if(Vars.startedWithSS == "Hatch"){
//            H.setBackgroundColor((Color.GREEN));
//        } else if (Vars.startedWithSS == "NaN"){
//            Null.setBackgroundColor(Color.GREEN);
//        }



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void side (View v) {
        Button b = (Button) v;
        if(Vars.exitHab == 0)
            exit(findViewById(R.id.button3));

        if (((ColorDrawable) b.getBackground()).getColor() != Color.DKGRAY){
            b.setBackgroundColor(Color.DKGRAY);
            Vars.slots += b.getText() + ", ";
            Vars.stackCSMovesSS.add(b.getId());
            Vars.universal.add("s" + Integer.toString(b.getId()));
        }
    }

    public void penalty(View v){
        Button b = (Button) v;
        if(b.getId() == R.id.p1SS)
            Vars.penalties++;
        else
            if(Vars.penalties > 0)
                Vars.penalties--;

        TextView P = findViewById(R.id.P);
        P.setText(Integer.toString(Vars.penalties));
    }

    public void check(){
        //S1, S3, s3, S7, s9,
        for(int x = 0; x <= Vars.slots.length()-3; x += 4){
            String s = Vars.slots.substring(x, x+2);
            if(s.equals("S1")){
                findViewById(R.id.R1).setBackgroundColor(Color.DKGRAY);
            } else if(s.equals("S2")){
                findViewById(R.id.R2).setBackgroundColor(Color.DKGRAY);
            } else if(s.equals("S3")){
                findViewById(R.id.R1).setBackgroundColor(Color.DKGRAY);
            }
//            else if(s.equals("S4")){
//                findViewById(R.id.R4).setBackgroundColor(Color.DKGRAY);
//            } else if(s.equals("S5")){
//                findViewById(R.id.R5).setBackgroundColor(Color.DKGRAY);
//            }
        }

        if(Vars.exitHab == 0){
            findViewById(R.id.button3).setBackgroundColor(Color.parseColor("#ff33b5e5"));
        } else {
            findViewById(R.id.button3).setBackgroundColor(Color.GREEN);
        }

//        if(Vars.groundC == 1){
//            findViewById(R.id.groundC).setBackgroundColor(Color.DKGRAY);
//            //findViewById(R.id.groundH).setBackgroundColor(Color.parseColor("#ffffbb33"));
//        }
//        if(Vars.groundH == 1){
//            findViewById(R.id.groundH).setBackgroundColor(Color.DKGRAY);
//            //findViewById(R.id.groundC).setBackgroundColor(Color.parseColor("#ffff8800"));
//        }

        if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] == 8){
            findViewById(R.id.CSC).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.CSC).setBackgroundColor(Color.parseColor("#ffff8800"));
        }
        if(Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1] == 8){
            findViewById(R.id.CSH).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.CSH).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[0] + Vars.rocketScoredSS[0]== 1) {
            findViewById(R.id.SS_H3RR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H3RR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }
        if(Vars.rocketScoredTO[1] + Vars.rocketScoredSS[1] == 2) {
            findViewById(R.id.SS_C1L).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[1] + Vars.rocketScoredSS[1] == 0) {
            findViewById(R.id.SS_C1L).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else if(Vars.rocketScoredTO[1] + Vars.rocketScoredSS[1] == 1) {
            findViewById(R.id.SS_C1L).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[2] + Vars.rocketScoredSS[2] == 2) {
            findViewById(R.id.SS_C1R).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[2] + Vars.rocketScoredSS[2] == 0) {
            findViewById(R.id.SS_C1R).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else if(Vars.rocketScoredTO[2] + Vars.rocketScoredSS[2] == 1) {
            findViewById(R.id.SS_C1R).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[3] + Vars.rocketScoredSS[3] == 2) {
            findViewById(R.id.SS_C2L).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[3] + Vars.rocketScoredSS[3] == 0) {
            findViewById(R.id.SS_C2L).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else if(Vars.rocketScoredTO[3] + Vars.rocketScoredSS[3] == 1) {
            findViewById(R.id.SS_C2L).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[4] + Vars.rocketScoredSS[4] == 2) {
            findViewById(R.id.SS_C2R).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[4] + Vars.rocketScoredSS[4] == 0) {
            findViewById(R.id.SS_C2R).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else if(Vars.rocketScoredTO[4] + Vars.rocketScoredSS[4] == 1) {
            findViewById(R.id.SS_C2R).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[5] + Vars.rocketScoredSS[5] == 2) {
            findViewById(R.id.SS_C3L).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[5] + Vars.rocketScoredSS[5] == 0) {
            findViewById(R.id.SS_C3L).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else if(Vars.rocketScoredTO[5] + Vars.rocketScoredSS[5] == 1) {
            findViewById(R.id.SS_C3L).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[6] + Vars.rocketScoredSS[6] == 2) {
            findViewById(R.id.SS_C3R).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[6] + Vars.rocketScoredSS[6] == 0) {
            findViewById(R.id.SS_C3R).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else if(Vars.rocketScoredTO[6] + Vars.rocketScoredSS[6] == 1) {
            findViewById(R.id.SS_C3R).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[9] + Vars.rocketScoredSS[9] == 1) {
            findViewById(R.id.SS_H1LL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H1LL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[10] + Vars.rocketScoredSS[10] == 1) {
            findViewById(R.id.SS_H1LR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H1LR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[11] + Vars.rocketScoredSS[11] == 1) {
            findViewById(R.id.SS_H1RL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H1RL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[12] + Vars.rocketScoredSS[12] == 1) {
            findViewById(R.id.SS_H1RR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H1RR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[13] + Vars.rocketScoredSS[13] == 1) {
            findViewById(R.id.SS_H2LL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H2LL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[14] + Vars.rocketScoredSS[14] == 1) {
            findViewById(R.id.SS_H2LR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H2LR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[15] + Vars.rocketScoredSS[15] == 1) {
            findViewById(R.id.SS_H2RL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H2RL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[16] + Vars.rocketScoredSS[16] == 1) {
            findViewById(R.id.SS_H2RR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H2RR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[17] + Vars.rocketScoredSS[17] == 1) {
            findViewById(R.id.SS_H3LL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H3LL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[18] + Vars.rocketScoredSS[18] == 1) {
            findViewById(R.id.SS_H3LR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H3LR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[19] + Vars.rocketScoredSS[19] == 1) {
            findViewById(R.id.SS_H3RL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H3RL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

    }

    @SuppressLint("ResourceType")
    public void add (View v){
//        Toast.makeText(getApplicationContext(), "Blah", Toast.LENGTH_SHORT).show();

        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;
        if(Vars.exitHab == 0)
            exit(findViewById(R.id.button3));
        switch (b.getId()) {
            case R.id.SS_H3RR:
                if ((Vars.rocketScoredSS[0] + Vars.rocketScoredTO[0]) < 1){
                    moves.setText(moves.getText().toString() + "H3RR ");
                    Vars.rocketScoredSS[0]++;
                    Vars.stackMovesSS.add(0);
                    Vars.universal.add("r" + Integer.toString(0));
                }
                b.setBackgroundColor(Color.DKGRAY);

                break;
            case R.id.SS_C1L:
                if ((Vars.rocketScoredSS[1] + Vars.rocketScoredTO[1]) < 2) {
                    moves.setText(moves.getText().toString() + "C1L ");
                    Vars.rocketScoredSS[1]++;
                    Vars.stackMovesSS.add(1);
                    Vars.universal.add("r" + Integer.toString(1));
                }
                if((Vars.rocketScoredSS[1] + Vars.rocketScoredTO[1]) == 2){
                    b.setBackgroundColor(Color.DKGRAY);
                } else {
                    b.setBackgroundColor(Color.GRAY);
                }
                break;
            case R.id.SS_C1R:
                if ((Vars.rocketScoredSS[2] + Vars.rocketScoredTO[2]) < 2){
                    moves.setText(moves.getText().toString() + "C1R ");
                    Vars.rocketScoredSS[2]++;
                    Vars.universal.add("r" + Integer.toString(2));
                    stackMovesSS.add(2);
                }
                if((Vars.rocketScoredSS[2] + Vars.rocketScoredTO[2]) == 2){
                    b.setBackgroundColor(Color.DKGRAY);
                } else {
                    b.setBackgroundColor(Color.GRAY);
                }
                break;
            case R.id.SS_C2L:
                if ((Vars.rocketScoredSS[3] + Vars.rocketScoredTO[3]) < 2) {
                    moves.setText(moves.getText().toString() + "C2L ");
                    Vars.rocketScoredSS[3]++;
                    Vars.universal.add("r" + Integer.toString(3));
                    stackMovesSS.add(3);
                }
                if((Vars.rocketScoredSS[3] + Vars.rocketScoredTO[3]) == 2){
                    b.setBackgroundColor(Color.DKGRAY);
                } else {
                    b.setBackgroundColor(Color.GRAY);
                }
                break;
            case R.id.SS_C2R:
                if ((Vars.rocketScoredSS[4] + Vars.rocketScoredTO[4]) < 2){
                    moves.setText(moves.getText().toString() + "C2R ");
                    Vars.rocketScoredSS[4]++;
                    Vars.universal.add("r" + Integer.toString(4));
                    stackMovesSS.add(4);
                }
                if((Vars.rocketScoredSS[4] + Vars.rocketScoredTO[4]) == 2){
                    b.setBackgroundColor(Color.DKGRAY);
                } else {
                    b.setBackgroundColor(Color.GRAY);
                }
                break;
            case R.id.SS_C3L:
                if ((Vars.rocketScoredSS[5] + Vars.rocketScoredTO[5]) < 2){
                    moves.setText(moves.getText().toString() + "C3L ");
                    Vars.rocketScoredSS[5]++;
                    Vars.universal.add("r" + Integer.toString(5));
                    stackMovesSS.add(5);
                }
                if((Vars.rocketScoredSS[5] + Vars.rocketScoredTO[5]) == 2){
                    b.setBackgroundColor(Color.DKGRAY);
                } else {
                    b.setBackgroundColor(Color.GRAY);
                }
                break;
            case R.id.SS_C3R:
                if ((Vars.rocketScoredSS[6] + Vars.rocketScoredTO[6]) < 2){
                    moves.setText(moves.getText().toString() + "C3R ");
                    Vars.rocketScoredSS[6]++;
                    Vars.universal.add("r" + Integer.toString(6));
                    stackMovesSS.add(6);
                }
                if((Vars.rocketScoredSS[6] + Vars.rocketScoredTO[6]) == 2){
                    b.setBackgroundColor(Color.DKGRAY);
                } else {
                    b.setBackgroundColor(Color.GRAY);
                }
                break;
            case R.id.SS_H1LL:
                if ((Vars.rocketScoredSS[9] + Vars.rocketScoredTO[9]) < 1){
                    moves.setText(moves.getText().toString() + "H1LL ");
                    Vars.rocketScoredSS[9]++;
                    Vars.universal.add("r" + Integer.toString(9));
                    stackMovesSS.add(9);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H1LR:
                if ((Vars.rocketScoredSS[10] + Vars.rocketScoredTO[10]) < 1){
                    moves.setText(moves.getText().toString() + "H1LR ");
                    Vars.rocketScoredSS[10]++;
                    Vars.universal.add("r" + Integer.toString(10));
                    stackMovesSS.add(10);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H1RL:
                if ((Vars.rocketScoredSS[11] + Vars.rocketScoredTO[11]) < 1){
                    moves.setText(moves.getText().toString() + "H1RL ");
                    Vars.rocketScoredSS[11]++;
                    Vars.universal.add("r" + Integer.toString(11));
                    stackMovesSS.add(11);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H1RR:
                if ((Vars.rocketScoredSS[12] + Vars.rocketScoredTO[12]) < 1){
                    moves.setText(moves.getText().toString() + "H1RR ");
                    Vars.rocketScoredSS[12]++;
                    Vars.universal.add("r" + Integer.toString(12));
                    stackMovesSS.add(12);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2LL:
                if ((Vars.rocketScoredSS[13] + Vars.rocketScoredTO[13]) < 1){
                    moves.setText(moves.getText().toString() + "H2LL ");
                    Vars.rocketScoredSS[13]++;
                    Vars.universal.add("r" + Integer.toString(13));
                    stackMovesSS.add(13);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2LR:
                if ((Vars.rocketScoredSS[14] + Vars.rocketScoredTO[14]) < 1){
                    moves.setText(moves.getText().toString() + "H2LR ");
                    Vars.rocketScoredSS[14]++;
                    Vars.universal.add("r" + Integer.toString(14));
                    stackMovesSS.add(14);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2RL:
                if ((Vars.rocketScoredSS[15] + Vars.rocketScoredTO[15]) < 1){
                    moves.setText(moves.getText().toString() + "H2RL ");
                    Vars.rocketScoredSS[15]++;
                    Vars.universal.add("r" + Integer.toString(15));
                    stackMovesSS.add(15);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2RR:
                if ((Vars.rocketScoredSS[16] + Vars.rocketScoredTO[16]) < 1){
                    moves.setText(moves.getText().toString() + "H2RR ");
                    Vars.rocketScoredSS[16]++;
                    Vars.universal.add("r" + Integer.toString(16));
                    stackMovesSS.add(16);

                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H3LL:
                if ((Vars.rocketScoredSS[17] + Vars.rocketScoredTO[17]) < 1){
                    moves.setText(moves.getText().toString() + "H3LL ");
                    Vars.rocketScoredSS[17]++;
                    Vars.universal.add("r" + Integer.toString(17));
                    stackMovesSS.add(17);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H3LR:
                if ((Vars.rocketScoredSS[18] + Vars.rocketScoredTO[18]) < 1){
                    moves.setText(moves.getText().toString() + "H3LR ");
                    Vars.rocketScoredSS[18]++;
                    Vars.universal.add("r" + Integer.toString(18));
                    stackMovesSS.add(18);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H3RL:
                if ((Vars.rocketScoredSS[19] + Vars.rocketScoredTO[19]) < 1){
                    moves.setText(moves.getText().toString() + "H3RL ");
                    Vars.rocketScoredSS[19]++;
                    Vars.universal.add("r" + Integer.toString(19));
                    stackMovesSS.add(19);
                }
                b.setBackgroundColor(Color.DKGRAY);
                break;
        }

        //moves.setText(moves.getText().toString() + Integer.toString((b.getId()%20)) + " ");
    }

    public void exit (View v){
        if(Vars.exitHab == 0){
            findViewById(R.id.button3).setBackgroundColor(Color.GREEN);
            Vars.exitHab = 1;
        } else {
            findViewById(R.id.button3).setBackgroundColor(Color.parseColor("#ff33b5e5"));
            Vars.exitHab = 0;
        }
    }

//    public void loaded (View v){
//        Button b = (Button) v;
//        Button H = findViewById(R.id.H);
//        Button C = findViewById(R.id.C);
//        Button Null = findViewById(R.id.Null);
//        if(b.getId() == R.id.C){
//            b.setBackgroundColor(Color.GREEN);
//            H.setBackgroundColor(Color.rgb(255,187,51));
//            Null.setBackgroundColor(Color.rgb(192,192,192));
//            Vars.startedWithSS = "Cargo";
//        }
//        if(b.getId() == R.id.H){
//            b.setBackgroundColor(Color.GREEN);
//            C.setBackgroundColor(Color.rgb(255,136,0));
//            Null.setBackgroundColor(Color.rgb(192,192,192));
//            Vars.startedWithSS = "Hatch";
//        }
//        if(b.getId() == R.id.Null){
//            b.setBackgroundColor(Color.GREEN);
//            H.setBackgroundColor(Color.rgb(255,187,51));
//            C.setBackgroundColor(Color.rgb(255,136,0));
//            Vars.startedWithSS = "NaN";
//        }
//    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        Button CSC = findViewById(R.id.CSC);
        Button CSH = findViewById(R.id.CSH);
        TextView moves = findViewById(R.id.moves);

        if(Vars.exitHab == 0)
            exit(findViewById(R.id.button3));

        if(b.getText().toString().equals("C")){
            if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] < 8) {
                Vars.CargoshipScoredSS[0]++;
                stackCSMovesSS.add(0);
                Vars.universal.add("c" + Integer.toString(0));
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
            }
        }
        if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] ==8){
            CSC.setBackgroundColor(Color.DKGRAY);
        }
        if(b.getText().toString().equals("H")){
            if(Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1] < 8) {
                Vars.CargoshipScoredSS[1]++;
                stackCSMovesSS.add(1);
                Vars.universal.add("c" + Integer.toString(1));
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
            }
        }
        if(Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1] == 8){
            CSH.setBackgroundColor(Color.DKGRAY);
        }
    }


    public void undo (View v){
        TextView moves = findViewById(R.id.moves);
        if(/**/!Vars.universal.empty()/**/ /*!Vars.stackMovesSS.empty()*/) {
            String x = Vars.universal.pop();
            Log.d("errors!?", "step 1");
            if(x.charAt(0) == 'r'){
                Vars.rocketScoredSS[Integer.valueOf(x.substring(1))]--;
                Log.d("errors!?", "step 2");
            }
            else if(x.charAt(0) == 'c'){
                int y = Integer.valueOf(x.substring(1));
                Vars.CargoshipScoredSS[y]--;
                Log.d("errors!?", "step 3");
            } else {
                Vars.slots = Vars.slots.substring(0, Vars.slots.length()-4);
                Log.d("errors!?", "step 4");
                String y = x.substring(1);
                int z = Integer.valueOf(y);
                Button b = findViewById(z);
                b.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                Log.d("errors!?", "step 5");
                return;
            }

            check();
        } else {
            return;
        }

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
        Vars.ssPos = b.getText().toString();

        if(v.getId() == R.id.l1){
            l2.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.l2){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.r2){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);

        }
        if(v.getId() == R.id.r1){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.m1){
            l1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
//            H.setVisibility(View.VISIBLE);
//            C.setVisibility(View.VISIBLE);
//            Null.setVisibility(View.VISIBLE);
        }
    }

    public void flip(View v){
        findViewById(R.id.button3).post(new Runnable() {
            @Override
            public void run() {
                Vars.counter++;
                ImageView i = findViewById(R.id.imageView);

                if(Vars.alliance.equals("red")){
                    if(Vars.counter%2 == 1){
                        i.setImageResource(R.drawable.fieldredbetterflipped);
                    } else {
                        i.setImageResource(R.drawable.fieldredbetter);
                    }
                } else {
                    if(Vars.counter%2 == 1){
                        i.setImageResource(R.drawable.fieldbluebetterflipped);
                    } else {
                        i.setImageResource(R.drawable.fieldbluebetter);
                    }
                }

                findViewById(R.id.l2).setX(1090-findViewById(R.id.l2).getX()-30);
                findViewById(R.id.l1).setX(1090-findViewById(R.id.l1).getX());
                findViewById(R.id.r2).setX(1090-findViewById(R.id.r2).getX()-30);
                findViewById(R.id.r1).setX(1090-findViewById(R.id.r1).getX());
                findViewById(R.id.m1).setX(1090-findViewById(R.id.m1).getX());

                findViewById(R.id.R1).setX(1090-findViewById(R.id.R1).getX());
                findViewById(R.id.R2).setX(1090-findViewById(R.id.R2).getX());
                findViewById(R.id.R1).setX(1090-findViewById(R.id.R1).getX());

                findViewById(R.id.CSC).setX(1090-findViewById(R.id.CSC).getX());
                findViewById(R.id.CSH).setX(1090-findViewById(R.id.CSH).getX());

                findViewById(R.id.SS_C3R).setX(1090-findViewById(R.id.SS_C3R).getX());
                findViewById(R.id.SS_C2R).setX(1090-findViewById(R.id.SS_C2R).getX());
                findViewById(R.id.SS_C1R).setX(1090-findViewById(R.id.SS_C1R).getX());
                findViewById(R.id.SS_C3L).setX(1090-findViewById(R.id.SS_C3L).getX());
                findViewById(R.id.SS_C2L).setX(1090-findViewById(R.id.SS_C2L).getX());
                findViewById(R.id.SS_C1L).setX(1090-findViewById(R.id.SS_C1L).getX());

                findViewById(R.id.SS_H3RL).setX(1090-findViewById(R.id.SS_H3RL).getX());
                findViewById(R.id.SS_H3RR).setX(1090-findViewById(R.id.SS_H3RR).getX());
                findViewById(R.id.SS_H2RR).setX(1090-findViewById(R.id.SS_H2RR).getX());
                findViewById(R.id.SS_H1RR).setX(1090-findViewById(R.id.SS_H1RR).getX());
                findViewById(R.id.SS_H2RL).setX(1090-findViewById(R.id.SS_H2RL).getX());
                findViewById(R.id.SS_H1RL).setX(1090-findViewById(R.id.SS_H1RL).getX());
                findViewById(R.id.SS_H3LL).setX(1090-findViewById(R.id.SS_H3LL).getX());
                findViewById(R.id.SS_H3LR).setX(1090-findViewById(R.id.SS_H3LR).getX());
                findViewById(R.id.SS_H2LR).setX(1090-findViewById(R.id.SS_H2LR).getX());
                findViewById(R.id.SS_H2LL).setX(1090-findViewById(R.id.SS_H2LL).getX());
                findViewById(R.id.SS_H1LL).setX(1090-findViewById(R.id.SS_H1LL).getX());
                findViewById(R.id.SS_H1LR).setX(1090-findViewById(R.id.SS_H1LR).getX());

                findViewById(R.id.undo).setX(1090-findViewById(R.id.undo).getX()-170);
                findViewById(R.id.button3).setX(1090-findViewById(R.id.button3).getX()-170);
            }
        });
    }

}
