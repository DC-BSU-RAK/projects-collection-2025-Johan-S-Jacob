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

class add_projects : AppCompatActivity() {

    // Declare class-level variables
    private lateinit var input: EditText
    private lateinit var saveButton: Button
    private lateinit var getButton: Button
    private lateinit var result: TextView
    private lateinit var sharedPref: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_projects)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Home button setup
        val home: Button = findViewById(R.id.home_btn1)
        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Initialize UI elements
        input = findViewById(R.id.txt1)
        saveButton = findViewById(R.id.add_project_btn)
        getButton = findViewById(R.id.show_btn)
        result = findViewById(R.id.result)

        // Initialize SharedPreferences
        sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Save Button Action
        saveButton.setOnClickListener {
            val myInput = input.text.toString()
            if (myInput.isNotBlank()) {
                sharedPref.edit().putString("text", myInput).apply()
                result.text = "Saved successfully!"
            } else {
                result.text = "Input is empty!"
            }
        }

        // Get Button Action
        getButton.setOnClickListener {
            val output = sharedPref.getString("text", "No data found")

            val intent = Intent(this, view_projects::class.java)
            intent.putExtra("project_data", output)  // send text to next screen
            startActivity(intent)
        }


    }
}
