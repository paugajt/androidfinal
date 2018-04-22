package com.justinpauga.cyphersystemscharactertracker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.content.Context
import android.content.SharedPreferences
import android.view.Gravity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    var info = Character::class.java.newInstance()
    private val fileName = "Characters"
    private var charList: ArrayList<Character> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadDataFromSharedPreferences()
    }

    override fun onResume() {
        super.onResume()
        populateCharacterView()
    }

    private fun populateCharacterView() {
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
                //view.callOnClick(openCharacterInfo(character))

            }
        }
    }

    private fun sendCharacter(character: Character) {
        val intent = Intent(this,CharacterInfo::class.java)
        intent.putExtra("character", character)
        startActivity(intent)
    }

    private fun openNewCharacter(view: View) {
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

    private fun charNameExists(name: String): Boolean {
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

    private fun saveCharacter(character: Character) {
        charList.add(character)
        saveDataToSharedPreferences()
    }

    private fun saveDataToSharedPreferences() {
        var sharedPreferences: SharedPreferences = getSharedPreferences("Character", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        var gson: Gson = Gson()
        var json: String = gson.toJson(charList)
        editor.putString("Char List", json)
        editor.apply()
    }

    private fun loadDataFromSharedPreferences() {
        var sharedPreferences: SharedPreferences = getSharedPreferences("Character", Context.MODE_PRIVATE)
        var gson: Gson = Gson()
        var json: String? = sharedPreferences.getString("Char List", null)
        val type = object : TypeToken<ArrayList<Character>>() {

        }.type
        var test: ArrayList<Character>? = gson.fromJson(json, type)

        if(test != null) {
            charList = test
        }
    }
}
