package com.demo.countrylistretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.demo.countrylistretrofit.data.CountryModel
import com.demo.countrylistretrofit.repository.Repository
import com.demo.countrylistretrofit.retrofit.RetroInstance
import com.demo.countrylistretrofit.retrofit.RetroServiceInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(private val repository: Repository): ViewModel() {

//    lateinit var liveDataList: MutableLiveData<List<CountryModel>>
//
//    init {
//        liveDataList = MutableLiveData()
//    }
//
//
//    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>> {
//        return liveDataList
//    }
//
//    fun makeAPICall() {
//        val retroInstance = RetroInstance.getRetroInstance()
//        val retroService  = retroInstance.create(RetroServiceInterface::class.java)
//        val call  = retroService.getData()
//        call.enqueue(object : Callback<List<CountryModel>> {
//            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
//                println("Hello")
//                Log.d("error", "${t}")
//                liveDataList.postValue(null)
//
//            }
//
//            override fun onResponse(
//                call: Call<List<CountryModel>>,
//                response: Response<List<CountryModel>>
//            ) {
//                println(response.body())
//
//                liveDataList.postValue(response.body())
//            }
//        })
//
//
//    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            /*val arr = Array(826, { i -> i * 1 })
            var str: String = "1"
            for (i in 2..arr.size-1)
            {
                str= str+", ${i}"
            }*/
            repository.getCharacters()


        }
    }
    val characters : LiveData<List<CountryModel>>
        get() = repository.characters
}