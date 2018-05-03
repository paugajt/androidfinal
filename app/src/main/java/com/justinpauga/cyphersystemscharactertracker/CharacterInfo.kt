package com.justinpauga.cyphersystemscharactertracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.character_info.*

class CharacterInfo() : AppCompatActivity() {

    var info = Character::class.java.newInstance()
    lateinit var char: Character
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_info)
        handleIntent()
    }

    override fun onResume() {
        super.onResume()
    }

    fun newRoundButtonIntent(view: View) {
        val newRoundButton = Intent(this, NewRound::class.java)
        newRoundButton.putExtra("character", char)
        startActivity(newRoundButton)
    }

    fun editCharacterInfo(view: View) {
        val editButton = Intent(this, EditCharacterInfo::class.java)
        editButton.putExtra("character", char)
        startActivityForResult(editButton, 1)
    }

    fun handleIntent() {
        val info = intent.extras
        if (info != null) {
            char = info.getSerializable("character") as Character
            view_name.text = char.getName()
            view_descriptor.text = char.getDescriptor()
            view_type.text = char.getType()
            view_focus.text = char.getFocus()
            view_tier.text = char.getTier().toString()
            view_effort.text = char.getEffort().toString()
            view_xp.text = char.getXp().toString()
            view_might_total.text = char.getMight().toString()
            view_might_remain.text = char.getMight().toString()
            view_speed_total.text = char.getSpeed().toString()
            view_speed_remain.text = char.getSpeed().toString()
            view_intellect_total.text = char.getIntelligence().toString()
            view_intellect_remain.text = char.getIntelligence().toString()
            view_abilities.text = char.getAbilities()
            view_attacks.text = char.getAttacks()
            view_cyphers.text = char.getCyphers()
            view_equipment.text = char.getEquipment()
            view_notes.setText(char.getNotes())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            info = data!!.getSerializableExtra("Character") as Character
            newCharacter(info)

        }

    }

    fun newCharacter(character: Character) {
        view_name.text = character.getName()
        view_descriptor.text = character.getDescriptor()
        view_type.text = character.getType()
        view_focus.text = character.getFocus()
        view_tier.text = character.getTier().toString()
        view_effort.text = character.getEffort().toString()
        view_xp.text = character.getXp().toString()
        view_might_total.text = character.getMight().toString()
        view_might_remain.text = character.getMight().toString()
        view_speed_total.text = character.getSpeed().toString()
        view_speed_remain.text = character.getSpeed().toString()
        view_intellect_total.text = character.getIntelligence().toString()
        view_intellect_remain.text = character.getIntelligence().toString()
        view_abilities.text = character.getAbilities()
        view_attacks.text = character.getAttacks()
        view_cyphers.text = character.getCyphers()
        view_equipment.text = character.getEquipment()
        view_notes.text = character.getNotes()
    }
}