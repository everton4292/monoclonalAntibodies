package com.everton.mononuclealanticoorps

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase.openDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import okhttp3.*
import java.io.IOException
import java.sql.SQLException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_ListView)
        listView.adapter = MyCustomAdapter(this)

        var search = search_view

        var toolbar = toolbar
        setSupportActionBar(toolbar)

        search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                search.isIconified = false
                var intent = Intent(this@MainActivity, MonoclonalDetails::class.java)
                startActivity(intent)

            }
        })
        fetchJson()

    }

    fun fetchJson() {
        val url = "http://192.168.15.12:3333/monoclonal"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue( object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println("//success pipou//")
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("//failed pipou//")
            }
        })
    }


    private class MyCustomAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context

        private val names = arrayListOf<String>(
            "Dipirona", "Valium", "opioide", "dorflex"
        )
        init {
            mContext = context
        }

        override fun getView(position: Int, convertView: View?, ViewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.main_row, ViewGroup, false)

            val nameTextView = rowMain.findViewById<TextView>(R.id.monoclonal_TextView)
            nameTextView.text = names.get(position)

            return rowMain


        }

        override fun getItem(position: Int): Any {
            return "TEXT STRING"

        }

        override fun getItemId(position: Int): Long {
            return position.toLong()

        }

        override fun getCount(): Int {
            return names.size


        }

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

class HomeFeed(val monoclonals: List<Monoclonals>)

class Monoclonals(val monoclonalId: Int, val name: String)

