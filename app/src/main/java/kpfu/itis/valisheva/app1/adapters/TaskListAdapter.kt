package kpfu.itis.valisheva.app1.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.TaskDiffItemCallback
import kpfu.itis.valisheva.app1.holders.TaskHolder
import kpfu.itis.valisheva.app1.db.Task

class TaskListAdapter(
    private val chooseAction: (Int) -> Unit,
    private val deleteAction: (Int) -> Unit
) : ListAdapter<Task,TaskHolder>(TaskDiffItemCallback()){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskHolder {
        return TaskHolder.create(parent,chooseAction,deleteAction)

    }

    override fun onBindViewHolder(
        holder: TaskHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            payloads.last().takeIf {
                it is Bundle
            }?.let {
                holder.updateFields(
                    it as Bundle
                )
            }
        }


    }
    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<Task>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}
