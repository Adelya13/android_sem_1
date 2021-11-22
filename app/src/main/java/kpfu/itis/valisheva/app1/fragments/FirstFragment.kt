package kpfu.itis.valisheva.app1.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.models.User
import kpfu.itis.valisheva.app1.adapters.UserListAdapter
import kpfu.itis.valisheva.app1.repositories.UserRepository
import kpfu.itis.valisheva.app1.databinding.FragmentFirstBinding
import kpfu.itis.valisheva.app1.decorators.SpaceItemDecorator


class FirstFragment : Fragment(R.layout.fragment_first) {

    private var binding: FragmentFirstBinding? = null
    private var userListAdapter: UserListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFirstBinding.bind(view)
        userListAdapter = UserListAdapter(Glide.with(this),{ deleteUser(it) })
        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding){
            this?.rvUsers?.run {
                adapter = userListAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
            this?.btnRefresh?.setOnClickListener{
                AddUserDialog.show(childFragmentManager, {addUser(it)})
            }
        }
        userListAdapter?.submitList(UserRepository.users)
    }

    private fun addUser(user: User){

        UserRepository.addUser(user.id,user)
        userListAdapter?.submitList(UserRepository.users)
    }
    private fun deleteUser(user: User){
        UserRepository.deleteUser(user)
        userListAdapter?.submitList(UserRepository.users)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

