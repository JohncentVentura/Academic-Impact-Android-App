package com.example.ccs322lacademicimpact;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText txtLoginUID;
    Button btnLoginLogin, btnLoginSignUp;

    SQLAccounts sqlAccounts = new SQLAccounts(this);
    int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLoginUID = (EditText) findViewById(R.id.txtLoginUID);
        btnLoginLogin = (Button) findViewById(R.id.btnLoginLogin);
        btnLoginSignUp = (Button) findViewById(R.id.btnLoginSignUp);

        uid = getIntent().getExtras().getInt("uid");

        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlAccounts.findRecord(sqlAccounts, txtLoginUID.getText().toString());

                try { //if txtLoginUID text found an equals in sqlAccounts
                    if(txtLoginUID.getText().toString().equals(sqlAccounts.findRecord(sqlAccounts, txtLoginUID.getText().toString()).get(0).getUid() + "")){
                        uid = sqlAccounts.findRecord(sqlAccounts, txtLoginUID.getText().toString()).get(0).getUid();

                        //Setting up what database will be use
                        SQLClasses.setDatabaseName(sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getClassesDB());
                        SQLActivities.setDatabaseName(sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getActivitiesDB());

                        Toast.makeText(LoginActivity.this, "Welcome "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getFirstName()+" "
                                +sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getLastName()+" to Academic Impact", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        intent.putExtra("uid", uid);
                        startActivity(intent);
                    }
                }
                catch (Exception e){ //if txtLoginUID text did not found an equals in sqlAccounts
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Invalid UID", Toast.LENGTH_SHORT).show();
                    txtLoginUID.setText("");
                }
            }
        });

        btnLoginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });
    }

    public static void printAccountDetails(SQLAccounts sqlAccounts, int uid){
        sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid));
        System.out.println("UID: "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getUid());
        System.out.println("Image: "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getImage());
        System.out.println("Last Name: "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getLastName());
        System.out.println("First Name: "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getFirstName());
        System.out.println("YearLevel: "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getYearLevel());
        System.out.println("Course: "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getCourse());
        System.out.println("ClassesDB: "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getClassesDB());
        System.out.println("ActivitiesDB: "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getActivitiesDB());
        System.out.println("NotesDB: "+sqlAccounts.findRecord(sqlAccounts, String.valueOf(uid)).get(0).getNotesDB());
    }
}