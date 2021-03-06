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


public class TeleOpActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            TextView moves = findViewById(R.id.moves);
            Vars.robotMovesSS = moves.getText().toString();
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
//                case R.id.navigation_notifications:
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
        setContentView(R.layout.activity_tele_op);

        check();

//        if (Vars.alliance == "blue") {
//            //int width = getWindowManager().getDefaultDisplay().getWidth();
//            ImageView image = findViewById(R.id.imageView);
//            image.setImageResource(R.drawable.fieldbluebetter);
//
//        }


        TextView moves = findViewById(R.id.moves);
        //TextView P = findViewById(R.id.P);
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
            flip(i);
        }

        //P.setText(Integer.toString(Vars.penalties));
        moves.setText(Vars.robotMovesSS);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.getMenu().getItem(0).setChecked(false);
        navigation.getMenu().getItem(1).setChecked(true);
        navigation.getMenu().getItem(2).setChecked(false);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        for (int x = 0; x < 3; x++) {
//            navigation.getMenu().getItem(x).setChecked(navigation.getMenu().getItem(x).getItemId() == R.id.navigation_dashboard);
//        }
    }

//    public void ground (View v){
//        if (v.getId() == R.id.groundC){
//            if(Vars.groundC == 1){
//                v.setBackgroundColor(Color.rgb(255,136,0));
//                Vars.groundC = 0;
//            } else {
//                v.setBackgroundColor(Color.DKGRAY);
//                Vars.groundC = 1;
//            }
//
//            //findViewById(R.id.groundH).setBackgroundColor(Color.rgb(255,187,51));
//        }
//        if (v.getId() == R.id.groundH){
//            if(Vars.groundH == 1){
//                v.setBackgroundColor(Color.rgb(255,187,51));
//                Vars.groundH = 0;
//            } else {
//                v.setBackgroundColor(Color.DKGRAY);
//                Vars.groundH = 1;
//            }
//            //findViewById(R.id.groundC).setBackgroundColor(Color.rgb(255,136,0));
//        }
//    }

//    public void penalty(View v){
//        Button b = (Button) v;
//        if(b.getId() == R.id.p1TO)
//            Vars.penalties++;
//        else
//        if(Vars.penalties > 0)
//            Vars.penalties--;
//
//        TextView P = findViewById(R.id.P);
//        P.setText(Integer.toString(Vars.penalties));
//    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        Button CSC = findViewById(R.id.CSC);
        Button CSH = findViewById(R.id.CSH);
        TextView moves = findViewById(R.id.moves);

        if(b.getText().toString().equals("C")){
            if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] < 8) {
                Vars.CargoshipScoredTO[0]++;
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
                Vars.universal.add("c" + Integer.toString(0));
            }
        }
        if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] == 8){
            CSC.setBackgroundColor(Color.DKGRAY);
        }
        if(b.getText().toString().equals("H")){
            if(Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1] < 8) {
                Vars.CargoshipScoredTO[1]++;
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
                Vars.universal.add("c" + Integer.toString(1));
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
                Vars.rocketScoredTO[Integer.valueOf(x.substring(1))]--;
                Log.d("errors!?", "step 2");
            }
            else if(x.charAt(0) == 'c'){
                int y = Integer.valueOf(x.substring(1));
                Vars.CargoshipScoredTO[y]--;
                Log.d("errors!?", "step 3");
            }
//            else {
//                Vars.slots = Vars.slots.substring(0, Vars.slots.length()-4);
//                Log.d("errors!?", "step 4");
//                String y = x.substring(1);
//                int z = Integer.valueOf(y);
////                Button b = findViewById(z);
////                b.setBackgroundColor(Color.parseColor("#ff33b5e5"));
//                Log.d("errors!?", "step 5");
//                return;
//            }

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

