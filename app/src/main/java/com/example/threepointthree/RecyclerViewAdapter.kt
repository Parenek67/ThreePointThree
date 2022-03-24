package com.example.threepointthree

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val persons: List<Person>):
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(persons.get(position))
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val name: TextView = itemView.findViewById(R.id.name)
        private val number: TextView = itemView.findViewById(R.id.number)
        private val image: ImageView = itemView.findViewById(R.id.image)
        fun bind(person: Person){
            name.text = person.name
            number.text = person.phoneNumber
            when(person.sex){
                "male" -> image.setImageResource(R.drawable.ic_baseline_male_24)
                "female" -> image.setImageResource(R.drawable.ic_baseline_female_24)
                "none" -> image.setImageResource(R.drawable.ic_baseline_question_mark_24)
            }
        }
    }
}
