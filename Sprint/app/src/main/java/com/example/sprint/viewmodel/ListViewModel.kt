package com.example.sprint.viewmodel

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class ListViewModel {
    fun getValues(): String {
        RetrofitInstance.getPokemon
    }

}

interface PokemonApi{

    @GET("{IdOrName}")
    fun getPokemon(@Path("IdOrName")idOrName: String): Call<PokemonDetail>
}

object RetrofitInstance{
    private const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

    fun getPokemon(pokemonNameOrId: String): Call<PokemonDetail>{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(PokemonApi::class.java).getPokemon(pokemonNameOrId)
    }
}