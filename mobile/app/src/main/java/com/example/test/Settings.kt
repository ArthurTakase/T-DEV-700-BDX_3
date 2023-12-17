package com.example.test

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Settings : Fragment() {
    private val jsonTools = JSONTools()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val buttonDisconnect = view.findViewById<Button>(R.id.button_disconnect)
        buttonDisconnect.setOnClickListener {
            disconnectUser()
            // launch vibration

        }

        return view
    }

    private fun disconnectUser() {
        jsonTools.emptyUserJson(requireContext())

        val intent = Intent(activity, Login::class.java)
        startActivity(intent)
        activity?.finish()
    }
}