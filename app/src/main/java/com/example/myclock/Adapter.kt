package com.example.myclock

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myclock.databinding.ItemClockBinding
import java.util.*

class Adapter(
    val context: Context,
    val click: (Clock) -> Unit
): RecyclerView.Adapter<Adapter.ClockViewHolder>() {

    private var dataList: List<Clock>? = null
    private val colors = listOf(
        R.color.color,
        R.color.color1,
        R.color.color2,
        R.color.color3,
        R.color.color4,
        R.color.color5,
        R.color.color6,
        R.color.color7,
        R.color.color8,
        R.color.color9,
        R.color.color10,
    )

    fun setData(list: List<Clock>) {
        dataList = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClockViewHolder {
        return ClockViewHolder(ItemClockBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ClockViewHolder, position: Int) {
        val clock = dataList?.get(position) ?: return
        holder.binding.tvTitle.text = clock.name
        holder.binding.tvIcon.text = clock.name.firstOrNull().toString()
        val r = (0..10).random()
        holder.binding.ivBg.setBackgroundColor(context.resources.getColor(colors[r]))
        holder.binding.root.setOnClickListener {
            click.invoke(clock)
        }
    }

    override fun getItemCount(): Int {
       return dataList?.size ?: 0
    }

    class ClockViewHolder(val binding: ItemClockBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}