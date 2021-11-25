package kpfu.itis.valisheva.app1.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kpfu.itis.valisheva.app1.databinding.ActivitySecondBinding

class SecondActivity :AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }
    }
}

