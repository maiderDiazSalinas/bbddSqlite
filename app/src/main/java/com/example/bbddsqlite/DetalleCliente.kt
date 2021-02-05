package com.example.bbddsqlite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf


class DetalleCliente : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_cliente, container, false)

    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            activity?.setTitle("Detalle Cliente")
            val id:Long?=arguments?.getLong("id")
            if(id!=null){
                val miCliente:Cliente=(activity?.application as Aplicacion).modelo.Buscar(id)
                view.findViewById<TextView>(R.id.detalleCliente_tvID).setText(miCliente.id.toString())
                view.findViewById<TextView>(R.id.detalleCliente_tvNombre).setText(miCliente.nombre)

                view.findViewById<Button>(R.id.detalleCliente_bModificar).setOnClickListener {
                    val bundle= bundleOf("id" to miCliente.id)
                    (activity as MainActivity).navHost.navController.navigate(R.id.action_detalleCliente_to_editarCliente, bundle)
                }

                view.findViewById<Button>(R.id.detalleCliente_bBorrar).setOnClickListener {
                    (activity?.application as Aplicacion).modelo.Borrar(id)
                    (activity as MainActivity).navHost.navController.navigate(R.id.action_detalleCliente_to_SecondFragment)
                }
            }
            else{
                Toast.makeText(activity, "Ha habido un error",Toast.LENGTH_SHORT).show()
                (activity as MainActivity).navHost.navController.navigate(R.id.action_detalleCliente_to_SecondFragment)
            }
        }

}