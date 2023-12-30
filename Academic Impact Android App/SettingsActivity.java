package com.example.ccs322lacademicimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    ImageButton ibtnSettingsBack;
    EditText txtSettingsUID, txtSettingsLastName, txtSettingsFirstName;
    Spinner spinnerSettingsYearLevel, spinnerSettingsCourse;
    ImageView imgSettingsImage;
    SeekBar sbSettingsImage;
    Button btnSettingsUpdate, btnSettingsSignout;

    SQLAccounts sqlAccounts = new SQLAccounts(this);
    int uid, image, imageCount = 0;
    String yearLevel, course, classesDBName, activitiesDBName, notesDBName;

    String[] yearLevelsArray = {"Kindergarten", "Primary Education", "Junior High", "Senior High", "College"};
    String[] noCoursesArrays = {"Reach Higher Education Level"};
    String[] strandsArray = {"General Academic Strand", "Science, Technology, Engineering and Mathematics", "Humanities and Social Sciences",
            "Accountancy, Business and Management", "Cookery", "Information and Communication Technology"};
    String[] coursesArray = {"Tourism and Hospitality Management", "Medical Technology / Pharmacy", "Computer Studies", "Nursing / Midwifery", "Criminology",
            "Accountancy", "Education", "Engineering and Architecture", "Business Administration", "Liberal Arts"};
    int[] bgArray = {R.drawable.bgchasm, R.drawable.bgdawnwinery, R.drawable.bgdragonspine, R.drawable.bginazuma,
            R.drawable.bgliyue, R.drawable.bgmonstadt, R.drawable.bgpool, R.drawable.bgshrine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ibtnSettingsBack = (ImageButton) findViewById(R.id.ibtnSettingsBack);
        txtSettingsUID = (EditText) findViewById(R.id.txtSettingsUID);
        txtSettingsLastName = (EditText) findViewById(R.id.txtSettingsLastName);
        txtSettingsFirstName = (EditText) findViewById(R.id.txtSettingsFirstName);
        spinnerSettingsYearLevel = (Spinner) findViewById(R.id.spinnerSettingsYearLevel);
        spinnerSettingsCourse = (Spinner) findViewById(R.id.spinnerSettingsCourse);
        imgSettingsImage = (ImageView) findViewById(R.id.imgSettigsImage);
        sbSettingsImage = (SeekBar) findViewById(R.id.sbSettingsImage);
        btnSettingsUpdate = (Button) findViewById(R.id.btnSettingsUpdate);
        btnSettingsSignout = (Button) findViewById(R.id.btnSettingsSignout);

        uid = getIntent().getExtras().getInt("uid");
        txtSettingsUID.setText(String.valueOf(uid));
        txtSettingsUID.setEnabled(false);
        txtSettingsLastName.setText(sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getLastName());
        txtSettingsFirstName.setText(sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getFirstName());
        image = sqlAccounts.findRecord(sqlAccounts, txtSettingsUID.getText().toString()).get(0).getImage();
        imgSettingsImage.setBackgroundResource(bgArray[imageCount]);
        sbSettingsImage.setMax(90);

        setSpinnerAdapter(spinnerSettingsYearLevel, yearLevelsArray);
        for(int i = 0; i < yearLevelsArray.length; i++){
            if(sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getYearLevel().equals(yearLevelsArray[i])){
                spinnerSettingsYearLevel.setSelection(i);
            }
        }

        spinnerSettingsYearLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerSettingsYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[0])) {
                    yearLevel = yearLevelsArray[0];
                    setSpinnerAdapter(spinnerSettingsCourse, noCoursesArrays);
                } else if (spinnerSettingsYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[1])) {
                    yearLevel = yearLevelsArray[1];
                    setSpinnerAdapter(spinnerSettingsCourse, noCoursesArrays);
                } else if (spinnerSettingsYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[2])) {
                    yearLevel = yearLevelsArray[2];
                    setSpinnerAdapter(spinnerSettingsCourse, noCoursesArrays);
                } else if (spinnerSettingsYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[3])) {
                    yearLevel = yearLevelsArray[3];
                    setSpinnerAdapter(spinnerSettingsCourse, strandsArray);
                } else if (spinnerSettingsYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[4])) {
                    yearLevel = yearLevelsArray[4];
                    setSpinnerAdapter(spinnerSettingsCourse, coursesArray);
                }
                System.out.println("yearLevel: " + yearLevel); //For Debugging
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerSettingsCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerSettingsCourse.getItemAtPosition(i).toString().equals(coursesArray[i])){
                    course = coursesArray[i];
                }
                else if(spinnerSettingsCourse.getItemAtPosition(i).toString().equals(strandsArray[i])){
                    course = strandsArray[i];
                }
                else{
                    course = noCoursesArrays[0];
                }
                System.out.println("course: "+course); //For Debugging
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sbSettingsImage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                for(int index = 1; index < bgArray.length + 1; index++) {
                    if (i <= (10 * index)) {
                        imageCount = index - 1;
                        break;
                    }
                }

                imgSettingsImage.setBackgroundResource(bgArray[imageCount]);
                image = bgArray[imageCount];
                System.out.println("Character: "+ bgArray[imageCount]); //For Debugging
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSettingsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlAccounts.findRecord(sqlAccounts, txtSettingsUID.getText().toString());

                sqlAccounts.updateRecord(sqlAccounts, sqlAccounts.findRecord(sqlAccounts, txtSettingsUID.getText().toString()).get(0).getUid()+"",
                        String.valueOf(image), txtSettingsLastName.getText().toString(), txtSettingsFirstName.getText().toString(), yearLevel, course,
                        sqlAccounts.findRecord(sqlAccounts, txtSettingsUID.getText().toString()).get(0).getClassesDB(),
                        sqlAccounts.findRecord(sqlAccounts, txtSettingsUID.getText().toString()).get(0).getActivitiesDB(),
                        sqlAccounts.findRecord(sqlAccounts, txtSettingsUID.getText().toString()).get(0).getNotesDB());

                Toast.makeText(SettingsActivity.this, "Update Complete...", Toast.LENGTH_SHORT).show();

                Toast.makeText(SettingsActivity.this, "Welcome "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getFirstName()+" "
                        +sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getLastName()+" to Academic Impact", Toast.LENGTH_SHORT).show();

                Intent intentUpdate = new Intent(SettingsActivity.this, MenuActivity.class);
                intentUpdate.putExtra("uid", uid);
                startActivity(intentUpdate);
            }
        });

        ibtnSettingsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(SettingsActivity.this, MenuActivity.class);
                intentBack.putExtra("uid", uid);
                startActivity(intentBack);
            }
        });

        btnSettingsSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = 0;
                Intent intentSignout = new Intent (SettingsActivity.this, LoginActivity.class);
                intentSignout.putExtra("uid", uid);
                startActivity(intentSignout);
            }
        });
    }

    private void setSpinnerAdapter(Spinner spinner, String[] array)
    {
        ArrayAdapter adapter = new ArrayAdapter(SettingsActivity.this, android.R.layout.simple_spinner_dropdown_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}