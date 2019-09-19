package com.appnio.playground.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appnio.playground.R


class FunAdpater : RecyclerView.Adapter<FunAdpater.CoolViewHolder>() {

    class CoolViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoolViewHolder {
        return CoolViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cool, parent, false) as TextView
        )
    }

    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: CoolViewHolder, position: Int) {
        holder.textView.text = "Hey, cool item $position"
    }

}