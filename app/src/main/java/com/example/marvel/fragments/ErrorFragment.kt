package com.example.marvel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.marvel.R

class ErrorFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.error_fragment, null);
        val errorCode = view.findViewById<TextView>(R.id.error_code)
        val let = arguments?.let { it.getInt("error", 0) }
        let?.let { errorCode.text = it.toString() }
        return view
    }
}