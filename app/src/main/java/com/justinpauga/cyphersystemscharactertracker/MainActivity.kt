package com.justinpauga.cyphersystemscharactertracker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.view.ViewGroup.LayoutParams.FILL_PARENT
import android.widget.TextView
import java.io.File


class MainActivity : AppCompatActivity() {

    private val fileName = "Characters"
    var charList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (charList.isEmpty()) {
            val linearLayout = findViewById<View>(R.id.add_character_linear_layout)
            //LinearLayout layout = (LinearLayout) findViewById(R.id.info);


            val emptyCharList = TextView(this)
            emptyCharList.text = "No Characters Yet"
            emptyCharList.id = 1

            emptyCharList.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)

            (linearLayout as LinearLayout).addView(emptyCharList)
        }


    }

    fun openNewCharacter(view: View) {
        val launchNewCharacter = Intent(this, NewCharacter::class.java)
        startActivity(launchNewCharacter)
    }

    fun createFile() {
        val file = File(filesDir, "Colors")
        if (file.exists()) {

        }
        else {

        }
    }
}
