package com.lfp2.bulletjournal

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val context: Context, private var taskList: MutableList<Task>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    override fun getItemCount() = taskList.size

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}