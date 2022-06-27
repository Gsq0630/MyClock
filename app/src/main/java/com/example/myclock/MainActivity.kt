package com.example.myclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myclock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DB.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.rvList.layoutManager = GridLayoutManager(this, 2)
        val adapter = Adapter(
            this
        ) {
            Log.d(TAG, "onCreate: it name = ${it.name} ")
        }
        binding.rvList.adapter = adapter
        adapter.setData(
            listOf(
                Clock(
                    1,
                  "名字",
                    "content",
                "shouldDate",
                1,
                    1,
                    "timestamp",
                    1
                ),
                Clock(
                    2,
                    "名字",
                    "content",
                    "shouldDate",
                    1,
                    1,
                    "timestamp",
                    1
                ),
                Clock(
                    3,
                    "名字",
                    "content",
                    "shouldDate",
                    1,
                    1,
                    "timestamp",
                    1
                ),
                Clock(
                    4,
                    "名字",
                    "content",
                    "shouldDate",
                    1,
                    1,
                    "timestamp",
                    1
                ),
                Clock(
                    5,
                    "名字",
                    "content",
                    "shouldDate",
                    1,
                    1,
                    "timestamp",
                    1
                ),
                Clock(
                    6,
                    "名字",
                    "content",
                    "shouldDate",
                    1,
                    1,
                    "timestamp",
                    1
                ),

            )


        )



        setContentView(binding.root)



    }
}