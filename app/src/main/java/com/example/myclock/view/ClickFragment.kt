package com.example.myclock.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myclock.Clock
import com.example.myclock.DB
import com.example.myclock.adapter.ClickItemAdapter
import com.example.myclock.databinding.FragmentClickBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClickFragment : Fragment() {

    lateinit var binding: FragmentClickBinding

    val adapter: ClickItemAdapter by lazy {
        ClickItemAdapter(requireContext(),::clickItem, ::longClickItem)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentClickBinding.inflate(inflater, container, false)
        initView()
        updateData()
        return binding.root
    }

    private fun initView() {
        binding.rvList.layoutManager = GridLayoutManager(requireContext(),3)
        binding.rvList.adapter = adapter
    }

    private fun updateData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val data = DB.database.clockDao().getAll()
            lifecycleScope.launch(Dispatchers.Main) {
                adapter.setData(data)
            }
        }
    }

    private fun clickItem(clock: Clock) {
        Log.d(TAG, "clickItem: clock = $clock")
        clock.state = 1
        lifecycleScope.launch(Dispatchers.IO) {
            DB.database.clockDao().insertAll(clock)
            updateData()
        }
    }

    private fun longClickItem(clock: Clock) {
        Log.d(TAG, "longClickItem: clock = $clock")
        val builder = AlertDialog.Builder(requireContext()).apply {
            setTitle("删除")
            setMessage("是否删除或者重置这个打卡")
            setNeutralButton("取消",) { _, _ ->
                Log.d(TAG, "longClickItem: 取消")
            }
            setPositiveButton("删除") { _, _ ->
                Log.d(TAG, "longClickItem: 删除")
                lifecycleScope.launch(Dispatchers.IO) {
                    DB.database.clockDao().delete(clock)
                    updateData()
                }
            }
            setNegativeButton("重置"){ _, _ ->
                Log.d(TAG, "longClickItem: 重置")
                lifecycleScope.launch(Dispatchers.IO) {
                    clock.state = 0
                    DB.database.clockDao().insertAll(clock)
                    updateData()
                }
            }
        }
        builder.create().show()
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }


    companion object {
        private const val TAG = "ClickFragment"

        @JvmStatic
        fun newInstance() = ClickFragment()
    }
}