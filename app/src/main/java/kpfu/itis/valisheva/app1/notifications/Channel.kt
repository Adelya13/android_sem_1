package kpfu.itis.valisheva.app1.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RawRes
import kpfu.itis.valisheva.app1.R


private val pattern = arrayOf(100L, 200L, 0, 400L).toLongArray()

class Channel(
    context: Context
) {
    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }


    private val audio by lazy {
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
    }


    private fun Context.getSoundUri(
        @RawRes id: Int
    ) = Uri.parse("android.resource://${packageName}/$id")

    fun createChannel(context: Context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                R.string.channel_id_1.toString(),
                context.getString(R.string.notification_channel_title),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.notification_channel_desc)
                lightColor = Color.GREEN
                vibrationPattern = pattern

                val sound: Uri = context.getSoundUri(R.raw.music)
                setSound(sound, audio)

            }.also {
                manager.createNotificationChannel(it)
            }
        }

    }

}

