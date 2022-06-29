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
import com.example.myclock.adapter.ClickItemAdapter
import com.example.myclock.databinding.FragmentClickBinding
import com.example.myclock.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClickFragment : Fragment() {

    lateinit var binding: FragmentClickBinding

    val adapter: ClickItemAdapter by lazy {
        ClickItemAdapter(requireContext(),::clickItem, ::longClickItem)
    }

    val historyClockDao : HistoryClockDao by lazy {
        DB.database.historyClockDao()
    }

    val clockDao: ClockDao by lazy {
        DB.database.clockDao()
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
            var lastChange = clockDao.getLastChange()
            var data = DB.database.clockDao().getAll()
            if (lastChange?.lateChangeDay != getDayDate()) {
                data.filter {
                    it.state == 0
                }.map { clock ->
                    HistoryClock(
                        clockId = clock.id,
                        state = 0,
                        name = clock.name,
                        habitId = clock.habitId
                    )
                }.forEach {
                    historyClockDao.insertAll(it)
                }
                data.forEach {
                    it.state = 0
                    clockDao.insertAll(it)
                }
                data = DB.database.clockDao().getAll()
                lastChange = lastChange ?: ExtendInfo()
                lastChange.lateChangeDay = getDayDate()
                clockDao.insertExt(lastChange)
            }
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
            val history = historyClockDao.selectHistory(clock.id, getDayDate()) ?: HistoryClock(
                clockId = clock.id,
                state = 1,
                name = clock.name,
                habitId = clock.habitId,
                timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24
            )
            Log.d(TAG, "clickItem: history = $history")
            DB.database.historyClockDao().insertAll(history)
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
                    historyClockDao.deleteClockById(clock.id)
                    updateData()
                }
            }
            setNegativeButton("重置"){ _, _ ->
                Log.d(TAG, "longClickItem: 重置")
                lifecycleScope.launch(Dispatchers.IO) {
                    clock.state = 0
                    DB.database.clockDao().insertAll(clock)
                    DB.database.historyClockDao().deleteByClockId(clock.id, getDayDate())
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