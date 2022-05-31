package com.example.crypton.service

import com.example.crypton.model.crytomodel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoApi {

    //API'mizin link
    //https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json

    //Veriyi almak için get kullanıyoruz. Get bir kople bir fonskiyon gibi çalışır. Bu sebeple bir fonksiyon tanımlıyoruz.,
    //
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Call<List<crytomodel>>
    //Call asenkron yani kullanıcı engellemeden internetten veri çekmeden kullandığımız fonskiyon.
}