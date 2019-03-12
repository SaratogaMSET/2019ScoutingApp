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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class NotesActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            EditText myNotes = findViewById(R.id.notes);
            Vars.myNotes = myNotes.getText().toString();
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

    public static Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        i = getIntent();

        if(i.getBooleanExtra("noShow", false)){
            send(findViewById(R.id.button18));
        }

        EditText notes = findViewById(R.id.notes);
        notes.setText(Vars.myNotes);

        Button badDrive = findViewById(R.id.badDrive);
        Button AveDrive = findViewById(R.id.AveDrive);
        Button GoodDrive = findViewById(R.id.GoodDrive);

        TextView t = findViewById(R.id.penalty);
        t.setText(Integer.toString(Vars.penalties));

        Button BadDep = findViewById(R.id.BadDep);
        Button AveDep = findViewById(R.id.AveDep);
        Button GoodDep = findViewById(R.id.GoodDep);

        Button BadDef = findViewById(R.id.BadDef);
        Button AveDef = findViewById(R.id.AveDef);
        Button GoodDef = findViewById(R.id.GoodDef);

//        Button rocketScoreNo = findViewById(R.id.rocketScoreNo);
//        Button rocketScoreYes = findViewById(R.id.rocketScoreYes);
//        if(!Vars.checked){
//            rocketScoreYes.setBackgroundColor(Color.GRAY);
//            rocketScoreNo.setBackgroundColor(Color.GREEN);
//        } else {
//            Vars.checked = true;
//            rocketScoreYes.setBackgroundColor(Color.GREEN);
//            rocketScoreNo.setBackgroundColor(Color.GRAY);
//        }

        if(Vars.driving.equals("Bad")){
            driving(badDrive);
        } else if(Vars.driving.equals("Average")){
            driving(AveDrive);
        } else if(Vars.driving.equals("Good")){
            driving(GoodDrive);
        } else if(Vars.driving.equals("NA")){
            driving(findViewById(R.id.naDrive));
        }

        if(Vars.accuracy.equals("Bad")){
            acc(BadDep);
        } else if(Vars.accuracy.equals("Average")){
            acc(AveDep);
        } else if(Vars.accuracy.equals("Good")){
            acc(GoodDep);
        } else if(Vars.accuracy.equals("NA")){
            acc(findViewById(R.id.naDep));
        }

        if(Vars.unsure.equals("no")){
            findViewById(R.id.unsure).setBackgroundColor(Color.GRAY);
        } else if(Vars.unsure.equals("yes")){
            findViewById(R.id.unsure).setBackgroundColor(Color.DKGRAY);
        }

        if(Vars.groundC == 1){
            findViewById(R.id.cargo).setBackgroundColor(Color.DKGRAY);
        }

        if(Vars.groundH == 1){
            findViewById(R.id.hatch).setBackgroundColor(Color.DKGRAY);
        }

        if(Vars.defense.equals("Bad")){
            defense(BadDef);
        } else if(Vars.defense.equals("Average")){
            defense(AveDef);
        } else if(Vars.defense.equals("Good")){
            defense(GoodDef);
        } else if(Vars.defense.equals("NA")){
            defense(findViewById(R.id.naDef));
        }

        Button noClimb = findViewById(R.id.noClimb);
        Button climb1 = findViewById(R.id.climb1);
        Button climb2 = findViewById(R.id.climb2);
        Button climb3 = findViewById(R.id.climb3);

        if(Vars.unsupportedClimb == 0){
            unsupportedClimb(noClimb);
        } else if(Vars.unsupportedClimb == 1){
            unsupportedClimb(climb1);
        } else if(Vars.unsupportedClimb == 2){
            unsupportedClimb(climb2);
        } else if(Vars.unsupportedClimb == 3){
            unsupportedClimb(climb3);
        }



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
            Vars.unsupportedClimb = 0;
        }
        if(v.getId() == R.id.climb1){
            noClimb.setBackgroundColor(Color.GRAY);
            climb1.setBackgroundColor(Color.GREEN);
            climb2.setBackgroundColor(Color.GRAY);
            climb3.setBackgroundColor(Color.GRAY);
            Vars.unsupportedClimb = 1;
        }
        if(v.getId() == R.id.climb2){
            noClimb.setBackgroundColor(Color.GRAY);
            climb1.setBackgroundColor(Color.GRAY);
            climb2.setBackgroundColor(Color.GREEN);
            climb3.setBackgroundColor(Color.GRAY);
            Vars.unsupportedClimb = 2;
        }
        if(v.getId() == R.id.climb3){
            noClimb.setBackgroundColor(Color.GRAY);
            climb1.setBackgroundColor(Color.GRAY);
            climb2.setBackgroundColor(Color.GRAY);
            climb3.setBackgroundColor(Color.GREEN);
            Vars.unsupportedClimb = 3;
        }
    }

    public void driving (View v){
        Button naDrive = findViewById(R.id.naDrive);
        Button badDrive = findViewById(R.id.badDrive);
        Button AveDrive = findViewById(R.id.AveDrive);
        Button GoodDrive = findViewById(R.id.GoodDrive);
        Button b = (Button)v;
        Vars.driving = b.getText().toString();
        if(b.getId() == R.id.badDrive){
            badDrive.setBackgroundColor(Color.GREEN);
            AveDrive.setBackgroundColor(Color.GRAY);
            GoodDrive.setBackgroundColor(Color.GRAY);
            naDrive.setBackgroundColor(Color.GRAY);
        }
        if(b.getId() == R.id.AveDrive){
            badDrive.setBackgroundColor(Color.GRAY);
            AveDrive.setBackgroundColor(Color.GREEN);
            GoodDrive.setBackgroundColor(Color.GRAY);
            naDrive.setBackgroundColor(Color.GRAY);
        }
        if(b.getId() == R.id.GoodDrive){
            badDrive.setBackgroundColor(Color.GRAY);
            AveDrive.setBackgroundColor(Color.GRAY);
            GoodDrive.setBackgroundColor(Color.GREEN);
            naDrive.setBackgroundColor(Color.GRAY);
        }
        if(b.getId() == R.id.naDrive){
            badDrive.setBackgroundColor(Color.GRAY);
            AveDrive.setBackgroundColor(Color.GRAY);
            GoodDrive.setBackgroundColor(Color.GRAY);
            naDrive.setBackgroundColor(Color.GREEN);
        }
    }

    public void acc (View v){
        Button BadDep = findViewById(R.id.BadDep);
        Button AveDep = findViewById(R.id.AveDep);
        Button GoodDep = findViewById(R.id.GoodDep);
        Button naDep = findViewById(R.id.naDep);
        Button b = (Button)v;
        Vars.accuracy = b.getText().toString();
        if(b.getId() == R.id.BadDep){
            BadDep.setBackgroundColor(Color.GREEN);
            AveDep.setBackgroundColor(Color.GRAY);
            GoodDep.setBackgroundColor(Color.GRAY);
            naDep.setBackgroundColor(Color.GRAY);
        }
        if(b.getId() == R.id.AveDep){
            BadDep.setBackgroundColor(Color.GRAY);
            AveDep.setBackgroundColor(Color.GREEN);
            GoodDep.setBackgroundColor(Color.GRAY);
            naDep.setBackgroundColor(Color.GRAY);
        }
        if(b.getId() == R.id.GoodDep){
            BadDep.setBackgroundColor(Color.GRAY);
            AveDep.setBackgroundColor(Color.GRAY);
            GoodDep.setBackgroundColor(Color.GREEN);
            naDep.setBackgroundColor(Color.GRAY);
        }
        if(b.getId() == R.id.naDep){
            BadDep.setBackgroundColor(Color.GRAY);
            AveDep.setBackgroundColor(Color.GRAY);
            GoodDep.setBackgroundColor(Color.GRAY);
            naDep.setBackgroundColor(Color.GREEN);
        }
    }

    public void defense (View v){
        Button BadDef = findViewById(R.id.BadDef);
        Button AveDef = findViewById(R.id.AveDef);
        Button GoodDef = findViewById(R.id.GoodDef);
        Button naDef = findViewById(R.id.naDef);
        Button b = (Button)v;
        Vars.defense = b.getText().toString();
        if(b.getId() == R.id.BadDef){
            BadDef.setBackgroundColor(Color.GREEN);
            AveDef.setBackgroundColor(Color.GRAY);
            GoodDef.setBackgroundColor(Color.GRAY);
            naDef.setBackgroundColor(Color.GRAY);
        }
        if(b.getId() == R.id.AveDef){
            BadDef.setBackgroundColor(Color.GRAY);
            AveDef.setBackgroundColor(Color.GREEN);
            GoodDef.setBackgroundColor(Color.GRAY);
            naDef.setBackgroundColor(Color.GRAY);
        }
        if(b.getId() == R.id.GoodDef){
            BadDef.setBackgroundColor(Color.GRAY);
            AveDef.setBackgroundColor(Color.GRAY);
            GoodDef.setBackgroundColor(Color.GREEN);
            naDef.setBackgroundColor(Color.GRAY);
        }
        if(b.getId() == R.id.naDef){
            BadDef.setBackgroundColor(Color.GRAY);
            AveDef.setBackgroundColor(Color.GRAY);
            GoodDef.setBackgroundColor(Color.GRAY);
            naDef.setBackgroundColor(Color.GREEN);
        }
    }

