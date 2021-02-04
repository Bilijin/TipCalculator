package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tipcalculator.databinding.ActivityPopUpBinding

class PopUpActivity : AppCompatActivity() {
    val binding : ActivityPopUpBinding by lazy {
        ActivityPopUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0,0)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            val tot = bundle.getString("total")
            binding.total.text = getString(R.string.total, tot)

            val tip = bundle.getString("tip")
            binding.tip.text = getString(R.string.tips , tip)

            val each = bundle.getString("each_total", "")
            if (each != "") {
                binding.each.text = getString(R.string.each_of, each.toString())
            } else {
                binding.each.visibility = View.GONE
            }

        }


    }
}