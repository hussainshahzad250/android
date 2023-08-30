package com.example.sampleproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleproject.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), ItemClickInterface {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        var api = RetrofitHelper.getInstance().create(ItemsAPI::class.java)

        var adapter = ItemsAdapter(this@MainActivity)
        binding.itemsList.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        binding.itemsList.adapter = adapter

        // launching a new coroutine
        GlobalScope.launch {
            val result = api.getPosts()
            if (result != null)
            // Checking the results
                Log.d("ayush: ", result.body().toString())

            withContext(Dispatchers.Main) {
                adapter.submitList(result.body() ?: mutableListOf<Post>())

                adapter.notifyDataSetChanged()
            }
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            var result = api.getPosts()
//
//            var adapter = ItemsAdapter(this@MainActivity)
//            binding.itemsList.layoutManager =
//                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//
////        var items = listOf<String>("Shahzad", "Shubham", "Manoj", "Romy")
//
//            binding.itemsList.adapter = adapter
//        }
    }

    override fun onClick(name: String) {
        val intent = Intent(this@MainActivity, ChildActivity::class.java)
        intent.putExtra("item_name", name)
        startActivity(intent)
    }
}