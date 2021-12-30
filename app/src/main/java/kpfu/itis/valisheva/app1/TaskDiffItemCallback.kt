package kpfu.itis.valisheva.app1

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import kpfu.itis.valisheva.app1.db.Task

class TaskDiffItemCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(
        oldItem: Task,
        newItem: Task
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Task,
        newItem: Task
    ): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Task, newItem: Task): Any? {
        val bundle = Bundle()
        if (oldItem.title != newItem.title) {
            bundle.putString("TITTLE", newItem.title)
        }
        if (oldItem.description != newItem.description) {
            bundle.putString("DESCRIPTION", newItem.description)
        }
        if (oldItem.date != newItem.date) {
            bundle.putString("DATE", newItem.date.toString())
        }
        if (bundle.isEmpty) return null
        return bundle
    }
}
