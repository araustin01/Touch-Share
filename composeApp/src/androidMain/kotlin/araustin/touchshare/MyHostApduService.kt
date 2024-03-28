package araustin.touchshare

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MyHostApduService : HostApduService() {

    companion object {
        private const val TAG = "MyHostApduService"
        private val HELLO_WORLD_APDU = "Hello, World!".toByteArray()
    }

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        return HELLO_WORLD_APDU;
        /*Toast.makeText(this, "HCE Activation!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Received APDU command: ${commandApdu?.joinToString(" ") { String.format("%02X", it) }}")

        // Check if the received APDU command matches SELECT AID (0x00A40400)
        if (commandApdu != null && commandApdu.size >= 4 && commandApdu[0] == 0x00.toByte() &&
            commandApdu[1] == 0xA4.toByte() && commandApdu[2] == 0x04.toByte() && commandApdu[3] == 0x00.toByte()
        ) {
            Log.d(TAG, "Received SELECT AID command")
            return byteArrayOf(0x90.toByte(), 0x00.toByte()) // Success response (SW1-SW2: 0x9000)
        }

        // If the received APDU command is not SELECT AID, respond with "Hello, World!" APDU
        return HELLO_WORLD_APDU*/
    }

    override fun onDeactivated(reason: Int) {
        Toast.makeText(this, "HCE Deactivation!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "HCE deactivated: reason=$reason")
    }
}