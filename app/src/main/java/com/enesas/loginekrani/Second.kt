package com.enesas.loginekrani

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.enesas.loginekrani.databinding.ActivitySecondBinding

class Second : AppCompatActivity() {

    var classMainActivity = MainActivity()
    var gelenSifre: String? = null
    var yeniSifre: String? = null

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)


        val intent = intent

        gelenSifre = intent.getStringExtra("sifreTransfer")
        classMainActivity.sistemdeKayitliSifre = gelenSifre

        println("İkinci aktiviteye giden şifre: ${gelenSifre}")

        Toast.makeText(this,"Hoşgeldiniz!",Toast.LENGTH_LONG).show()
    }

    fun sifreDegistir(view:View) {
        yeniSifre = binding.editText4.text.toString()
        if (classMainActivity.sistemdeKayitliSifre == binding.editText3.text.toString()){

            if (classMainActivity.sistemdeKayitliSifre == binding.editText4.text.toString()){
                Toast.makeText(this,"Eski şifreniz ile yeni şifreniz aynı olamaz!.",Toast.LENGTH_LONG).show()
            }else{
                classMainActivity.sistemdeKayitliSifre = yeniSifre

                val intent = Intent(applicationContext, MainActivity::class.java)

                intent.putExtra("yeniSifre",classMainActivity.sistemdeKayitliSifre)

                Toast.makeText(this,"Giriş sayfasına yönlendiriliyorsunuz...",Toast.LENGTH_LONG).show()


                startActivity(intent)
                finish()
            }


        }else if(classMainActivity.sistemdeKayitliSifre != binding.editText3.text.toString()){
            Toast.makeText(this,"Eski şifreniz hatalı.",Toast.LENGTH_LONG).show()
        }
    }
}