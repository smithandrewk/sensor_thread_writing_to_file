package com.example.separate_thread_hello_world.presentation

import android.util.Log
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class FilesHandler(filesDir: File) {
    private val filesDir = filesDir
    private val appStartTimeReadable = SimpleDateFormat("yyyy-MM-dd_HH_mm_ss", Locale.ENGLISH).format(Date())
    private val appStartTimeMillis = Calendar.getInstance().timeInMillis

    // Files
    private lateinit var dataFolderName: String
    private var rawFileIndex: Int = 0
    private lateinit var fRaw: FileOutputStream         // File output stream to write raw acc data

    init {

        createInitialFiles()
    }

    private fun createInitialFiles(){
        // Create folder for this session's files
        dataFolderName = appStartTimeReadable
        File(filesDir, dataFolderName).mkdir()
        createNewRawFile()

        // Info File
        try {
            val json = JSONObject()
                .put("App Start Time Millis", appStartTimeMillis)
                .put("App Start Time Readable", appStartTimeReadable)
            File(this.filesDir, "$dataFolderName/Info.json").appendText(json.toString())
        } catch (e: Exception) { e.printStackTrace() }
    }
    // Setup Functions
    private fun createNewRawFile() {
        // Create a new raw file for accelerometer data
        Log.i("0003", "Creating New Raw File")
        if (rawFileIndex == 0) {
            // Create "raw" directory
            File(this.filesDir, "$dataFolderName/raw").mkdir()
        }
        else {
            closeRawFile()
        }
        val rawFilename = "$appStartTimeReadable.$rawFileIndex.csv"
        fRaw = FileOutputStream(File(this.filesDir, "$dataFolderName/raw/$rawFilename"))
        fRaw.write("File Start Time: ${Calendar.getInstance().timeInMillis}\n".toByteArray())
        fRaw.write("event_timestamp,x,y,z\n".toByteArray())
        rawFileIndex++
    }
    fun writeStringToRawFile(string: String){
        fRaw.write(string.toByteArray())
    }

    private fun closeRawFile(){
        fRaw.close()
    }
}