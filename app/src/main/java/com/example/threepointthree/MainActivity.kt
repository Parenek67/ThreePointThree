package com.example.threepointthree

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sp: Spinner = findViewById(R.id.sp)
        val persons = readFromJson()
        val rv: RecyclerView = findViewById(R.id.rv)
        val adapter = RecyclerViewAdapter(persons)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        sp.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.fields))
        sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> persons.sortBy { it.name }
                    1 -> persons.sortBy { it.phoneNumber }
                    2 -> persons.sortBy { it.sex }
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun readFromJson(): MutableList<Person>{
        val jsonString = applicationContext.assets.open("Persons.json").
            bufferedReader().use { it.readText() }
        val gson = Gson()
        val type = object : TypeToken<List<Person>>() {}.type
        val persons: MutableList<Person> = gson.fromJson(jsonString, type)
        Log.d("aaa", persons.get(0).name +" "+ persons.get(0).sex)
        return persons
    }
}