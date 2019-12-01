package com.example.myapplication.roomRecyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R


class NoteAddActivity : AppCompatActivity() {
    private var btnAddNote: Button? = null
    private var etAddNote: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new_note)

        btnAddNote = findViewById(R.id.btnAddNote)
        etAddNote = findViewById(R.id.etAddNote)

        btnAddNote!!.setOnClickListener {
            val intent = Intent()
            if (TextUtils.isEmpty(etAddNote!!.text)) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                intent.putExtra(MYDATA, etAddNote!!.text.toString())
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }

    companion object {

        val MYDATA = "data"
    }
}