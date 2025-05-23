package com.example.multipage_application

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //------------------------------------------------------------------------------------------
        //Instruction button

        val instructButton : Button = findViewById(R.id.home_btn2)
        instructButton.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_instructions,null)
            val instructWindow = PopupWindow(popupView, 1000,2000,true)
            instructWindow.showAtLocation(popupView, Gravity.BOTTOM,0,200)

            val closeButton : Button = popupView.findViewById(R.id.close_btn)
            closeButton.setOnClickListener{
                instructWindow.dismiss()
            }
        }


        //------------------------------------------------------------------------------------------
        //Assigning image buttons

        val add: ImageButton = findViewById(R.id.add_projects)
        val view: ImageButton = findViewById(R.id.view_projects)


        add.setOnClickListener {
            val intent = Intent(this, add_projects::class.java)
            startActivity(intent)
        }



        view.setOnClickListener {
            val intent = Intent(this, view_projects::class.java)
            startActivity(intent)
        }



        //------------------------------------------------------------------------------------------
        //setting up preferences/data



    }
}