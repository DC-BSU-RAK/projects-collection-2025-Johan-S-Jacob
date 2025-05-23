package com.example.multipage_application

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class view_projects : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")


    private lateinit var resultText: TextView
    private lateinit var showButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_projects)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // assigning the home button
        val home: Button = findViewById(R.id.home_btn4)

        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //-------------------------------------------------------------------------------------------
        //shared preferences
        resultText = findViewById(R.id.txt2) // make sure txt2 exists in the layout
        val data = intent.getStringExtra("project_data")  // receive the data
        resultText.text = data ?: "No data found"




        //-------------------------------------------------------------------------------------------
        //show button
        showButton = findViewById(R.id.show_btn)
        // Set click listener for the Show Projects button
        showButton.setOnClickListener {
            val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val savedData = sharedPref.getString("text", "No data found")
            resultText.text = savedData
        }
    }
}