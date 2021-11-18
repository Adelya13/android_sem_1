package kpfu.itis.valisheva.app1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.holders.UserCardHolder
import kpfu.itis.valisheva.app1.models.UserCard


class CardAdapter(
    private val list: List<UserCard>,
) : RecyclerView.Adapter<UserCardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        return UserCardHolder(view)
    }

    override fun onBindViewHolder(holder: UserCardHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}


