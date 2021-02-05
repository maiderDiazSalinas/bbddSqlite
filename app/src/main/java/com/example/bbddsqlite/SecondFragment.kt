package com.example.bbddsqlite

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {


    private var misClientes:MutableList<Cliente> = mutableListOf()
    lateinit private var miAdaptador: Adaptador
    lateinit private var miRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_second, container, false)
        activity?.setTitle("Lista clientes")

        misClientes = (activity?.application as Aplicacion).listaClientes

        this.miAdaptador= Adaptador(misClientes,activity as Context,activity as Activity)

        if(misClientes.size==0){
            rootView.findViewById<TextView>(R.id.second_tvVacio).visibility=View.VISIBLE
            rootView.findViewById<RecyclerView>(R.id.second_rvClientes).visibility=View.GONE
        }
        else{
            rootView.findViewById<TextView>(R.id.second_tvVacio).visibility=View.GONE
            rootView.findViewById<RecyclerView>(R.id.second_rvClientes).visibility=View.VISIBLE
            this.miRecyclerView=rootView.findViewById(R.id.second_rvClientes)
            miRecyclerView.layoutManager= LinearLayoutManager(activity)
            miRecyclerView.itemAnimator = DefaultItemAnimator()
            miRecyclerView.adapter=miAdaptador
            miAdaptador.notifyDataSetChanged()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            (activity as MainActivity).navHost.navController.navigate(R.id.action_SecondFragment_to_sumarCliente)
        }

    }

    override fun onResume() {
        super.onResume()
        miAdaptador.notifyDataSetChanged()
    }
}