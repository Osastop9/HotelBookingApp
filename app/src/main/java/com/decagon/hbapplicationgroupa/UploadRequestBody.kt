package com.decagon.hbapplicationgroupa

import android.os.Handler
import android.os.Looper
import com.decagon.hbapplicationgroupa.ui.ProfileFragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream


open class UploadRequestBody(
    //image to be upload
    private val file: File,
    private val contentType: String,
    private val callBack: ProfileFragment

) : RequestBody() {

    // to update the progress
    interface uploadCalback {
        fun onProgressUpdate(percentage: Int)
    }




    // error
    override fun contentType() = "$contentType/*".toMediaTypeOrNull()


    override fun contentLength() = file.length()

    override fun writeTo(sink: BufferedSink) {
        val length = file.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fileInputStream = FileInputStream(file)
       //  to track the byte of the image upload
        var uploaded = 0L


        // whenever you are using a resource  that need to be close we used "Used function"
        fileInputStream.use { inputStream ->
            var read: Int
            // to update the progress in the main thread
            val handler = Handler(Looper.getMainLooper())
            while (inputStream.read(buffer).also {read = it } != -1){
               // handler.post(ProgressUpdate(uploaded, length))
                uploaded += read
                sink.write(buffer,0, read)
            }
        }
    }

    companion object{
        //size of the image
        private const val DEFAULT_BUFFER_SIZE = 1048
    }
}

