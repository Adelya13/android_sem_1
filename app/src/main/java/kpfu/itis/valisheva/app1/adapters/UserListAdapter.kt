package kpfu.itis.valisheva.app1.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.UserDiffItemCallback
import kpfu.itis.valisheva.app1.holders.UserHolder
import kpfu.itis.valisheva.app1.models.User


class UserListAdapter(
    private val glide: RequestManager,
    private val deleteAction: (User) -> Unit
) : ListAdapter<User, UserHolder>(UserDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(view,glide, deleteAction)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            payloads.last().takeIf { it is Bundle }?.let {
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<User>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}

