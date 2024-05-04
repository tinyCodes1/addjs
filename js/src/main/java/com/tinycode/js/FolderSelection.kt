package com.tinycode.js


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast

object FolderSelection {
    const val REQUEST_SELECT_FOLDER = 123
    fun selectFolder(activity: Activity, key: String) {
        val intent = Intent(activity, SelectionActivity::class.java)
        intent.putExtra("key", key)
        activity.startActivityForResult(intent, REQUEST_SELECT_FOLDER)
    }
}

class SelectionActivity : Activity() {
    val FRQ = FolderSelection.REQUEST_SELECT_FOLDER
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val key = this.intent.getStringExtra("key")
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        intent.putExtra("key", key)
        startActivityForResult(intent, FRQ)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FRQ) {
            if (resultCode == RESULT_OK) {
                val key = this.intent.getStringExtra("key")
                val uri: Uri? = data.data
                uri?.let { uriTemp ->
                    contentResolver.takePersistableUriPermission(
                        uriTemp,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                if (key != null) {
                    saveDataLocally(this, key, uri.toString())
                }
                finish()
            } else {
                Toast.makeText(this, "Folder selection canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

