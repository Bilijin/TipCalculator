package com.example.tipcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layout = binding.layout

        val set = ConstraintSet()
        set.clone(layout)


        binding.split.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                set.clear(R.id.calculate, ConstraintSet.TOP)

                set.setVisibility(R.id.no_text, 0)

                set.connect(R.id.calculate, ConstraintSet.TOP, R.id.no_text, ConstraintSet.BOTTOM, 50)

                set.applyTo(layout)
            }
            else {
                set.setVisibility(R.id.no_text, 8)

                set.clear(R.id.calculate, ConstraintSet.TOP)
                set.connect(R.id.calculate, ConstraintSet.TOP, R.id.split, ConstraintSet.BOTTOM, 50)

                set.applyTo(binding.layout)
            }
        }

        binding.calculate.setOnClickListener {
            if (binding.bill.text?.isNotEmpty()!!) {
                if (binding.tipPer.text?.isNotEmpty()!!) {
                    if (binding.no.text?.isNotEmpty()!!) {
                        split()
                    }else {
                        tip()
                    }
                } else {
                    Toast.makeText(this, "Please enter your tip percentage", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter your bill", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun tip() {
        val bill = binding.bill.text.toString().toInt()
        val per = binding.tipPer.text.toString().toInt()

        val tip = (per * bill) / 100
        val total = tip + bill

        val tipped = NumberFormat.getCurrencyInstance().format(tip)
        val totality = NumberFormat.getCurrencyInstance().format(total)

        binding.tip.visibility = View.VISIBLE
        binding.tip.text = (getString(R.string.tips, tipped))
        binding.total.visibility = View.VISIBLE
        binding.total.text = (getString(R.string.totals, totality))

        val intent = Intent(this, PopUpActivity::class.java)

        intent.putExtra("tip", tipped)
        intent.putExtra("total", totality)

        startActivity(intent)
    }

    private fun split() {
        val bill = binding.bill.text.toString().toInt()
        val per = binding.tipPer.text.toString().toInt()

        val tip = (per * bill) / 100
        val total = tip + bill
        val each = total / binding.no.text.toString().toInt()

        val tipped = NumberFormat.getCurrencyInstance().format(tip)
        val totality = NumberFormat.getCurrencyInstance().format(total)
        val eachOf = NumberFormat.getCurrencyInstance().format(each)

        binding.tip.visibility = View.VISIBLE
        binding.tip.text = (getString(R.string.tips, tipped))
        binding.total.visibility = View.VISIBLE
        binding.total.text = (getString(R.string.totals, totality))

        val intent = Intent(this, PopUpActivity::class.java)

        intent.putExtra("tip", tipped)
        intent.putExtra("total", totality)
        intent.putExtra("each_total", eachOf)

        startActivity(intent)

    }
}