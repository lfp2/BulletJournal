package com.lfp2.bulletjournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        val fab: FloatingActionButton = findViewById(R.id.fab);

        fab.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_today -> {
                val todayTaskFragment = TaskFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, todayTaskFragment, todayTaskFragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_history -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
