package com.everton.mononuclealanticoorps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_row.view.*

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.main_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return homeFeed.monoclonals.count()
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val monoclonalNames = homeFeed.monoclonals.get(position)
        holder.view.monoclonal_TextView.text = monoclonalNames.name
    }

}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}