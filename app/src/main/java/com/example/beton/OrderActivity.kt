package com.example.beton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import kotlin.math.roundToInt

class OrderActivity : AppCompatActivity() {
    private var distance: Float? = null
    private lateinit var chosenAddress: String
    private lateinit var dist: TextView
    private lateinit var area: TextView
    private lateinit var price: TextView
    private lateinit var address: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dist = findViewById(R.id.dist)
        area = findViewById(R.id.area)
        price = findViewById(R.id.price)
        address = findViewById(R.id.address)

        val receivedIntent = intent
        distance = receivedIntent.getFloatExtra("dist", 0F)
        chosenAddress = receivedIntent.getStringExtra("address").toString()

        val km = distance!! / 1000
        val zone = (km / 10).roundToInt()
        val sum = zone * 200

        address.text = chosenAddress
        dist.text = "${km} km"
        area.text = "zone ${zone}"
        price.text = "${sum} rub"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}