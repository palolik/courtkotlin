package com.demo.countrylistretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.countrylistretrofit.adapter.CountryListAdapter
import com.demo.countrylistretrofit.databinding.ActivityMainBinding
import com.demo.countrylistretrofit.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: CountryListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.countryListRecyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdapter(this)
        binding.countryListRecyclerview.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer { countryList ->
            if (countryList != null) {
                recyclerAdapter.setCountryList(countryList)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }
}
