package com.example.ranas.a2019scoutingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
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
            Vars.robotMovesTO = moves.getText().toString();
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


        TextView moves = findViewById(R.id.moves);
        TextView P = findViewById(R.id.P);

        P.setText(Integer.toString(Vars.penaltiesSS + Vars.penaltiesTO));
        moves.setText(Vars.robotMovesTO);

        TextView C = findViewById(R.id.groundCcount);
        C.setText(Integer.toString(Vars.groundC));
        TextView H = findViewById(R.id.groundHcount);
        H.setText(Integer.toString(Vars.groundH));

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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

    public void penalty(View v){
        Button b = (Button) v;
        if(b.getId() == R.id.p1TO)
            Vars.penaltiesTO++;
        else
        if(Vars.penaltiesTO > 0)
            Vars.penaltiesTO--;

        TextView P = findViewById(R.id.P);
        P.setText(Integer.toString(Vars.penaltiesSS + Vars.penaltiesTO));
    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        Button CSC = findViewById(R.id.CSC);
        Button CSH = findViewById(R.id.CSH);
        TextView moves = findViewById(R.id.moves);

        if(b.getText().toString().equals("C")){
            if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] < 8) {
                Vars.CargoshipScoredTO[0]++;
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
                Vars.stackCSMovesTO.add(0);
            }
        }
        if(Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0] == 8){
            CSC.setBackgroundColor(Color.DKGRAY);
        }
        if(b.getText().toString().equals("H")){
            if(Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1] < 8) {
                Vars.CargoshipScoredTO[1]++;
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
                Vars.stackCSMovesTO.add(1);
            }
        }
        if(Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1] == 8){
            CSH.setBackgroundColor(Color.DKGRAY);
        }
    }

    public void undo (View v){
        TextView moves = findViewById(R.id.moves);
        if(!Vars.stackMovesTO.empty())
            Vars.rocketScoredTO[Vars.stackMovesTO.pop()]--;

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

    public void undo2 (View v){
        TextView moves = findViewById(R.id.moves);
        if(!Vars.stackCSMovesTO.empty()) {
            int x = Vars.stackCSMovesTO.pop();
            Vars.CargoshipScoredTO[x]--;
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

    @SuppressLint("ResourceType")
    public void add (View v){
        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;
        int id = b.getId();

        if(b.getId() == R.id.TO_H3RR) {
            if ((Vars.rocketScoredSS[0] + Vars.rocketScoredTO[0]) < 1){
                moves.setText(moves.getText().toString() + "H3RR ");
                Vars.rocketScoredTO[0]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(9);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_C1L){
            if ((Vars.rocketScoredSS[1] + Vars.rocketScoredTO[1]) < 2) {
                moves.setText(moves.getText().toString() + "C1L ");
                Vars.rocketScoredTO[1]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(1);
            }
            if((Vars.rocketScoredSS[1] + Vars.rocketScoredTO[1]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C1R){
            if ((Vars.rocketScoredSS[2] + Vars.rocketScoredTO[2]) < 2){
                moves.setText(moves.getText().toString() + "C1R ");
                Vars.rocketScoredTO[2]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(2);
            }
            if((Vars.rocketScoredSS[2] + Vars.rocketScoredTO[2]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C2L){
            if ((Vars.rocketScoredSS[3] + Vars.rocketScoredTO[3]) < 2) {
                moves.setText(moves.getText().toString() + "C2L ");
                Vars.rocketScoredTO[3]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(3);
            }
            if((Vars.rocketScoredSS[3] + Vars.rocketScoredTO[3]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C2R){
            if ((Vars.rocketScoredSS[4] + Vars.rocketScoredTO[4]) < 2){
                moves.setText(moves.getText().toString() + "C2R ");
                Vars.rocketScoredTO[4]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(4);
            }
            if((Vars.rocketScoredSS[4] + Vars.rocketScoredTO[4]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C3L){
            if ((Vars.rocketScoredSS[5] + Vars.rocketScoredTO[5]) < 2){
                moves.setText(moves.getText().toString() + "C3L ");
                Vars.rocketScoredTO[5]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(5);
            }
            if((Vars.rocketScoredSS[5] + Vars.rocketScoredTO[5]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C3R){
            if ((Vars.rocketScoredSS[6] + Vars.rocketScoredTO[6]) < 2){
                moves.setText(moves.getText().toString() + "C3R ");
                Vars.rocketScoredTO[6]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(6);
            }
            if((Vars.rocketScoredSS[6] + Vars.rocketScoredTO[6]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_H1LL){
            if ((Vars.rocketScoredSS[9] + Vars.rocketScoredTO[9]) < 1){
                moves.setText(moves.getText().toString() + "H1LL ");
                Vars.rocketScoredTO[9]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(9);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H1LR){
            if ((Vars.rocketScoredSS[10] + Vars.rocketScoredTO[10]) < 1){
                moves.setText(moves.getText().toString() + "H1LR ");
                Vars.rocketScoredTO[10]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(10);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H1RL){
            if ((Vars.rocketScoredSS[11] + Vars.rocketScoredTO[11]) < 1){
                moves.setText(moves.getText().toString() + "H1RL ");
                Vars.rocketScoredTO[11]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(11);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H1RR){
            if ((Vars.rocketScoredSS[12] + Vars.rocketScoredTO[12]) < 1){
                moves.setText(moves.getText().toString() + "H1RR ");
                Vars.rocketScoredTO[12]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(12);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H2LL){
            if ((Vars.rocketScoredSS[13] + Vars.rocketScoredTO[13]) < 1){
                moves.setText(moves.getText().toString() + "H2LL ");
                Vars.rocketScoredTO[13]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(13);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H2LR){
            if ((Vars.rocketScoredSS[14] + Vars.rocketScoredTO[14]) < 1){
                moves.setText(moves.getText().toString() + "H2LR ");
                Vars.rocketScoredTO[14]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(14);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H2RL){
            if ((Vars.rocketScoredSS[15] + Vars.rocketScoredTO[15]) < 1){
                moves.setText(moves.getText().toString() + "H2RL ");
                Vars.rocketScoredTO[15]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(15);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H2RR){
            if ((Vars.rocketScoredSS[16] + Vars.rocketScoredTO[16]) < 1){
                moves.setText(moves.getText().toString() + "H2RR ");
                Vars.rocketScoredTO[16]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(16);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H3LL){
            if ((Vars.rocketScoredSS[17] + Vars.rocketScoredTO[17]) < 1){
                moves.setText(moves.getText().toString() + "H3LL ");
                Vars.rocketScoredTO[17]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(17);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H3LR){
            if ((Vars.rocketScoredSS[18] + Vars.rocketScoredTO[18]) < 1){
                moves.setText(moves.getText().toString() + "H3LR ");
                Vars.rocketScoredTO[18]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(18);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H3RL){
            if ((Vars.rocketScoredSS[19] + Vars.rocketScoredTO[19]) < 1){
                moves.setText(moves.getText().toString() + "H3RL ");
                Vars.rocketScoredTO[19]++;
                Vars.stackUsedUpTO.add(b.getId());
                Vars.stackMovesTO.add(19);
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }

        //moves.setText(moves.getText().toString() + Integer.toString((b.getId()%20)) + " ");
    }

}
