package com.justinpauga.cyphersystemscharactertracker

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View

import android.widget.*
import com.justinpauga.cyphersystemscharactertracker.R.id.*
import kotlinx.android.synthetic.main.new_round.*
import java.util.*

class NewRound : AppCompatActivity() {
    val typeOfDamage = arrayOf("Might", "Speed", "Intelligence")
    val amountOfDamage = Array<Int>(9, {it + 1})
    val amountToHeal = Array<Int>(20, {it + 1})


    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    private var mShakeDetector: ShakeDetector? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_round)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mShakeDetector = ShakeDetector()
        mShakeDetector!!.setOnShakeListener(object : ShakeDetector.OnShakeListener {

            override fun onShake(count: Int) {
                handleShakeEvent(count)
            }
        })


        val damageB: Button = findViewById(R.id.damage_button)
        damage_button.setOnClickListener {

            val damageDialog = AlertDialog.Builder(this)

            val damageView = layoutInflater.inflate(R.layout.damage_spinner, null) as View

            val damageTypeSpinner = damageView.findViewById<Spinner>(R.id.damage_taken)
            var amountSpinner = damageView.findViewById<Spinner>(R.id.amount_taken)

            val roundAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, typeOfDamage)
            val amountAdapter = ArrayAdapter<Int>(this, R.layout.support_simple_spinner_dropdown_item, amountOfDamage)

            roundAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            amountAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

            damageTypeSpinner.adapter = roundAdapter
            amountSpinner.adapter = amountAdapter

            damageDialog.setTitle("Choose Damage Taken")

            damageDialog.setPositiveButton("OK") { dialog, which ->

                val typeSelected = damageTypeSpinner.selectedItem.toString()
                val damageAmountSelected = amountSpinner.selectedItem.toString().toInt()

                calcDamage(typeSelected, damageAmountSelected)

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
            val healView = layoutInflater.inflate(R.layout.heal_spinner, null) as View

            val healTypeSpinner = healView.findViewById<Spinner>(R.id.heal_type)
            val healSpinner = healView.findViewById<Spinner>(R.id.heal_amount)

            val healTypeAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, typeOfDamage)
            val healAdapter = ArrayAdapter<Int>(this, R.layout.support_simple_spinner_dropdown_item, amountToHeal)

            healAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            healTypeSpinner.adapter = healTypeAdapter
            healSpinner.adapter = healAdapter

            healDialog.setTitle("Heal Amount")

            healDialog.setPositiveButton("OK") { dialog, which ->
                val healType = healTypeSpinner.selectedItem.toString()
                val healAmount = healSpinner.selectedItem.toString().toInt()

                calcHeal(healType, healAmount)
            }
            healDialog.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()

            }
            healDialog.setView(healView)
            healDialog.create()
            healDialog.show()
        }

        val cypherB: Button = findViewById(R.id.cypher_button)
        cypher_button.setOnClickListener {
            val cypherDialog = AlertDialog.Builder(this)
            cypherDialog.setTitle("Use Cypher")

            cypherDialog.setPositiveButton("Ok") { dialog, which ->
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
            val abilityView = layoutInflater.inflate(R.layout.ability_spinner, null) as View

            val abilityTypeSpinner = abilityView.findViewById<Spinner>(R.id.ability_type)
            val abilitySpinner = abilityView.findViewById<Spinner>(R.id.ability_amount)

            val abilityTypeAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, typeOfDamage)
            val abilityAdapter = ArrayAdapter<Int>(this, R.layout.support_simple_spinner_dropdown_item, amountToHeal)

            abilityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            abilityTypeSpinner.adapter = abilityTypeAdapter
            abilitySpinner.adapter = abilityAdapter

            abilityDialog.setTitle("Ability Used")

            abilityDialog.setPositiveButton("OK") { dialog, which ->
                val abilityType = abilityTypeSpinner.selectedItem.toString()
                val abilityAmount = abilitySpinner.selectedItem.toString().toInt()

                calcHeal(abilityType, abilityAmount)
            }
                abilityDialog.setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()

                }
                abilityDialog.setView(abilityView)
                abilityDialog.create()
                abilityDialog.show()
        }
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
            round_might_remain.text = char.getMight().toString()
            round_speed_remain.text = char.getSpeed().toString()
            round_intellect_remain.text = char.getIntelligence().toString()
            round_might_total.text = char.getMight().toString()
            round_speed_total.text = char.getSpeed().toString()
            round_intellect_total.text = char.getIntelligence().toString()
            round_tier.text = char.getTier().toString()
            round_xp.text = char.getXp().toString()
            round_equipment.text = char.getEquipment()
            round_focus.text = char.getFocus()
            round_notes.setText(char.getNotes(), TextView.BufferType.EDITABLE)
            round_type.text = char.getType()

        }
    }
    fun calcDamage(type : String, amount: Int){
        var mightTextView = findViewById<TextView>(R.id.round_might_remain)
        var speedTextView = findViewById<TextView>(R.id.round_speed_remain)
        var intellectTextView = findViewById<TextView>(R.id.round_intellect_remain)
        var might = round_might_remain.text
        var speed = round_speed_remain.text
        var intellect = round_intellect_remain.text

        if (type.equals("Might",ignoreCase = true))
        {
            might = (might.toString().toInt() - amount).toString()
            mightTextView.text = might

        }
        else if(type.equals("Speed", ignoreCase = true)){
            speed = (speed.toString().toInt() - amount).toString()
            speedTextView.text = speed

        }
        else{
            intellect = (intellect.toString().toInt() - amount).toString()
            intellectTextView.text = intellect
        }
    }
    fun calcHeal(type :String, amount: Int){
        var mightTextView = findViewById<TextView>(R.id.round_might_remain)
        var speedTextView = findViewById<TextView>(R.id.round_speed_remain)
        var intellectTextView = findViewById<TextView>(R.id.round_intellect_remain)
        var might = round_might_remain.text
        var speed = round_speed_remain.text
        var intellect = round_intellect_remain.text

        if (type.equals("Might",ignoreCase = true))
        {
            might = (might.toString().toInt() + amount).toString()
            mightTextView.text = might

        }
        else if(type.equals("Speed", ignoreCase = true)){
            speed = (speed.toString().toInt() + amount).toString()
            speedTextView.text = speed

        }
        else{
            intellect = (intellect.toString().toInt() + amount).toString()
            intellectTextView.text = intellect
        }
    }
    fun calcAbility(type :String, amount: Int){
        var mightTextView = findViewById<TextView>(R.id.round_might_remain)
        var speedTextView = findViewById<TextView>(R.id.round_speed_remain)
        var intellectTextView = findViewById<TextView>(R.id.round_intellect_remain)
        var might = round_might_remain.text
        var speed = round_speed_remain.text
        var intellect = round_intellect_remain.text

        if (type.equals("Might",ignoreCase = true))
        {
            might = (might.toString().toInt() - amount).toString()
            mightTextView.text = might

        }
        else if(type.equals("Speed", ignoreCase = true)){
            speed = (speed.toString().toInt() - amount).toString()
            speedTextView.text = speed

        }
        else{
            intellect = (intellect.toString().toInt() - amount).toString()
            intellectTextView.text = intellect
        }
    }

    override fun onResume() {
        super.onResume()
        newRoundIntentHandle()

        mSensorManager!!.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(mShakeDetector)
    }

    fun handleShakeEvent(count: Int) {
        if(count < 2) {
            val random = Random()
            val roll: Int = random.nextInt(21 - 1) + 1

            val dialogBuilder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.roll_dialog, null) as View
            val showRoll = dialogView.findViewById<TextView>(R.id.rollText)
            showRoll.text = roll.toString()

            dialogBuilder.setView(dialogView)
            dialogBuilder.setTitle("You Rolled a:")
            val b = dialogBuilder.create()
            b.show()
        }

    }

}