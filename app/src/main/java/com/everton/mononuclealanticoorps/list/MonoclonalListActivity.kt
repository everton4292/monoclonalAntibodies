package com.everton.mononuclealanticoorps.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.everton.mononuclealanticoorps.R
import com.everton.mononuclealanticoorps.details.MonoclonalDetails
import kotlinx.android.synthetic.main.activity_main.*


class MonoclonalListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_RecyclerView.layoutManager = LinearLayoutManager(this)
        main_RecyclerView.adapter = MonoclonalListAdapter()

        val search = search_view

        val toolbar = toolbar
        setSupportActionBar(toolbar)

        search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                search.isIconified = false
                val intent = Intent(this@MonoclonalListActivity, MonoclonalDetails::class.java)
                startActivity(intent)

            }
        })
    }
}








