package com.example.fefu_fitnes.UI.Views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.databinding.FragmentTimetableBinding


class TimetableFragment: Fragment() {
    private var _binding: FragmentTimetableBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView:RecyclerView
    private var adapter: RecyclerViewAdapter? = null

    private var allRecyclerItems = mutableListOf<View>()

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
        listOf("пн", "15"),
        listOf("вт", "16"),
        listOf("ср", "17"),
        listOf("чт", "18"),
        listOf("пт", "19"),
        listOf("сб", "20"),
        listOf("вс", "21"),
        listOf("пн", "22"),
        listOf("вт", "23"),
        listOf("ср", "24"),
        listOf("чт", "25"),
        listOf("пт", "26"),
        listOf("сб", "27"),
        listOf("вс", "28"),
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
                return true
            }
        }

        recyclerView = binding.recyclerView

        recyclerView.layoutManager = myLinearLayoutManager

        updateUI()

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        var recyclerItemCurrentPosition = 6

        binding.dateButtonForward.setOnClickListener {
            if (recyclerItemCurrentPosition != recyclerViewConstants.lastIndex)
                recyclerItemCurrentPosition +=7

            recyclerView.scrollToPosition(recyclerItemCurrentPosition)
        }

        binding.dateButtonBack.setOnClickListener {
            if (recyclerItemCurrentPosition - 7 > 0)
                recyclerItemCurrentPosition -=7
            else
                recyclerItemCurrentPosition = 6

            recyclerView.scrollToPosition(recyclerItemCurrentPosition-6)
        }
    }


    private inner class RecyclerViewAdapter(val data:List<List<String>>):
        RecyclerView.Adapter<RecyclerViewHolder>(){
        @SuppressLint("SetTextI18n")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = layoutInflater.inflate(R.layout.item_day_timetable_unchecked, parent, false)
            allRecyclerItems.add(view)
            view.setOnClickListener{
                for (item in allRecyclerItems){
                    if (item != it)
                        item.setBackgroundResource(R.drawable.timetable_item_day_unchecked)
                }
                it.setBackgroundResource(R.drawable.timetable_item_day_checked)
            }
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

    private fun updateUI(){
        adapter = RecyclerViewAdapter(recyclerViewConstants)
        recyclerView.adapter = adapter
    }


    private inner class RecyclerViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textDay:TextView = itemView.findViewById(R.id.day_name)
        val textNumber:TextView = itemView.findViewById(R.id.day_number)
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