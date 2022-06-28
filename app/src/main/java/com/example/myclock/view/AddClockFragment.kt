package com.example.myclock.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.myclock.Clock
import com.example.myclock.DB
import com.example.myclock.R
import com.example.myclock.databinding.FragmentAddClockBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddClockFragment : Fragment() {
  lateinit var binding : FragmentAddClockBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    binding = FragmentAddClockBinding.inflate(inflater, container, false)
    initListener()
    return binding.root
  }

  private fun initListener() {
    binding.btnAdd.setOnClickListener {
      val text = binding.etName.text
      Log.d(TAG, "initListener:text = $text ")
      if (text == null) return@setOnClickListener
      lifecycleScope.launch(Dispatchers.IO) {
        DB.database.clockDao().insertAll(
          Clock(name = text.toString())
        )
      }
      binding.etName.setText("")
      Toast.makeText(requireContext(), "${text}添加成功", Toast.LENGTH_SHORT).show()
    }
  }

  companion object {
    private const val TAG = "AddClockFragment"
    @JvmStatic
    fun newInstance() = AddClockFragment()
  }
}