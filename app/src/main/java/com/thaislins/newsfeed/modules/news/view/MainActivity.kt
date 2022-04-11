package com.thaislins.newsfeed.modules.news.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thaislins.newsfeed.R
import com.thaislins.newsfeed.databinding.ActivityMainBinding
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.view.adapter.NewsAdapter
import com.thaislins.newsfeed.modules.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModel()
    val typeList = ArrayList<String>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvNews.layoutManager = LinearLayoutManager(this)

        //Create progress bar dialog
        val progressdialog = AlertDialog.Builder(this)
        progressdialog.setCancelable(false).setView(R.layout.layout_loading)
        val dialog = progressdialog.create()

        //Load data
        newsViewModel.loadNewsList()
        dialog.show()

        val newsListObserver = Observer<List<News>?> { list ->
            if (list != null) {
                val adapter = NewsAdapter(list, view.context)
                binding.rvNews.adapter = adapter
                //Add to typeList
                typeList.add("all")
                typeList.addAll(list.map { it.type })
                dialog.dismiss()
            }
        }

        newsViewModel.newsList.observe(this, newsListObserver)
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
                val builder = AlertDialog.Builder(this);
                builder.setTitle("Filter news by:")
                    .setSingleChoiceItems(
                        typeList.distinct().toTypedArray(),
                        -1
                    ) { dialogInterface, i ->
                        newsViewModel.filterNewsList(typeList[i])
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
            super.onOptionsItemSelected(item)
        }
    }
}