package com.justinpauga.cyphersystemscharactertracker

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.view.ViewGroup.LayoutParams.FILL_PARENT
import android.widget.TextView
import android.widget.Toast
import com.justinpauga.cyphersystemscharactertracker.R.drawable.text_border
import java.io.File
import android.R.attr.button
import android.view.Gravity
import kotlinx.android.synthetic.main.character_info.*


class MainActivity : AppCompatActivity() {

    var info = Character::class.java.newInstance()
    private val fileName = "Characters"
    private val charList: ArrayList<Character> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        populateCharacterView()
    }

    override fun onPause() {
        super.onPause()
        saveToFile()
    }

    fun populateCharacterView() {
        val linearLayout = findViewById<View>(R.id.add_character_linear_layout)
        (linearLayout as LinearLayout).removeAllViews()

        if (charList.isEmpty()) {
            //LinearLayout layout = (LinearLayout) findViewById(R.id.info);
            val emptyCharList = TextView(this)
            emptyCharList.text = "No Characters Yet"
            emptyCharList.setBackgroundResource(R.drawable.text_border)
            emptyCharList.height = 200
            emptyCharList.gravity = Gravity.CENTER

            emptyCharList.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)

            (linearLayout as LinearLayout).addView(emptyCharList)
        } else {
            for (character in charList!!) {
                val view = TextView(this)
                view.text = "${character.getName()} is a ${character.getDescriptor()} ${character.getType()} who ${character.getFocus()}" +
                        "\n Might: ${character.getMight().toString()} Speed: ${character.getSpeed().toString()} Int: ${character.getIntelligence().toString()}"
                //view.id = idCounter
                view.setBackgroundResource(R.drawable.text_border)
                view.height = 200
                view.gravity = Gravity.CENTER
                view.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                (linearLayout as LinearLayout).addView(view)
                view.setOnClickListener({
                    sendCharacter(character) })
            }
        }
    }

    fun sendCharacter(character: Character) {
        val intent = Intent(this,CharacterInfo::class.java)
        intent.putExtra("character", character)
        startActivity(intent)
    }

    fun openNewCharacter(view: View) {
        val launchNewCharacter = Intent(this, NewCharacter::class.java)
        startActivityForResult(launchNewCharacter, 1)
    }

    fun addChartoList(character: Character) {
        if (charNameExists(character.getName())) {
            val toast = Toast.makeText(this, "A character with this name already exists", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            charList.add(character)
        }
    }

    fun charNameExists(name: String): Boolean {
        for (character in charList) {
            if (character.getName().equals(name)) {
                return true
            }
        }
        return false
    }

    fun removeCharFromList(character: Character) {
        val name = character.getName()
        if (charNameExists(character.getName())) {
            charList.remove(character)
            val toast = Toast.makeText(this, "${name} has been deleted", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            info = data!!.getSerializableExtra("Character") as Character
            saveCharacter(info)
        }

    }

    fun saveCharacter(character: Character) {
        charList.add(character)
    }

    fun saveToFile() {
        val file = File(filesDir, fileName)
        if (file.exists()) {
            for(character in charList) {

                file.appendText("\n${character.getName()},${character.getType()}," +
                        "${character.getFocus()},${character.getTier()},${character.getEffort()}," +
                        "${character.getXp()}, ${character.getMight()}, ${character.getSpeed()}," +
                        "${character.getIntelligence()},${character.getAbilities()}," +
                        "${character.getAttacks()},${character.getCyphers()},${character.getEquipment()}," +
                        "${character.getNotes()}")
            }
        }
    }

}
