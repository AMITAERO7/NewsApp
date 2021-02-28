package com.hackernight.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hackernight.newsapp.R
import com.hackernight.newsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private  var _binding : ActivityNewsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        _binding?.bottomNavigationView?.setupWithNavController(findNavController(R.id.newsNavHostFragment))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}