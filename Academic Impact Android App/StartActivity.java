package com.example.ccs322lacademicimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    TextView tvStart;
    Button btnStartStart;

    int uid = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tvStart = (TextView) findViewById(R.id.tvStart);
        btnStartStart = (Button)findViewById(R.id.btnStartStart);

        tvStart.setText("COGNOSPHERE shall own all rights, titles, and interests (including but not limited to the ownership, intellectual property rights, " +
                "neighboring rights and other rights and interests) in and to the COGNOSPHERE Services under this Agreement. You acknowledge that your use of " +
                "the COGNOSPHERE Services does not confer you any right or interest or otherwise, in any aspect or feature of it, including but not limited to " +
                "(if any) any in-game rewards, achievements, characters, Virtual Currency, levels and other content. You further acknowledge that any character " +
                "data, game progress, game customization and/or other data pertaining to your use of the COGNOSPHERE Services may cease to be available to you at " +
                "any time without prior notice in the sole discretion of COGNOSPHERE.");

        btnStartStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });
    }
}