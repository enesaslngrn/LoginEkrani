package com.enesas.loginekrani

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.enesas.loginekrani.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreferences
    var sistemdeKayitliKullaniciAdi: String? = null
    var sistemdeKayitliSifre: String? = null
    var kullaniciAdi: String? = null
    var sifre: String? = null
    var alinanSifre: String? = null

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val intent = intent

        sharedPreference = this.getSharedPreferences("com.enesas.loginekrani", MODE_PRIVATE)

        alinanSifre = intent.getStringExtra("yeniSifre")

        sistemdeKayitliKullaniciAdi = sharedPreference.getString("username","")
        sistemdeKayitliSifre = sharedPreference.getString("password","")


        if (alinanSifre != null){
            sistemdeKayitliSifre = alinanSifre
            sharedPreference.edit().putString("password",alinanSifre).apply()
        }

        println("Geri main aktiviteye dönen şifre:$alinanSifre")
        println("Kullanıcı adı: $kullaniciAdi")
        println("Şifre: $sifre")
        println("Sistem kullanıcı adı: $sistemdeKayitliKullaniciAdi")
        println("Sistem şifre: $sistemdeKayitliSifre")

    }

    fun girisYap(view: View) {
        kullaniciAdi = binding.editText.text.toString()
        sifre = binding.editText2.text.toString()

        val intent = Intent(applicationContext, Second::class.java)

        if (kullaniciAdi == "" && sifre == ""){
            Toast.makeText(this,"Lütfen kullanıcı adı ve şifre giriniz!",Toast.LENGTH_SHORT).show()
        }else if (kullaniciAdi == "" && sifre != ""){
            Toast.makeText(this,"Lütfen kullanıcı adı giriniz!",Toast.LENGTH_SHORT).show()
        }else if (kullaniciAdi != "" && sifre == ""){
            Toast.makeText(this,"Lütfen şifre giriniz!",Toast.LENGTH_SHORT).show()
        }

        else if(sistemdeKayitliKullaniciAdi == "" && sistemdeKayitliSifre == ""){

            sharedPreference.edit().putString("username",kullaniciAdi).apply()
            sharedPreference.edit().putString("password",sifre).apply()

            sistemdeKayitliKullaniciAdi = kullaniciAdi
            sistemdeKayitliSifre = sifre

            intent.putExtra("sifreTransfer",sistemdeKayitliSifre)

            println("Kullanıcı adı: $kullaniciAdi")
            println("Şifre: $sifre")
            println("Sistem kullanıcı adı: $sistemdeKayitliKullaniciAdi")
            println("Sistem şifre: $sistemdeKayitliSifre")


            startActivity(intent)

            finish()
        }else{
            if(kullaniciAdi == sistemdeKayitliKullaniciAdi && sifre == sistemdeKayitliSifre){

                intent.putExtra("sifreTransfer",sistemdeKayitliSifre)

                println("Kullanıcı adı: $kullaniciAdi")
                println("Şifre: $sifre")
                println("Sistem kullanıcı adı: $sistemdeKayitliKullaniciAdi")
                println("Sistem şifre: $sistemdeKayitliSifre")

                startActivity(intent)

                finish()
            }else if(kullaniciAdi != sistemdeKayitliKullaniciAdi && sifre != sistemdeKayitliSifre){
                Toast.makeText(this,"Kullanıcı adı ya da şifre hatalı!",Toast.LENGTH_SHORT).show()
            }else if(kullaniciAdi != sistemdeKayitliKullaniciAdi){
                Toast.makeText(this,"Hatalı kullanıcı adı!",Toast.LENGTH_SHORT).show()
            }else if(sifre != sistemdeKayitliSifre){
                Toast.makeText(this,"Hatalı şifre!",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun sil(view: View){

        sharedPreference.edit().remove("username").apply()
        sharedPreference.edit().remove("password").apply()

        println("Sistem kullanıcı adı: $sistemdeKayitliKullaniciAdi")
        println("Sistem şifre: $sistemdeKayitliSifre")
        println("Kullanıcı adı: $kullaniciAdi")
        println("Şifre: $sifre")
    }
}