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
        val cities= resources.getStringArray(R.array.city_array)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.country.adapter = spinnerAdapter

        val autoCompleteAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cities)
        binding.complete.setAdapter(autoCompleteAdapter)

        binding.signin.setOnClickListener {
            val name: String = binding.name.editText?.text.toString()
            val email: String = binding.email.editText?.text.toString()
            val password: String = binding.password.editText?.text.toString()
            val gender: String = if (binding.Male.isChecked) "Male" else "Female"
            val countryFromSpinner: String = binding.country.selectedItem.toString()
            val countryFromAutoComplete: String = binding.complete.text.toString()
            val isAccepted: Boolean = binding.accept.isChecked

            if (name.isEmpty()) {
                binding.name.error = "Name can't be empty"
            } else if (email.isEmpty()) {
                binding.email.error = "Email can't be empty"
            }else if (password.isEmpty()) {
                binding.password.error = "Password can't be empty"
            } else if (!isAccepted) {
                Toast.makeText(this, "You must accept the terms and conditions", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@MainActivity, Dashboard::class.java)
                intent.putExtra("name", name)
                intent.putExtra("email",email)
                intent.putExtra("password", password)
                intent.putExtra("gender", gender)
                intent.putExtra("countryFromSpinner", countryFromSpinner)
                intent.putExtra("countryFromAutoComplete", countryFromAutoComplete)
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