package kpfu.itis.valisheva.app1.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.databinding.FragmentListOfTracksBinding
import kpfu.itis.valisheva.app1.decorators.SpaceDecorator
import kpfu.itis.valisheva.app1.repositories.TrackRepository
import kpfu.itis.valisheva.app1.rv.TrackAdapter

class ListOfTracksFragment : Fragment(R.layout.fragment_list_of_tracks) {
    private var binding: FragmentListOfTracksBinding? = null
    private var trackAdapter: TrackAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListOfTracksBinding.bind(view)
        trackAdapter = TrackAdapter(TrackRepository.tracks){
            showTrackFragment(it)
        }

        with(binding){
            this?.rvTracks?.run {
                adapter = trackAdapter
                addItemDecoration(
                    DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
                )
                addItemDecoration(
                    SpaceDecorator(requireContext())
                )
            }

        }
    }

    private fun showTrackFragment(id: Int) {
        val bundle = Bundle().apply {
            putInt("id", id)
        }
        val options = NavOptions.Builder()
            .setLaunchSingleTop(false)
            .setEnterAnim(R.anim.enter_from_right)
            .setExitAnim(R.anim.exit_to_left)
            .setPopEnterAnim(R.anim.enter_from_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .build()
        findNavController().navigate(
            R.id.action_listOfTracksFragment_to_trackFragment,
            bundle,
            options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

