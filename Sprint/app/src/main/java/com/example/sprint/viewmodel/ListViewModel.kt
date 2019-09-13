package com.example.sprint.viewmodel

import android.os.Parcelable
import com.example.sprint.model.Structures
import com.example.sprint.model.Tech
import com.example.sprint.model.Technologies
import com.google.gson.GsonBuilder
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface AgeOfEmpireApi{

    @GET("technologies")
    fun getTechnology(): Call<Technologies>

    @GET("structures")
    fun getStructure(): Call<Structures>
}

object RetrofitInstance{
    private const val BASE_URL = "https://age-of-empires-2-api.herokuapp.com//api/v1/"

    fun getStructure(): Call<Structures>{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(AgeOfEmpireApi::class.java).getStructure()
    }

    fun getTechnology(): Call<Technologies>{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(AgeOfEmpireApi::class.java).getTechnology()
    }
}

object NeededValues{
    lateinit var technology: Technologies
    lateinit var techs: List<Tech>
    var favorite = false

    fun getItemsList(): MutableList<MyTechValues>{
        val myList = mutableListOf<MyTechValues>()

        for(i in techs.indices){
            myList.add(MyTechValues(techs[i].id, techs[i].name, techs[i].description, false))
        }

        return myList
    }

    fun getRetrofitInstance(){}
}


@Parcelize
class MyTechValues(val id: Int, val name: String, val description: String, val favorite: Boolean):
    Parcelable