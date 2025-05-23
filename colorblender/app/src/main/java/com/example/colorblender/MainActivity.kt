package com.example.colorblender

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
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
        val spinner1: Spinner = findViewById(R.id.spinner1)
        val spinner2: Spinner = findViewById(R.id.spinner2)
        val output: TextView = findViewById(R.id.output)
        val blend: Button = findViewById(R.id.blend)

        val colour1 = arrayOf("Red", "Blue","Yellow","Green","Black","White") //list of colors for the first choice
        val colour2 = arrayOf("Red", "Blue","Yellow","Green","Black","White")//list of colors for the second choice

        // creating a spinner as dropdown for colour choices
        spinner1.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, colour1)
        spinner2.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, colour2)


        //assigning values for each colour choices
        val choices1 = mapOf(
            "Red" to mapOf(
                "Blue" to "Purple",
                "Yellow" to "Orange",
                "Green" to "Brown",
                "Black" to "Maroon",
                "White" to "Pink"
            ),
            "Blue" to mapOf(
                "Red" to "Purple",
                "Yellow" to "Green",
                "Green" to "Cyan",
                "Black" to "Navy Blue",
                "White" to "Sky Blue"
            ),
            "Yellow" to mapOf(
                "Blue" to "Green",
                "Red" to "Orange",
                "Green" to "Lime Green",
                "Black" to "Olive Green",
                "White" to "Pastel Yellow"
            ),
            "Green" to mapOf(
                "Blue" to "Cyan",
                "Yellow" to "Lime Green",
                "Red" to "Brown",
                "Black" to "Forest Green",
                "White" to "Mint Green"
            ),
            "Black" to mapOf(
                "Blue" to "Navy Blue",
                "Yellow" to "Olive Green",
                "Green" to "Forest Green",
                "Red" to "Maroon",
                "White" to "Gray"
            ),
            "White" to mapOf(
                "Blue" to "Sky Blue",
                "Yellow" to "Pastel Yellow",
                "Green" to "Mint Green",
                "Black" to "Gray",
                "Red" to "Pink"
            )
        )
        //executes the program on the click of the button
        blend.setOnClickListener {
            val spinner1 = spinner1.selectedItem.toString() //choice of the first colour
            val spinner2 = spinner2.selectedItem.toString() //choice of the second color


            // if invalid choice is made then it will return a command
            val output1 = choices1[spinner1]?.get(spinner2) ?: "Select two colours"

            output.text = output1

            val instructButton : Button = findViewById(R.id.instruction_btn)
            instructButton.setOnClickListener{
                val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val popupView = inflater.inflate(R.layout.activity_instructions_pop_up,null)
                val instructWindow = PopupWindow(popupView, 1000,1700,true)
                instructWindow.showAtLocation(popupView, Gravity.BOTTOM,0,200)

                val closeButton : Button = popupView.findViewById(R.id.close_btn)
                closeButton.setOnClickListener{
                    instructWindow.dismiss()
                }
            }



        }
    }
}