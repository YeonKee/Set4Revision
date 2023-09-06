package com.example.set4revision

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.set4revision.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.stateSpinner.onItemSelectedListener = this

        ArrayAdapter.createFromResource(this, R.array.stateSelection, android.R.layout.simple_spinner_item).also{
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.stateSpinner.adapter = adapter
        }

        binding.calculateRoadTaxBtn.setOnClickListener{
            calculateRoadTax()
        }

        binding.contactUsBtn.setOnClickListener{
            contactUs()
        }

    }

    private fun calculateRoadTax(){
        val engineString : String = binding.engineInput.text.toString()

        if(engineString.isEmpty()){
            binding.roadTaxTotal.text = "Please enter valid engine capacity."
        }else{
            val engine : Int = engineString.toInt()
            val stateChosen : String = binding.stateChosen.text.toString()

            when(stateChosen){
                "Peninsular Malaysia" -> {
                    if(engine <= 1000){
                        binding.roadTaxTotal.text = "RM20.00"
                    }else if(engine in 1001 ..1200){
                        binding.roadTaxTotal.text = "RM55.00"
                    }else if(engine in 1201 ..1400){
                        binding.roadTaxTotal.text = "RM70.00"
                    }else if(engine in 1401 ..1600){
                        binding.roadTaxTotal.text = "RM90.00"
                    }else {
                        binding.roadTaxTotal.text = "RM200.00"
                    }
                }

                "Sabah and Sarawak" -> {
                    if(engine <= 1000){
                        binding.roadTaxTotal.text = "RM20.00"
                    }else if(engine in 1001 ..1200){
                        binding.roadTaxTotal.text = "RM44.00"
                    }else if(engine in 1201 ..1400){
                        binding.roadTaxTotal.text = "RM56.00"
                    }else if(engine in 1401 ..1600){
                        binding.roadTaxTotal.text = "RM72.00"
                    }else {
                        binding.roadTaxTotal.text = "RM160.00"
                    }
                }
            }
        }
    }

    private fun contactUs(){
//        var phoneIntent = Intent(Intent.ACTION_DIAL)
//        phoneIntent.data = Uri.parse("tel:03-61261601")
//        startActivity(phoneIntent)

        var emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:abc@gmail.com")
        emailIntent.putExtra(Intent.EXTRA_TITLE, "Road Tax")
        startActivity(emailIntent)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, Id: Long) {
        var stateChosen : String = parent.getItemAtPosition(position).toString()
        binding.stateChosen.text = stateChosen
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        binding.stateChosen.text = "No state selected"
    }
}