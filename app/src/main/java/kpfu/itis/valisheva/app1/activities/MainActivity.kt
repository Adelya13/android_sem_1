package kpfu.itis.valisheva.app1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController

import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.databinding.ActivityMainBinding
import kpfu.itis.valisheva.app1.findController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }
        controller = findController(R.id.container)
        val bottomNavView = binding?.bottomNavigationView
        bottomNavView?.setupWithNavController(controller)

    }

}

