package com.everton.mononuclealanticoorps

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(val monoclonal: ArrayList<Monoclonal>) :
    RecyclerView.Adapter<MainAdapter.CustomViewHolder>(),
    Filterable {
    var monoclonalFilterList = ArrayList<Monoclonal>()
    var originalList = monoclonal
    init {
        monoclonalFilterList = monoclonal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.main_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return monoclonalFilterList.size
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val monoclonal = monoclonalFilterList[position]
        holder.itemView.monoclonal_TextView?.text = monoclonal.name

        //holder?.monoclonal = monoclonal
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (constraint?.length == 0) {
                    monoclonalFilterList = monoclonal
                } else {
                    monoclonalFilterList = getFilteredResults(constraint.toString().toLowerCase(Locale.ROOT))
                    /*val resultList = ArrayList<Monoclonal>()
                    for (row in monoclonal) {
                        if (row.toString().toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    monoclonalFilterList = resultList*/
                }
                val filterResults = FilterResults()
                filterResults.values = monoclonalFilterList
                return filterResults
            }


            @Suppress("UNCHEKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                monoclonalFilterList = results?.values as ArrayList<Monoclonal>
                notifyDataSetChanged()
            }

            protected fun getFilteredResults(constraint: String?): ArrayList<Monoclonal> {
                val results: ArrayList<Monoclonal> = ArrayList()
                val charSrch = constraint.toString()
                for (item in originalList) {
                    if (item.name?.toLowerCase()!!.contains(charSrch)) {
                        results.add(item)
                    }
                }
                return results
            }
        }


    }


    class CustomViewHolder(val view: View, var monoclonal: Monoclonal? = null) :
        RecyclerView.ViewHolder(view) {

        companion object {
            val MONOCLONAL_TITLE_KEY = "TITLE"
        }

        init {
            view.setOnClickListener {
                val intent = Intent(view.context, MonoclonalDetails::class.java)

                intent.putExtra(MONOCLONAL_TITLE_KEY, monoclonal?.name)

                view.context.startActivity(intent)


            }
        }
    }
}

