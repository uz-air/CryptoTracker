package com.ankur.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ankur.crypto.data.CryptoResponseData
import com.ankur.crypto.ui.CryptoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.R.attr.name
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration


class MainActivity : AppCompatActivity() {

    // 1
    private val repoRetriever by lazy { Repository() }

    //2
    private val callback = object : Callback<CryptoResponseData> {
        override fun onFailure(call: Call<CryptoResponseData>?, t: Throwable?) {
            Log.e("MainActivity", "Problem calling Github API {${t?.message}}")
        }

        override fun onResponse(
                call: Call<CryptoResponseData>?,
                response: Response<CryptoResponseData>?
        ) {
            val resultList = response?.body()?.data
            val adapter = CryptoAdapter(resultList)
            val recycler = findViewById<RecyclerView>(R.id.view)
            val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            //decorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.file)!!)
            recycler.addItemDecoration(decorator)
           /* recycler.addItemDecoration(
                    DividerItemDecoration(
                            recycler.context,
                            LinearLayoutManager.HORIZONTAL
                    )
            )*/

            recycler.adapter = adapter
            Log.d("TAG", resultList.toString())
            var i = 1
            val n = 10
            var t1 = 0
            var t2 = 1
            print("First $n terms: ")



            while (i <= n) {
                print("$t1 + ")
                val sum = t1 + t2
                t1 = t2
                t2 = sum
                i++
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            /* Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
             startActivity(Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS))
 */
            repoRetriever.getRepositories(callback)
        }


    }


}