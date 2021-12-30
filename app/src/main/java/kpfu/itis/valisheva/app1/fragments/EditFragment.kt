package kpfu.itis.valisheva.app1.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kpfu.itis.valisheva.app1.AppConstants.KEY_TASK_ID
import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.activities.FirstActivity
import kpfu.itis.valisheva.app1.databinding.FragmentEditBinding
import kpfu.itis.valisheva.app1.db.AppDataBase
import kpfu.itis.valisheva.app1.db.Task
import java.text.SimpleDateFormat
import java.util.*


private const val PERMISSION_KEY = 1

class EditFragment : Fragment(R.layout.fragment_edit) {

    private var binding: FragmentEditBinding? = null
    private lateinit var database: AppDataBase
    private lateinit var client: FusedLocationProviderClient
    private var task : Task? = null
    private var chosenDate: Date? = null
    private var currTaskId : Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditBinding.bind(view)
        client = LocationServices.getFusedLocationProviderClient(this.requireActivity())
        database = AppDataBase.invoke(context) as AppDataBase

        if (arguments?.containsKey(KEY_TASK_ID) == true) {

            arguments?.getInt(KEY_TASK_ID)?.let {
                currTaskId = it
                task = database.taskDao().getTaskById(it)
            }
            getTaskElements()
        }

        if(checkPermission() == true){
            getCurrentLocation()
        } else {
            requestPermissions(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), PERMISSION_KEY)
        }

        with(binding){
            this?.btnAddData?.setOnClickListener {
                createDatePicker()
            }
            this?.btnSendData?.setOnClickListener{
                saveTask()
                backToTaskList()
            }
        }
    }
    private fun createDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this.requireContext(),
            DatePickerDialog.OnDateSetListener() {_ , year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                calendar.add(Calendar.MONTH, 1)
                chosenDate = calendar.time
                binding?.tvDate?.text = (transformToDateFormat(calendar.time))
            }, year, month, day)
        datePicker.show()
    }

    private fun checkPermission(): Boolean? {
        activity?.apply {
            return (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        }
        return null
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSION_KEY && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getCurrentLocation()
        }else{
            (activity as? FirstActivity)?.onBackPressed()
        }
    }
    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) or
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            client.lastLocation.addOnCompleteListener{
                val location = it.result
                if(location != null) {
                    binding?.etLongitude?.setText(location.longitude.toString())
                    binding?.etLatitude?.setText(location.latitude.toString())
                }
            }
        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
    private fun backToTaskList(){
        findNavController().navigate(
            R.id.action_editFragment_to_taskListFragment
        )
    }
    private fun saveTask(){
        var newTask: Task? = null
        with(binding){
            val title = this?.etTittle?.text.toString()
            if(title.isNotEmpty()){
                val description: String = this?.etDescription?.text?.toString() ?: ""
                val date = chosenDate ?: Date()
                val id = currTaskId?:0
                val latitude : String = this?.etLongitude?.text?.toString() ?: "0.0"
                val longitude : String = this?.etLatitude?.text?.toString() ?: "0.0"
                val newLatitude: Double = latitude.toDouble()
                val newLongitude : Double = longitude.toDouble()
                println(id.toString() + title + description + date + longitude+ latitude)
                newTask = Task(id,title,description,date,newLongitude,newLatitude)
                newTask?.let{
                    saveTask(it)
                }?: showMessage("Something gone wrong")
            }else {
                showMessage("Title shouldn't be empty")
            }
        }
    }
    private fun getTaskElements(){
        task?.let { it->
            with(binding){
                this?.tvDate?.text = transformToDateFormat(it.date)
                this?.etTittle?.setText(it.title)
                this?.etDescription?.setText(it.description)
                this?.etLatitude?.setText(it.latitude.toString())
                this?.etLongitude?.setText(it.longitude.toString())
            }
        }
    }
    private fun saveTask(task: Task){
        database.taskDao().save(task)
    }
    private fun transformToDateFormat(date: Date?): String {
        return if (date != null) {
            val dateFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)
            dateFormat.format(date)
        } else {
            "None"
        }
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
