package com.example.myapplication.roomRecyclerview

import android.app.Activity
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.roomRecyclerview.NoteAddActivity.Companion.MYDATA
import com.example.myapplication.roomRecyclerview.NoteEditActivity.Companion.NOTE_ID
import com.example.myapplication.roomRecyclerview.NoteEditActivity.Companion.UPDATED_NOTE
import com.example.myapplication.roomRecyclerview.utils.Note
import com.example.myapplication.roomRecyclerview.utils.NoteViewModel
import java.util.*

class MainRoomActivity : AppCompatActivity(), NoteAdapterCallback {

    private val mTag = this.javaClass.simpleName
    private var btnAdd: Button? = null
    private lateinit var noteViewModel: NoteViewModel

    private var recyclerview: RecyclerView? = null
    private var adapter: NoteListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_room)

        Log.d(mTag, "$mTag launched")

        val list = ArrayList<Note>()
        recyclerview = findViewById(R.id.recyclerview)
        recyclerview!!.layoutManager = LinearLayoutManager(this)
        adapter = NoteListAdapter(this, list, this)
        recyclerview!!.setAdapter(adapter)

        btnAdd = findViewById(R.id.btnAdd)
        btnAdd!!.setOnClickListener { startActivityForResult(Intent(applicationContext, NoteAddActivity::class.java), NEW_NOTE_ACTIVITY_RESULT_CODE) }

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this, Observer<List<Note>> { notes -> adapter!!.setNotes(notes) })

        LoadApplications().execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            try {
                val note_id = UUID.randomUUID().toString()
                Log.d(mTag, "note_id      = $note_id")
                Log.d(mTag, "note_data    = " + data!!.getStringExtra(MYDATA)!!)
                val note = Note(note_id, data.getStringExtra(MYDATA))
                noteViewModel!!.insert(note)
                Log.d(mTag, "note saved")
            } catch (e: Exception) {
                Log.d(mTag, "exception = " + e.message)
            }

        } else if (requestCode == UPDATE_NOTE_ACTIVITY_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            val note = Note(data!!.getStringExtra(NOTE_ID), data.getStringExtra(UPDATED_NOTE))
            noteViewModel!!.update(note)
            Log.d(mTag, "note updated")
        } else {
            Log.d(mTag, "note not saved")
        }
    }

    override fun OnDeleteItemClick(note: Note) {
        noteViewModel!!.delete(note)
    }

    companion object {

        var UPDATE_NOTE_ACTIVITY_RESULT_CODE = 2
        private val NEW_NOTE_ACTIVITY_RESULT_CODE = 1
    }

    private inner class LoadApplications : AsyncTask<Void, Void, ArrayList<Note>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(applicationContext, "start", Toast.LENGTH_SHORT).show()
        }

        override fun doInBackground(vararg params: Void?): ArrayList<Note> {
            return checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA))
        }

        override fun onPostExecute(result: ArrayList<Note>) {
            super.onPostExecute(result)

            Toast.makeText(applicationContext, "end ", Toast.LENGTH_SHORT).show()

            for (i in 0 until result.size) {
                noteViewModel.insert(result[i])
            }
        }
    }

    private fun checkForLaunchIntent(list: List<ApplicationInfo>): ArrayList<Note> {
        val tempDrawerList = arrayListOf<Note>()
        for (info in list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {

                    /*Log.d(
                        mTag,
                        "app Name = ${info.loadLabel(cxt.packageManager)}\t" +
                                "package name = ${info.packageName}\t" +
                                "system app = ${isSystemApp(info)}"
                    )*/

                    /**
                     * only add those which are not match with this package name
                     */
                    if (!TextUtils.equals(info.packageName, packageName)) {
                        val d = Note(
                                info.packageName,
                                info.loadLabel(packageManager).toString()
                        )
                        tempDrawerList.add(d)
                    }

                }
            } catch (e: Exception) {
                Log.d(mTag, "(checkForLaunchIntent) catch error = $e")
            }
        }
        return tempDrawerList
    }
}