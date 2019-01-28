package com.example.ranas.a2019scoutingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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


        TextView moves = findViewById(R.id.moves);
        TextView P = findViewById(R.id.P);

        P.setText(Integer.toString(MainActivity.penaltiesSS + MainActivity.penaltiesTO));
        moves.setText(MainActivity.robotMovesTO);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
        TextView moves = findViewById(R.id.moves);

        moves.setText(moves.getText().toString() + b.getText().toString() + " ");
        if(b.getText().toString().equals("C")){
            MainActivity.CargoshipScoredTO[0]++;
        }
        if(b.getText().toString().equals("H")){
            MainActivity.CargoshipScoredTO[1]++;
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
    //TODO fix something?!
    public void add (View v){
        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;
        if(b.getId() == R.id.TO_H3RR){
            moves.setText(moves.getText().toString() + "H3RR ");
            MainActivity.rocketScoredSS[0]++;
        }
        if(b.getId() == R.id.TO_C1L){
            moves.setText(moves.getText().toString() + "C1L ");
            MainActivity.rocketScoredSS[1]++;
        }
        if(b.getId() == R.id.TO_C1R){
            moves.setText(moves.getText().toString() + "C1R ");
            MainActivity.rocketScoredSS[2]++;
        }
        if(b.getId() == R.id.TO_C2L){
            moves.setText(moves.getText().toString() + "C2L ");
            MainActivity.rocketScoredSS[3]++;
        }
        if(b.getId() == R.id.TO_C2R){
            moves.setText(moves.getText().toString() + "C2R ");
            MainActivity.rocketScoredSS[4]++;
        }
        if(b.getId() == R.id.TO_C3L){
            moves.setText(moves.getText().toString() + "C3L ");
            MainActivity.rocketScoredSS[5]++;
        }
        if(b.getId() == R.id.TO_C3R){
            moves.setText(moves.getText().toString() + "C3R ");
            MainActivity.rocketScoredSS[6]++;
        }
        if(b.getId() == R.id.TO_H1LL){
            moves.setText(moves.getText().toString() + "H1LL ");
            MainActivity.rocketScoredSS[9]++;
        }
        if(b.getId() == R.id.TO_H1LR){
            moves.setText(moves.getText().toString() + "H1LR ");
            MainActivity.rocketScoredSS[10]++;
        }
        if(b.getId() == R.id.TO_H1RL){
            moves.setText(moves.getText().toString() + "H1RL ");
            MainActivity.rocketScoredSS[11]++;
        }
        if(b.getId() == R.id.TO_H1RR){
            moves.setText(moves.getText().toString() + "H1RR ");
            MainActivity.rocketScoredSS[12]++;
        }
        if(b.getId() == R.id.TO_H2LL){
            moves.setText(moves.getText().toString() + "H2LL ");
            MainActivity.rocketScoredSS[13]++;
        }
        if(b.getId() == R.id.TO_H2LR){
            moves.setText(moves.getText().toString() + "H2LR ");
            MainActivity.rocketScoredSS[14]++;
        }
        if(b.getId() == R.id.TO_H2RL){
            moves.setText(moves.getText().toString() + "H2RL ");
            MainActivity.rocketScoredSS[15]++;
        }
        if(b.getId() == R.id.TO_H2RR){
            moves.setText(moves.getText().toString() + "H2RR ");
            MainActivity.rocketScoredSS[16]++;
        }
        if(b.getId() == R.id.TO_H3LL){
            moves.setText(moves.getText().toString() + "H3LL ");
            MainActivity.rocketScoredSS[17]++;
        }
        if(b.getId() == R.id.TO_H3LR){
            moves.setText(moves.getText().toString() + "H3LR ");
            MainActivity.rocketScoredSS[18]++;
        }
        if(b.getId() == R.id.TO_H3RL){
            moves.setText(moves.getText().toString() + "H3RL ");
            MainActivity.rocketScoredSS[19]++;
        }

        //moves.setText(moves.getText().toString() + Integer.toString((b.getId()%20)) + " ");
    }

}
