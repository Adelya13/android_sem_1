package kpfu.itis.valisheva.app1.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kpfu.itis.valisheva.app1.AppConstants.KEY_TASK_ID
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.adapters.TaskListAdapter
import kpfu.itis.valisheva.app1.databinding.FragmentTaskListBinding
import kpfu.itis.valisheva.app1.db.AppDataBase
import kpfu.itis.valisheva.app1.decorators.SpaceItemDecorator
import kpfu.itis.valisheva.app1.db.Task

class TaskListFragment : Fragment(R.layout.fragment_task_list) {
    private var binding: FragmentTaskListBinding? = null
    private var taskListAdapter: TaskListAdapter? = null
    private lateinit var database: AppDataBase
    private lateinit var tasks: List<Task>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaskListBinding.bind(view)
        database = AppDataBase.invoke(context) as AppDataBase

        taskListAdapter = TaskListAdapter(
            {updateTask(it)},
            {deleteTask(it)}
        )
        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())
        with(binding){
            this?.toolbar?.setOnMenuItemClickListener {
                onOptionsItemSelected(it)
            }
            this?.rvTasks?.run {
                adapter = taskListAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
            this?.btnAddTask?.setOnClickListener{
                updateTask(null)
            }
        }
        updateTaskList()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId ==  R.id.action_remove_all) {
            removeAllTasks()
            return true
        }else super.onOptionsItemSelected(item)

        return true
    }
    private fun removeAllTasks(){
        if(binding?.rvTasks?.visibility == View.VISIBLE) {
            database.taskDao().deleteAllTasks()
            updateTaskList()
            showMessage("All tasks deleted successfully")
        } else showMessage("You haven't tasks yet")
    }
    private fun updateTask(taskId: Int?){
        var bundle: Bundle? = null
        taskId?.let {
            bundle = Bundle().apply {
                putInt(KEY_TASK_ID, taskId)
            }
        }
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .build()

        findNavController().navigate(
            R.id.action_taskListFragment_to_editFragment,
            bundle,
            options
        )

    }
    private fun updateTaskList(){
        tasks = database.taskDao().findAllTasks()
        binding?.apply {
            if (tasks.isEmpty()) {
               tvNoTask.visibility = View.VISIBLE
                rvTasks.visibility = View.GONE
            } else {
                tvNoTask.visibility = View.GONE
                rvTasks.visibility = View.VISIBLE
            }
        }
        taskListAdapter?.submitList(ArrayList(tasks))
    }
    private fun deleteTask(taskId: Int){
        database.taskDao().deleteTaskById(taskId)
        updateTaskList()
        showMessage("Task deleted successfully")
    }
    private fun showMessage(message: String){
        Snackbar.make(
            requireActivity().findViewById(R.id.container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
