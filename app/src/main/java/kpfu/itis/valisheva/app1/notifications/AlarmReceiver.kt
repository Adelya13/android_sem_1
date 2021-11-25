package kpfu.itis.valisheva.app1.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.activities.SecondActivity


class AlarmReceiver : BroadcastReceiver() {


    private var manager : NotificationManager? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.alarm_manager") {
            manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val intent = getIntent(context)
            build(
                context,
                intent
            )
            build(context,intent)

        }
    }

    private fun getIntent(context: Context) : PendingIntent? {
        return Intent(context, SecondActivity::class.java).let {
            PendingIntent.getActivities(
                context,
                1,
                arrayOf(it),
                PendingIntent.FLAG_ONE_SHOT
            )
        }

    }

    private fun build(context: Context?, intent: PendingIntent?){
        val notification = context?.let {
            NotificationCompat.Builder(it, R.string.channel_id_1.toString())
                .setSmallIcon(R.drawable.ic_clock)
                .setContentTitle("Вставай")
                .setContentText("Время вышло!")
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(intent)
                .setSound(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.music))
                .setVibrate(longArrayOf(100L, 200L, 0, 400L))
        }
        if (context != null) {
            notification?.let {
                    not -> NotificationManagerCompat.from(context).notify(1, not.build())
            }
        }
    }
}

