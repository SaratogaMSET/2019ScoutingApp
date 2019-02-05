package com.example.ranas.a2019scoutingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.MalformedInputException;

import static com.example.ranas.a2019scoutingapp.MainActivity.unsupportedClimb;

public class NotesActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            EditText myNotes = findViewById(R.id.notes);
            MainActivity.myNotes = myNotes.getText().toString();
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
        setContentView(R.layout.activity_notes);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        EditText notes = findViewById(R.id.notes);
        notes.setText(MainActivity.myNotes);

        Button badDrive = findViewById(R.id.badDrive);
        Button AveDrive = findViewById(R.id.AveDrive);
        Button GoodDrive = findViewById(R.id.GoodDrive);

        Button BadDep = findViewById(R.id.BadDep);
        Button AveDep = findViewById(R.id.AveDep);
        Button GoodDep = findViewById(R.id.GoodDep);

        Button BadDef = findViewById(R.id.BadDef);
        Button AveDef = findViewById(R.id.AveDef);
        Button GoodDef = findViewById(R.id.GoodDef);

        Button rocketScoreNo = findViewById(R.id.rocketScoreNo);
        Button rocketScoreYes = findViewById(R.id.rocketScoreYes);
        if(!MainActivity.checked){
            rocketScoreYes.setBackgroundColor(Color.GRAY);
            rocketScoreNo.setBackgroundColor(Color.GREEN);
        } else {
            MainActivity.checked = true;
            rocketScoreYes.setBackgroundColor(Color.GREEN);
            rocketScoreNo.setBackgroundColor(Color.GRAY);
        }

        if(MainActivity.driving.equals("Bad")){
            driving(badDrive);
        } else if(MainActivity.driving.equals("Average")){
            driving(AveDrive);
        } else if(MainActivity.driving.equals("Good")){
            driving(GoodDrive);
        }

        if(MainActivity.accuracy.equals("Bad")){
            acc(BadDep);
        } else if(MainActivity.accuracy.equals("Average")){
            acc(AveDep);
        } else if(MainActivity.accuracy.equals("Good")){
            acc(GoodDep);
        }

        if(MainActivity.defense.equals("Bad")){
            defense(BadDef);
        } else if(MainActivity.defense.equals("Average")){
            defense(AveDef);
        } else if(MainActivity.defense.equals("Good")){
            defense(GoodDef);
        }

        Button noClimb = findViewById(R.id.noClimb);
        Button climb1 = findViewById(R.id.climb1);
        Button climb2 = findViewById(R.id.climb2);
        Button climb3 = findViewById(R.id.climb3);

        if(MainActivity.unsupportedClimb == 0){
            unsupportedClimb(noClimb);
        } else if(MainActivity.unsupportedClimb == 1){
            unsupportedClimb(climb1);
        } else if(MainActivity.unsupportedClimb == 2){
            unsupportedClimb(climb2);
        } else if(MainActivity.unsupportedClimb == 3){
            unsupportedClimb(climb3);
        }

        Button noSupport = findViewById(R.id.noSupport);
        Button support2 = findViewById(R.id.support2);
        Button support3 = findViewById(R.id.support3);

        if(MainActivity.support == 0){
            support(noSupport);
        } else if(MainActivity.support == 2){
            support(support2);
        } else if(MainActivity.support == 3){
            support(support3);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void support (View v){
        Button noSupport = findViewById(R.id.noSupport);
        Button support2 = findViewById(R.id.support2);
        Button support3 = findViewById(R.id.support3);
        Button b = (Button) v;

        if(v.getId() == R.id.noSupport){
            noSupport.setBackgroundColor(Color.GREEN);
            support2.setBackgroundColor(Color.GRAY);
            support3.setBackgroundColor(Color.GRAY);
            MainActivity.support = 0;
        }
        if(v.getId() == R.id.support2){
            noSupport.setBackgroundColor(Color.GRAY);
            support2.setBackgroundColor(Color.GREEN);
            support3.setBackgroundColor(Color.GRAY);
            MainActivity.support = 2;

        }
        if(v.getId() == R.id.support3){
            noSupport.setBackgroundColor(Color.GRAY);
            support2.setBackgroundColor(Color.GRAY);
            support3.setBackgroundColor(Color.GREEN);
            MainActivity.support = 3;
        }
    }

    public void unsupportedClimb (View v){
        Button noClimb = findViewById(R.id.noClimb);
        Button climb1 = findViewById(R.id.climb1);
        Button climb2 = findViewById(R.id.climb2);
        Button climb3 = findViewById(R.id.climb3);
        Button b = (Button)v;

        if(v.getId() == R.id.noClimb){
            noClimb.setBackgroundColor(Color.GREEN);
            climb1.setBackgroundColor(Color.GRAY);
            climb2.setBackgroundColor(Color.GRAY);
            climb3.setBackgroundColor(Color.GRAY);
            unsupportedClimb = 0;
        }
        if(v.getId() == R.id.climb1){
            noClimb.setBackgroundColor(Color.GRAY);
            climb1.setBackgroundColor(Color.GREEN);
            climb2.setBackgroundColor(Color.GRAY);
            climb3.setBackgroundColor(Color.GRAY);
            unsupportedClimb = 1;
        }
        if(v.getId() == R.id.climb2){
            noClimb.setBackgroundColor(Color.GRAY);
            climb1.setBackgroundColor(Color.GRAY);
            climb2.setBackgroundColor(Color.GREEN);
            climb3.setBackgroundColor(Color.GRAY);
            unsupportedClimb = 2;
        }
        if(v.getId() == R.id.climb3){
            noClimb.setBackgroundColor(Color.GRAY);
            climb1.setBackgroundColor(Color.GRAY);
            climb2.setBackgroundColor(Color.GRAY);
            climb3.setBackgroundColor(Color.GREEN);
            unsupportedClimb = 3;
        }
    }

    public void driving (View v){
        Button badDrive = findViewById(R.id.badDrive);
        Button AveDrive = findViewById(R.id.AveDrive);
        Button GoodDrive = findViewById(R.id.GoodDrive);
        Button b = (Button)v;
        MainActivity.driving = b.getText().toString();
        if(v.getId() == R.id.badDrive){
            badDrive.setBackgroundColor(Color.GREEN);
            AveDrive.setBackgroundColor(Color.GRAY);
            GoodDrive.setBackgroundColor(Color.GRAY);
        }
        if(v.getId() == R.id.AveDrive){
            badDrive.setBackgroundColor(Color.GRAY);
            AveDrive.setBackgroundColor(Color.GREEN);
            GoodDrive.setBackgroundColor(Color.GRAY);
        }
        if(v.getId() == R.id.GoodDrive){
            badDrive.setBackgroundColor(Color.GRAY);
            AveDrive.setBackgroundColor(Color.GRAY);
            GoodDrive.setBackgroundColor(Color.GREEN);
        }
    }

    public void acc (View v){
        Button BadDep = findViewById(R.id.BadDep);
        Button AveDep = findViewById(R.id.AveDep);
        Button GoodDep = findViewById(R.id.GoodDep);
        Button b = (Button)v;
        MainActivity.accuracy = b.getText().toString();
        if(v.getId() == R.id.BadDep){
            BadDep.setBackgroundColor(Color.GREEN);
            AveDep.setBackgroundColor(Color.GRAY);
            GoodDep.setBackgroundColor(Color.GRAY);
        }
        if(v.getId() == R.id.AveDep){
            BadDep.setBackgroundColor(Color.GRAY);
            AveDep.setBackgroundColor(Color.GREEN);
            GoodDep.setBackgroundColor(Color.GRAY);
        }
        if(v.getId() == R.id.GoodDep){
            BadDep.setBackgroundColor(Color.GRAY);
            AveDep.setBackgroundColor(Color.GRAY);
            GoodDep.setBackgroundColor(Color.GREEN);
        }
    }

    public void defense (View v){
        Button BadDef = findViewById(R.id.BadDef);
        Button AveDef = findViewById(R.id.AveDef);
        Button GoodDef = findViewById(R.id.GoodDef);
        Button b = (Button)v;
        MainActivity.defense = b.getText().toString();
        if(v.getId() == R.id.BadDef){
            BadDef.setBackgroundColor(Color.GREEN);
            AveDef.setBackgroundColor(Color.GRAY);
            GoodDef.setBackgroundColor(Color.GRAY);
        }
        if(v.getId() == R.id.AveDef){
            BadDef.setBackgroundColor(Color.GRAY);
            AveDef.setBackgroundColor(Color.GREEN);
            GoodDef.setBackgroundColor(Color.GRAY);
        }
        if(v.getId() == R.id.GoodDef){
            BadDef.setBackgroundColor(Color.GRAY);
            AveDef.setBackgroundColor(Color.GRAY);
            GoodDef.setBackgroundColor(Color.GREEN);
        }
    }

    public void toggle(View v){
        Button rocketScoreNo = findViewById(R.id.rocketScoreNo);
        Button rocketScoreYes = findViewById(R.id.rocketScoreYes);
        if(v.getId() == rocketScoreNo.getId()){
            MainActivity.checked = false;
            rocketScoreYes.setBackgroundColor(Color.GRAY);
            rocketScoreNo.setBackgroundColor(Color.GREEN);
        } else {
            MainActivity.checked = true;
            rocketScoreYes.setBackgroundColor(Color.GREEN);
            rocketScoreNo.setBackgroundColor(Color.GRAY);
        }
    }

    public void send (View v) throws IOException {
        // ready to put all the data
        EditText myNotes = findViewById(R.id.notes);
        MainActivity.myNotes = myNotes.getText().toString();

        File directory = getExternalFilesDir(null);
        Log.d("sendScoutReports", "Directory is " + directory.getAbsolutePath());
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        String filename = "ScoutingData_" + android_id + ".txt";

        if(MainActivity.driving.equals("") || MainActivity.accuracy.equals("") || MainActivity.defense.equals("") || myNotes.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            File entry = new File(directory, filename);
            if(!entry.exists()){
                entry.createNewFile();
                Toast.makeText(getApplicationContext(), "created file", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "file exists", Toast.LENGTH_LONG).show();
            }
            JSONObject item = new JSONObject();
            //Toast.makeText(getApplicationContext(), "Created JSON", Toast.LENGTH_LONG).show();

            item.put("Team_#", Integer.valueOf(MainActivity.myTeamNumber));
            item.put("Match_#", Integer.valueOf(MainActivity.myMatchNumber));
            item.put("Alliance", MainActivity.alliance);
            int temp = 0;
            int temp2 = 0;
            for(int x = 0; x < 7; x++){
                temp += MainActivity.rocketScoredSS[x];
            }
            for(int x = 0; x < MainActivity.rocketScoredSS.length; x++){
                temp2 += MainActivity.rocketScoredSS[x];
            }
            item.put("Crg_scrd in SS in rkt", temp);
            item.put("Hch_scrd in SS on rkt", temp2 - temp);
            //Toast.makeText(getApplicationContext(), "part 1a", Toast.LENGTH_LONG).show();

            //Toast.makeText(getApplicationContext(), "part 1", Toast.LENGTH_LONG).show();

            item.put("Crg_scrd in the rkt_lvl_1", (MainActivity.rocketScoredSS[1] + MainActivity.rocketScoredSS[2] +
                    MainActivity.rocketScoredTO[1] + MainActivity.rocketScoredTO[2]));
            item.put("Hch_scrd on the rkt_lvl_1", (MainActivity.rocketScoredSS[9] + MainActivity.rocketScoredSS[10] + MainActivity.rocketScoredSS[11] + MainActivity.rocketScoredSS[12] +
                    MainActivity.rocketScoredTO[9] + MainActivity.rocketScoredTO[10] + MainActivity.rocketScoredTO[11] + MainActivity.rocketScoredTO[12]));
            item.put("Crg_scrd in the rkt_lvl_2", (MainActivity.rocketScoredSS[3] + MainActivity.rocketScoredSS[4] +
                    MainActivity.rocketScoredTO[3] + MainActivity.rocketScoredTO[4]));
            item.put("Hch_scrd on the rkt_lvl_2", (MainActivity.rocketScoredSS[13] + MainActivity.rocketScoredSS[14] + MainActivity.rocketScoredSS[15] + MainActivity.rocketScoredSS[16] +
                    MainActivity.rocketScoredTO[13] + MainActivity.rocketScoredTO[14] + MainActivity.rocketScoredTO[15] + MainActivity.rocketScoredTO[16]));
            item.put("Crg_scrd in the rkt_lvl_3", (MainActivity.rocketScoredSS[5] + MainActivity.rocketScoredSS[6] +
                    MainActivity.rocketScoredTO[5] + MainActivity.rocketScoredTO[6]));
            item.put("Hch_scrd on the rkt_lvl_3", (MainActivity.rocketScoredSS[17] + MainActivity.rocketScoredSS[18] + MainActivity.rocketScoredSS[19] + MainActivity.rocketScoredSS[0] +
                    MainActivity.rocketScoredTO[17] + MainActivity.rocketScoredTO[18] + MainActivity.rocketScoredTO[19] + MainActivity.rocketScoredTO[0]));
            //TODO "Crg_shp sctn scrd_in_SS": "S1, S2",
            item.put("Crg_shp sctn scrd_in_SS", "DO SOMETHING!!!!!!!!!!!!!!!!!!!!!!!");


            item.put("Ttl_crg scrd in crg_shp", MainActivity.CargoshipScoredSS[0] + MainActivity.CargoshipScoredTO[0]);
            item.put("Ttl_hch scrd on crg_shp", MainActivity.CargoshipScoredSS[1] + MainActivity.CargoshipScoredTO[1]);
            item.put("Ttl_# of grnd_pkups", MainActivity.groundC+MainActivity.groundH);

            if(MainActivity.checked){
                item.put("Scrd_both sides_of_rkt", 1);
            } else {
                item.put("Scrd_both sides_of_rkt", 0);
            }
            item.put("Starting position", MainActivity.ssPos);
            item.put("Preloaded game_piece", MainActivity.startedWithSS);
            if(MainActivity.ssPos.charAt(1) == '2'){
                item.put("Starting on HAB_lvl_2", 1);
            } else {
                item.put("Starting on HAB_lvl_2", 0);
            }

//            if(MainActivity.unsupportedClimb != 0){
//                item.put("Performed an unsupported climb (0-No, 1-Yes)", 1);
//            } else {
//                item.put("Performed an unsupported climb (0-No, 1-Yes)", 0);
//            }
//            item.put("Unsupported what level?", MainActivity.unsupportedClimb);
//            if(MainActivity.support != 0){
//                item.put("Supported another robot (0-No, 1-Yes)", 1);
//            } else {
//                item.put("Supported another robot (0-No, 1-Yes)", 0);
//            }
            //item.put("Supported what level?", MainActivity.support);

            if(MainActivity.unsupportedClimb == 0){
                item.put("Ended on HAB_lvl_1", 0);
                item.put("Ended on HAB_lvl_2", 0);
                item.put("Ended on HAB_lvl_3", 0);
            } else
            if(MainActivity.unsupportedClimb == 1){
                item.put("Ended on HAB_lvl_1", 1);
                item.put("Ended on HAB_lvl_2", 0);
                item.put("Ended on HAB_lvl_3", 0);
            } else
            if(MainActivity.unsupportedClimb == 2){
                item.put("Ended on HAB_lvl_1", 0);
                item.put("Ended on HAB_lvl_2", 1);
                item.put("Ended on HAB_lvl_3", 0);
            } else
            if(MainActivity.unsupportedClimb == 3){
                item.put("Ended on HAB_lvl_1", 0);
                item.put("Ended on HAB_lvl_2", 0);
                item.put("Ended on HAB_lvl_3", 1);
            }


            item.put("Penalties", (MainActivity.penaltiesSS + MainActivity.penaltiesTO));
            item.put("Driving", MainActivity.driving);
            item.put("Deployment Accuracy", MainActivity.accuracy);
            item.put("Defense", MainActivity.defense);
            item.put("Notes", MainActivity.myNotes);
            item.put("Scouter", MainActivity.myScouterName);
            //Toast.makeText(getApplicationContext(), "part 2", Toast.LENGTH_LONG).show();

            FileOutputStream fos = new FileOutputStream(entry, true);
            fos.write(item.toString(4).getBytes());
            fos.write(",\n".getBytes());
            fos.close();

            Toast.makeText(getApplicationContext(), "Thanks for scouting match number " + MainActivity.myMatchNumber + "!", Toast.LENGTH_LONG).show();
            Intent returnHome = new Intent();
            returnHome.setClass(getApplicationContext(), MainActivity.class);
            returnHome.putExtra("path", directory.getAbsolutePath());
            startActivity(returnHome);
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "try again", Toast.LENGTH_LONG).show();
            Log.d("exception", "error is blah blah" + e.toString());
        }

    }

}
