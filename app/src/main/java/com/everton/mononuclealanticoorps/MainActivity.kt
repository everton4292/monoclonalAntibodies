package com.everton.mononuclealanticoorps

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase.openDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
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