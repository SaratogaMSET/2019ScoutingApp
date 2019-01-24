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
        setContentView(R.layout.activity_tele_op);


        TextView moves = findViewById(R.id.moves);
        moves.setText(MainActivity.robotMovesTO);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        TextView moves = findViewById(R.id.moves);

        moves.setText(moves.getText().toString() + b.getText().toString() + " ");
        if(b.getText().toString() == "C"){
            MainActivity.CargoshipScoredTO[0]++;
        }
        if(b.getText().toString() == "H"){
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
    public void add (View v){
        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;
        if((b.getId()%20) == 0){
            moves.setText(moves.getText().toString() + "H3RR ");
            MainActivity.rocketScoredTO[0]++;
        }
        if((b.getId()%20) == 1){
            moves.setText(moves.getText().toString() + "C1L ");
            MainActivity.rocketScoredTO[1]++;
        }
        if((b.getId()%20) == 2){
            moves.setText(moves.getText().toString() + "C1R ");
            MainActivity.rocketScoredTO[2]++;
        }
        if((b.getId()%20) == 3){
            moves.setText(moves.getText().toString() + "C2L ");
            MainActivity.rocketScoredTO[3]++;
        }
        if((b.getId()%20) == 4){
            moves.setText(moves.getText().toString() + "C2R ");
            MainActivity.rocketScoredTO[4]++;
        }
        if((b.getId()%20) == 5){
            moves.setText(moves.getText().toString() + "C3L ");
            MainActivity.rocketScoredTO[5]++;
        }
        if((b.getId()%20) == 6){
            moves.setText(moves.getText().toString() + "C3R ");
            MainActivity.rocketScoredTO[6]++;
        }
//        if((b.getId()%20) == 7){
//            moves.setText(moves.getText().toString() + "C1L ");
//        }
//        if((b.getId()%20) == 8){
//            moves.setText(moves.getText().toString() + "C1L ");
//        }
        if((b.getId()%20) == 9){
            moves.setText(moves.getText().toString() + "H1LL ");
            MainActivity.rocketScoredTO[9]++;
        }
        if((b.getId()%20) == 10){
            moves.setText(moves.getText().toString() + "H1LR ");
            MainActivity.rocketScoredTO[10]++;
        }
        if((b.getId()%20) == 11){
            moves.setText(moves.getText().toString() + "H1RL ");
            MainActivity.rocketScoredTO[11]++;
        }
        if((b.getId()%20) == 12){
            moves.setText(moves.getText().toString() + "H1RR ");
            MainActivity.rocketScoredTO[12]++;
        }
        if((b.getId()%20) == 13){
            moves.setText(moves.getText().toString() + "H2LL ");
            MainActivity.rocketScoredTO[13]++;
        }
        if((b.getId()%20) == 14){
            moves.setText(moves.getText().toString() + "H2LR ");
            MainActivity.rocketScoredTO[14]++;
        }
        if((b.getId()%20) == 15){
            moves.setText(moves.getText().toString() + "H2RL ");
            MainActivity.rocketScoredTO[15]++;
        }
        if((b.getId()%20) == 16){
            moves.setText(moves.getText().toString() + "H2RR ");
            MainActivity.rocketScoredTO[16]++;
        }
        if((b.getId()%20) == 17){
            moves.setText(moves.getText().toString() + "H3LL ");
            MainActivity.rocketScoredTO[17]++;
        }
        if((b.getId()%20) == 18){
            moves.setText(moves.getText().toString() + "H3LR ");
            MainActivity.rocketScoredTO[18]++;
        }
        if((b.getId()%20) == 19){
            moves.setText(moves.getText().toString() + "H3RL ");
            MainActivity.rocketScoredTO[19]++;
        }

        //moves.setText(moves.getText().toString() + Integer.toString((b.getId()%20)) + " ");
    }

}
