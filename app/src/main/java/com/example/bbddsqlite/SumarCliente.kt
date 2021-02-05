package com.example.bbddsqlite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class SumarCliente : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sumar_cliente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle("Insertar Cliente")
        view.findViewById<Button>(R.id.sumarCliente_bInsertar).setOnClickListener {
            var nombre=view.findViewById<EditText>(R.id.sumarCliente_etNombre)
            if(nombre.text.isEmpty()){
                Toast.makeText(activity,"Tienes que insertar un nombre",Toast.LENGTH_SHORT).show()
            }
            else {
                (activity?.application as Aplicacion).modelo.Insertar(Cliente(nombre.text.toString()))
                (activity as MainActivity).navHost.navController.navigate(R.id.action_sumarCliente_to_SecondFragment)
            }
        }
    }
}