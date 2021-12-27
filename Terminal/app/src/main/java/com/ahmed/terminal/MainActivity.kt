package com.ahmed.terminal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmed.terminal.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity(), ItemAdapter.ItemClicker {

    private lateinit var binding: ActivityMainBinding
    var adapterSocial: ItemAdapter? = null
    var mList = ArrayList<ItemModel>()

    private val url =
        https://run.mocky.io/v3/0085e3ac-c7d6-4a93-ba92-925f65be6fb3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val requestQueue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0..2) {
                    val title = response.getJSONArray("data").getJSONObject(i).getString("t")
                    val quantity = response.getJSONArray("data").getJSONObject(i).getInt("q")
                    mList.add(ItemModel(title, quantity))

                    adapterSocial = ItemAdapter(mList)
                    adapterSocial?.mListener = this
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.recyclerView.adapter = adapterSocial
                }
            },
            { error ->

            }
        )
        requestQueue.add(jsonObjectRequest)

        val intent = Intent(this, MyService::class.java)
        startService(intent)
    }

    override fun onItemClick() {

        val messageIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:1234"))
        startActivity(messageIntent)
    }
}

class AutoStart : BroadcastReceiver() {

    override fun onReceive(context: Context, arg1: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED==arg1.action)
        val intent = Intent(context, MyService::class.java)
        context.startService(intent)

        Log.d("myLog", "Auto start completed")
    }
}