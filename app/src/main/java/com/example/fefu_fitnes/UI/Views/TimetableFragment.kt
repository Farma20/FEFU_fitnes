package com.example.fefu_fitnes.UI.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.databinding.FragmentTimetableBinding


class TimetableFragment: Fragment() {
    private var _binding: FragmentTimetableBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView:RecyclerView
    private var adapter: RecyclerViewAdapter? = null

    private val recyclerViewConstants = listOf(
        listOf("пн", "1"),
        listOf("вт", "2"),
        listOf("ср", "3"),
        listOf("чт", "4"),
        listOf("пт", "5"),
        listOf("сб", "6"),
        listOf("вс", "7"),
        listOf("пн", "8"),
        listOf("вт", "9"),
        listOf("ср", "10"),
        listOf("чт", "11"),
        listOf("пт", "12"),
        listOf("сб", "13"),
        listOf("вс", "14"),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimetableBinding.inflate(inflater, container, false)

        //отключение прокрутки recylerView
        val myLinearLayoutManager = object :
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false) {

            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }

        recyclerView = binding.recyclerView

        recyclerView.layoutManager = myLinearLayoutManager

        updateUI()

        return binding.root
    }

    private fun updateUI(){
        adapter = RecyclerViewAdapter(recyclerViewConstants)
        recyclerView.adapter = adapter
    }



    private inner class RecyclerViewAdapter(val data:List<List<String>>): RecyclerView.Adapter<RecyclerViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = layoutInflater.inflate(R.layout.item_day_timetable, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val item = data[position]
            holder.apply {
                textDay.text = item[0]
                textNumber.text = item[1]
            }
        }

        override fun getItemCount(): Int {
            return data.count()
        }

    }


    private inner class RecyclerViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textDay:TextView = itemView.findViewById(R.id.day_name)
        val textNumber:TextView = itemView.findViewById(R.id.day_number)
    }



    override fun onStart() {
        super.onStart()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object{
        fun newInstance():TimetableFragment{
            return TimetableFragment()
        }
    }
}