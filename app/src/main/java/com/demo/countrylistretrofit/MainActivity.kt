package com.demo.countrylistretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.countrylistretrofit.adapter.CountryListAdapter
import com.demo.countrylistretrofit.data.CountryModel
import com.demo.countrylistretrofit.repository.Repository
import com.demo.countrylistretrofit.retrofit.RetroInstance
import com.demo.countrylistretrofit.retrofit.RetroServiceInterface
import com.demo.countrylistretrofit.viewmodel.MainActivityViewModel
import com.demo.countrylistretrofit.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
//    private var recyclerAdapter: CountryListAdapter

    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        val repository = Repository(service)
        mainViewModel = ViewModelProvider(this,
            MainViewModelFactory(repository)
        ).get(MainActivityViewModel::class.java)
        mainViewModel.characters.observe(this) {
            val arr = ArrayList<CountryModel>()
//            val hs: HashSet<String> = HashSet()
            for (i in 0 until 825) {
                val char_brief =
                    CountryModel(it[i].name, it[i].image, it[i].status, it[i].species, it[i].gender)
                arr.add(char_brief)
            }
            initRecyclerView(arr)
        }


//        initRecyclerView()
//        initViewModel()


    }

    private fun initRecyclerView(arr: ArrayList<CountryModel>) {
        var recyclerAdapter: CountryListAdapter
        countryListRecyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdapter(this)
        countryListRecyclerview.adapter =recyclerAdapter
        recyclerAdapter.setCountryList(arr)
    }
//
//    private fun initViewModel() {
//            val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
//        viewModel.getLiveDataObserver().observe(this, Observer {
//            if(it != null) {
//                recyclerAdapter.setCountryList(it)
//                recyclerAdapter.notifyDataSetChanged()
//            } else {
//                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
//            }
//        })
//        viewModel.makeAPICall()
//    }
}