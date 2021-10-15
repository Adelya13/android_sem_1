package kpfu.itis.valisheva.app1

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kpfu.itis.valisheva.app1.databinding.ActivityMainBinding

private const val REQUEST_CODE_1 = 1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)

        }


        binding.intentBtn1.setOnClickListener{
            val textTo  = arrayOf(binding.editTextTo.text.toString())
            val textSubject = binding.editTextSubject.text.toString()
            val textMessage = binding.editTextMessage.text.toString()

            val sendEmail = Intent(Intent.ACTION_SENDTO ).apply{

                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, textTo)
                putExtra(Intent.EXTRA_SUBJECT, textSubject)
                putExtra(Intent.EXTRA_TEXT, textMessage)

            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(sendEmail, REQUEST_CODE_1)
            }

        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var message = ""

        message = "Send message again!)"

        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }


}





