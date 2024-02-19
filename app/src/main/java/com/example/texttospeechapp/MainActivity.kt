package com.example.texttospeechapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.texttospeechapp.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var binding: ActivityMainBinding
    private  var textToSpeech: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSpeak!!.isEnabled = false

        textToSpeech = TextToSpeech(this,this)

        binding.btnSpeak.setOnClickListener{
            speakOut()
        }

    }

    private fun speakOut() {
        val text = binding.etInput.text.toString()
        textToSpeech!!.speak(text,TextToSpeech.QUEUE_FLUSH,null)
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = textToSpeech!!.setLanguage(Locale.UK)

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this,"Language not supported",Toast.LENGTH_SHORT).show()
            }else{
                binding.btnSpeak.isEnabled = true
            }
        }
    }

    override fun onDestroy() {

        if(textToSpeech != null){
            textToSpeech!!.stop()
        }
        super.onDestroy()

    }
}