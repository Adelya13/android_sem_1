package kpfu.itis.valisheva.app1.holders

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kpfu.itis.valisheva.app1.databinding.ItemUserBinding
import kpfu.itis.valisheva.app1.models.User


class UserHolder(
    item: View,
    private val glide: RequestManager,
    private val deleteAction: (User) -> Unit
) : RecyclerView.ViewHolder(item) {

    private val KEY_USERNAME = "USERNAME"
    private val KEY_STATUS = "STATUS"
    private var user: User? = null
    private var binding: ItemUserBinding? = ItemUserBinding.bind(item)


    fun bind(item: User) {
        this.user = item
        with(binding) {
            user?.let{
                this?.tvUsername?.text = item.username
                this?.tvUserStatus?.text = item.status
                this?.tvUsername?.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        android.R.color.holo_red_dark
                    )
                )
                this?.itemDelete?.setOnClickListener{
                    deleteAction(item)
                }
                glide.load(item.url).into(this?.ivAvatar!!)
                this.ivAvatar

            }
        }
    }

    fun updateFields(bundle: Bundle?) {
        with(binding) {
            if (bundle?.containsKey(KEY_USERNAME) == true) {
                bundle.getString(KEY_USERNAME).also {
                    this?.tvUsername?.text = it
                }
            }
            if (bundle?.containsKey(KEY_STATUS) == true) {
                bundle.getString(KEY_STATUS).also {
                    this?.tvUserStatus?.text = it
                }
            }
        }
    }
}

