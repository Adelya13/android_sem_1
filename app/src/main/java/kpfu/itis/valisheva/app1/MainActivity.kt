package kpfu.itis.valisheva.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextClock
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById<ImageButton>(R.id.edit_username)
        var userName : TextView = findViewById(R.id.cat_name)
        var editName = findViewById<EditText>(R.id.cat_name_edit)


        btn.setOnClickListener {
            userName.visibility = View.INVISIBLE
            editName.visibility = View.VISIBLE

        }
        btn.setOnClickListener {
            userName.text = editName.text
            userName.visibility = View.VISIBLE
            editName.visibility = View.INVISIBLE
        }

    }
}

