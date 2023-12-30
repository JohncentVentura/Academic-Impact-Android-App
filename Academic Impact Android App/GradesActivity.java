package com.example.ccs322lacademicimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GradesActivity extends AppCompatActivity {

    ImageButton ibtnGradesBack, ibtnGradesEdit;
    Button btnGradesAdminEnter, btnGradesFind;
    EditText txtGradesAdminCode, txtGradesClassID, txtGradesPrelim, txtGradesMidterm, txtGradesSemiFinal, txtGradesFinal;
    ImageView imgGradesPrelim, imgGradesMidterm, imgGradesSemiFinal, imgGradesFinal;
    ListView listviewGrades;

    SQLClasses sqlClasses = new SQLClasses(this);
    List<String> recordList = new ArrayList<String>();
    ArrayAdapter adapter;
    int uid;
    String adminCode = "ccs322";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        ibtnGradesBack = (ImageButton) findViewById(R.id.ibtnGradesBack);
        ibtnGradesEdit = (ImageButton) findViewById(R.id.ibtnGradesEdit);
        btnGradesFind = (Button) findViewById(R.id.btnGradesFind);
        btnGradesAdminEnter = (Button) findViewById(R.id.btnGradesAdminEnter);
        txtGradesClassID = (EditText) findViewById(R.id.txtGradesClassID);
        txtGradesAdminCode = (EditText) findViewById(R.id.txtGradesAdminCode);
        txtGradesPrelim = (EditText) findViewById(R.id.txtGradesPrelim);
        txtGradesMidterm = (EditText) findViewById(R.id.txtGradesMidterm);
        txtGradesSemiFinal = (EditText) findViewById(R.id.txtGradesSemiFinal);
        txtGradesFinal = (EditText) findViewById(R.id.txtGradesFinal);
        imgGradesPrelim = (ImageView) findViewById(R.id.imgGradesPrelim);
        imgGradesMidterm = (ImageView) findViewById(R.id.imgGradesMidterm);
        imgGradesSemiFinal = (ImageView) findViewById(R.id.imgGradesSemiFinal);
        imgGradesFinal = (ImageView) findViewById(R.id.imgGradesFinal);
        listviewGrades = (ListView) findViewById(R.id.listviewGrades);

        uid = getIntent().getExtras().getInt("uid");
        ibtnGradesEdit.setEnabled(false);
        txtGradesPrelim.setEnabled(false);
        txtGradesMidterm.setEnabled(false);
        txtGradesSemiFinal.setEnabled(false);
        txtGradesFinal.setEnabled(false);

        imgGradesPrelim.setVisibility(View.INVISIBLE);
        imgGradesMidterm.setVisibility(View.INVISIBLE);
        imgGradesSemiFinal.setVisibility(View.INVISIBLE);
        imgGradesFinal.setVisibility(View.INVISIBLE);

        btnGradesAdminEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtGradesAdminCode.getText().toString().equals(adminCode)) {
                    ibtnGradesEdit.setEnabled(true);
                    txtGradesPrelim.setEnabled(true);
                    txtGradesMidterm.setEnabled(true);
                    txtGradesSemiFinal.setEnabled(true);
                    txtGradesFinal.setEnabled(true);
                } else {
                    Toast.makeText(GradesActivity.this, "Invalid Admic Code", Toast.LENGTH_SHORT).show();
                    txtGradesAdminCode.setText("");
                }
            }
        });

        btnGradesFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (txtGradesClassID.getText().toString().equals(sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getCid() + "")) {
                        sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString());
                        txtGradesPrelim.setText(sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getPrelimGrade());
                        txtGradesMidterm.setText(sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getMidtermGrade());
                        txtGradesSemiFinal.setText(sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getSemiFinalGrade());
                        txtGradesFinal.setText(sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getFinalGrade());

                        setGradeImages(txtGradesPrelim);
                        setGradeImages(txtGradesMidterm);
                        setGradeImages(txtGradesSemiFinal);
                        setGradeImages(txtGradesFinal);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(GradesActivity.this, "Invalid Class ID", Toast.LENGTH_SHORT).show();
                    txtGradesClassID.setText("");
                }
            }
        });

        ibtnGradesEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlClasses.updateRecord(sqlClasses, sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getCid()+"",
                        sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getImage()+"",
                        sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getName(),
                        sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getInstructor(),
                        sqlClasses.findRecord(sqlClasses, txtGradesClassID.getText().toString()).get(0).getTime(),
                        txtGradesPrelim.getText().toString(), txtGradesMidterm.getText().toString(),
                        txtGradesSemiFinal.getText().toString(), txtGradesFinal.getText().toString());

                txtGradesClassID.setText("");
                txtGradesPrelim.setText("");
                txtGradesMidterm.setText("");
                txtGradesSemiFinal.setText("");
                txtGradesFinal.setText("");
                fetchData();
            }
        });

        ibtnGradesBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GradesActivity.this, MenuActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });

        fetchData();
    }

    private void fetchData() {
        ArrayList<DBClasses> list = sqlClasses.getAllRecords(sqlClasses);
        recordList.clear();
        for (int i = 0; i < list.size(); i++) {
            recordList.add("Class: " + sqlClasses.getAllRecords(sqlClasses).get(i).getCid() + " - " +
                    "" + sqlClasses.getAllRecords(sqlClasses).get(i).getName() + ": " +
                    "PE " + sqlClasses.getAllRecords(sqlClasses).get(i).getPrelimGrade() + " / " +
                    "ME: " + sqlClasses.getAllRecords(sqlClasses).get(i).getMidtermGrade() + " / " +
                    "SF: " + sqlClasses.getAllRecords(sqlClasses).get(i).getSemiFinalGrade() + " / " +
                    "FE: " + sqlClasses.getAllRecords(sqlClasses).get(i).getFinalGrade());
        }

        adapter = new ArrayAdapter<String>(GradesActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, recordList);
        listviewGrades.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setGradeImages(EditText editText){
        if(Integer.parseInt(editText.getText().toString()) >= 94){

        }
        else if(Integer.parseInt(editText.getText().toString()) >= 88){

        }
        else if(Integer.parseInt(editText.getText().toString()) >= 82){

        }
        else if(Integer.parseInt(editText.getText().toString()) >= 75){

        }
        else {

        }
    }

}

