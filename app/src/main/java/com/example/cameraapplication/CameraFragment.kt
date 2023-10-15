package com.example.cameraapplication

import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cameraapplication.databinding.FragmentCameraBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CameraFragment : Fragment(), SurfaceHolder.Callback {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var camera: Camera
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var surfaceView: SurfaceView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (checkCameraPermission()){
            camera = Camera.open()
            surfaceView = view.findViewById(R.id.surfaceView)
            surfaceHolder = surfaceView.holder
            surfaceHolder.addCallback(this)
        }

        camera.setDisplayOrientation(90)

        binding.makePhotoButton.setOnClickListener {

            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val currentTime = dateFormat.format(Date())

            val photosDirectory = "/storage/emulated/0/Android/media/com.example.cameraapplication/photos/"
            val dateFile = File(photosDirectory, "date.txt")
            dateFile.appendText("\n$currentTime")

        }

    }


    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
            camera.setPreviewDisplay(holder)
            camera.startPreview()
        }
        catch (e: java.lang.Exception){
            e.printStackTrace()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        camera.stopPreview()
        // camera.release()
    }

    private fun checkCameraPermission(): Boolean{
        return if (
            requireActivity().checkSelfPermission(android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED
        ){
            true
        }else{
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA),1)
            false
        }
    }
}

