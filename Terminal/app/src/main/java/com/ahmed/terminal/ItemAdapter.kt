package com.ahmed.terminal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.terminal.databinding.LayoutItemBinding
import java.util.ArrayList

class ItemAdapter(var mList: ArrayList<ItemModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: LayoutItemBinding
    lateinit var mListener: ItemClicker

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_item, parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val mHolder = holder as ItemViewHolder

        mHolder.binding.textViewTitle.text = mList[position].title
        mHolder.binding.textViewQuantity.text = mList[position].quantity.toString()

        mHolder.binding.buttonBuy.setOnClickListener {
            mListener.onItemClick()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: LayoutItemBinding = DataBindingUtil.bind(view)!!
    }

    interface ItemClicker {
        fun onItemClick()
    }
}