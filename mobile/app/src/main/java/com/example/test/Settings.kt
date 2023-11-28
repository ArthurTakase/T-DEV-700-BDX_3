package com.example.test

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Settings : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val buttonDisconnect = view.findViewById<Button>(R.id.button_disconnect)
        buttonDisconnect.setOnClickListener {
            disconnectUser()
        }

        return view
    }

    private fun disconnectUser() {
        val jsonTools = JSONTools()
        jsonTools.emptyUserJson(requireContext())

        val intent = Intent(activity, MainActivity2::class.java)
        startActivity(intent)
        activity?.finish()
    }
}