package com.everton.mononuclealanticoorps

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_row.view.*

class MainAdapter(val monoclonal : Array<Monoclonal>) : RecyclerView.Adapter<CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.main_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return monoclonal.size
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val monoclonal = monoclonal[position]
        holder?.view?.monoclonal_TextView?.text = monoclonal.name

        holder?.monoclonal = monoclonal
    }


}

class CustomViewHolder(val view: View, var monoclonal: Monoclonal? = null) : RecyclerView.ViewHolder(view) {

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