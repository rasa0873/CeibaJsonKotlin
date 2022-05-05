package com.rasaapps.raul.ceibajsonkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        jumping()
    }

    fun jumping(){
        val jump = Intent(this, MainActivity::class.java)
        startActivity(jump)
        finish()
    }


}