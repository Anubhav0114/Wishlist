package com.example.wishlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wishlist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WishListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = WishListAdapter(this)

        adapter.setOnClickListener { position, url ->
            Log.i("TAG" , "On click listener position : $position")
            Toast.makeText(this , "The url is clicked at position : $url" , Toast.LENGTH_SHORT).show()
            val browserIntent = Intent(Intent.ACTION_VIEW , Uri.parse(url))
             startActivity(browserIntent)
        }




        setUp()

        binding.btnSubmit.setOnClickListener{


            var item = Item(binding.etItem.text.toString() , binding.etItemPrice.text.toString() , binding.itemUrl.text.toString())
            var position = adapter.itemCount
            adapter.addItem(item , position)
            binding.etItem.text = null
            binding.etItemPrice.text = null
            binding.itemUrl.text = null

        }
    }

    private fun setUp(){

        binding.rv.adapter = adapter

        binding.rv.layoutManager = LinearLayoutManager(this)

    }
}