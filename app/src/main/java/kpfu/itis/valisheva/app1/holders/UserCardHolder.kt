package kpfu.itis.valisheva.app1.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kpfu.itis.valisheva.app1.adapters.PhotoAdapter
import kpfu.itis.valisheva.app1.databinding.ItemCardviewBinding
import kpfu.itis.valisheva.app1.models.UserCard

class UserCardHolder (
    item: View
) : RecyclerView.ViewHolder(item) {

    private var userCard: UserCard? = null
    private val binding = ItemCardviewBinding.bind(item)

    fun bind(item: UserCard) {
        this.userCard = item
        with(binding) {
            cardTitle.text = item.username
            cardDescription.text = item.status
            userViewpager.adapter = PhotoAdapter(Glide.with(itemView),item.photos)
        }
    }
}

