package com.justinpauga.cyphersystemscharactertracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.new_round.*

class NewRound : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_round)

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


}