package kpfu.itis.valisheva.app1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

import com.google.android.material.snackbar.Snackbar
import kpfu.itis.valisheva.app1.databinding.ActivityMainBinding
import kpfu.itis.valisheva.app1.notifications.Channel
import kpfu.itis.valisheva.app1.services.AlarmManagerService
import java.util.*

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private var service: AlarmManagerService? = null
    private var alarmChannel: Channel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }


        alarmChannel = Channel(this).also {
            it.createChannel(this)

        }
        service = AlarmManagerService(this)


        with(binding){


            btnStart.setOnClickListener {
                val hours = etHours.text.toString()
                val minutes = etMinutes.text.toString()
                start(hours,minutes)

            }
            btnStop.setOnClickListener{
                stop()
            }
        }
    }

    private fun start(hours : String, minutes:String){
        if(hours.isNotEmpty() && minutes.isNotEmpty()) {
            val h = hours.toInt()
            val m = minutes.toInt()
            if (h <= 23 && h >= 0 &&  m <= 59 &&  m >= 0){
                service?.setAlarm(hours.toInt(), minutes.toInt())
                showMessage("Время задано")
            }else {
                showMessage("Введите корректное время")
            }
        }
        else{
            showMessage("Время не задано")
        }

    }

    private fun stop() {
        if (service?.getCalendar() != null){
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle("Удалить часы")
                setMessage("Вы уверены?")
                setPositiveButton("Удалить") { dialog, _ ->
                    service?.stopAlarm()
                    dialog.cancel()
                }
                setNegativeButton("Закрыть") { dialog, _ ->
                    dialog.cancel()
                }
            }.show()
        } else{
           showMessage("Время не задано")
        }
    }

    private fun showMessage(text: String){
        binding.let {
            Snackbar.make(
                it.root,
                text,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service = null
    }

}

