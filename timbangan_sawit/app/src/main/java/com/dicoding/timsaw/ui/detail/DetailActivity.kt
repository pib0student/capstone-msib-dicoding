package com.dicoding.timsaw.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.timsaw.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noWeighing = intent.getIntExtra(EXTRA_NO_WEIGHING, 0)
        val date = intent.getStringExtra(EXTRA_DATE)
        val time = intent.getStringExtra(EXTRA_TIME)
        val driverName = intent.getStringExtra(EXTRA_DRIVER_NAME)
        val platNumber = intent.getStringExtra(EXTRA_NO_VEHICLE)
        val manyBunches = intent.getIntExtra(EXTRA_MANY_BUNCHES, 0)
        val bruto = intent.getIntExtra(EXTRA_BRUTO, 0)
        val tara = intent.getIntExtra(EXTRA_TARA, 0)
        val neto = intent.getIntExtra(EXTRA_NETO, 0)

        with(detailBinding){
            tvNo.text = noWeighing.toString()
            tvDate.text = date
            tvTime.text = time
            tvDriver.text = driverName
            tvPlatNumber.text = platNumber
            tvTotalBunches.text = manyBunches.toString()
            tvTotalBruto.text = bruto.toString()
            tvTotalTara.text = tara.toString()
            tvTotalNeto.text = neto.toString()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    companion object {
        const val EXTRA_NO_WEIGHING = "extra_no_weighing"
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_TIME = "extra_time"
        const val EXTRA_DRIVER_NAME = "extra_driver_name"
        const val EXTRA_NO_VEHICLE = "extra_no_vehicle"
        const val EXTRA_MANY_BUNCHES = "extra_many_bunches"
        const val EXTRA_BRUTO = "extra_bruto"
        const val EXTRA_TARA = "extra_tara"
        const val EXTRA_NETO = "extra_neto"
    }
}