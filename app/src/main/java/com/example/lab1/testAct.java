package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class testAct extends AppCompatActivity {

    protected EditText tvfullname;
    protected EditText tvmPhone;

    protected EditText tvID;

    protected ImageView imageView;

    protected Button btn1;
    protected Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tvfullname = findViewById(R.id.tvfullname);
        tvmPhone = findViewById(R.id.tvmPhone);
        tvID = findViewById(R.id.tvCMT);
        imageView = findViewById(R.id.imageView2);
        btn1 = findViewById(R.id.btnOK);
        btn2 = findViewById(R.id.btnCancel);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay du lieu cho main activity
                int id = Integer.parseInt(tvID.getText().toString());
                String name = tvfullname.getText().toString();
                String phone = tvmPhone.getText().toString();
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putInt("Id", id);
                b.putString("Name", name);
                b.putString("Phone",phone);
                intent.putExtras(b);
                setResult(150, intent);
                finish();
            }
        });
    }
}