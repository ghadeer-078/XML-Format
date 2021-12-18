package com.example.xmlformatassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlformatassignment.Model.Student
import com.example.xmlformatassignment.Resource.RVAdapter
import com.example.xmlformatassignment.Resource.XmlParser
import java.io.IOException

class MainActivity : AppCompatActivity() {


    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    private lateinit var students: List<Student>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        students = listOf()

        rvMain = findViewById(R.id.rvMain)
        loadRV()

        parseXML()
    }

    private fun loadRV() {
        rvAdapter = RVAdapter(students)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)
    }


    private fun parseXML() {
        try {
            val parser = XmlParser()
            val iStream = assets.open("student_details.xml")
            students = parser.parse(iStream)
            rvAdapter.update(students)
        } catch (e: IOException) {
            println("ISSUE: $e")
        }
    }

}