package araustin.touchshare

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize NFC adapter
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        // Set click listener for send button
        findViewById<Button>(R.id.sendBtn).setOnClickListener {
            // Check if NFC is available
            nfcAdapter?.let { adapter ->
                val isHceSupported =
                    packageManager.hasSystemFeature("android.hardware.nfc.hce")
                Toast.makeText(this, "HCE service supported: $isHceSupported", Toast.LENGTH_SHORT).show()
                if (adapter.isEnabled) {
                    this.startService(Intent(this, MyHostApduService::class.java));
                    val isServiceRunning = isMyServiceRunning(this, MyHostApduService::class.java)
                    if (isServiceRunning) {
                        // Service is running, create and send NDEF message
                        Toast.makeText(this, "HCE service running", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Service is not running", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "NFC is not enabled", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Function to create an NDEF message
    private fun createNdefMessage(text: String): NdefMessage {
        val record = NdefRecord.createTextRecord(null, text)
        return NdefMessage(arrayOf(record))
    }

    // Function to check if a service is running
    private fun isMyServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningServices = activityManager.getRunningServices(Integer.MAX_VALUE)
        for (serviceInfo in runningServices) {
            if (serviceClass.name == serviceInfo.service.className) {
                return true
            }
        }
        return false
    }
}
