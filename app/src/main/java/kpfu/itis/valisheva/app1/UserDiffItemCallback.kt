package kpfu.itis.valisheva.app1

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import kpfu.itis.valisheva.app1.models.User

class UserDiffItemCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean = oldItem.status == newItem.status

    override fun areContentsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: User, newItem: User): Any? {
        val bundle = Bundle()
        if (oldItem.username != newItem.username) {
            bundle.putString("USERNAME", newItem.username)
        }
        if (oldItem.status != newItem.status) {
            bundle.putString("STATUS", newItem.status)
        }
        if (oldItem.url != newItem.url) {
            bundle.putString("URL", newItem.url)
        }
        if (bundle.isEmpty) return null
        return bundle
    }
}

