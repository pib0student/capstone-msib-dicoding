package com.dicoding.timsaw.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.timsaw.data.Data
import com.dicoding.timsaw.data.api.ApiConfig
import com.dicoding.timsaw.databinding.ActivityMainBinding
import com.dicoding.timsaw.ui.detail.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    private var mainAdapter = MainAdapter(results = ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView() {
        with(activityMainBinding.rvData){
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
        mainAdapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback{
            override fun onItemClick(data: Data.Results) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_DRIVER_NAME, data.driver_name)
                    it.putExtra(DetailActivity.EXTRA_NO_VEHICLE, data.no_vehicle)
                    it.putExtra(DetailActivity.EXTRA_NO_WEIGHING, data.no_weighing)
                    it.putExtra(DetailActivity.EXTRA_TIME, data.time)
                    it.putExtra(DetailActivity.EXTRA_DATE, data.date)
                    it.putExtra(DetailActivity.EXTRA_MANY_BUNCHES, data.many_bunches)
                    it.putExtra(DetailActivity.EXTRA_BRUTO, data.bruto)
                    it.putExtra(DetailActivity.EXTRA_TARA, data.tara)
                    it.putExtra(DetailActivity.EXTRA_NETO, data.neto)

                    startActivity(it)
                }
            }
        })
    }

    private fun getDataFromApi() {
        showLoading(true)
        ApiConfig.instance.getData().enqueue(object: Callback<Data>{
            override fun onResponse(
                call: Call<Data>,
                response: Response<Data>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    showResult( response.body()!! )
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                printLog( t.toString() )
            }

        })
    }

    private fun showLoading(loading: Boolean) {
        when(loading) {
            true -> activityMainBinding.progressBar.visibility = View.VISIBLE
            false -> activityMainBinding.progressBar.visibility = View.GONE
        }
    }

    private fun showResult(body: Data) {
        for (result in body.results) printLog("driver_name: ${result.driver_name}")
        mainAdapter.setData(body.results)
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    companion object{
        const val TAG = "MainActivity"
    }
}