package com.example.auauapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import com.example.auauapp.R
import com.example.auauapp.models.Cachorro
import com.example.auauapp.services.PetService
import com.example.auauapp.utils.ConexaoApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CadastrarCachorro : AppCompatActivity() {
    lateinit var api:Retrofit;
    lateinit var etRaca:EditText;
    lateinit var etPreco:EditText;
    lateinit var ivCachorroFeliz : ImageView;
    lateinit var tvCadastroOk:TextView;
    lateinit var sParaCriancas:Switch;
    lateinit var raca:String;
    var preco:Int=0;
    var paraCriancas:Boolean =false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_cachorro)
        etRaca = findViewById(R.id.et_raca);
        etPreco = findViewById(R.id.et_preco);
        api = ConexaoApi.getRetrofitInstance();
        sParaCriancas = findViewById(R.id.para_criancas);
        ivCachorroFeliz = findViewById(R.id.iv_cachorro_feliz);
        tvCadastroOk = findViewById(R.id.tv_cadastro_ok);
    }

    fun atualizarInformacoes(){
        raca = etRaca.text.toString() ;
        if(etRaca.text.isBlank()){
            preco=0;
        }else {
            preco = etPreco.text.toString().toInt();
        }
        paraCriancas = sParaCriancas.isChecked;
    }
    fun validarCampos():Boolean{
        atualizarInformacoes();
        if(raca.isBlank()) {
            etRaca.error = "A raça não está em branco"
            return false;
        }
        return true;
    }

    fun cadastro(view : View){
        if(validarCampos()) {
            val endpoint: PetService = api.create(PetService::class.java);
            val cachorro: Cachorro = Cachorro(raca, preco, paraCriancas);
            val callback = endpoint.cadastrarPet(cachorro);

            callback.enqueue(object : Callback<Cachorro> {
                override fun onResponse(
                    call: Call<Cachorro>,
                    response: Response<Cachorro>
                ) {
                    when (response.code()) {
                        201 -> {
                            tvCadastroOk.visibility = View.VISIBLE;
                            ivCachorroFeliz.visibility = View.VISIBLE;
                            finish()
                        }
                        else -> {
                            Toast.makeText(
                                baseContext,
                                "Algo deu errado tente novamente!",
                                Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                }

                override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                    Toast.makeText(baseContext, "Algo deu errado!", Toast.LENGTH_SHORT).show();
                }
            })
        }
    }


}