package araustin.touchshare

import App
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

    private var nfcAdapter: NfcAdapter? = null
    private var nfcPendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize NFC adapter
        /*nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if(nfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        nfcPendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_IMMUTABLE
        )
        */
        // Set click listener for send button
            findViewById<Button>(R.id.sendBtn).setOnClickListener {
            // Check if NFC is available
            /*nfcAdapter?.let { adapter ->
                if (adapter.isEnabled) {
                    // Create NDEF message
                    val message = NdefMessage(
                        arrayOf(NdefRecord.createTextRecord("en", "Hello NFC"))
                    )
                    // Send NDEF message
                    // unsupported setNdefPushMessage for Android 14? -> adapter.setNdefPushMessage(message, this)
                } else {
                    Toast.makeText(this, "NFC is not available", Toast.LENGTH_SHORT).show()
                }
            }*/
        }
    }
}