package com.ankur.crypto

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankur.crypto.data.CryptoResponseData
import com.ankur.crypto.databinding.ActivityMainBinding
import com.ankur.crypto.ui.CryptoAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    // 1
    private val repoRetriever by lazy { Repository() }
    private lateinit var binding: ActivityMainBinding

    //2
    private val callback by lazy {
        object : Callback<CryptoResponseData> {
            override fun onFailure(call: Call<CryptoResponseData>?, t: Throwable?) {
                stopLoading()
                getString(R.string.error_msg).makeSnack(binding.root)
            }

            override fun onResponse(
                call: Call<CryptoResponseData>?,
                response: Response<CryptoResponseData>?
            ) {
                val resultList = response?.body()?.data
                val adapter = CryptoAdapter(resultList)
                binding.view.adapter = adapter
                stopLoading()
            }
        }
    }



    private fun stopLoading() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
        binding.view.visibility = View.VISIBLE
        binding.swiperefresh.isRefreshing = false
    }

    private fun startLoading() {
        binding.shimmerLayout.startShimmer()
        binding.view.visibility = View.GONE
        binding.shimmerLayout.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_refresh -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            startLoadingAndCallApi()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        binding.shimmerLayout.startShimmer()
        super.onResume()
    }

    override fun onPause() {
        binding.shimmerLayout.stopShimmer()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.swiperefresh.setOnRefreshListener {
            startLoadingAndCallApi()
        }
        startLoadingAndCallApi()
    }

    private fun startLoadingAndCallApi() {
        if (isNetworkConnected()) {
            startLoading()
            repoRetriever.getRepositories(callback)
        } else {
            getString(R.string.internet_error).makeSnack(binding.root)
        }
    }


}