package com.example.myclock.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myclock.R
import com.example.myclock.databinding.ItemClickHistoryBinding
import com.example.myclock.room.HistoryClock

class HistoryAdapter (val context: Context,
): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

  private var dataList: List<HistoryClock>? = null

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


  fun setData(list: List<HistoryClock>) {
    dataList = list
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
    return HistoryViewHolder(ItemClickHistoryBinding.inflate(LayoutInflater.from(context), parent, false))
  }

  override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
    dataList ?: return
    val data = dataList!![position]
    val index = data.date.last() - '0'
    Log.d("TAG", "onBindViewHolder: position = $position index = $index")
    holder.binding.tvName.text = data.name
    holder.binding.tvState.text = if (data.state == 1) "√" else "×"
    holder.binding.tvTime.text = data.date
    val color = context.resources.getColor(colors[index])
    holder.binding.tvName.setTextColor(color)
    holder.binding.tvState.setTextColor(color)
    holder.binding.tvTime.setTextColor(color)
    holder.binding.topLine.visibility = View.GONE
    if (position > 0) {
      val lastData = dataList!![position - 1]
      if (lastData.date.last() != data.date.last()) {
        holder.binding.topLine.visibility = View.VISIBLE
      }
    }
  }

  override fun getItemCount(): Int {
    return dataList?.size ?: 0
  }

  class HistoryViewHolder(val binding: ItemClickHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
  }

}