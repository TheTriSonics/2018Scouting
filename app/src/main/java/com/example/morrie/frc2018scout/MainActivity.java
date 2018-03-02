package com.example.morrie.frc2018scout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

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

        Spinner climbSpinner = (Spinner) findViewById(R.id.climbSpinner);
        ArrayAdapter<CharSequence> Cadapt = ArrayAdapter.createFromResource(this, R.array.Climb, android.R.layout.simple_spinner_item);
        Cadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        climbSpinner.setAdapter(Cadapt);

        EditText matchNum = (EditText) findViewById(R.id.matchNumberText);
        Switch allianceColor = (Switch) findViewById(R.id.allianceColorSwitch);
        CheckBox autoBaseline = (CheckBox) findViewById(R.id.baselineCheckBox);
        EditText autoSwitchNum = (EditText) findViewById(R.id.autonSwitch);
        EditText autoScaleNum = (EditText) findViewById(R.id.autonScale);
        CheckBox autoIncorrectSwitch = (CheckBox) findViewById(R.id.incorrectColorCheckbox);
        CheckBox autoBaseLine = (CheckBox) findViewById(R.id.baselineCheckBox);
        EditText teleopOpponentSwitchNum = (EditText) findViewById(R.id.teleopOpponentSwitch);
        EditText teleopScaleNum = (EditText) findViewById(R.id.teleopScale);
        EditText teleopAllianceSwitchNum = (EditText) findViewById(R.id.teleopAllianceSwitch);
        EditText vaultNum = (EditText) findViewById(R.id.vault);
        CheckBox defense = (CheckBox) findViewById(R.id.defenseCheckbox);
        EditText driverNum = (EditText) findViewById(R.id.driverRating);

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

        Button driverRatingP = (Button) findViewById(R.id.driverRatingPlus);
        driverRatingP.setOnClickListener(v -> {
            int start = Integer.parseInt(driverNum.getText().toString());
            if (start < 5) {
                start++;
                String result = String.valueOf(start);
                driverNum.setText(result);
            }
        });

        Button driverRatingM = (Button) findViewById(R.id.driverRatingMinus);
        driverRatingM.setOnClickListener(v -> {
            int start = Integer.parseInt(driverNum.getText().toString());
            if (start > 0) {
                start--;
                String result = String.valueOf(start);
                driverNum.setText(result);
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
