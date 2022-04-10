package com.thaislins.newsfeed.modules.news.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thaislins.newsfeed.R
import com.thaislins.newsfeed.databinding.ActivityMainBinding
import com.thaislins.newsfeed.modules.news.view.adapter.NewsAdapter
import com.thaislins.newsfeed.modules.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvNews.layoutManager = LinearLayoutManager(this)

        newsViewModel.newsList.observe(this, {
            if (it != null) {
                val adapter = NewsAdapter(it, view.context)
                binding.rvNews.adapter = adapter
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter -> {
            if (newsViewModel.newsList.value != null) {
                val typeList: List<String> = newsViewModel.newsList.value!!.map { it.type }
                val builder = AlertDialog.Builder(this);
                builder.setTitle("Filter news by:")
                    .setSingleChoiceItems(typeList.distinct().toTypedArray(), -1) { dialogInterface, i ->
                        Log.d("TYPE", typeList[i])
                        dialogInterface.dismiss()
                    }


                builder.setNeutralButton("Cancel") { dialog, which ->
                    dialog.cancel()
                }

                builder.create().show()
            }
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}