//    public void undo2 (View v){
//        TextView moves = findViewById(R.id.moves);
//        if(!Vars.stackCSMovesTO.empty()) {
//            int x = Vars.stackCSMovesTO.pop();
//            Vars.CargoshipScoredTO[x]--;
//            check();
//        } else {
//            return;
//        }
//
//        while(true){
//            if(moves.getText().toString().charAt(moves.getText().toString().length()-2) == ':'){
//                break;
//            }
//            String str = moves.getText().toString();
//            str = str.substring(0, str.length() - 1);
//            moves.setText(str);
//
//            int x = moves.getText().toString().length()-1;
//
//            if(moves.getText().toString().charAt(x) == ' ') {
//                break;
//            }
//        }
//    }

    public void check(){

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
            findViewById(R.id.TO_H3RR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H3RR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[1] + Vars.rocketScoredSS[1] == 2) {
            findViewById(R.id.TO_C1L).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[1] + Vars.rocketScoredSS[1] == 0) {
            findViewById(R.id.TO_C1L).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else {
            findViewById(R.id.TO_C1L).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[2] + Vars.rocketScoredSS[2] == 2) {
            findViewById(R.id.TO_C1R).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[2] + Vars.rocketScoredSS[2] == 0) {
            findViewById(R.id.TO_C1R).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else {
            findViewById(R.id.TO_C1R).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[3] + Vars.rocketScoredSS[3] == 2) {
            findViewById(R.id.TO_C2L).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[3] + Vars.rocketScoredSS[3] == 0) {
            findViewById(R.id.TO_C2L).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else {
            findViewById(R.id.TO_C2L).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[4] + Vars.rocketScoredSS[4] == 2) {
            findViewById(R.id.TO_C2R).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[4] + Vars.rocketScoredSS[4] == 0) {
            findViewById(R.id.TO_C2R).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else {
            findViewById(R.id.TO_C2R).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[5] + Vars.rocketScoredSS[5] == 2) {
            findViewById(R.id.TO_C3L).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[5] + Vars.rocketScoredSS[5] == 0) {
            findViewById(R.id.TO_C3L).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else {
            findViewById(R.id.TO_C3L).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[6] + Vars.rocketScoredSS[6] == 2) {
            findViewById(R.id.TO_C3R).setBackgroundColor(Color.DKGRAY);
        } else if(Vars.rocketScoredTO[6] + Vars.rocketScoredSS[6] == 0) {
            findViewById(R.id.TO_C3R).setBackgroundColor(Color.parseColor("#ffff8800"));
        } else {
            findViewById(R.id.TO_C3R).setBackgroundColor(Color.GRAY);
        }

        if(Vars.rocketScoredTO[9] + Vars.rocketScoredSS[9] == 1) {
            findViewById(R.id.TO_H1LL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H1LL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[10] + Vars.rocketScoredSS[10] == 1) {
            findViewById(R.id.TO_H1LR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H1LR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[11] + Vars.rocketScoredSS[11] == 1) {
            findViewById(R.id.TO_H1RL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H1RL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[12] + Vars.rocketScoredSS[12] == 1) {
            findViewById(R.id.TO_H1RR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H1RR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[13] + Vars.rocketScoredSS[13] == 1) {
            findViewById(R.id.TO_H2LL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H2LL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[14] + Vars.rocketScoredSS[14] == 1) {
            findViewById(R.id.TO_H2LR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H2LR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[15] + Vars.rocketScoredSS[15] == 1) {
            findViewById(R.id.TO_H2RL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H2RL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[16] + Vars.rocketScoredSS[16] == 1) {
            findViewById(R.id.TO_H2RR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H2RR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[17] + Vars.rocketScoredSS[17] == 1) {
            findViewById(R.id.TO_H3LL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H3LL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[18] + Vars.rocketScoredSS[18] == 1) {
            findViewById(R.id.TO_H3LR).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H3LR).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }

        if(Vars.rocketScoredTO[19] + Vars.rocketScoredSS[19] == 1) {
            findViewById(R.id.TO_H3RL).setBackgroundColor(Color.DKGRAY);
        } else {
            findViewById(R.id.TO_H3RL).setBackgroundColor(Color.parseColor("#ffffbb33"));
        }
    }

    @SuppressLint("ResourceType")
    public void add (View v){
        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;

        if(b.getText().charAt(1) == '1'){
            if(Vars.scorable < 1)
                Vars.scorable = 1;
        }
        if(b.getText().charAt(1) == '2'){
            if(Vars.scorable < 2)
                Vars.scorable = 2;
        }
        if(b.getText().charAt(1) == '3'){
            if(Vars.scorable < 3)
                Vars.scorable = 3;
        }

        if(b.getId() == R.id.TO_H3RR) {
            if ((Vars.rocketScoredSS[0] + Vars.rocketScoredTO[0]) < 1){
                moves.setText(moves.getText().toString() + "H3RR ");
                Vars.rocketScoredTO[0]++;
                Vars.universal.add("r" + Integer.toString(0));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_C1L){
            if ((Vars.rocketScoredSS[1] + Vars.rocketScoredTO[1]) < 2) {
                moves.setText(moves.getText().toString() + "C1L ");
                Vars.rocketScoredTO[1]++;
                Vars.universal.add("r" + Integer.toString(1));
            }
            if((Vars.rocketScoredSS[1] + Vars.rocketScoredTO[1]) == 2){
                b.setBackgroundColor(Color.DKGRAY);
            } else {
                b.setBackgroundColor(Color.GRAY);
            }
        } else
        if(b.getId() == R.id.TO_C1R){
            if ((Vars.rocketScoredSS[2] + Vars.rocketScoredTO[2]) < 2){
                moves.setText(moves.getText().toString() + "C1R ");
                Vars.rocketScoredTO[2]++;
                Vars.universal.add("r" + Integer.toString(2));
            }
            if((Vars.rocketScoredSS[2] + Vars.rocketScoredTO[2]) == 2){
                b.setBackgroundColor(Color.DKGRAY);
            } else {
                b.setBackgroundColor(Color.GRAY);
            }
        } else
        if(b.getId() == R.id.TO_C2L){
            if ((Vars.rocketScoredSS[3] + Vars.rocketScoredTO[3]) < 2) {
                moves.setText(moves.getText().toString() + "C2L ");
                Vars.rocketScoredTO[3]++;
                Vars.universal.add("r" + Integer.toString(3));
            }
            if((Vars.rocketScoredSS[3] + Vars.rocketScoredTO[3]) == 2){
                b.setBackgroundColor(Color.DKGRAY);
            } else {
                b.setBackgroundColor(Color.GRAY);
            }
        } else
        if(b.getId() == R.id.TO_C2R){
            if ((Vars.rocketScoredSS[4] + Vars.rocketScoredTO[4]) < 2){
                moves.setText(moves.getText().toString() + "C2R ");
                Vars.rocketScoredTO[4]++;
                Vars.universal.add("r" + Integer.toString(4));
            }
            if((Vars.rocketScoredSS[4] + Vars.rocketScoredTO[4]) == 2){
                b.setBackgroundColor(Color.DKGRAY);
            } else {
                b.setBackgroundColor(Color.GRAY);
            }
        } else
        if(b.getId() == R.id.TO_C3L){
            if ((Vars.rocketScoredSS[5] + Vars.rocketScoredTO[5]) < 2){
                moves.setText(moves.getText().toString() + "C3L ");
                Vars.rocketScoredTO[5]++;
                Vars.universal.add("r" + Integer.toString(5));
            }
            if((Vars.rocketScoredSS[5] + Vars.rocketScoredTO[5]) == 2){
                b.setBackgroundColor(Color.DKGRAY);
            } else {
                b.setBackgroundColor(Color.GRAY);
            }
        } else
        if(b.getId() == R.id.TO_C3R){
            if ((Vars.rocketScoredSS[6] + Vars.rocketScoredTO[6]) < 2){
                moves.setText(moves.getText().toString() + "C3R ");
                Vars.rocketScoredTO[6]++;
                Vars.universal.add("r" + Integer.toString(6));
            }
            if((Vars.rocketScoredSS[6] + Vars.rocketScoredTO[6]) == 2){
                b.setBackgroundColor(Color.DKGRAY);
            } else {
                b.setBackgroundColor(Color.GRAY);
            }
        } else
        if(b.getId() == R.id.TO_H1LL){
            if ((Vars.rocketScoredSS[9] + Vars.rocketScoredTO[9]) < 1){
                moves.setText(moves.getText().toString() + "H1LL ");
                Vars.rocketScoredTO[9]++;
                Vars.universal.add("r" + Integer.toString(9));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H1LR){
            if ((Vars.rocketScoredSS[10] + Vars.rocketScoredTO[10]) < 1){
                moves.setText(moves.getText().toString() + "H1LR ");
                Vars.rocketScoredTO[10]++;
                Vars.universal.add("r" + Integer.toString(10));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H1RL){
            if ((Vars.rocketScoredSS[11] + Vars.rocketScoredTO[11]) < 1){
                moves.setText(moves.getText().toString() + "H1RL ");
                Vars.rocketScoredTO[11]++;
                Vars.universal.add("r" + Integer.toString(11));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H1RR){
            if ((Vars.rocketScoredSS[12] + Vars.rocketScoredTO[12]) < 1){
                moves.setText(moves.getText().toString() + "H1RR ");
                Vars.rocketScoredTO[12]++;
                Vars.universal.add("r" + Integer.toString(12));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H2LL){
            if ((Vars.rocketScoredSS[13] + Vars.rocketScoredTO[13]) < 1){
                moves.setText(moves.getText().toString() + "H2LL ");
                Vars.rocketScoredTO[13]++;
                Vars.universal.add("r" + Integer.toString(13));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H2LR){
            if ((Vars.rocketScoredSS[14] + Vars.rocketScoredTO[14]) < 1){
                moves.setText(moves.getText().toString() + "H2LR ");
                Vars.rocketScoredTO[14]++;
                Vars.universal.add("r" + Integer.toString(14));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H2RL){
            if ((Vars.rocketScoredSS[15] + Vars.rocketScoredTO[15]) < 1){
                moves.setText(moves.getText().toString() + "H2RL ");
                Vars.rocketScoredTO[15]++;
                Vars.universal.add("r" + Integer.toString(15));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H2RR){
            if ((Vars.rocketScoredSS[16] + Vars.rocketScoredTO[16]) < 1){
                moves.setText(moves.getText().toString() + "H2RR ");
                Vars.rocketScoredTO[16]++;
                Vars.universal.add("r" + Integer.toString(16));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H3LL){
            if ((Vars.rocketScoredSS[17] + Vars.rocketScoredTO[17]) < 1){
                moves.setText(moves.getText().toString() + "H3LL ");
                Vars.rocketScoredTO[17]++;
                Vars.universal.add("r" + Integer.toString(17));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H3LR){
            if ((Vars.rocketScoredSS[18] + Vars.rocketScoredTO[18]) < 1){
                moves.setText(moves.getText().toString() + "H3LR ");
                Vars.rocketScoredTO[18]++;
                Vars.universal.add("r" + Integer.toString(18));
            }
            b.setBackgroundColor(Color.DKGRAY);
        } else
        if(b.getId() == R.id.TO_H3RL){
            if ((Vars.rocketScoredSS[19] + Vars.rocketScoredTO[19]) < 1){
                moves.setText(moves.getText().toString() + "H3RL ");
                Vars.rocketScoredTO[19]++;
                Vars.universal.add("r" + Integer.toString(19));
            }
            b.setBackgroundColor(Color.DKGRAY);
        }

        //moves.setText(moves.getText().toString() + Integer.toString((b.getId()%20)) + " ");
    }

    public void flip(View v){
        findViewById(R.id.button4).post(new Runnable() {
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

                findViewById(R.id.CSC).setX(1090-findViewById(R.id.CSC).getX());
                findViewById(R.id.CSH).setX(1090-findViewById(R.id.CSH).getX());

                findViewById(R.id.TO_C3R).setX(1090-findViewById(R.id.TO_C3R).getX());
                findViewById(R.id.TO_C2R).setX(1090-findViewById(R.id.TO_C2R).getX());
                findViewById(R.id.TO_C1R).setX(1090-findViewById(R.id.TO_C1R).getX());
                findViewById(R.id.TO_C3L).setX(1090-findViewById(R.id.TO_C3L).getX());
                findViewById(R.id.TO_C2L).setX(1090-findViewById(R.id.TO_C2L).getX());
                findViewById(R.id.TO_C1L).setX(1090-findViewById(R.id.TO_C1L).getX());

                findViewById(R.id.TO_H3RL).setX(1090-findViewById(R.id.TO_H3RL).getX());
                findViewById(R.id.TO_H3RR).setX(1090-findViewById(R.id.TO_H3RR).getX());
                findViewById(R.id.TO_H2RR).setX(1090-findViewById(R.id.TO_H2RR).getX());
                findViewById(R.id.TO_H1RR).setX(1090-findViewById(R.id.TO_H1RR).getX());
                findViewById(R.id.TO_H2RL).setX(1090-findViewById(R.id.TO_H2RL).getX());
                findViewById(R.id.TO_H1RL).setX(1090-findViewById(R.id.TO_H1RL).getX());
                findViewById(R.id.TO_H3LL).setX(1090-findViewById(R.id.TO_H3LL).getX());
                findViewById(R.id.TO_H3LR).setX(1090-findViewById(R.id.TO_H3LR).getX());
                findViewById(R.id.TO_H2LR).setX(1090-findViewById(R.id.TO_H2LR).getX());
                findViewById(R.id.TO_H2LL).setX(1090-findViewById(R.id.TO_H2LL).getX());
                findViewById(R.id.TO_H1LL).setX(1090-findViewById(R.id.TO_H1LL).getX());
                findViewById(R.id.TO_H1LR).setX(1090-findViewById(R.id.TO_H1LR).getX());

                findViewById(R.id.button2).setX(1090-findViewById(R.id.button2).getX()-170);
            }
        });
    }

}
