package com.example.myclock.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myclock.view.AddClockFragment
import com.example.myclock.view.ClickFragment
import com.example.myclock.view.HistoryFragment
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior

class ViewPagerAdapter(private val activity: FragmentActivity) : FragmentStateAdapter(activity) {

  override fun getItemCount(): Int {
    return 3
  }

  override fun createFragment(position: Int): Fragment {
    Log.d("TAG", "createFragment: position = $position")
    return when (position) {
      0 -> ClickFragment.newInstance()
      1 -> HistoryFragment.newInstance()
      else -> AddClockFragment.newInstance()
    }
  }
}
