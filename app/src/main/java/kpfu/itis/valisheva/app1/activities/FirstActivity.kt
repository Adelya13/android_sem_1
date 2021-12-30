package kpfu.itis.valisheva.app1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import kpfu.itis.valisheva.app1.R

import kpfu.itis.valisheva.app1.databinding.ActivityFirstBinding
import kpfu.itis.valisheva.app1.findController

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }
        controller = findController(R.id.container)
    }
}
