package com.example.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {


    private lateinit var splashLayout: LinearLayout
    private lateinit var mainLayout: ScrollView
    private lateinit var detailsLayout: ScrollView

    private lateinit var txtAverage: TextView
    private lateinit var txtDetails: TextView

    private lateinit var editMin: EditText
    private lateinit var editMax: EditText
    private lateinit var editCondition: EditText

    private lateinit var spinnerDays: Spinner


    private val days = arrayOf(
        "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday", "Sunday"
    )


    private val minTemps = IntArray(7)
    private val maxTemps = IntArray(7)
    private val conditions = Array(7) { "" }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        splashLayout = findViewById(R.id.splashLayout)
        mainLayout = findViewById(R.id.mainLayout)
        detailsLayout = findViewById(R.id.detailsLayout)


        txtAverage = findViewById(R.id.txtAverage)
        txtDetails = findViewById(R.id.txtDetails)

        editMin = findViewById(R.id.editMin)
        editMax = findViewById(R.id.editMax)
        editCondition = findViewById(R.id.editCondition)


        spinnerDays = findViewById(R.id.spinnerDays)


        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
             days
        )

        spinnerDays.adapter = adapter

        //Buttons
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnExitSplash = findViewById<Button>(R.id.btnExitSplash)


        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnAverage = findViewById<Button>(R.id.btnAverage)
        val btnDetails = findViewById<Button>(R.id.btnDetails)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnExit = findViewById<Button>(R.id.btnExit)

        val btnBack = findViewById<Button>(R.id.btnBack)


        //Splash Screen Navigation
        btnStart.setOnClickListener {
            splashLayout.visibility = View.GONE
            mainLayout.visibility = View.GONE
        }


        btnExitSplash.setOnClickListener {
            finish()

        }
        //Save Data
        btnSave.setOnClickListener {

            if (editMin.text.isEmpty() ||
                editMax.text.isEmpty() ||
                editCondition.text.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                val index = spinnerDays.selectedItemPosition

                minTemps[index] = editMin.text.toString().toInt()
                maxTemps[index] = editMax.text.toString().toInt()
                conditions[index] = editCondition.text.toString()

                Toast.makeText(
                    this,
                    "Data Saved Successfully",
                    Toast.LENGTH_LONG

                ).show()

                editMin.text.clear()
                editMax.text.clear()
                editCondition.text.clear()
            }
        }

        // Calculate Average
        btnAverage.setOnClickListener {

            var total = 0

            for (i in maxTemps.indices) {
                total += maxTemps[i]
            }

            val average = total/maxTemps.size

            txtAverage.text = "Average Weekly Temperature: $average°C"

        }

        //View Details
        btnDetails.setOnClickListener {

            var display = ""

            for (i in days.indices) {

                display += "${days[i]}\n"
                display += "Min Temp: ${minTemps[i]}°C\n"
                display += "Max Temp: ${maxTemps[i]}°C\n"
                display += "Condition: ${conditions[i]}\n\n"
            }

            txtDetails.text = display
            mainLayout.visibility = View.VISIBLE
            detailsLayout.visibility = View.VISIBLE
        }

        // Back Button
        btnBack.setOnClickListener {
            detailsLayout.visibility = View.VISIBLE
            mainLayout.visibility = View.VISIBLE
        }

        // Clear Data
        btnClear.setOnClickListener {

            for (i in minTemps.indices) {
                minTemps[i] = 0
                maxTemps[i] = 0
                conditions[i] = ""
            }

            txtAverage.text = "Average Weekly Temperature"

            Toast.makeText(
                this,
                "Data Cleared",
                Toast.LENGTH_LONG
            ).show()

        }

        // Exit App
        btnExit.setOnClickListener {
            finish()




             }


        }


    }
