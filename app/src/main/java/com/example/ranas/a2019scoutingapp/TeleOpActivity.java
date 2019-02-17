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
            MainActivity.robotMovesTO = moves.getText().toString();
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
        if (MainActivity.alliance == "red") {
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

        P.setText(Integer.toString(MainActivity.penaltiesSS + MainActivity.penaltiesTO));
        moves.setText(MainActivity.robotMovesTO);

        TextView C = findViewById(R.id.groundCcount);
        C.setText(Integer.toString(MainActivity.groundC));
        TextView H = findViewById(R.id.groundHcount);
        H.setText(Integer.toString(MainActivity.groundH));

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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

    public void penalty(View v){
        Button b = (Button) v;
        if(b.getId() == R.id.p1TO)
            MainActivity.penaltiesTO++;
        else
        if(MainActivity.penaltiesTO > 0)
            MainActivity.penaltiesTO--;

        TextView P = findViewById(R.id.P);
        P.setText(Integer.toString(MainActivity.penaltiesSS + MainActivity.penaltiesTO));
    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        Button CSC = findViewById(R.id.CSC);
        Button CSH = findViewById(R.id.CSH);
        TextView moves = findViewById(R.id.moves);

        if(b.getText().toString().equals("C")){
            if(MainActivity.CargoshipScoredSS[0] + MainActivity.CargoshipScoredTO[0] < 8) {
                MainActivity.CargoshipScoredTO[0]++;
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
            }
        }
        if(MainActivity.CargoshipScoredSS[0] + MainActivity.CargoshipScoredTO[0] == 8){
            CSC.setBackgroundColor(Color.DKGRAY);
        }
        if(b.getText().toString().equals("H")){
            if(MainActivity.CargoshipScoredSS[1] + MainActivity.CargoshipScoredTO[1] < 8) {
                MainActivity.CargoshipScoredTO[1]++;
                moves.setText(moves.getText().toString() + b.getText().toString() + " ");
            }
        }
        if(MainActivity.CargoshipScoredSS[1] + MainActivity.CargoshipScoredTO[1] == 8){
            CSH.setBackgroundColor(Color.DKGRAY);
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

    @SuppressLint("ResourceType")
    public void add (View v){
        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;
        int id = b.getId();

        if(b.getId() == R.id.TO_H3RR) {
            if ((MainActivity.rocketScoredSS[0] + MainActivity.rocketScoredTO[0]) < 1){
                moves.setText(moves.getText().toString() + "H3RR ");
                MainActivity.rocketScoredTO[0]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_C1L){
            if ((MainActivity.rocketScoredSS[1] + MainActivity.rocketScoredTO[1]) < 2) {
                moves.setText(moves.getText().toString() + "C1L ");
                MainActivity.rocketScoredTO[1]++;
            }
            if((MainActivity.rocketScoredSS[1] + MainActivity.rocketScoredTO[1]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C1R){
            if ((MainActivity.rocketScoredSS[2] + MainActivity.rocketScoredTO[2]) < 2){
                moves.setText(moves.getText().toString() + "C1R ");
                MainActivity.rocketScoredTO[2]++;
            }
            if((MainActivity.rocketScoredSS[2] + MainActivity.rocketScoredTO[2]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C2L){
            if ((MainActivity.rocketScoredSS[3] + MainActivity.rocketScoredTO[3]) < 2) {
                moves.setText(moves.getText().toString() + "C2L ");
                MainActivity.rocketScoredTO[3]++;
            }
            if((MainActivity.rocketScoredSS[3] + MainActivity.rocketScoredTO[3]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C2R){
            if ((MainActivity.rocketScoredSS[4] + MainActivity.rocketScoredTO[4]) < 2){
                moves.setText(moves.getText().toString() + "C2R ");
                MainActivity.rocketScoredTO[4]++;
            }
            if((MainActivity.rocketScoredSS[4] + MainActivity.rocketScoredTO[4]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C3L){
            if ((MainActivity.rocketScoredSS[5] + MainActivity.rocketScoredTO[5]) < 2){
                moves.setText(moves.getText().toString() + "C3L ");
                MainActivity.rocketScoredTO[5]++;
            }
            if((MainActivity.rocketScoredSS[5] + MainActivity.rocketScoredTO[5]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_C3R){
            if ((MainActivity.rocketScoredSS[6] + MainActivity.rocketScoredTO[6]) < 2){
                moves.setText(moves.getText().toString() + "C3R ");
                MainActivity.rocketScoredTO[6]++;
            }
            if((MainActivity.rocketScoredSS[6] + MainActivity.rocketScoredTO[6]) == 2){
                findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
            }
        }
        if(b.getId() == R.id.TO_H1LL){
            if ((MainActivity.rocketScoredSS[9] + MainActivity.rocketScoredTO[9]) < 1){
                moves.setText(moves.getText().toString() + "H1LL ");
                MainActivity.rocketScoredTO[9]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H1LR){
            if ((MainActivity.rocketScoredSS[10] + MainActivity.rocketScoredTO[10]) < 1){
                moves.setText(moves.getText().toString() + "H1LR ");
                MainActivity.rocketScoredTO[10]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H1RL){
            if ((MainActivity.rocketScoredSS[11] + MainActivity.rocketScoredTO[11]) < 1){
                moves.setText(moves.getText().toString() + "H1RL ");
                MainActivity.rocketScoredTO[11]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H1RR){
            if ((MainActivity.rocketScoredSS[12] + MainActivity.rocketScoredTO[12]) < 1){
                moves.setText(moves.getText().toString() + "H1RR ");
                MainActivity.rocketScoredTO[12]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H2LL){
            if ((MainActivity.rocketScoredSS[13] + MainActivity.rocketScoredTO[13]) < 1){
                moves.setText(moves.getText().toString() + "H2LL ");
                MainActivity.rocketScoredTO[13]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H2LR){
            if ((MainActivity.rocketScoredSS[14] + MainActivity.rocketScoredTO[14]) < 1){
                moves.setText(moves.getText().toString() + "H2LR ");
                MainActivity.rocketScoredTO[14]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H2RL){
            if ((MainActivity.rocketScoredSS[15] + MainActivity.rocketScoredTO[15]) < 1){
                moves.setText(moves.getText().toString() + "H2RL ");
                MainActivity.rocketScoredTO[15]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H2RR){
            if ((MainActivity.rocketScoredSS[16] + MainActivity.rocketScoredTO[16]) < 1){
                moves.setText(moves.getText().toString() + "H2RR ");
                MainActivity.rocketScoredTO[16]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H3LL){
            if ((MainActivity.rocketScoredSS[17] + MainActivity.rocketScoredTO[17]) < 1){
                moves.setText(moves.getText().toString() + "H3LL ");
                MainActivity.rocketScoredTO[17]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H3LR){
            if ((MainActivity.rocketScoredSS[18] + MainActivity.rocketScoredTO[18]) < 1){
                moves.setText(moves.getText().toString() + "H3LR ");
                MainActivity.rocketScoredTO[18]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }
        if(b.getId() == R.id.TO_H3RL){
            if ((MainActivity.rocketScoredSS[19] + MainActivity.rocketScoredTO[19]) < 1){
                moves.setText(moves.getText().toString() + "H3RL ");
                MainActivity.rocketScoredTO[19]++;
            }
            findViewById(b.getId()).setBackgroundColor(Color.DKGRAY);
        }

        //moves.setText(moves.getText().toString() + Integer.toString((b.getId()%20)) + " ");
    }

}
