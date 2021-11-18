package kpfu.itis.valisheva.app1.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kpfu.itis.valisheva.app1.databinding.DialogUpgrateItemBinding
import kpfu.itis.valisheva.app1.models.User


class AddUserDialog : DialogFragment() {

    private lateinit var binding: DialogUpgrateItemBinding

    var positiveCallback: ((User) -> Unit)? = null
    var negativeCallback: ((Boolean) -> Unit)? = null

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog{
        return parentFragment?.let {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Add user")
                .setView(DialogUpgrateItemBinding.inflate(layoutInflater).let {
                    binding = it
                    it.root
                })
                .setMessage("Write username, status and position")
                .setCancelable(true)
                .setNegativeButton("Close") {
                        dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("Edit") {
                        _, _ ->
                    addUser()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    private fun addUser(){
        with(binding){
            val username = this?.etUsername?.text.toString()
            val status = this?.etStatus?.text.toString()
            val position = this?.etPosition?.text.toString()
            if (username.isNotEmpty()){
                val user = User(position.toInt(),username,status,"https://i.pinimg.com/474x/f4/72/06/f47206e14861019d9d1429f8b5a4f72a.jpg")
                positiveCallback?.invoke(user)
            }
            else{
                negativeCallback?.invoke(false)
            }
        }
    }
    companion object {
        fun show(
            fragmentManager: FragmentManager,
            positive: (User) -> Unit,
            negative: ((Boolean) -> Unit)? = null
        ) {
            AddUserDialog().apply {
                positiveCallback = positive
                negativeCallback = negative
                show(fragmentManager, AddUserDialog::class.java.name)
            }
        }
    }
}

