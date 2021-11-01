package kpfu.itis.valisheva.app1.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.entity.User
import kpfu.itis.valisheva.app1.repositories.UserRepository
import kpfu.itis.valisheva.app1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile)  {

    private lateinit var binding: FragmentProfileBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        setData()



    }
    private fun setData(){
        with(binding){
            var user : User? = null

            arguments?.getInt("id")?.let {
                user = UserRepository.getUserById(it)
            }
            user?.let {
                tvUsername.text = it.username
                tvUserStatus.text = it.status
                tvUserInfo.text = it.info
                Glide.with(ivUserAvatar).load(it.url).into(ivUserAvatar)
            }
        }
    }

}


