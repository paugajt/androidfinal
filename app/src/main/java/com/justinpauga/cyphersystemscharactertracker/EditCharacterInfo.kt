package com.justinpauga.cyphersystemscharactertracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.edit_character.*
import java.io.File

class EditCharacterInfo() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_character)
        editButtonIntent()
    }

    fun cancelEditCharacter(view: View){
//        val cancelButton = Intent(this, CharacterInfo::class.java)
//        startActivity(cancelButton)
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
    fun editButtonIntent(){
        val info = intent.extras
        if (info != null) {
            val char = info.getSerializable("character") as Character
            edit_name.setText(char.getName(), TextView.BufferType.EDITABLE)
            edit_descriptor.setText(char.getDescriptor(), TextView.BufferType.EDITABLE)
            edit_type.setText(char.getType(), TextView.BufferType.EDITABLE)
            edit_focus.setText(char.getFocus(), TextView.BufferType.EDITABLE)
            edit_tier.setText(Integer.toString(char.getTier()), TextView.BufferType.EDITABLE)
            edit_effort.setText(Integer.toString(char.getEffort()), TextView.BufferType.EDITABLE)
            edit_xp.setText(Integer.toString(char.getXp()), TextView.BufferType.EDITABLE)
            edit_might.setText(Integer.toString(char.getMight()), TextView.BufferType.EDITABLE)
            edit_speed.setText(Integer.toString(char.getSpeed()), TextView.BufferType.EDITABLE)
            edit_intellect.setText(Integer.toString(char.getIntelligence()), TextView.BufferType.EDITABLE)
            edit_abilities.setText(char.getAbilities(), TextView.BufferType.EDITABLE)
            edit_attacks.setText(char.getAttacks(), TextView.BufferType.EDITABLE)
            edit_cyphers.setText(char.getCyphers(), TextView.BufferType.EDITABLE)
            edit_equipment.setText(char.getEquipment(), TextView.BufferType.EDITABLE)
            edit_notes.setText(char.getNotes(), TextView.BufferType.EDITABLE)
        }
    }
    fun saveButtonIntent(view: View){
        var character = Character::class.java.newInstance()
        character.setName(edit_name.text.toString())
        character.setDescriptor(edit_descriptor.text.toString())
        character.setType(edit_type.text.toString())
        character.setFocus(edit_focus.text.toString())
        character.setTier(edit_tier.text.toString().toInt())
        character.setEffort(edit_effort.text.toString().toInt())
        character.setXp(edit_xp.text.toString().toInt())
        character.setMight(edit_might.text.toString().toInt())
        character.setSpeed(edit_speed.text.toString().toInt())
        character.setIntelligence(edit_intellect.text.toString().toInt())
        character.setAbilities(edit_abilities.text.toString())
        character.setAttacks(edit_attacks.text.toString())
        character.setCyphers(edit_cyphers.text.toString())
        character.setEquipment(edit_equipment.text.toString())
        character.setNotes(edit_notes.text.toString())
        sendCharacter(view,character)
    }
    fun deleteChar(){
        val info = intent.extras
        val character = info.getSerializable("character") as Character
        val file = File(filesDir, character.getName())
        file.delete()
    }
    fun saveChar(){
        val info = intent.extras
        val character = info.getSerializable("character") as Character
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
    fun sendCharacter(view: View, character: Character) {
        var intent = Intent()
        intent.putExtra("character", character)
        finish()
    }


}