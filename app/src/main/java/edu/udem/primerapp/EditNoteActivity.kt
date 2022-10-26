package edu.udem.primerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import java.util.*

class EditNoteActivity : AppCompatActivity() {

    private lateinit var note: Note
    private lateinit var notesDao: NotesDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val database = MyDatabase.getInstance(this)
        notesDao = database.notesDao()

        val noteId = intent.getIntExtra("id", -1)
        if(noteId!=-1){
            note = notesDao.getNote(noteId)
            findViewById<EditText>(R.id.edit_message).setText(note!!.title)

            findViewById<Switch>(R.id.switch1).isChecked = note.reminder!!

        }

    }

    fun saveNote(view: View) {

        if(!this::note.isInitialized){
            note = Note()
        }
        note!!.title = findViewById<EditText>(R.id.edit_message).getText().toString()
        note!!.reminder = findViewById<Switch>(R.id.switch1).isChecked()
        notesDao.insertNote(note!!)
        finish()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        else if(item.itemId ==R.id.delete ){
            notesDao.delete(note)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(this::note.isInitialized){
            menuInflater.inflate(R.menu.menu_edit,menu)
        }

        return true
    }
}
