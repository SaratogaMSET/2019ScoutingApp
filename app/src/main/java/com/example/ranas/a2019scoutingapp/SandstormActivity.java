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
                case R.id.navigation_notifications:
                    MainActivity.robotMovesSS = moves.getText().toString();
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

        moves.setText(MainActivity.robotMovesSS);
        H.setVisibility(View.INVISIBLE);
        C.setVisibility(View.INVISIBLE);
        Null.setVisibility(View.INVISIBLE);

        Log.d("Stuff", MainActivity.ssPos);

        if(MainActivity.ssPos == "L1"){
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
        if(MainActivity.ssPos == "R1"){
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
        if(MainActivity.ssPos == "L2"){
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
        if(MainActivity.ssPos == "R2"){
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
        if(MainActivity.ssPos == "M1"){
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
        if(MainActivity.startedWithSS == "C"){
            C.setBackgroundColor(Color.GREEN);
        }else if(MainActivity.startedWithSS == "H"){
            H.setBackgroundColor((Color.GREEN));
        } else if (MainActivity.startedWithSS == "--"){
            Null.setBackgroundColor(Color.GREEN);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @SuppressLint("ResourceType")
    public void add (View v){
        TextView moves = findViewById(R.id.moves);
        Button b = (Button) v;
        if((b.getId()%20) == 0){
            moves.setText(moves.getText().toString() + "H3RR ");
            MainActivity.rocketScoredSS[0]++;
        }
        if((b.getId()%20) == 1){
            moves.setText(moves.getText().toString() + "C1L ");
            MainActivity.rocketScoredSS[1]++;
        }
        if((b.getId()%20) == 2){
            moves.setText(moves.getText().toString() + "C1R ");
            MainActivity.rocketScoredSS[2]++;
        }
        if((b.getId()%20) == 3){
            moves.setText(moves.getText().toString() + "C2L ");
            MainActivity.rocketScoredSS[3]++;
        }
        if((b.getId()%20) == 4){
            moves.setText(moves.getText().toString() + "C2R ");
            MainActivity.rocketScoredSS[4]++;
        }
        if((b.getId()%20) == 5){
            moves.setText(moves.getText().toString() + "C3L ");
            MainActivity.rocketScoredSS[5]++;
        }
        if((b.getId()%20) == 6){
            moves.setText(moves.getText().toString() + "C3R ");
            MainActivity.rocketScoredSS[6]++;
        }
//        if((b.getId()%20) == 7){
//            moves.setText(moves.getText().toString() + "C1L ");
//        }
//        if((b.getId()%20) == 8){
//            moves.setText(moves.getText().toString() + "C1L ");
//        }
        if((b.getId()%20) == 9){
            moves.setText(moves.getText().toString() + "H1LL ");
            MainActivity.rocketScoredSS[9]++;
        }
        if((b.getId()%20) == 10){
            moves.setText(moves.getText().toString() + "H1LR ");
            MainActivity.rocketScoredSS[10]++;
        }
        if((b.getId()%20) == 11){
            moves.setText(moves.getText().toString() + "H1RL ");
            MainActivity.rocketScoredSS[11]++;
        }
        if((b.getId()%20) == 12){
            moves.setText(moves.getText().toString() + "H1RR ");
            MainActivity.rocketScoredSS[12]++;
        }
        if((b.getId()%20) == 13){
            moves.setText(moves.getText().toString() + "H2LL ");
            MainActivity.rocketScoredSS[13]++;
        }
        if((b.getId()%20) == 14){
            moves.setText(moves.getText().toString() + "H2LR ");
            MainActivity.rocketScoredSS[14]++;
        }
        if((b.getId()%20) == 15){
            moves.setText(moves.getText().toString() + "H2RL ");
            MainActivity.rocketScoredSS[15]++;
        }
        if((b.getId()%20) == 16){
            moves.setText(moves.getText().toString() + "H2RR ");
            MainActivity.rocketScoredSS[16]++;
        }
        if((b.getId()%20) == 17){
            moves.setText(moves.getText().toString() + "H3LL ");
            MainActivity.rocketScoredSS[17]++;
        }
        if((b.getId()%20) == 18){
            moves.setText(moves.getText().toString() + "H3LR ");
            MainActivity.rocketScoredSS[18]++;
        }
        if((b.getId()%20) == 19){
            moves.setText(moves.getText().toString() + "H3RL ");
            MainActivity.rocketScoredSS[19]++;
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
            MainActivity.startedWithSS = "C";
        }
        if(b.getId() == R.id.H){
            b.setBackgroundColor(Color.GREEN);
            C.setBackgroundColor(Color.rgb(255,136,0));
            Null.setBackgroundColor(Color.rgb(192,192,192));
            MainActivity.startedWithSS = "H";
        }
        if(b.getId() == R.id.Null){
            b.setBackgroundColor(Color.GREEN);
            H.setBackgroundColor(Color.rgb(255,187,51));
            C.setBackgroundColor(Color.rgb(255,136,0));
            MainActivity.startedWithSS = "--";
        }
    }

    public void CargoshipAdd(View v){
        Button b = (Button) v;
        TextView moves = findViewById(R.id.moves);

        moves.setText(moves.getText().toString() + b.getText().toString() + " ");
        if(b.getText().toString() == "C"){
            MainActivity.CargoshipScoredSS[0]++;
        }
        if(b.getText().toString() == "H"){
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
            H.setX(l1.getX()+ 140);
            H.setY(l1.getY());
            C.setX(H.getX() + 85);
            C.setY(H.getY());
            Null.setX(C.getX() + 85);
            Null.setY(C.getY());
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.l2){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
            H.setX(l1.getX()+140);
            H.setY(l1.getY());
            C.setX(H.getX() + 85);
            C.setY(H.getY());
            Null.setX(C.getX() + 85);
            Null.setY(C.getY());
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.r2){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
            H.setX(r1.getX()+140);
            H.setY(r1.getY());
            C.setX(H.getX() + 85);
            C.setY(H.getY());
            Null.setX(C.getX() + 85);
            Null.setY(C.getY());
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);

        }
        if(v.getId() == R.id.r1){
            l1.setBackgroundColor(Color.TRANSPARENT);
            m1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
            H.setX(r1.getX()+140);
            H.setY(r1.getY());
            C.setX(H.getX() + 85);
            C.setY(H.getY());
            Null.setX(C.getX() + 85);
            Null.setY(C.getY());
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.m1){
            l1.setBackgroundColor(Color.TRANSPARENT);
            r1.setBackgroundColor(Color.TRANSPARENT);
            l2.setBackgroundColor(Color.TRANSPARENT);
            r2.setBackgroundColor(Color.TRANSPARENT);
            H.setX(m1.getX()+140);
            H.setY(m1.getY());
            C.setX(H.getX() + 85);
            C.setY(H.getY());
            Null.setX(C.getX() + 85);
            Null.setY(C.getY());
            H.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            Null.setVisibility(View.VISIBLE);
        }
    }

}
