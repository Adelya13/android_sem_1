package kpfu.itis.valisheva.app1.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.UserAdapter
import kpfu.itis.valisheva.app1.repositories.UserRepository
import kpfu.itis.valisheva.app1.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private var userAdapter : UserAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        val itemDecoration: RecyclerView.ItemDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        with(binding){
            rvUsers.addItemDecoration(itemDecoration)
        }



        userAdapter = UserAdapter(UserRepository.users,Glide.with(this)) {
            showUser(it)
        }

        view.findViewById<RecyclerView>(R.id.rv_users).run{
            adapter = userAdapter
        }

    }

    private fun showUser(id: Int) {
        val bundle = Bundle().apply {
            putInt("id", id)
        }
        findNavController().navigate(R.id.action_mainFragment_to_profileFragment, bundle)
    }
}


