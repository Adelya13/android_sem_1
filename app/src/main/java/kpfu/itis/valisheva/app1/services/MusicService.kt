package kpfu.itis.valisheva.app1.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import kpfu.itis.valisheva.app1.models.Track
import kpfu.itis.valisheva.app1.repositories.TrackRepository

class MusicService : Service() {

    private var currentId: Int? = null
    private lateinit var trackList : ArrayList<Track>
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var musicBinder: MusicBinder
    private lateinit var notificationService: NotificationService



    override fun onBind(intent: Intent?): IBinder = this.musicBinder

    override fun onCreate() {
        super.onCreate()
        currentId = 0
        trackList = TrackRepository.tracks
        mediaPlayer = MediaPlayer()
        musicBinder = MusicBinder()
        notificationService = NotificationService(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            PREV -> {
                prev()
                currentId?.let {
                    notificationService.buildNotification(it)
                }

            }
            NEXT -> {
                next()
                currentId?.let {
                    notificationService.buildNotification(it)
                }
            }
            STOP -> {
                stop()
                currentId?.let {
                    notificationService.rebuildNotification(it)
                }
            }

            PAUSE -> {
                if (mediaPlayer.isPlaying) pause()
                currentId?.let {
                    notificationService.rebuildNotification(it)
                }
            }
            PLAY ->{
                if (!mediaPlayer.isPlaying) play()
                currentId?.let {
                    notificationService.buildNotification(it)
                }
            }

        }

        return super.onStartCommand(intent, flags, startId)
    }



    fun prev(){
        currentId?.let {
            currentId = if (it == 0) {
                trackList.size - 1
            } else {
                it - 1
            }
            setTrack(currentId ?: 0)
            play()
        }
    }
    fun next(){
        currentId?.let{
            currentId = if (it + 1 < trackList.size) it + 1
            else 0

        }
        setTrack(currentId ?: 0)
        play()

    }
    fun stop(){
        mediaPlayer.stop()
        setTrack(currentId ?: 0)
    }
    fun pause(){
        mediaPlayer.pause()
    }
    fun play() {
        mediaPlayer.start()
    }

    fun setTrack(id: Int) {
        with(mediaPlayer) {
            if (isPlaying) stop()
        }
        mediaPlayer = MediaPlayer.create(
            applicationContext,
            trackList[id].trackSrc)

        currentId = id
        currentId?.let {
            notificationService.buildNotification(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }


    fun getCurrentId(): Int? {
        return this.currentId
    }
    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }
}

