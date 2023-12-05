package com.example.test

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class NFC : AppCompatActivity() {

    private var jsonTextFront: TextView? = null
    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    private var writingTagFilters: Array<IntentFilter>? = null
    private var writeMode: Boolean = false
    private var myTag: Tag? = null
    private var nfcTools: NFCTools = NFCTools()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)

        jsonTextFront = findViewById(R.id.nfc_content)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            Toast.makeText(this, "This device does not support NFC", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        //readFromIntent(intent)
        pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_MUTABLE)
        val tagDetected = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT)
        writingTagFilters = arrayOf(tagDetected)
    }

    @SuppressLint("SetTextI18n")
    private fun readFromIntent(intent: Intent) {
        val action: String? = intent.action
        Log.d("NFC", "readFromIntent: $action")
        if (NfcAdapter.ACTION_TAG_DISCOVERED == action
            || NfcAdapter.ACTION_TECH_DISCOVERED == action
            || NfcAdapter.ACTION_NDEF_DISCOVERED == action) {

            // get memory information from the intent
            intent.getByteArrayExtra(NfcAdapter.EXTRA_ID).toString()
            Toast.makeText(this, "NFC intent received", Toast.LENGTH_LONG).show()

            val byteArrayExtra = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)
            jsonTextFront!!.text = "NFC Tag: " + nfcTools.byteArrayToHexString(byteArrayExtra)

            val tagN: Parcelable? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            try {
                val data: Cash = nfcTools.extractDataMemory(intent)
                jsonTextFront!!.text = "NFC Tag: " + data.accountName + " // " + data.accountNumber
                // lancer le paiement ici
            } catch (e: Exception) {
                Log.d("NFC", "readFromIntent: $e")
                jsonTextFront!!.text = "Cannot read NFC tag"
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        readFromIntent(intent)
        if (NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            myTag = intent.getParcelableExtra("android.nfc.extra.TAG")
        }
    }

    override fun onPause() {
        super.onPause()
        writeModeOff()
    }

    override fun onResume() {
        super.onResume()
        writeModeOn()
    }

    private fun writeModeOn() {
        writeMode = true
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, writingTagFilters, null)
    }

    private fun writeModeOff() {
        writeMode = false
        nfcAdapter?.disableForegroundDispatch(this)
    }
}