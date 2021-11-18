package kpfu.itis.valisheva.app1.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.adapters.CardAdapter
import kpfu.itis.valisheva.app1.databinding.FragmentSecondBinding
import kpfu.itis.valisheva.app1.decorators.SpaceItemDecorator
import kpfu.itis.valisheva.app1.repositories.UserCardRepository

class SecondFragment : Fragment(R.layout.fragment_second) {
    private var binding: FragmentSecondBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSecondBinding.bind(view)
        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding){
            this?.rvUsersImg?.run {
                adapter = CardAdapter(UserCardRepository.cardsList)
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

