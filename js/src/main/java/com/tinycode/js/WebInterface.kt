package com.tinycode.js

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.documentfile.provider.DocumentFile
import org.json.JSONObject
import java.util.Base64

fun saveDataLocally(context: Context, key: String, value: String) {
    val sharedPreferences = context.getSharedPreferences("SP", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString(key, value).apply()
}

class WebInterface(private var context: Context) {
    private fun checkUri(uri: Uri) {
        uri.let { uriTemp ->
            context.contentResolver.takePersistableUriPermission(
                uriTemp,
                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
        }
    }

    @JavascriptInterface
    fun saveData(key: String, value: String) {
        saveDataLocally(context, key, value)
    }

    @JavascriptInterface
    fun show(toast: String) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun openWith(uriString: String) {
        val uri = Uri.parse(uriString)
        val intent = Intent(Intent.ACTION_VIEW)
        if (uri.scheme == "content") {
            val extension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
            val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            intent.setDataAndType(uri, mimeType)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                show("No app found for file.")
            }
        }
    }

    @JavascriptInterface
    fun getData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences("SP", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    @JavascriptInterface
    fun listFiles(
        uriString: String,
        extension: String = "none",
        recursive: Boolean = false,
        returnKey: String = "returnKey"
    ): String {
        val listReturn = JSONObject()

        fun listFilesInner(dir: DocumentFile, list: JSONObject) {
            if (dir.isFile) {
                if ((dir.name?.lowercase()
                        ?.endsWith(extension.lowercase()) == true) || extension == "none"
                ) dir.name?.let {
                    list.put(it, dir.uri)
                }
            } else if (dir.isDirectory && recursive) {
                dir.listFiles().forEach { childFile ->
                    listFilesInner(childFile, list)
                }
            } else {
                show("Error in listing files.")
            }
        }

        val uri = Uri.parse(uriString)
        checkUri(uri)
        val documentFile = DocumentFile.fromTreeUri(context, uri)
        if (documentFile != null) {
            listFilesInner(documentFile, listReturn)
        }
        return JSONObject().put(returnKey, listReturn).toString()
    }

    @JavascriptInterface
    fun getFile(uriStr: String): String? {
        val uri = Uri.parse(uriStr)
        val file = DocumentFile.fromSingleUri(context, uri)
        return if (file?.isFile == true) {
            val fileInputStream = context.contentResolver.openInputStream(file.uri)
            val byteArray = fileInputStream?.readBytes()
            fileInputStream?.close()
            val str64 = Base64.getEncoder().encodeToString(byteArray)
            val fileData = JSONObject()
            fileData.put("name", file.name)
            fileData.put("type", file.type)
            fileData.put("uri", file.uri)
            fileData.put("base64", str64)
            fileData.toString()
        } else null
    }

    @JavascriptInterface
    fun selectDirectory(key: String) {
        Handler(Looper.getMainLooper()).post {
            val activity = context as? Activity
            if (activity != null) {
                FolderSelection.selectFolder(activity, key)
            }
        }
    }
}

