package com.example.crypton.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crypton.R
import com.example.crypton.adapter.RecycleAdapter
import com.example.crypton.model.crytomodel
import com.example.crypton.service.CryptoApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecycleAdapter.Listener {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels : ArrayList<crytomodel>? = null
    private var recyclerAdapter : RecycleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadData()
    }

    private fun loadData(){
        //Retrofit sınıfımızı oluşturduk. Gson dönüştürücüyü ekledik. Geriye birtek bunu servis kullanarak api ile bağlamak kaldı.
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CryptoApi::class.java)
        val call = service.getData()
        //Bu satırdan sonra artık verilerimi almaya başlayabiliriz ama bize gelen cevap başırılımı, başarısızmı,
        // null mu dolu mu kontrol etmemiz lazım.
        call.enqueue(object: Callback<List<crytomodel>>{
            override fun onResponse(
                call: Call<List<crytomodel>>,
                response: Response<List<crytomodel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        cryptoModels?.let {
                            recyclerAdapter = RecycleAdapter(it, this@MainActivity)
                            recyclerView.adapter = recyclerAdapter
                        }

                        /*
                        for(cryptoModel : crytomodel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)

                        }*/
                    }
                }
            }

            override fun onFailure(call: Call<List<crytomodel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    override fun onItemClick(cryptomodel: crytomodel) {
        Toast.makeText(this,"Clicked : ${cryptomodel.currency}", Toast.LENGTH_LONG).show()
    }

}