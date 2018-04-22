package com.justinpauga.cyphersystemscharactertracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import kotlinx.android.synthetic.main.new_round.*

class NewRound : AppCompatActivity() {
    val typeOfDamage = arrayOf("Might", "Intelligence", "Damage")
    val amountOfDamage = Array<Int>(9, {it + 1})


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_round)

        val damageB: Button = findViewById(R.id.damage_button)
        damage_button.setOnClickListener {
            val damageDialog = AlertDialog.Builder(this)

            val damageView = layoutInflater.inflate(R.layout.round_spinner, null) as View
            val amountSpinner = damageView.findViewById<Spinner>(R.id.damage_taken)
            var damageSpinner = damageView.findViewById<Spinner>(R.id.amount_taken)
            val roundAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, typeOfDamage)
            val amountAdapter = ArrayAdapter<Int>(this, R.layout.support_simple_spinner_dropdown_item, amountOfDamage)
            roundAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            amountAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            amountSpinner.adapter = amountAdapter
            damageSpinner.adapter = roundAdapter


            damageDialog.setTitle("Choose Damage Taken")

            damageDialog.setPositiveButton("OK") { dialog, which ->
                //Todo take the damage and subtract from total
            }
            damageDialog.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()

            }
            damageDialog.setView(damageView)
            damageDialog.create()
            damageDialog.show()
        }
        val healB: Button = findViewById(R.id.heal_button)
        heal_button.setOnClickListener {
            val healDialog = AlertDialog.Builder(this)
            healDialog.setTitle("Enter Damage Taken")

            healDialog.setPositiveButton("OK") { dialog, which ->
                //Todo
            }
            healDialog.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()

            }
            healDialog.show()
        }

        val cypherB: Button = findViewById(R.id.cypher_button)
        cypher_button.setOnClickListener {
            val cypherDialog = AlertDialog.Builder(this)
            cypherDialog.setTitle("Enter Damage Taken")

            cypherDialog.setPositiveButton("OK") { dialog, which ->
                //Todo
            }
            cypherDialog.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()

            }
            cypherDialog.show()
        }

        val abilityB: Button = findViewById(R.id.ability_button)
        ability_button.setOnClickListener {
            val abilityDialog = AlertDialog.Builder(this)
            abilityDialog.setTitle("Enter Damage Taken")

            abilityDialog.setPositiveButton("OK") { dialog, which ->
                //Todo
            }
            abilityDialog.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()

            }
            abilityDialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        newRoundIntentHandle()
    }

    fun endRoundOnClick(view: View) {
        val endRoundButton = Intent(this, MainActivity::class.java)
        startActivity(endRoundButton)
    }

    fun newRoundIntentHandle() {
        val info = intent.extras
        if (info != null) {
            val char = info.getSerializable("character") as Character
            round_name.text = char.getName()
            round_descriptor.text = char.getDescriptor()
            round_abilities.text = char.getAbilities()
            round_attacks.text = char.getAttacks()
            round_cyphers.text = char.getCyphers()
            round_effort.text = char.getEffort().toString()
            round_might_total.text = char.getMight().toString()
            round_speed_total.text = char.getSpeed().toString()
            round_intellect_total.text = char.getIntelligence().toString()
            round_tier.text = char.getTier().toString()
            round_xp.text = char.getXp().toString()
            round_equipment.text = char.getEquipment()
            round_focus.text = char.getFocus()
            //round_notes.text = char.setNotes()
            round_type.text = char.getType()

        }
    }
}