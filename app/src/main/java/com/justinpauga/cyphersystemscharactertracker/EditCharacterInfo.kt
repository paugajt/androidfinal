package com.justinpauga.cyphersystemscharactertracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.edit_character.*

class EditCharacterInfo() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_character)
        editButtonIntent()
    }

    fun cancelEditCharacter(view: View){
        val cancelButton = Intent(this, CharacterInfo::class.java)
        startActivity(cancelButton)
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

//    fun sendCharacter(view: View, character: Character) {
//        var intent = Intent()
//        intent.putExtra("Character", character)
//        setResult(Activity.RESULT_OK, intent)
//        finish()
//    }

}