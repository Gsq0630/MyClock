package com.example.myclock.view

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myclock.R
import com.example.myclock.adapter.ClickItemAdapter
import com.example.myclock.adapter.HistoryAdapter
import com.example.myclock.databinding.FragmentClickBinding
import com.example.myclock.databinding.FragmentHistoryBinding
import com.example.myclock.room.DB
import com.example.myclock.room.HistoryClockDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

  lateinit var binding : FragmentHistoryBinding


  val adapter: HistoryAdapter by lazy {
    HistoryAdapter(requireContext())
  }

  val historyClockDao : HistoryClockDao by lazy {
    DB.database.historyClockDao()
  }


  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View { // Inflate the layout for this fragment
    binding = FragmentHistoryBinding.inflate(inflater, container, false)
    initView()
    updateData()
    return binding.root
  }

  private fun initView() {
    binding.rvList.layoutManager = LinearLayoutManager(requireContext())
    binding.rvList.adapter = adapter
  }

  private fun updateData() {
    lifecycleScope.launch(Dispatchers.IO) {
      val data = historyClockDao.getAll()
      Log.d(TAG, "updateData: data = $data")
      lifecycleScope.launch(Dispatchers.Main) {
        adapter.setData(data)
      }
    }
  }

  override fun onResume() {
    super.onResume()
    updateData()
  }

  companion object {
    private const val TAG = "HistoryFragment"

    @JvmStatic
    fun newInstance() = HistoryFragment()
  }
}