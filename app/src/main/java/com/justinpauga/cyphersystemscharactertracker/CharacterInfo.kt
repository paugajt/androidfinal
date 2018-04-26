package com.justinpauga.cyphersystemscharactertracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.character_info.*

class CharacterInfo() : AppCompatActivity() {

    lateinit var char: Character
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_info)
    }

    override fun onResume(){
        super.onResume()
        handleIntent()
    }
    fun newRoundButtonIntent (view: View){
        val newRoundButton = Intent(this, NewRound::class.java)
        newRoundButton.putExtra("character", char)
        startActivity(newRoundButton)
    }
    fun editCharacterInfo(view: View){
        val editButton = Intent(this, NewCharacter::class.java)
        startActivity(editButton)
    }
    fun handleIntent(){
        val info = intent.extras
        if (info != null){
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


}