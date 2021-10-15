package kpfu.itis.valisheva.app1

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kpfu.itis.valisheva.app1.databinding.ActivityMainBinding
import kpfu.itis.valisheva.app1.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }


        intent?.also{
            if(it.action == Intent.ACTION_SEND && it.type=="text/plain"){

                binding.textViewMessage.text = intent.getStringExtra(Intent.EXTRA_TEXT)

            }
        }



    }


}



