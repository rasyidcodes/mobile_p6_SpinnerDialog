package com.example.mobile_p6_spinnerdialog

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mobile_p6_spinnerdialog.databinding.ActivityPresensiBinding

class PresensiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPresensiBinding

    private var _Day = 0
    private var _Month  = 0
    private var _Year = 0
    private var _Hour = 0
    private var _Minute = 0
    private var _Pos = 0

    private val listOfMonth = arrayOf(
        "Januari",
        "Febuari",
        "Maret",
        "April",
        "Juni",
        "Juli",
        "Agustus",
        "September",
        "Oktober",
        "November",
        "Desember"
    )

    private val listOfAttedances = arrayOf("Hadir tepat waktu","Sakit", "Terlambat","Izin")


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPresensiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            dpMain.setOnDateChangeListener { _, year, month, dayOfMonth ->
                _Day = dayOfMonth
                _Month  = month
                _Year = year
            }

            val adapterAttendances = ArrayAdapter(
                this@PresensiActivity,
                R.layout.simple_spinner_dropdown_item,
                listOfAttedances)

            adapterAttendances.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spnAttendances.adapter = adapterAttendances

            tpMain.setOnTimeChangedListener{_,hourOfDay,minute ->
                _Hour = hourOfDay
                _Minute = minute
            }

            spnAttendances.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){

                        _Pos = position

                        if (position>0){
                            etKet.visibility = View.VISIBLE
                        }
                        else{
                            etKet.visibility = View.GONE
                        }

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

            btnSubmit.setOnClickListener {
                val selectedTime = String.format("%02d:%02d", _Hour, _Minute)
                val _MonthString = listOfMonth[_Month + 1]
                val selectedDate = "$_Day $_MonthString $_Year"

                if (_Day == 0 ){
                    Toast.makeText(this@PresensiActivity, "Date Cant Empty!", Toast.LENGTH_SHORT).show()
                }else if (_Hour == 0){
                    Toast.makeText(this@PresensiActivity, "Time Cant Empty!", Toast.LENGTH_SHORT).show()
                }else{
                    if (_Pos > 0 && etKet.text.toString() == ""){
                        Toast.makeText(this@PresensiActivity, "Keterangan Cant Empty!", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@PresensiActivity, "Presensi Berhasil $selectedDate jam $selectedTime", Toast.LENGTH_SHORT).show()
                    }

                }
            }

        }


    }
}