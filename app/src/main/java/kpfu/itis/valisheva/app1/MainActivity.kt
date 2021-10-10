package kpfu.itis.valisheva.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kpfu.itis.valisheva.app1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }

    }
}