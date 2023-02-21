package com.example.fefu_fitnes.UI.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.fefu_fitnes.UI.Models.MainInfo
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.ViewModels.MainMenuViewModel

class MainMenuFragment: Fragment() {

    private lateinit var hostActivity: MainActivity

    private lateinit var recyclerView: RecyclerView
    private  var adapter: RecyclerAdapter? = null

    private val menuInfoViewModel: MainMenuViewModel by lazy {
        ViewModelProvider(this)[MainMenuViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main_menu, container, false)

        recyclerView = view.findViewById(R.id.main_recycler_view) as RecyclerView


        updateUI()

        return view
    }

    override fun onStart() {
        super.onStart()


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    private fun updateUI(){
        val menuItem = menuInfoViewModel.mainInfo
        adapter = RecyclerAdapter(menuItem)
        recyclerView.adapter = adapter
    }

    private inner class RecyclerAdapter(var mainInfo:List<MainInfo>):
        RecyclerView.Adapter<RecyclerViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = layoutInflater.inflate(R.layout.item_recycler_view, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            val itemMenu = mainInfo[position]
            holder.apply {
                text1.text = itemMenu.text1
                text2.text = itemMenu.text2
            }
        }

        override fun getItemCount(): Int = mainInfo.size

    }

    private inner class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){

        val text1: TextView = itemView.findViewById(R.id.text1)
        val text2: TextView = itemView.findViewById(R.id.text2)

    }


    companion object {
        fun newInstance(): MainMenuFragment {
            return MainMenuFragment()
        }
    }
}