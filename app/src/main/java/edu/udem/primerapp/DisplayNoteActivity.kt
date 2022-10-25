package edu.udem.primerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_note)

        val message = intent.getStringExtra("nota1")
        findViewById<TextView>(R.id.textView).text = message

    }
}