package com.example.auauapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.auauapp.R
import com.example.auauapp.models.Cachorro
import com.example.auauapp.services.PetService
import com.example.auauapp.utils.ConexaoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ListarCachorros : AppCompatActivity() {
    lateinit var lContainer: LinearLayout;
    lateinit var tvX: TextView;
    lateinit var tvY: TextView;
    lateinit var tvZ: TextView;
    lateinit var layoutContainer: LinearLayout;

    lateinit var api: Retrofit;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_cachorros)
        lContainer = findViewById(R.id.l_container);
        api = ConexaoApi.getRetrofitInstance();
        tvX = findViewById(R.id.tv_x);
        tvZ = findViewById(R.id.tv_z)
        tvY = findViewById(R.id.tv_y);
        layoutContainer = findViewById(R.id.l_container);
    }

    fun listar() {
        val endpoint = api.create(PetService::class.java)
        val callback = endpoint.listarCachorros();

        callback.enqueue(object : Callback<List<Cachorro>> {
            override fun onResponse(
                call: Call<List<Cachorro>>,
                response: Response<List<Cachorro>>
            ) {
                when (response.code()) {
                    200 -> {
                        var contadorX: Int = 0;
                        var contadorY: Int = 0;
                        val newTextView: TextView = TextView(baseContext);
                        newTextView.text = response.body()?.toString();
                        layoutContainer.addView(newTextView);

                        response.body()?.forEach {


                            if (it?.indicadoCriancas) {
                                contadorX++;
                            } else {
                                contadorY++;
                            }

                        }
                        tvX.text = "Cachorros indicados para crianças ${contadorX.toString()}";
                        tvY.text = "Cachorros indicados para crianças ${contadorY.toString()}";
                        tvZ.text = "Total de Cachorros: ${(contadorX + contadorY).toString()}"
                    }
                    else -> {
                        tvX.text = "vazio";
                    }
                }
            }

            override fun onFailure(call: Call<List<Cachorro>>, t: Throwable) {
                Toast.makeText(baseContext, "Algo deu errado", Toast.LENGTH_SHORT).show()
            }
        })


    }
}