package com.example.cameraapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cameraapplication.databinding.FragmentListBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        val path = "/storage/emulated/0/Android/media/com.example.cameraapplication/photos"
        val file = File(path, "date.txt")
        var photoDataList : List<MyAdapter.PhotoData>? = null
        try {
            photoDataList = readDataFromFile(file)
        } catch (exception : NoSuchFileException) {
            val dir = File(path + "date.txt")
            dir.mkdir()

        } finally {
            for ((index, photoData) in photoDataList?.withIndex()!!) {
                photoData.photoName = "photo_${index + 1}"
            }

            // Инициализация адаптера и установка его в RecyclerView
            val adapter = MyAdapter(photoDataList)
            binding.photoList.adapter = adapter

            val layoutManager = LinearLayoutManager(requireContext())
            binding.photoList.layoutManager = layoutManager
        }
    }

    private fun readDataFromFile(file: File): List<MyAdapter.PhotoData> {
        val data = mutableListOf<MyAdapter.PhotoData>()
        if (file.exists()) {
            try {
                val reader = BufferedReader(FileReader(file))
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    // Преобразование строки в объект PhotoData и добавление его в список
                    data.add(MyAdapter.PhotoData(photoName = "", photoDateTime = line ?: ""))
                }
                reader.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        data.removeAt(0)
        return data
    }
}