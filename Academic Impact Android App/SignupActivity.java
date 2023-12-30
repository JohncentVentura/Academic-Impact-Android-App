package com.example.ccs322lacademicimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText txtSignupUID, txtSignupLastName, txtSignupFirstName;
    Spinner spinnerSignupYearLevel, spinnerSignupCourse;
    ImageView imgSignupImage;
    SeekBar sbSignupImage;
    Button btnSignupSignIn, btnSignupCancel;

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
        setContentView(R.layout.activity_signup);

        txtSignupUID = (EditText) findViewById(R.id.txtSignupUID);
        txtSignupLastName = (EditText) findViewById(R.id.txtSignupLastName);
        txtSignupFirstName = (EditText) findViewById(R.id.txtSignupFirstName);
        spinnerSignupYearLevel = (Spinner) findViewById(R.id.spinnerSignupYearLevel);
        spinnerSignupCourse = (Spinner) findViewById(R.id.spinnerSignupCourse);
        imgSignupImage = (ImageView) findViewById(R.id.imgSignupImage);
        sbSignupImage = (SeekBar) findViewById(R.id.sbSignupImage);
        btnSignupSignIn = (Button) findViewById(R.id.btnSignupSignIn);
        btnSignupCancel = (Button) findViewById(R.id.btnSignupCancel);

        uid = getIntent().getExtras().getInt("uid");
        setSpinnerAdapter(spinnerSignupYearLevel, yearLevelsArray);
        imgSignupImage.setBackgroundResource(bgArray[imageCount]);
        sbSignupImage.setMax(90);

        spinnerSignupYearLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerSignupYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[0])) {
                    yearLevel = yearLevelsArray[0];
                    setSpinnerAdapter(spinnerSignupCourse, noCoursesArrays);
                } else if (spinnerSignupYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[1])) {
                    yearLevel = yearLevelsArray[1]; 
                    setSpinnerAdapter(spinnerSignupCourse, noCoursesArrays);
                } else if (spinnerSignupYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[2])) {
                    yearLevel = yearLevelsArray[2];
                    setSpinnerAdapter(spinnerSignupCourse, noCoursesArrays);
                } else if (spinnerSignupYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[3])) {
                    yearLevel = yearLevelsArray[3];
                    setSpinnerAdapter(spinnerSignupCourse, strandsArray);
                } else if (spinnerSignupYearLevel.getItemAtPosition(i).toString().equals(yearLevelsArray[4])) {
                    yearLevel = yearLevelsArray[4];
                    setSpinnerAdapter(spinnerSignupCourse, coursesArray);
                }
                System.out.println("yearLevel: " + yearLevel); //For Debugging
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerSignupCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerSignupCourse.getItemAtPosition(i).toString().equals(coursesArray[i])){
                    course = coursesArray[i];
                }
                else if(spinnerSignupCourse.getItemAtPosition(i).toString().equals(strandsArray[i])){
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


        sbSignupImage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                for(int index = 1; index < bgArray.length + 1; index++) {
                    if (i <= (10 * index)) {
                        imageCount = index - 1;
                        break;
                    }
                }

                imgSignupImage.setBackgroundResource(bgArray[imageCount]);
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

        btnSignupSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlAccounts.findRecord(sqlAccounts, txtSignupUID.getText().toString());

                try{ //if txtSignupUID text found an equals in sqlAccounts
                    if(txtSignupUID.getText().toString().equals(sqlAccounts.findRecord(sqlAccounts, txtSignupUID.getText().toString()).get(0).getUid() + "")){
                        Toast.makeText(SignupActivity.this, "Existing UID", Toast.LENGTH_SHORT).show();
                        txtSignupUID.setText("");
                    }
                }
                catch (Exception e){ //if txtSignupUID text did not found an equals in sqlAccounts
                    uid = Integer.parseInt(txtSignupUID.getText().toString());

                    //If SQLAccounts DATABASE_VERSION is changed, change the names of this following
                    classesDBName = "classesDB5"+uid;
                    activitiesDBName = "activitiesDB5"+uid;
                    notesDBName = "notesDB5"+uid;

                    sqlAccounts.insertRecord(sqlAccounts, txtSignupUID.getText().toString(), String.valueOf(image), txtSignupLastName.getText().toString(),
                            txtSignupFirstName.getText().toString(), yearLevel, course, classesDBName, activitiesDBName, notesDBName);

                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                }
            }
        });

        btnSignupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = 0;

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });
    }

    private void setSpinnerAdapter(Spinner spinner, String[] array)
    {
        ArrayAdapter adapter = new ArrayAdapter(SignupActivity.this, android.R.layout.simple_spinner_dropdown_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}