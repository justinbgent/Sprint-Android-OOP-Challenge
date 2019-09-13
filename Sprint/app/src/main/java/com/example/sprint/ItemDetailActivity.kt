package com.example.sprint

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.example.sprint.ItemDetailFragment.Companion.ARG_ITEM_ID
import com.example.interfaces.R
import com.example.sprint.model.Vehicle
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity(),
    ItemDetailFragment.ObtainWeight {

    override fun getWeight(weight: Int) {
        Toast.makeText(this, "Weight: ${weight}lbs", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        TODO: This is just to make my added code easy to find
        if (savedInstanceState == null) {
            val fragment = ItemDetailFragment()
            val bundle = Bundle()
            val item = intent.getParcelableExtra<Parcelable>(ARG_ITEM_ID) as Vehicle?
            bundle.putParcelable(ARG_ITEM_ID, item)
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {

                navigateUpTo(Intent(this, ItemListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
