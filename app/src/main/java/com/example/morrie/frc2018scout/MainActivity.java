package com.example.morrie.frc2018scout;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner teamMembersSpinner = (Spinner) findViewById(R.id.scouterNameSpinner);
        ArrayAdapter<CharSequence> TMadapt = ArrayAdapter.createFromResource(this, R.array.TeamMembers, android.R.layout.simple_spinner_item);
        TMadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamMembersSpinner.setAdapter(TMadapt);

        Spinner teamNumbersSpinner = (Spinner) findViewById(R.id.teamNumberSpinner);
        ArrayAdapter<CharSequence> TNadapt = ArrayAdapter.createFromResource(this, R.array.KetteringTeamNumbers, android.R.layout.simple_spinner_item);
        TNadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamNumbersSpinner.setAdapter(TNadapt);

        Spinner eventSpinner = (Spinner) findViewById(R.id.eventSpinner);
        ArrayAdapter<CharSequence> Eadapt = ArrayAdapter.createFromResource(this, R.array.Event, android.R.layout.simple_spinner_item);
        Eadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventSpinner.setAdapter(Eadapt);

        Spinner startingLocationSpinner = (Spinner) findViewById(R.id.startingPositionSpinner);
        ArrayAdapter<CharSequence> SLadapt = ArrayAdapter.createFromResource(this, R.array.StartingLocation, android.R.layout.simple_spinner_item);
        SLadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startingLocationSpinner.setAdapter(SLadapt);

        EditText matchNum = (EditText) findViewById(R.id.matchNumberText);
        Switch allianceColor = (Switch) findViewById(R.id.allianceColorSwitch);
        CheckBox autoBaseline = (CheckBox) findViewById(R.id.baselineCheckBox);
        EditText autoSwitchNum = (EditText) findViewById(R.id.autonSwitch);
        EditText autoScaleNum = (EditText) findViewById(R.id.autonScale);
        CheckBox autoIncorrectSwitch = (CheckBox) findViewById(R.id.incorrectColorCheckbox);
        EditText teleopOpponentSwitchNum = (EditText) findViewById(R.id.teleopOpponentSwitch);
        EditText teleopScaleNum = (EditText) findViewById(R.id.teleopScale);
        EditText teleopAllianceSwitchNum = (EditText) findViewById(R.id.teleopAllianceSwitch);
        EditText vaultNum = (EditText) findViewById(R.id.vault);
        CheckBox defense = (CheckBox) findViewById(R.id.defenseCheckbox);
        CheckBox climb = (CheckBox) findViewById(R.id.climbCheckbox);
        CheckBox platformPark = (CheckBox) findViewById(R.id.platformChechbox);
        CheckBox lifted = (CheckBox) findViewById(R.id.liftedCheckbox);
        EditText comments = (EditText) findViewById(R.id.commentsText);
        Button submit = (Button) findViewById(R.id.submitButton);

        submit.setOnClickListener(v -> {
            if (teamMembersSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.scouterNameSpinner), "Please enter Scouter Name", Snackbar.LENGTH_SHORT).show();
            } else if (eventSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.eventSpinner), "Please enter the Event", Snackbar.LENGTH_SHORT).show();
            } else if (teamNumbersSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.teamNumberSpinner), "Please enter Team Number", Snackbar.LENGTH_SHORT).show();
            } else if (matchNum.getText().toString().equals("")) {
                Snackbar.make(findViewById(R.id.matchNumberText), "Please enter Match Number", Snackbar.LENGTH_SHORT).show();
            } else if (startingLocationSpinner.getSelectedItem().toString().equals("")) {
                Snackbar.make(findViewById(R.id.startingPositionSpinner), "Please enter Autonomous Starting Position", Snackbar.LENGTH_SHORT).show();
            } else {
                JSONObject output = new JSONObject();
                try {
                    output.put("name", teamMembersSpinner.getSelectedItem());
                    output.put("event", eventSpinner.getSelectedItem().toString());
                    output.put("team_number", teamNumbersSpinner.getSelectedItem().toString());
                    output.put("match_number", matchNum.getText());
                    output.put("starting_position", startingLocationSpinner.getSelectedItem().toString());
                    output.put("baseline", autoBaseline.isChecked());
                    output.put("auto_switch", autoSwitchNum.getText());
                    output.put("auto_scale", autoScaleNum.getText());
                    output.put("auto_incorrect_color", autoIncorrectSwitch.isChecked());
                    output.put("tele_opponent_switch", teleopOpponentSwitchNum.getText());
                    output.put("tele_scale", teleopScaleNum.getText());
                    output.put("tele_alliance_switch", teleopAllianceSwitchNum.getText());
                    output.put("vault", vaultNum.getText());
                    output.put("defense", defense.isChecked());
                    output.put("climb", climb.isChecked());
                    output.put("parked_on_platform", platformPark.isChecked());
                    output.put("lifted_by_robot", lifted.isChecked());
                    output.put("comments", comments.getText());
                    Log.d("output", output.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Write JSON to file
                String outStr = matchNum.getText() + "-" + teamNumbersSpinner.getSelectedItem().toString() + ".json";
                File dir = new File(Environment.getExternalStorageDirectory() + "/scoutingFiles/");
                if(!dir.exists()){
                    dir.mkdir();
                }

                try{
                    File outFile = new File(dir, outStr);
                    FileOutputStream out = new FileOutputStream(outFile);
                    out.write(output.toString().getBytes());
                    out.close();
                } catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                Snackbar.make(findViewById(R.id.myConstraintLayout), "Successfully Saved: " + outStr, Snackbar.LENGTH_SHORT).show();

                teamMembersSpinner.setSelection(0);
                eventSpinner.setSelection(0);
                teamNumbersSpinner.setSelection(0);
                matchNum.setText("");
                allianceColor.setChecked(false);
                startingLocationSpinner.setSelection(0);
                autoBaseline.setChecked(false);
                autoSwitchNum.setText("0");
                autoScaleNum.setText("0");
                autoIncorrectSwitch.setChecked(false);
                teleopOpponentSwitchNum.setText("0");
                teleopScaleNum.setText("0");
                teleopAllianceSwitchNum.setText("0");
                vaultNum.setText("0");
                defense.setChecked(false);
                climb.setChecked(false);
                platformPark.setChecked(false);
                lifted.setChecked(false);
                comments.setText("");
            }

        });


        Button autoSwitchP = (Button) findViewById(R.id.autonSwitchPlus);
        autoSwitchP.setOnClickListener(v -> {
            int start = Integer.parseInt(autoSwitchNum.getText().toString());
            if (start < 10) {
                start++;
                String result = String.valueOf(start);
                autoSwitchNum.setText(result);
            }
        });

        Button autoSwitchM = (Button) findViewById(R.id.autonSwitchMinus);
        autoSwitchM.setOnClickListener(v -> {
            int start = Integer.parseInt(autoSwitchNum.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                autoSwitchNum.setText(result);
            }
        });

        Button autoScaleP = (Button) findViewById(R.id.autonScalePlus);
        autoScaleP.setOnClickListener(v -> {
            int start = Integer.parseInt(autoScaleNum.getText().toString());
            if (start < 10) {
                start++;
                String result = String.valueOf(start);
                autoScaleNum.setText(result);
            }
        });

        Button autoScaleM = (Button) findViewById(R.id.autonScaleMinus);
        autoScaleM.setOnClickListener(v -> {
            int start = Integer.parseInt(autoScaleNum.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                autoScaleNum.setText(result);
            }
        });

        Button teleopOpponentSwitchP = (Button) findViewById(R.id.teleopOpponentSwitchPlus);
        teleopOpponentSwitchP.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopOpponentSwitchNum.getText().toString());
            if (start < 50) {
                start++;
                String result = String.valueOf(start);
                teleopOpponentSwitchNum.setText(result);
            }
        });

        Button teleopOpponentSwitchM = (Button) findViewById(R.id.teleopOpponentSwitchMinus);
        teleopOpponentSwitchM.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopOpponentSwitchNum.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                teleopOpponentSwitchNum.setText(result);
            }
        });

        Button teleopScaleP = (Button) findViewById(R.id.teleopScalePlus);
        teleopScaleP.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopScaleNum.getText().toString());
            if (start < 50) {
                start++;
                String result = String.valueOf(start);
                teleopScaleNum.setText(result);
            }
        });

        Button teleopScaleM = (Button) findViewById(R.id.teleopScaleMinus);
        teleopScaleM.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopScaleNum.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                teleopScaleNum.setText(result);
            }
        });

        Button teleopAllianceSwitchP = (Button) findViewById(R.id.teleopAllianceSwitchPlus);
        teleopAllianceSwitchP.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopAllianceSwitchNum.getText().toString());
            if (start < 50) {
                start++;
                String result = String.valueOf(start);
                teleopAllianceSwitchNum.setText(result);
            }
        });

        Button teleopAllianceSwitchM = (Button) findViewById(R.id.teleopAllianceSwitchMinus);
        teleopAllianceSwitchM.setOnClickListener(v -> {
            int start = Integer.parseInt(teleopAllianceSwitchNum.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                teleopAllianceSwitchNum.setText(result);
            }
        });

        Button vaultP = (Button) findViewById(R.id.vaultPlus);
        vaultP.setOnClickListener(v -> {
            int start = Integer.parseInt(vaultNum.getText().toString());
            if (start < 9) {
                start++;
                String result = String.valueOf(start);
                vaultNum.setText(result);
            }
        });

        Button vaultM = (Button) findViewById(R.id.vaultMinus);
        vaultM.setOnClickListener(v -> {
            int start = Integer.parseInt(vaultNum.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                vaultNum.setText(result);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
