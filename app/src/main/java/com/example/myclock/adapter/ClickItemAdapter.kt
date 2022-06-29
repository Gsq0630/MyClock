package com.example.myclock.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myclock.room.Clock
import com.example.myclock.R
import com.example.myclock.databinding.ItemClockBinding
import com.example.myclock.ex.clipImage

class ClickItemAdapter(val context: Context,
                       val click: (Clock) -> Unit,
                       val olngClick: (Clock) -> Unit
): RecyclerView.Adapter<ClickItemAdapter.ClockViewHolder>() {

    companion object {
        private const val TAG = "ClickItemAdapter"
    }

    private var dataList: List<Clock>? = null
    private val colors = listOf(
        R.color.color4,
        R.color.color5,
        R.color.color6,
        R.color.color7,
        R.color.color8,
        R.color.color9,
        R.color.color10,
        R.color.color,
        R.color.color1,
        R.color.color2,
        R.color.color3,
    )

    fun setData(list: List<Clock>) {
        dataList = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClockViewHolder {
        return ClockViewHolder(ItemClockBinding.inflate(LayoutInflater.from(context), parent, false)).apply {
            clipImage(binding.ivBg, context.resources.getDimension(R.dimen.dimen_50dp))
        }
    }

    override fun onBindViewHolder(holder: ClockViewHolder, position: Int) {
        val clock = dataList?.get(position) ?: return
        holder.binding.tvTitle.text = clock.name
        holder.binding.tvIcon.text = clock.name.firstOrNull().toString()
        if (clock.state == 1) {
            holder.binding.ivBg.setBackgroundResource(R.drawable.img_1)
            holder.binding.ivBg.alpha = 0.29f
            holder.binding.tvIcon.setTextColor(context.resources.getColor(R.color.color11))
        } else {
            val code = clock.name.hashCode() % 10
            Log.d("TAG", "onBindViewHolder: code = $code name = ${clock.name}")
            val r = (0..10).random()
            holder.binding.ivBg.setBackgroundColor(context.resources.getColor(colors[code]))
            holder.binding.ivBg.alpha = 1f
            holder.binding.tvIcon.setTextColor(context.resources.getColor(R.color.white))
        }
        holder.binding.root.setOnClickListener {
            click.invoke(clock)
        }
        holder.binding.root.setOnLongClickListener {
            olngClick.invoke(clock)
            true
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    class ClockViewHolder(val binding: ItemClockBinding) : RecyclerView.ViewHolder(binding.root) {
    }

}