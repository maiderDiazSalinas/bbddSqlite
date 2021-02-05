package com.example.bbddsqlite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle("Gestión clientes")

        view.findViewById<Button>(R.id.first_bConsultar).setOnClickListener {
            (activity as MainActivity).navHost.navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}