package com.dicoding.timsaw.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.timsaw.data.Data
import com.dicoding.timsaw.databinding.ItemDataBinding

class MainAdapter(private val results: ArrayList<Data.Results>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Data.Results)
    }

    fun setData(data: List<Data.Results>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dataDriver: Data.Results){
            with(binding){
                tvNo.text = dataDriver.no_weighing.toString()
                tvDate.text = dataDriver.date
                tvTime.text = dataDriver.time
                tvDriver.text = dataDriver.driver_name
                tvPlatNumber.text = dataDriver.no_vehicle
            }
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClick(dataDriver)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int = results.size
}