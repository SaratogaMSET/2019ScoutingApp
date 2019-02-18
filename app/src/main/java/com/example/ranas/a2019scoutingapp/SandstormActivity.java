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
import android.widget.Toast;

import static com.example.ranas.a2019scoutingapp.Vars.groundC;
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

        if (Vars.alliance == "red") {
            //int width = getWindowManager().getDefaultDisplay().getWidth();
            ImageView image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.frc_field_red);
            //l2.setText(l2.getX() + " " + l2.getY());
//            Toast.makeText(getApplicationContext(), Integer.toString(l1.getLeft()), Toast.LENGTH_LONG).show();
//            l1.setLeft(100 - (int) l1.getLeft());
//            l2.setLeft(100 - (int) l2.getLeft());
//            r1.setLeft(100 - (int) r1.getLeft());
//            r2.setLeft(100 - (int) r2.getLeft());
//            m1.setLeft(100 - (int) m1.getLeft());

        }

        P.setText(Integer.toString(Vars.penaltiesSS + Vars.penaltiesTO));
        moves.setText(Vars.robotMovesSS);
        H.setVisibility(View.INVISIBLE);
        C.setVisibility(View.INVISIBLE);
        Null.setVisibility(View.INVISIBLE);

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
        if(Vars.startedWithSS == "Cargo"){
            C.setBackgroundColor(Color.GREEN);
        }else if(Vars.startedWithSS == "Hatch"){
            H.setBackgroundColor((Color.GREEN));
        } else if (Vars.startedWithSS == "NaN"){
            Null.setBackgroundColor(Color.GREEN);
        }

        TextView C1 = findViewById(R.id.groundCcount);
        C1.setText(Integer.toString(Vars.groundC));
        TextView H1 = findViewById(R.id.groundHcount);
        H1.setText(Integer.toString(Vars.groundH));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void side (View v) {
        Button b = (Button) v;
        if (((ColorDrawable) b.getBackground()).getColor() != Color.DKGRAY){
            b.setBackgroundColor(Color.DKGRAY);
            Vars.slots += b.getText() + ", ";
        }
    }

    public void penalty(View v){
        Button b = (Button) v;
        if(b.getId() == R.id.p1SS)
            Vars.penaltiesSS++;
        else
            if(Vars.penaltiesSS > 0)
                Vars.penaltiesSS--;

        TextView P = findViewById(R.id.P);
        P.setText(Integer.toString(Vars.penaltiesSS + Vars.penaltiesTO));
    }

    public void check(){

        if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] == 8){
            findViewById(R.id.CSC).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.CSC).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }
        if(Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1] == 8){
            findViewById(R.id.CSH).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.CSH).setBackgroundColor(Color.parseColor("#ffff8800"));
        }

        if(Vars.rocketScoredTO[0] + Vars.rocketScoredSS[0]== 1) {
            findViewById(R.id.SS_H3RR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_H3RR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }
        if(Vars.rocketScoredTO[1] + Vars.rocketScoredSS[1] == 2) {
            findViewById(R.id.SS_C1L).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_C1L).setBackgroundColor(Color.parseColor("#ffff8800"));
        }
        if(Vars.rocketScoredTO[2] + Vars.rocketScoredSS[2] == 2) {
            findViewById(R.id.SS_C1R).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_C1R).setBackgroundColor(Color.parseColor("#ffff8800"));
        }
        if(Vars.rocketScoredTO[3] + Vars.rocketScoredSS[3] == 2) {
            findViewById(R.id.SS_C2L).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_C2L).setBackgroundColor(Color.parseColor("#ffff8800"));
        }
        if(Vars.rocketScoredTO[4] + Vars.rocketScoredSS[4] == 2) {
            findViewById(R.id.SS_C2R).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_C2R).setBackgroundColor(Color.parseColor("#ffff8800"));
        }

        if(Vars.rocketScoredTO[5] + Vars.rocketScoredSS[5] == 2) {
            findViewById(R.id.SS_C3L).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_C3L).setBackgroundColor(Color.parseColor("#ffff8800"));
        }

        if(Vars.rocketScoredTO[6] + Vars.rocketScoredSS[6] == 2) {
            findViewById(R.id.SS_C3R).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.SS_C3R).setBackgroundColor(Color.parseColor("#ffff8800"));
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
        int id = b.getId();

        switch (b.getId()) {
            case R.id.SS_H3RR:
                if ((Vars.rocketScoredSS[0] + Vars.rocketScoredTO[0]) < 1){
                    moves.setText(moves.getText().toString() + "H3RR ");
                    Vars.rocketScoredSS[0]++;
                    Vars.stackMovesSS.add(0);
                    Vars.stackUsedUpSS.add(b.getId());
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_C1L:
                if ((Vars.rocketScoredSS[1] + Vars.rocketScoredTO[1]) < 2) {
                    moves.setText(moves.getText().toString() + "C1L ");
                    Vars.rocketScoredSS[1]++;
                    Vars.stackMovesSS.add(1);
                    Vars.stackUsedUpSS.add(b.getId());
                }
                if((Vars.rocketScoredSS[1] + Vars.rocketScoredTO[1]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C1R:
                if ((Vars.rocketScoredSS[2] + Vars.rocketScoredTO[2]) < 2){
                    moves.setText(moves.getText().toString() + "C1R ");
                    Vars.rocketScoredSS[2]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(2);
                }
                if((Vars.rocketScoredSS[2] + Vars.rocketScoredTO[2]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C2L:
                if ((Vars.rocketScoredSS[3] + Vars.rocketScoredTO[3]) < 2) {
                    moves.setText(moves.getText().toString() + "C2L ");
                    Vars.rocketScoredSS[3]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(3);
                }
                if((Vars.rocketScoredSS[3] + Vars.rocketScoredTO[3]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C2R:
                if ((Vars.rocketScoredSS[4] + Vars.rocketScoredTO[4]) < 2){
                    moves.setText(moves.getText().toString() + "C2R ");
                    Vars.rocketScoredSS[4]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(4);
                }
                if((Vars.rocketScoredSS[4] + Vars.rocketScoredTO[4]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C3L:
                if ((Vars.rocketScoredSS[5] + Vars.rocketScoredTO[5]) < 2){
                    moves.setText(moves.getText().toString() + "C3L ");
                    Vars.rocketScoredSS[5]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(5);
                }
                if((Vars.rocketScoredSS[5] + Vars.rocketScoredTO[5]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C3R:
                if ((Vars.rocketScoredSS[6] + Vars.rocketScoredTO[6]) < 2){
                    moves.setText(moves.getText().toString() + "C3R ");
                    Vars.rocketScoredSS[6]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(6);
                }
                if((Vars.rocketScoredSS[6] + Vars.rocketScoredTO[6]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_H1LL:
                if ((Vars.rocketScoredSS[9] + Vars.rocketScoredTO[9]) < 1){
                    moves.setText(moves.getText().toString() + "H1LL ");
                    Vars.rocketScoredSS[9]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(9);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H1LR:
                if ((Vars.rocketScoredSS[10] + Vars.rocketScoredTO[10]) < 1){
                    moves.setText(moves.getText().toString() + "H1LR ");
                    Vars.rocketScoredSS[10]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(10);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H1RL:
                if ((Vars.rocketScoredSS[11] + Vars.rocketScoredTO[11]) < 1){
                    moves.setText(moves.getText().toString() + "H1RL ");
                    Vars.rocketScoredSS[11]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(11);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H1RR:
                if ((Vars.rocketScoredSS[12] + Vars.rocketScoredTO[12]) < 1){
                    moves.setText(moves.getText().toString() + "H1RR ");
                    Vars.rocketScoredSS[12]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(12);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2LL:
                if ((Vars.rocketScoredSS[13] + Vars.rocketScoredTO[13]) < 1){
                    moves.setText(moves.getText().toString() + "H2LL ");
                    Vars.rocketScoredSS[13]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(13);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2LR:
                if ((Vars.rocketScoredSS[14] + Vars.rocketScoredTO[14]) < 1){
                    moves.setText(moves.getText().toString() + "H2LR ");
                    Vars.rocketScoredSS[14]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(14);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2RL:
                if ((Vars.rocketScoredSS[15] + Vars.rocketScoredTO[15]) < 1){
                    moves.setText(moves.getText().toString() + "H2RL ");
                    Vars.rocketScoredSS[15]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(15);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2RR:
                if ((Vars.rocketScoredSS[16] + Vars.rocketScoredTO[16]) < 1){
                    moves.setText(moves.getText().toString() + "H2RR ");
                    Vars.rocketScoredSS[16]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(16);

                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H3LL:
                if ((Vars.rocketScoredSS[17] + Vars.rocketScoredTO[17]) < 1){
                    moves.setText(moves.getText().toString() + "H3LL ");
                    Vars.rocketScoredSS[17]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(17);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H3LR:
                if ((Vars.rocketScoredSS[18] + Vars.rocketScoredTO[18]) < 1){
                    moves.setText(moves.getText().toString() + "H3LR ");
                    Vars.rocketScoredSS[18]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(18);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H3RL:
                if ((Vars.rocketScoredSS[19] + Vars.rocketScoredTO[19]) < 1){
                    moves.setText(moves.getText().toString() + "H3RL ");
                    Vars.rocketScoredSS[19]++;
                    Vars.stackUsedUpSS.add(b.getId());
                    stackMovesSS.add(19);
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
        }

        //moves.setText(moves.getText().toString() + Integer.toString((b.getId()%20)) + " ");
    }

    public void loaded (View v){
        Button b = (Button) v;
        Button H = findViewById(R.id.H);
        Button C = findViewById(R.id.C);
        Button Null = findViewById(R.id.Null);
        if(b.getId() == R.id.C){
            b.setBackgroundColor(Color.GREEN);
            H.setBackgroundColor(Color.rgb(255,187,51));
            Null.setBackgroundColor(Color.rgb(192,192,192));
            Vars.startedWithSS = "Cargo";
        }
        if(b.getId() == R.id.H){
            b.setBackgroundColor(Color.GREEN);
            C.setBackgroundColor(Color.rgb(255,136,0));
            Null.setBackgroundColor(Color.rgb(192,192,192));
            Vars.startedWithSS = "Hatch";
        }
        if(b.getId() == R.id.Null){
            b.setBackgroundColor(Color.GREEN);
            H.setBackgroundColor(Color.rgb(255,187,51));
            C.setBackgroundColor(Color.rgb(255,136,0));
            Vars.startedWithSS = "NaN";
        }
    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        Button CSC = findViewById(R.id.CSC);
        Button CSH = findViewById(R.id.CSH);
        TextView moves = findViewById(R.id.moves);

        if(b.getText().toString().equals("C")){
            if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] < 8) {
                Vars.CargoshipScoredSS[0]++;
                stackCSMovesSS.add(0);
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
            }
        }
        if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] == 8){
            CSC.setBackgroundColor(Color.DKGRAY);
        }
        if(b.getText().toString().equals("H")){
            if(Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1] < 8) {
                Vars.CargoshipScoredSS[1]++;
                stackCSMovesSS.add(1);
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
            }
        }
        if(Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1] == 8){
            CSH.setBackgroundColor(Color.DKGRAY);
        }
    }

    public void ground (View v){
        if (v.getId() == R.id.groundC){
            Vars.groundC++;
            TextView C = findViewById(R.id.groundCcount);
            C.setText(Integer.toString(Vars.groundC));
        }
        if (v.getId() == R.id.groundH){
            Vars.groundH++;
            TextView H = findViewById(R.id.groundHcount);
            H.setText(Integer.toString(Vars.groundH));
        }
    }

    public void undo2 (View v){
        TextView moves = findViewById(R.id.moves);
        if(!Vars.stackCSMovesSS.empty()) {
            int x = Vars.stackCSMovesSS.pop();
            Vars.CargoshipScoredSS[x]--;
            check();
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


    public void undo (View v){
        TextView moves = findViewById(R.id.moves);
        if(!Vars.stackMovesSS.empty()) {
            int x = Vars.stackMovesSS.pop();
            int id = Vars.stackUsedUpSS.pop();
            Vars.rocketScoredSS[x]--;
            check();
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
        Button H = findViewById(R.id.H);
        Button C = findViewById(R.id.C);
        Button Null = findViewById(R.id.Null);

        Button b = (Button) v;
        Vars.ssPos = b.getText().toString();

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
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);
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
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);
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
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);

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
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);
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
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);
        }
    }

}
