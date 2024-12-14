package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countries = resources.getStringArray(R.array.countries_array)

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.country.adapter = spinnerAdapter

        val autoCompleteAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries)
        binding.complete.setAdapter(autoCompleteAdapter)

        binding.signin.setOnClickListener {
            val name: String = binding.name.editText?.text.toString()
            val password: String = binding.textInputLayout.editText?.text.toString()
            val gender: String = if (binding.Male.isChecked) "Male" else "Female"
            val countryFromSpinner: String = binding.country.selectedItem.toString() // Get selected country from Spinner
            val countryFromAutoComplete: String = binding.complete.text.toString() // Get text from AutoCompleteTextView
            val isAccepted: Boolean = binding.accept.isChecked

            if (name.isEmpty()) {
                binding.name.error = "Name can't be empty"
            } else if (password.isEmpty()) {
                binding.textInputLayout.error = "Password can't be empty"
            } else if (!isAccepted) {
                Toast.makeText(this, "You must accept the terms and conditions", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@MainActivity, Dashboard::class.java)
                intent.putExtra("name", name)
                intent.putExtra("password", password)
                intent.putExtra("gender", gender)
                intent.putExtra("countryFromSpinner", countryFromSpinner) // Pass country from Spinner
                intent.putExtra("countryFromAutoComplete", countryFromAutoComplete) // Pass country from AutoCompleteTextView
                startActivity(intent)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}