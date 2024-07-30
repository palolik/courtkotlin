package com.demo.countrylistretrofit.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load
import com.demo.countrylistretrofit.R
import com.demo.countrylistretrofit.data.CountryModel
import com.demo.countrylistretrofit.databinding.CountryListRowBinding

class CountryListAdapter(private val activity: Activity) : RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {

    private var countryList: List<CountryModel> = listOf()

    fun setCountryList(countryList: List<CountryModel>) {
        this.countryList = countryList
        notifyDataSetChanged() // Notify adapter of data changes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CountryListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        countryList.getOrNull(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = countryList.size

    class MyViewHolder(private val binding: CountryListRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CountryModel) {
            binding.tvName.text = "${data.name} "
            binding.tvCapital.text = "Capital: ${data.capital}"
            binding.tvRegion.text = "Region: ${data.region}"
            binding.flagImage.load(data.flag) {
                decoderFactory(SvgDecoder.Factory())
                crossfade(true)
                error(R.drawable.error) // Optional error image
            }
        }
    }
}
