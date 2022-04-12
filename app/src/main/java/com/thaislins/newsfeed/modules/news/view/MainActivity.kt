package com.thaislins.newsfeed.modules.news.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thaislins.newsfeed.R
import com.thaislins.newsfeed.databinding.ActivityMainBinding
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.model.Resource
import com.thaislins.newsfeed.modules.news.view.adapter.NewsAdapter
import com.thaislins.newsfeed.modules.news.viewmodel.NewsViewModel
import com.thaislins.newsfeed.utils.CheckNetworkConnection
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModel()
    private lateinit var checkNetworkConnection: CheckNetworkConnection
    val typeList = ArrayList<String>() //List of news type
    private lateinit var dialog: AlertDialog

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvNews.layoutManager = LinearLayoutManager(this)

        //Create progress bar dialog
        val progressDialog = AlertDialog.Builder(this)
        progressDialog.setCancelable(false).setView(R.layout.layout_loading)
        dialog = progressDialog.create()

        addNewsObserver() //Create observer for news list
        callNetworkConnection() //Creates observer for network connection and shows dialog when disconnected
        loadNewsList()
    }

    fun loadNewsList() {
        newsViewModel.loadNewsList()
        dialog.show()
    }

    fun addNewsObserver() {
        val newsListObserver = Observer<Resource<List<News>?>> { list ->
            dialog.dismiss()
            if (list?.data != null) {
                binding.emptyView.visibility = View.INVISIBLE
                val adapter = NewsAdapter(list.data, this)
                binding.rvNews.adapter = adapter
                //Add to typeList
                typeList.add("all")
                typeList.addAll(list.data.map { it.type })
            } else {
                binding.emptyView.visibility = View.VISIBLE
            }
        }

        newsViewModel.newsList.observe(this, newsListObserver)
    }

    private fun callNetworkConnection() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Internet Connection")
        builder.setMessage("Make sure you're connected to a Wi-Fi or mobile network and try again")
        val alertDialog = builder.create()

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection.observe(this, { isConnected ->
            if (isConnected) {
                alertDialog.dismiss()
            } else {
                alertDialog.show()
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
                val builder = AlertDialog.Builder(this);
                builder.setTitle("Filter news by:")
                    .setSingleChoiceItems(
                        typeList.distinct().toTypedArray(),
                        -1
                    ) { dialogInterface, i ->
                        newsViewModel.filterNewsList(typeList[i])
                        dialogInterface.dismiss()
                    }

                builder.setNeutralButton("Cancel") { dialog, _ ->
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