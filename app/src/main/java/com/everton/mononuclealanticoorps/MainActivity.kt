package com.everton.mononuclealanticoorps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var adapter: MainAdapter
    lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.main_RecyclerView)
        rv.layoutManager = LinearLayoutManager(rv.context)
        rv.setHasFixedSize(true)


        val search = search_view

        val toolbar = toolbar
        setSupportActionBar(toolbar)

        search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                search.isIconified = false
                var intent = Intent(this@MainActivity, MonoclonalDetails::class.java)
                startActivity(intent)

            }
        })

        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                adapter.filter.filter(newText)
                return true
            }

        })

        fetchJson()
    }




    fun fetchJson() {
        val url = "http://192.168.15.12:3333/monoclonal"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println("//success pipou//")
                println(body)

                val gson = GsonBuilder().create()

                var type = object : TypeToken<ArrayList<Monoclonal>>() {}.type
                val monoclonal = gson.fromJson<ArrayList<Monoclonal>>(body, type)
                //val monoclonal: List<Any> = Arrays.asList(GsonBuilder().create().fromJson(body, Array<Any>::class.java)
                //)

                runOnUiThread {
                    main_RecyclerView.adapter = MainAdapter(monoclonal)
                    adapter = MainAdapter(monoclonal)
                    rv.adapter = adapter

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("//failed pipou//")
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == R.id.action_settings) {
            var intent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }

        if (id == R.id.action_exit) {

            System.exit(0)
            return true
        }



        return super.onOptionsItemSelected(item)
    }


}


class Monoclonal(
    val _id: String? = "",
    val monoclonalId: String? = "",
    val name: String? = "",
    val target: String? = "",
    val concentration: String? = "",
    val dose: String? = "",
    val cycles: String? = "",
    val risk: String? = "",
    val infusionTime: String? = "",
    val premedication: String? = "",
    val filter: String? = "",
    val photosensibility: String? = "",
    val other: String? = ""

)



