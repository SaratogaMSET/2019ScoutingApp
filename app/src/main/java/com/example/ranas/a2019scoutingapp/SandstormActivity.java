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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.ranas.a2019scoutingapp.MainActivity.groundC;

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

//        if (MainActivity.alliance == "red") {
//            //int width = getWindowManager().getDefaultDisplay().getWidth();
//            ImageView image = findViewById(R.id.imageView);
//            image.setImageResource(R.drawable.frc_field_red);
//            //l2.setText(l2.getX() + " " + l2.getY());
//            Toast.makeText(getApplicationContext(), Integer.toString(l1.getLeft()), Toast.LENGTH_LONG).show();
//            l1.setLeft(100 - (int) l1.getLeft());
//            l2.setLeft(100 - (int) l2.getLeft());
//            r1.setLeft(100 - (int) r1.getLeft());
//            r2.setLeft(100 - (int) r2.getLeft());
//            m1.setLeft(100 - (int) m1.getLeft());
//
//        }

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
        if(MainActivity.startedWithSS == "Cargo"){
            C.setBackgroundColor(Color.GREEN);
        }else if(MainActivity.startedWithSS == "Hatch"){
            H.setBackgroundColor((Color.GREEN));
        } else if (MainActivity.startedWithSS == "NaN"){
            Null.setBackgroundColor(Color.GREEN);
        }

        TextView C1 = findViewById(R.id.groundCcount);
        C1.setText(Integer.toString(MainActivity.groundC));
        TextView H1 = findViewById(R.id.groundHcount);
        H1.setText(Integer.toString(MainActivity.groundH));

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
    public void add (View v){
//        Toast.makeText(getApplicationContext(), "Blah", Toast.LENGTH_SHORT).show();

        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;
        int id = b.getId();

        switch (b.getId()) {
            case R.id.SS_H3RR:
                if ((MainActivity.rocketScoredSS[0] + MainActivity.rocketScoredTO[0]) < 1){
                    moves.setText(moves.getText().toString() + "H3RR ");
                    MainActivity.rocketScoredSS[0]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_C1L:
                if ((MainActivity.rocketScoredSS[1] + MainActivity.rocketScoredTO[1]) < 2) {
                    moves.setText(moves.getText().toString() + "C1L ");
                    MainActivity.rocketScoredSS[1]++;
                }
                if((MainActivity.rocketScoredSS[1] + MainActivity.rocketScoredTO[1]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C1R:
                if ((MainActivity.rocketScoredSS[2] + MainActivity.rocketScoredTO[2]) < 2){
                    moves.setText(moves.getText().toString() + "C1R ");
                    MainActivity.rocketScoredSS[2]++;
                }
                if((MainActivity.rocketScoredSS[2] + MainActivity.rocketScoredTO[2]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C2L:
                if ((MainActivity.rocketScoredSS[3] + MainActivity.rocketScoredTO[3]) < 2) {
                    moves.setText(moves.getText().toString() + "C2L ");
                    MainActivity.rocketScoredSS[3]++;
                }
                if((MainActivity.rocketScoredSS[3] + MainActivity.rocketScoredTO[3]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C2R:
                if ((MainActivity.rocketScoredSS[4] + MainActivity.rocketScoredTO[4]) < 2){
                    moves.setText(moves.getText().toString() + "C2R ");
                    MainActivity.rocketScoredSS[4]++;
                }
                if((MainActivity.rocketScoredSS[4] + MainActivity.rocketScoredTO[4]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C3L:
                if ((MainActivity.rocketScoredSS[5] + MainActivity.rocketScoredTO[5]) < 2){
                    moves.setText(moves.getText().toString() + "C3L ");
                    MainActivity.rocketScoredSS[5]++;
                }
                if((MainActivity.rocketScoredSS[5] + MainActivity.rocketScoredTO[5]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
                break;
            case R.id.SS_C3R:
                if ((MainActivity.rocketScoredSS[6] + MainActivity.rocketScoredTO[6]) < 2){
                    moves.setText(moves.getText().toString() + "C3R ");
                    MainActivity.rocketScoredSS[6]++;
                }
                if((MainActivity.rocketScoredSS[6] + MainActivity.rocketScoredTO[6]) == 2){
                    findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                }
            case R.id.SS_H1LL:
                if ((MainActivity.rocketScoredSS[9] + MainActivity.rocketScoredTO[9]) < 1){
                    moves.setText(moves.getText().toString() + "H1LL ");
                    MainActivity.rocketScoredSS[9]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H1LR:
                if ((MainActivity.rocketScoredSS[10] + MainActivity.rocketScoredTO[10]) < 1){
                    moves.setText(moves.getText().toString() + "H1LR ");
                    MainActivity.rocketScoredSS[10]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H1RL:
                if ((MainActivity.rocketScoredSS[11] + MainActivity.rocketScoredTO[11]) < 1){
                    moves.setText(moves.getText().toString() + "H1RL ");
                    MainActivity.rocketScoredSS[11]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H1RR:
                if ((MainActivity.rocketScoredSS[12] + MainActivity.rocketScoredTO[12]) < 1){
                    moves.setText(moves.getText().toString() + "H1RR ");
                    MainActivity.rocketScoredSS[12]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2LL:
                if ((MainActivity.rocketScoredSS[13] + MainActivity.rocketScoredTO[13]) < 1){
                    moves.setText(moves.getText().toString() + "H2LL ");
                    MainActivity.rocketScoredSS[13]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2LR:
                if ((MainActivity.rocketScoredSS[14] + MainActivity.rocketScoredTO[14]) < 1){
                    moves.setText(moves.getText().toString() + "H2LR ");
                    MainActivity.rocketScoredSS[14]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2RL:
                if ((MainActivity.rocketScoredSS[15] + MainActivity.rocketScoredTO[15]) < 1){
                    moves.setText(moves.getText().toString() + "H2RL ");
                    MainActivity.rocketScoredSS[15]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H2RR:
                if ((MainActivity.rocketScoredSS[16] + MainActivity.rocketScoredTO[16]) < 1){
                    moves.setText(moves.getText().toString() + "H2RR ");
                    MainActivity.rocketScoredSS[16]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H3LL:
                if ((MainActivity.rocketScoredSS[17] + MainActivity.rocketScoredTO[17]) < 1){
                    moves.setText(moves.getText().toString() + "H3LL ");
                    MainActivity.rocketScoredSS[17]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H3LR:
                if ((MainActivity.rocketScoredSS[18] + MainActivity.rocketScoredTO[18]) < 1){
                    moves.setText(moves.getText().toString() + "H3LR ");
                    MainActivity.rocketScoredSS[18]++;
                }
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.SS_H3RL:
                if ((MainActivity.rocketScoredSS[19] + MainActivity.rocketScoredTO[19]) < 1){
                    moves.setText(moves.getText().toString() + "H3RL ");
                    MainActivity.rocketScoredSS[19]++;
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
            MainActivity.startedWithSS = "Cargo";
        }
        if(b.getId() == R.id.H){
            b.setBackgroundColor(Color.GREEN);
            C.setBackgroundColor(Color.rgb(255,136,0));
            Null.setBackgroundColor(Color.rgb(192,192,192));
            MainActivity.startedWithSS = "Hatch";
        }
        if(b.getId() == R.id.Null){
            b.setBackgroundColor(Color.GREEN);
            H.setBackgroundColor(Color.rgb(255,187,51));
            C.setBackgroundColor(Color.rgb(255,136,0));
            MainActivity.startedWithSS = "NaN";
        }
    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        Button CSC = findViewById(R.id.CSC);
        Button CSH = findViewById(R.id.CSH);
        TextView moves = findViewById(R.id.moves);

        if(b.getText().toString().equals("C")){
            if(MainActivity.CargoshipScoredSS[0] + MainActivity.CargoshipScoredTO[0] < 8) {
                MainActivity.CargoshipScoredSS[0]++;
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
            }
        }
        if(MainActivity.CargoshipScoredSS[0] + MainActivity.CargoshipScoredTO[0] == 8){
            CSC.setBackgroundColor(Color.DKGRAY);
        }
        if(b.getText().toString().equals("H")){
            if(MainActivity.CargoshipScoredSS[1] + MainActivity.CargoshipScoredTO[1] < 8) {
                MainActivity.CargoshipScoredSS[1]++;
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
            }
        }
        if(MainActivity.CargoshipScoredSS[1] + MainActivity.CargoshipScoredTO[1] == 8){
            CSH.setBackgroundColor(Color.DKGRAY);
        }
    }

    public void ground (View v){
        if (v.getId() == R.id.groundC){
            MainActivity.groundC++;
            TextView C = findViewById(R.id.groundCcount);
            C.setText(Integer.toString(MainActivity.groundC));
        }
        if (v.getId() == R.id.groundH){
            MainActivity.groundH++;
            TextView H = findViewById(R.id.groundHcount);
            H.setText(Integer.toString(MainActivity.groundH));
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
        Button H = findViewById(R.id.H);
        Button C = findViewById(R.id.C);
        Button Null = findViewById(R.id.Null);

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
