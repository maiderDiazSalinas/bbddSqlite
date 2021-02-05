package com.example.bbddsqlite

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView

class Adaptador(private val listaClientes: List<Cliente>, val context: Context, val actividad: Activity) : RecyclerView.Adapter<Adaptador.miViewHolder>(){
    inner class miViewHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        lateinit private var miItem: Cliente
        var nombre: TextView
        var id: TextView

        init {
            nombre = view.findViewById<View>(R.id.item_tvNombre) as TextView
            id = view.findViewById<View>(R.id.item_tvID) as TextView
            view.setOnClickListener(this)
        }

        fun setItem(miCliente: Cliente) {
            miItem = miCliente
            nombre.text = miCliente.nombre
            id.text = miCliente.id.toString()
        }

        override fun onClick(view: View) {
            val bundle= bundleOf("id" to this.id.text.toString().toLong())
            (actividad as MainActivity).navHost.navController.navigate(R.id.action_SecondFragment_to_detalleCliente, bundle)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): miViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return miViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: miViewHolder, position: Int) {
        val movie = listaClientes[position]
        holder.setItem(movie)
    }

    override fun getItemCount(): Int {
        return listaClientes.size
    }
}