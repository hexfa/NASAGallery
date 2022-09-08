package com.nasagallery.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nasagallery.R
import com.nasagallery.databinding.NasaItemBinding
import com.nasagallery.model.local.NASAPhotoItem

internal class NasaItemAdapter(val listener: NasaItemCallBack) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val nasaItemList: MutableList<NASAPhotoItem> = ArrayList()
    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        val holderNasaItemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.nasa_item,
            parent,
            false
        )

        return NasaItemHolder(holderNasaItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NasaItemHolder).onBind(getItem(position))
    }

    override fun getItemCount(): Int {
        return nasaItemList.size
    }

    fun addData(list: List<NASAPhotoItem>) {
        this.nasaItemList.clear()
        this.nasaItemList.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): NASAPhotoItem {
        return nasaItemList[position]
    }

    inner class NasaItemHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun onBind(nasaItem: NASAPhotoItem) {
            val holderNasaItemBinding = dataBinding as NasaItemBinding
            holderNasaItemBinding.txtViewDate.text = nasaItem.date
            Glide.with(mContext).load(nasaItem.url).into(holderNasaItemBinding.imgViewPhotoNasa)
            holderNasaItemBinding.txtViewTitle.text = nasaItem.title
            holderNasaItemBinding.imgBtnArrowRight.setOnClickListener {

                listener.goToDetailPage(nasaItem.title)
            }


        }
    }
}