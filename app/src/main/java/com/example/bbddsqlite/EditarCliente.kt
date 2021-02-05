package com.example.bbddsqlite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf

class EditarCliente : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_cliente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle("Modificar Cliente")
        val id: Long? = arguments?.getLong("id")
        if (id != null) {
            val nombre=view.findViewById<EditText>(R.id.editarCliente_etNombre)
            val miCliente: Cliente = (activity?.application as Aplicacion).modelo.Buscar(id)
            if (miCliente.id.compareTo(0)==0) {
               Toast.makeText(activity,"No existe el usuario",Toast.LENGTH_SHORT).show()
            }
            else {
                view.findViewById<TextView>(R.id.editarCliente_tvID).setText(String.format("ID " + miCliente.id.toString()))
                nombre.setText(miCliente.nombre)

                view.findViewById<Button>(R.id.editarCliente_bInsertar).setOnClickListener {
                    if (miCliente.nombre == nombre.text.toString()) {
                        Toast.makeText(activity, "No has cambiado el valor", Toast.LENGTH_SHORT).show()
                    } else {
                        (activity?.application as Aplicacion).modelo.Modificar(Cliente(miCliente.id, nombre.text.toString()))
                        Toast.makeText(activity, "Cliente modificado", Toast.LENGTH_SHORT).show()
                        val bundle = bundleOf("id" to miCliente.id)
                        (activity as MainActivity).navHost.navController.navigate(R.id.action_editarCliente_to_SecondFragment, bundle)
                    }
                }
            }
        }
    }
}