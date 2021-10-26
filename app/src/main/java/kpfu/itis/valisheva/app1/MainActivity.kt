package kpfu.itis.valisheva.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController

import androidx.navigation.fragment.NavHostFragment
import kpfu.itis.valisheva.app1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }
        controller = findController(R.id.container)

    }
}