package kpfu.itis.valisheva.app1.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.fragment.app.Fragment
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.databinding.FragmentTrackBinding
import kpfu.itis.valisheva.app1.repositories.TrackRepository
import kpfu.itis.valisheva.app1.services.MusicService


class TrackFragment : Fragment(R.layout.fragment_track){
    private var binding: FragmentTrackBinding? = null
    private var musicService: MusicService? = null

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            musicService = (service as? MusicService.MusicBinder)?.getService()
            if(musicService != null){
                arguments?.getInt("id")?.let { id ->
                    setView(id)
                }
            }

        }

        override fun onServiceDisconnected(className: ComponentName) {
            musicService = null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTrackBinding.bind(view)

    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(
            this.context,
            MusicService::class.java
        )
        activity?.bindService(
            intent,
            connection,
            Context.BIND_AUTO_CREATE
        )
    }


    private fun setView(id: Int) {
        setTrackView(id)
        initMusic(id)
    }

    private fun initMusic(id: Int){

        binding?.apply {

            with(binding){
                this?.btnPlay?.setOnClickListener {
                    musicService?.play()

                }
                this?.btnPause?.setOnClickListener {
                    musicService?.pause()

                }
                this?.btnStop?.setOnClickListener {
                    musicService?.stop()

                }
                this?.btnPrev?.setOnClickListener {
                    musicService?.prev()
                    update(musicService?.getCurrentId()?:0)
                }
                this?.btnNext?.setOnClickListener {
                    musicService?.next()
                    update(musicService?.getCurrentId()?:0)
                }
            }
        }
    }

    private fun update(id:Int){
        setTrackView(id)
        with(binding) {
            this?.btnPlay?.setOnClickListener {
                musicService?.play()
            }
        }
    }

    private fun setTrackView(id: Int){
        val current = TrackRepository.tracks[id]

        with(binding) {
            this?.tvTrackName?.text = current.title
            this?.tvAuthor?.text = current.author
            this?.ivCover?.setImageResource(current.cover)
        }
    }
}


