package com.everton.mononuclealanticoorps.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.everton.mononuclealanticoorps.R
import com.everton.mononuclealanticoorps.details.MonoclonalDetails
import com.everton.mononuclealanticoorps.model.Monoclonal
import com.everton.mononuclealanticoorps.model.MonoclonalResponse
import kotlinx.android.synthetic.main.main_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class MonoclonalListAdapter : RecyclerView.Adapter<MonoclonalListAdapter.ListViewHolder>() {

    private val monoclonalList: MutableList<Monoclonal> = mutableListOf()

    fun insertData(monocList: MonoclonalResponse){
        val monoclonals = monocList.results.map { it }
        monoclonalList.addAll(monoclonals)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(monoclonal: Monoclonal){
            itemView.monoclonal_TextView.text = monoclonal.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val cellForRow = LayoutInflater.from(parent.context).inflate(R.layout.main_row, parent, false)
        return ListViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return monoclonalList.size
    }

    private fun getItem(position: Int): Monoclonal {
        return monoclonalList[position]
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

}

