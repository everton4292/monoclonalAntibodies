package com.everton.mononuclealanticoorps

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase.openDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.sql.SQLException

class MainActivity : AppCompatActivity() {

    lateinit var myDbHelper: DataBaseHelper
    var databaseOpened = false
    lateinit var simpleCursorAdapter: SimpleCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var search = search_view

        var toolbar = toolbar
        setSupportActionBar(toolbar)

        search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                search.isIconified = false

            }
        })

        myDbHelper = DataBaseHelper(this);
        if(myDbHelper.checkDataBase()) {
            openDatabase()
        } else {
            var task = LoadDatabaseAsync(this@MainActivity)
            task.execute()
        }

        val from = Array<String>(40) {"words"}
        var to = IntArray(40) {R.id.suggestion_text}

        var suggestionAdapter = SimpleCursorAdapter(this@MainActivity, R.layout.suggestion_row, null, from, to, 0) {
            fun changeCursor(cursor: Cursor) {
                super.swapCursor(cursor)
            }
        }



    }


    fun openDatabase() {
        try {
            myDbHelper.openDatabase();
            databaseOpened = true;
        } catch (e: SQLException) {
            e.printStackTrace()
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