//    public void toggle(View v){
//        Button rocketScoreNo = findViewById(R.id.rocketScoreNo);
//        Button rocketScoreYes = findViewById(R.id.rocketScoreYes);
//        if(v.getId() == rocketScoreNo.getId()){
//            Vars.checked = false;
//            rocketScoreYes.setBackgroundColor(Color.GRAY);
//            rocketScoreNo.setBackgroundColor(Color.GREEN);
//        } else {
//            Vars.checked = true;
//            rocketScoreYes.setBackgroundColor(Color.GREEN);
//            rocketScoreNo.setBackgroundColor(Color.GRAY);
//        }
//    }

    public void ground (View v){
        Button b = (Button) v;
        if(b.getText().equals("Hatch")){
            Vars.unsure = "no";
            if(Vars.groundH == 0){
                Vars.groundH = 1;
                findViewById(R.id.hatch).setBackgroundColor(Color.DKGRAY);
                findViewById(R.id.unsure).setBackgroundColor(Color.GRAY);
            } else {
                Vars.groundH = 0;
                findViewById(R.id.hatch).setBackgroundColor(Color.parseColor("#ffffbb33"));
                findViewById(R.id.unsure).setBackgroundColor(Color.GRAY);
            }
        } else if(b.getText().equals("Cargo")) {
            Vars.unsure = "no";
            if(Vars.groundC == 0){
                Vars.groundC = 1;
                findViewById(R.id.cargo).setBackgroundColor(Color.DKGRAY);
                findViewById(R.id.unsure).setBackgroundColor(Color.GRAY);
            } else {
                Vars.groundC = 0;
                findViewById(R.id.cargo).setBackgroundColor(Color.parseColor("#ffff8800"));
                findViewById(R.id.unsure).setBackgroundColor(Color.GRAY);
            }
        } else if(b.getText().equals("Unsure")){
            Vars.unsure = "yes";
            Vars.groundC = 0;
            Vars.groundH = 0;
            findViewById(R.id.hatch).setBackgroundColor(Color.parseColor("#ffffbb33"));
            findViewById(R.id.cargo).setBackgroundColor(Color.parseColor("#ffff8800"));
            findViewById(R.id.unsure).setBackgroundColor(Color.DKGRAY);
        }


    }

    public void penalties(View v){
        Button b = (Button) v;
        if(b.getText().equals("-1")){
            if(Vars.penalties > 0)
                Vars.penalties--;
        } else {
            Vars.penalties++;
        }

        TextView t = findViewById(R.id.penalty);
        t.setText(Integer.toString(Vars.penalties));
    }

    public void send (View v) {
        // ready to put all the data
        EditText myNotes = findViewById(R.id.notes);
        if(!i.getBooleanExtra("noShow", false))
            Vars.myNotes = myNotes.getText().toString();

        File directory = getExternalFilesDir(null);
        Log.d("sendScoutReports", "Directory is " + directory.getAbsolutePath());
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        String filename = "ScoutingData_" + android_id + ".txt";
        if(i.getBooleanExtra("noShow", false)){

        }
        if(Vars.driving.equals("") || Vars.accuracy.equals("") || Vars.defense.equals("") || Vars.myNotes.equals("") || Vars.unsure.isEmpty()){
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

            item.put("Team_#", Integer.valueOf(Vars.myTeamNumber));
            item.put("Match_#", Integer.valueOf(Vars.myMatchNumber));

            item.put("Alliance", Vars.alliance);
            item.put("Starting position", Vars.ssPos);
            item.put("Preloaded game_piece", Vars.startedWithSS);
            if(Vars.ssPos.charAt(1) == '2'){
                item.put("Starting on HAB_lvl_2", 1);
            } else {
                item.put("Starting on HAB_lvl_2", 0);
            }

            item.put("Exited HAB?", Vars.exitHab);

            int temp = 0;
            int temp2 = 0;
            for(int x = 1; x < 7; x++){
                temp += Vars.rocketScoredSS[x];
            }
            for(int x = 0; x < Vars.rocketScoredSS.length; x++){
                temp2 += Vars.rocketScoredSS[x];
            }
            item.put("Crg_scrd in SS in rkt", temp);
            item.put("Hch_scrd in SS on rkt", temp2 - temp);


            if(Vars.slots.length() != 0){
                item.put("Crg_shp sctn scrd_in_SS", Vars.slots.substring(0, Vars.slots.length()-2));
            } else {
                item.put("Crg_shp sctn scrd_in_SS", "none");
            }

            item.put("Crg_scrd in the rkt_lvl_1", (Vars.rocketScoredSS[1] + Vars.rocketScoredSS[2] +
                    Vars.rocketScoredTO[1] + Vars.rocketScoredTO[2]));
            item.put("Hch_scrd on the rkt_lvl_1", (Vars.rocketScoredSS[9] + Vars.rocketScoredSS[10] + Vars.rocketScoredSS[11] + Vars.rocketScoredSS[12] +
                    Vars.rocketScoredTO[9] + Vars.rocketScoredTO[10] + Vars.rocketScoredTO[11] + Vars.rocketScoredTO[12]));
            item.put("Crg_scrd in the rkt_lvl_2", (Vars.rocketScoredSS[3] + Vars.rocketScoredSS[4] +
                    Vars.rocketScoredTO[3] + Vars.rocketScoredTO[4]));
            item.put("Hch_scrd on the rkt_lvl_2", (Vars.rocketScoredSS[13] + Vars.rocketScoredSS[14] + Vars.rocketScoredSS[15] + Vars.rocketScoredSS[16] +
                    Vars.rocketScoredTO[13] + Vars.rocketScoredTO[14] + Vars.rocketScoredTO[15] + Vars.rocketScoredTO[16]));
            item.put("Crg_scrd in the rkt_lvl_3", (Vars.rocketScoredSS[5] + Vars.rocketScoredSS[6] +
                    Vars.rocketScoredTO[5] + Vars.rocketScoredTO[6]));
            item.put("Hch_scrd on the rkt_lvl_3", (Vars.rocketScoredSS[17] + Vars.rocketScoredSS[18] + Vars.rocketScoredSS[19] + Vars.rocketScoredSS[0] +
                    Vars.rocketScoredTO[17] + Vars.rocketScoredTO[18] + Vars.rocketScoredTO[19] + Vars.rocketScoredTO[0]));

            item.put("Ttl_crg scrd in crg_shp", Vars.CargoshipScoredSS[0] + Vars.CargoshipScoredTO[0]);
            item.put("Ttl_hch scrd on crg_shp", Vars.CargoshipScoredSS[1] + Vars.CargoshipScoredTO[1]);

            //ground pickup
            if(Vars.unsure.equals("yes")){
                item.put("Grnd_pkup hch", "unsure");
                item.put("Grnd_pkup crg", "unsure");
            }else{
                if(Vars.groundH == 1)
                    item.put("Grnd_pkup hch", "yes");
                else
                    item.put("Grnd_pkup hch", "no");
                if(Vars.groundC == 1)
                    item.put("Grnd_pkup crg", "yes");
                else
                    item.put("Grnd_pkup crg", "no");
            }
            item.put("#_hch_scrd back_side of_rckt", Vars.rocketScoredSS[0] + Vars.rocketScoredTO[0] + Vars.rocketScoredSS[12] + Vars.rocketScoredTO[12] + Vars.rocketScoredSS[16] + Vars.rocketScoredTO[16] + Vars.rocketScoredSS[10] +
                        Vars.rocketScoredTO[10] + Vars.rocketScoredSS[14] + Vars.rocketScoredTO[14] + Vars.rocketScoredSS[18] + Vars.rocketScoredTO[18]);






//            if(Vars.unsupportedClimb != 0){
//                item.put("Performed an unsupported climb (0-No, 1-Yes)", 1);
//            } else {
//                item.put("Performed an unsupported climb (0-No, 1-Yes)", 0);
//            }
//            item.put("Unsupported what level?", Vars.unsupportedClimb);
//            if(Vars.support != 0){
//                item.put("Supported another robot (0-No, 1-Yes)", 1);
//            } else {
//                item.put("Supported another robot (0-No, 1-Yes)", 0);
//            }
            //item.put("Supported what level?", Vars.support);

            if(Vars.unsupportedClimb == 0){
                item.put("Ended on HAB_lvl_1", 0);
                item.put("Ended on HAB_lvl_2", 0);
                item.put("Ended on HAB_lvl_3", 0);
            } else
            if(Vars.unsupportedClimb == 1){
                item.put("Ended on HAB_lvl_1", 1);
                item.put("Ended on HAB_lvl_2", 0);
                item.put("Ended on HAB_lvl_3", 0);
            } else
            if(Vars.unsupportedClimb == 2){
                item.put("Ended on HAB_lvl_1", 0);
                item.put("Ended on HAB_lvl_2", 1);
                item.put("Ended on HAB_lvl_3", 0);
            } else
            if(Vars.unsupportedClimb == 3){
                item.put("Ended on HAB_lvl_1", 0);
                item.put("Ended on HAB_lvl_2", 0);
                item.put("Ended on HAB_lvl_3", 1);
            }

            item.put("Driving", Vars.driving);
            item.put("Deployment Accuracy", Vars.accuracy);
            item.put("Defense", Vars.defense);
            item.put("Notes____________", Vars.myNotes);
            item.put("Penalties", (Vars.penalties));
            item.put("Scouter", Vars.myScouterName);
            //Toast.makeText(getApplicationContext(), "part 2", Toast.LENGTH_LONG).show();

            FileOutputStream fos = new FileOutputStream(entry, true);
            fos.write(item.toString(4).getBytes());
            fos.write(",\n".getBytes());
            fos.close();

            Toast.makeText(getApplicationContext(), "Thanks for scouting match number " + Vars.myMatchNumber + "!", Toast.LENGTH_LONG).show();
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
