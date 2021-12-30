package kpfu.itis.valisheva.app1.holders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kpfu.itis.valisheva.app1.AppConstants.KEY_DATE
import kpfu.itis.valisheva.app1.AppConstants.KEY_DESCRIPTION
import kpfu.itis.valisheva.app1.AppConstants.KEY_TITLE
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.databinding.ItemTaskBinding
import kpfu.itis.valisheva.app1.db.Task
import java.text.SimpleDateFormat
import java.util.*

class TaskHolder(
    private val binding: ItemTaskBinding,
    private val chooseAction: (Int) -> Unit,
    private val deleteAction: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private var task: Task? = null

    fun bind(item: Task){
        this.task = item
        with(binding){
            task.let {
                this?.tvTittle?.text = item.title
                this?.tvDescription?.text = item.description
                this?.tvDate?.text = SimpleDateFormat("dd MMM", Locale.ENGLISH).format(item.date.time)

                this?.remove?.setOnClickListener{
                    deleteAction(item.id)
                }
                itemView.setOnClickListener {
                    chooseAction(item.id)
                }

            }
        }
    }

    fun updateFields(bundle: Bundle?) {
        with(binding) {
            if (bundle?.containsKey(KEY_TITLE) == true) {
                bundle.getString(KEY_TITLE).also {
                    this.tvTittle.text = it
                }
            }
            if (bundle?.containsKey(KEY_DESCRIPTION) == true) {
                bundle.getString(KEY_DESCRIPTION).also {
                    this.tvDescription.text = it
                }
            }
            if (bundle?.containsKey(KEY_DATE) == true) {
                bundle.getString(KEY_DATE).also {
                    this.tvDate.text = SimpleDateFormat("dd MMM", Locale.ENGLISH).format(it)
                }
            }
        }
    }

    companion object{

        fun create(
            parent: ViewGroup,
            chooseAction: (Int) -> Unit,
            deleteAction: (Int) -> Unit
        ) = TaskHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), chooseAction, deleteAction
        )
    }

}
