package com.justinpauga.cyphersystemscharactertracker

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.new_round.*
import org.w3c.dom.Text
import java.util.*

class NewRound : AppCompatActivity() {

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
        mShakeDetector!!.setOnShakeListener(object: ShakeDetector.OnShakeListener {

            override fun onShake(count: Int) {
                handleShakeEvent(count)
            }
        })


        val damageB: Button = findViewById(R.id.damage_button)
        damage_button.setOnClickListener{
            val damageDialog = AlertDialog.Builder(this)
            damageDialog.setTitle("Enter Damage Taken")

            damageDialog.setPositiveButton("OK"){
                dialog, which ->
                //Todo
            }
            damageDialog.setNegativeButton("Cancel"){
                dialog, which ->
                dialog.dismiss()

            }
            damageDialog.show()
        }

    }
    fun endRoundOnClick(view: View){
        val endRoundButton = Intent(this, MainActivity::class.java)
        startActivity(endRoundButton)
    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(mShakeDetector)
    }

    fun handleShakeEvent(count: Int) {
        val random = Random()
        val roll: Int = random.nextInt(21 -1) + 1

        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.roll_dialog, null) as View
        val showRoll = dialogView.findViewById<TextView>(R.id.roll_view)
        showRoll.text = roll.toString()

        dialogBuilder.setView(dialogView)
        dialogBuilder.setTitle("You Rolled a:")
        val b = dialogBuilder.create()
        b.show()


    }

}