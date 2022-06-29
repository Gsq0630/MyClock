package com.example.myclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myclock.adapter.ViewPagerAdapter
import com.example.myclock.databinding.ActivityMainBinding
import com.example.myclock.room.DB

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DB.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() {
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
    }

}