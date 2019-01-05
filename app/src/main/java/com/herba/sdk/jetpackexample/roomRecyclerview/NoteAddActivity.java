package com.herba.sdk.jetpackexample.roomRecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.herba.sdk.jetpackexample.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NoteAddActivity extends AppCompatActivity {

    public static final String MYDATA = "data";
    private Button btnAddNote;
    private EditText etAddNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new_note);

        btnAddNote  = findViewById(R.id.btnAddNote);
        etAddNote   = findViewById(R.id.etAddNote);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if(TextUtils.isEmpty(etAddNote.getText()))
                {
                    setResult(RESULT_CANCELED,intent);
                }
                else
                {
                    intent.putExtra(MYDATA,etAddNote.getText().toString());
                    setResult(RESULT_OK,intent);
                }
                finish();
            }
        });
    }
}