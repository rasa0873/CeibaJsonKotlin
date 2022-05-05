package com.rasaapps.raul.ceibajsonkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import java.lang.ref.WeakReference

class PublicacionesAdapter() : Adapter<PublicacionesAdapter.ViewHolder>() {

    // creating a variable for array list and context.
    private var publicacionesArrayList: ArrayList<String>? = null
    private var context: Context? = null

    // creating a constructor for our variables.
    constructor(publicacionesArrayList: ArrayList<String>?, context: Context?) : this() {
        this.publicacionesArrayList = publicacionesArrayList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // below line is to inflate our layout.
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.publicacion_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = Integer.toString(position+1)
        var namePublicacion : String? = publicacionesArrayList?.get(position)
        holder.publicacionNameTV.text = namePublicacion
    }

    override fun getItemCount(): Int {
       return publicacionesArrayList?.size!!
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our views.
        val publicacionNameTV: TextView

        init {
            // initializing our views with their ids.
            publicacionNameTV = itemView.findViewById(R.id.idTVPublicacionName)
        }

    }




}