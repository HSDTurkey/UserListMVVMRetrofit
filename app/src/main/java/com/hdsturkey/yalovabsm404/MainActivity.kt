package com.hdsturkey.yalovabsm404

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hdsturkey.yalovabsm404.activities.UserActivity
import com.hdsturkey.yalovabsm404.utils.SharedPreferencesHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SharedPreferencesHelper.init(this)


    }


    //2- Ovveride Menu Creation Process
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.opt_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //3- Handle Menu Clicks
    /**
     * Return false to allow normal menu processing to proceed, true to consume it here.
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_add -> {
                toast("Add Menu Clicked")
                startActivity(UserActivity::class.java)
                return true
            }
            R.id.menu_edit -> {
                toast("Edit Menu Clicked")
                return true
            }
            R.id.menu_delete -> {
                toast("Delete Menu Clicked")
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }

    fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    fun Activity.startActivity(targetActivity: Class<*>) {
        startActivity(Intent(this,targetActivity))
    }

}