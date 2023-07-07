package com.example.wordsapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.data.SettingsDataStore
import com.example.wordsapp.databinding.FragmentLetterListBinding


class LetterListFragment : Fragment() {

    private var _binding : FragmentLetterListBinding? = null

    private val binding get() = _binding!!
    private var isLinearLayoutManager = true
    private lateinit var settingsDataStore: SettingsDataStore
    val TAG  = "LetterListFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initialize variable
        settingsDataStore = SettingsDataStore(requireContext())
        recyclerView = binding.recyclerView
        chooseLayout()
        setUpMenu()
    }

    private fun setUpMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider{

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                Log.d(TAG,"onCreateMenu called");
                menuInflater.inflate(R.menu.layout_menu,menu)
                val layoutButton = menu.findItem(R.id.action_switch_layout)
                if (layoutButton != null) {
                    chooseIcon(layoutButton)
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.action_switch_layout->{
                        isLinearLayoutManager = !isLinearLayoutManager
                        chooseLayout()
                        chooseIcon(menuItem)
                        true
                    }
                    else ->false
                    //else -> super.onMenuClosed(menuItem.me)
                }
            }

        },viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun chooseIcon(menuItem: MenuItem){
        if(menuItem==null) return
        menuItem.icon =
            if (isLinearLayoutManager) ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear)

    }

    private fun chooseLayout(){

        if(isLinearLayoutManager){
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        else{
            recyclerView.layoutManager = GridLayoutManager(context,4)
        }
        recyclerView.adapter = LetterAdapter()
    }
}