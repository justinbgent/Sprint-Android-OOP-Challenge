package com.example.sprint.viewmodel

import android.content.Context
import android.util.Log
import com.example.sprint.model.*
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface AgeOfEmpireApi {

    @GET("technologies")
    fun getTechnology(): Call<Technologies>

    @GET("structures")
    fun getStructure(): Call<Structures>
}

object RetrofitInstance {
    private const val BASE_URL = "https://age-of-empires-2-api.herokuapp.com//api/v1/"

    fun getStructure(): Call<Structures> {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(AgeOfEmpireApi::class.java).getStructure()
    }

    fun getTechnology(): Call<Technologies> {
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


class NeededValues(context: Context) {
    companion object{
        var favorite = false
        var theList = mutableListOf<MyValues>()
    }

    var listener: WorkWithThread? = null

    interface WorkWithThread {
        fun getList(structAndTechList: MutableList<MyValues>)
    }

    init {
        if (context is WorkWithThread) {
            listener = context
        }
    }

    fun getRetrofitTechInstance() {
        RetrofitInstance.getTechnology().enqueue(object : Callback<Technologies> {
            override fun onFailure(call: Call<Technologies>, t: Throwable) {
                Log.i("findit", "It is failing")
            }

            override fun onResponse(call: Call<Technologies>, response: Response<Technologies>) {
                val myList = mutableListOf<MyValues>()
                response.body()?.technologies?.forEach{
                    myList.add(TechToMake(it.id, it.name, it.description, false))
                }
                listener?.getList(myList)
            }
        })
    }

    fun getRetrofitStructInstance() {
        RetrofitInstance.getStructure().enqueue(object : Callback<Structures> {
            override fun onFailure(call: Call<Structures>, t: Throwable) {
                Log.i("findit", "It is failing")
            }

            override fun onResponse(call: Call<Structures>, response: Response<Structures>) {
                val myList = mutableListOf<MyValues>()
                response.body()?.structures?.forEach{
                    myList.add(StructureToMake(it.id, it.name, it.age, false))
                }
                listener?.getList(myList)
            }
        })

    }
}

