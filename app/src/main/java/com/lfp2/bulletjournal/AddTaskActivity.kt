package com.lfp2.bulletjournal

import android.app.TimePickerDialog
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Exclude
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.synthetic.main.activity_add_task.*
import java.text.SimpleDateFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private var user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        timeSelected.visibility = View.INVISIBLE

        database = FirebaseDatabase.getInstance().reference

        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                createCalendar(timeSelected)
                timeSelected.visibility = View.VISIBLE
            }
        }

        timeSelected.setOnClickListener {
            createCalendar(timeSelected)
        }

        taskTimeDesired.setOnClickListener {
            createCalendar(taskTimeDesired)
        }

        addTaskButton.setOnClickListener(){
            writeNewTask(user!!.uid)
        }
    }

    private fun writeNewTask(userUid: String){
        val key = database.child("user-tasks").child(userUid).push().key
        val nameTask = textInputEditText.text.toString()
        val task = Task(
                    nameTask,
                    switch1.isChecked,
                    timeSelected.text.toString(),
                    taskTimeDesired.text.toString(),
                false,
                    key)
        database.child("user-tasks").child(userUid).child(key!!).setValue(task)
            .addOnSuccessListener {
                Toast.makeText(this, "Added task $nameTask", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error while adding this task", Toast.LENGTH_LONG).show()
            }

    }

    private fun createCalendar(editView: TextView){
        val cal = Calendar.getInstance()
        val timeSetListener =
            TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                editView.text = SimpleDateFormat("HH:mm").format(cal.time)
                editView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)
            }
        TimePickerDialog(
            this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        ).show()
    }
}