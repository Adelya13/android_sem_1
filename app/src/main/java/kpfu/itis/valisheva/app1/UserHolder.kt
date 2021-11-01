package kpfu.itis.valisheva.app1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kpfu.itis.valisheva.app1.databinding.ItemUserBinding
import kpfu.itis.valisheva.app1.entity.User

class UserHolder(
    private val binding: ItemUserBinding,
    private val glide: RequestManager,
    private val action: (Int) -> Unit,

) : RecyclerView.ViewHolder(binding.root){
    fun bind(item : User){
        with(binding){
            tvUsername.text = item.username
            tvUserStatus.text = item.status
            glide.load(item.url).into(ivAvatar)
            ivAvatar
        }
        itemView.setOnClickListener {
            action(item.id)
        }

    }
    companion object{

        fun create(
            parent: ViewGroup,
            glide : RequestManager,
            action: (Int) -> Unit
        ) = UserHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide, action
        )

    }
}


