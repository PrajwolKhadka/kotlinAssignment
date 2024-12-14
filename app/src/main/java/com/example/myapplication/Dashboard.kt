package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.shoesAdapter
import com.example.myapplication.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater) // Inflate the layout using binding
        setContentView(binding.root)

        // Retrieve data from intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val gender= intent.getStringExtra("gender")
        val country= intent.getStringExtra("countryFromSpinner")
        val city= intent.getStringExtra("countryFromAutoComplete")
        // Set the text to the TextViews using binding
        binding.welcomeTextView.text = "Welcome $name"
        binding.emailTextView.text = "Email: $email"
        binding.gender.text="Gender: $gender"
        binding.country.text="Country: $country"
        binding.city.text="City: $city"

        val recyclerView: RecyclerView = binding.recy
        recyclerView.layoutManager = LinearLayoutManager(this)


        val imageList = arrayListOf(R.drawable.shoe1, R.drawable.shoe2, R.drawable.shoe3, R.drawable.shoe4)
        val titleList = arrayListOf("Jordan 1", "Nike Air Max","Jordan Air", "Nike")
        val descList = arrayListOf("Shoes as it should be", "Comfort and style", "Ease of wearing", "Affordable")


// Create an instance of the adapter
        val adapter = shoesAdapter(this, imageList, titleList, descList)
        recyclerView.adapter = adapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}