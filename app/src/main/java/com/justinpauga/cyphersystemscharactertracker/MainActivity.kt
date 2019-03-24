package com.justinpauga.cyphersystemscharactertracker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import java.io.File
import android.app.AlertDialog
import android.view.Gravity
import java.util.*
import kotlin.collections.ArrayList




class MainActivity : AppCompatActivity() {

    var info = Character::class.java.newInstance()
    private var charList: ArrayList<Character> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charList = getCharList()
    }

    override fun onResume() {
        super.onResume()
        populateCharacterView()
    }


    fun getCharList(): ArrayList<Character> {
        val files = applicationContext.fileList()
        //val charInfo: Array<String> = Array<String>(15, {""})
        val charList: ArrayList<Character> = ArrayList()
        var charInfo: List<String>
        for(file in files) {
            val thisFile = File(filesDir,file)
            var sc = Scanner(thisFile)
            if(sc.hasNext()) {
                var line = sc.nextLine()
                val char: Character = Character::class.java.newInstance()
                charInfo = line.split(",")
                if(charInfo.size == 15) {
                    char.setName(charInfo[0])
                    char.setDescriptor(charInfo[1])
                    char.setType(charInfo[2])
                    char.setFocus(charInfo[3])
                    char.setTier(charInfo[4].trim().toInt())
                    char.setEffort(charInfo[5].trim().toInt())
                    char.setXp(charInfo[6].trim().toInt())
                    char.setMight(charInfo[7].trim().toInt())
                    char.setSpeed(charInfo[8].trim().toInt())
                    char.setIntelligence(charInfo[9].trim().toInt())
                    char.setAbilities(charInfo[10])
                    char.setAttacks(charInfo[11])
                    char.setCyphers(charInfo[12])
                    char.setEquipment(charInfo[13])
                    char.setNotes(charInfo[14])

                    charList.add(char)
            }
            }
        }
        return charList

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
                view.height = 200
                view.gravity = Gravity.CENTER
                view.setBackgroundResource(R.drawable.text_border)
                view.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                (linearLayout as LinearLayout).addView(view)
                view.setOnClickListener({
                    sendCharacter(character) })

                view.setOnLongClickListener {
                    deleteChar(character)
                    true
                }
            }
        }
    }

    fun deleteChar(character: Character) {
        val deleteDialog = AlertDialog.Builder(this)
        val name = character.getName()
        deleteDialog.setTitle("Delete Character?")
        deleteDialog.setMessage("Are you sure you want to delete ${character.getName()}? \n This cannot be undone!")
        deleteDialog.setPositiveButton("Delete") { dialog, which ->
            val file = File(filesDir, character.getName())
            file.delete()
            val intent = intent
            finish()
            startActivity(intent)
        }
        deleteDialog.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        deleteDialog.create()
        deleteDialog.show()
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            info = data!!.getSerializableExtra("Character") as Character
            saveCharacter(info)
        }

    }

    fun saveCharacter(character: Character) {
        charList.add(character)
        saveToFile(character)
    }

    fun saveToFile(character:Character) {

            val file = File(filesDir, character.getName())
            if (!file.exists()) {
                val out = file.printWriter()
                out.print("${character.getName()},${character.getDescriptor()},${character.getType()}," +
                        "${character.getFocus()},${character.getTier()},${character.getEffort()}," +
                        "${character.getXp()}, ${character.getMight()}, ${character.getSpeed()}," +
                        "${character.getIntelligence()},${character.getAbilities()}," +
                        "${character.getAttacks()},${character.getCyphers()},${character.getEquipment()}," +
                        "${character.getNotes()}")
                out.close()
            }
    }


}
