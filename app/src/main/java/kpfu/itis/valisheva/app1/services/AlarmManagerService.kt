package kpfu.itis.valisheva.app1.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import kpfu.itis.valisheva.app1.notifications.AlarmReceiver
import java.util.*

class AlarmManagerService(
    context: Context
) {
    private var calendar : Calendar? = null

    private val manager by lazy {
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    private val intent by lazy{
        Intent(context, AlarmReceiver::class.java).apply {

            action = "com.alarm_manager"

        }.let {
            PendingIntent.getBroadcast(
                context,
                0,
                it,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }

    fun setAlarm(hours: Int, minutes: Int){
        calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minutes)
        }

        calendar?.let {
            manager.setExact(
                AlarmManager.RTC_WAKEUP,
                it.timeInMillis,
                intent
            )
        }
    }

    fun stopAlarm(){
        manager.cancel(intent)
        calendar = null

    }
    fun getCalendar(): Calendar?{
        return calendar
    }

}

