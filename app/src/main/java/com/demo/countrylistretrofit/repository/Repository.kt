package com.demo.countrylistretrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.countrylistretrofit.data.CountryModel
import com.demo.countrylistretrofit.retrofit.RetroInstance
import com.demo.countrylistretrofit.retrofit.RetroServiceInterface
import retrofit2.Retrofit


class Repository(private val charService: RetroServiceInterface) {

    private val characterLiveData = MutableLiveData<List<CountryModel>>()
    val characters: LiveData<List<CountryModel>>
        get() = characterLiveData

    suspend fun getCharacters() {
        val result = charService.getData()
        if (result.isSuccessful) {
            val items = result.body()
            if (items!=null){
                for(i in 1 until 826)
                    characterLiveData.postValue(items!!)
            }
        }
    }
}