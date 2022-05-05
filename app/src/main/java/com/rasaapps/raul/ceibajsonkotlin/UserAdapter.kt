package com.rasaapps.raul.ceibajsonkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import java.lang.ref.WeakReference

class UserAdapter() : Adapter<UserAdapter.ViewHolder>() {

    private var listener = WeakReference<JumpListener>(null)

    // creating a variable for array list and context.
    private var userDataArrayList: ArrayList<UserModal>? = null
    private var context: Context? = null

    // creating a constructor for our variables.
    constructor(userDataArrayList: ArrayList<UserModal>?, context: Context?) : this() {
        this.userDataArrayList = userDataArrayList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // below line is to inflate our layout.
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = Integer.toString(position+1)
        var modal : UserModal? = userDataArrayList?.get(position)
        holder.userNameTV.text = modal!!.getUserName()
        holder.userPhoneTV.text = modal.getUserPhone()
        holder.userEmailTV.text = modal.getUserEmail()

        holder.publicacionesTV.setOnClickListener { release(pos) }

    }

    fun release(id : String){
        listener.get()?.onReadRequest(id)
    }


    override fun getItemCount(): Int {
       return userDataArrayList?.size!!
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our views.
        val userNameTV: TextView
        val userEmailTV: TextView
        val userPhoneTV: TextView
        val publicacionesTV: TextView

        //private ImageView courseIV;
        init {
            // initializing our views with their ids.
            userNameTV = itemView.findViewById(R.id.idTVUserName)
            userPhoneTV = itemView.findViewById(R.id.idTVPhone)
            userEmailTV = itemView.findViewById(R.id.idTVEmail)
            publicacionesTV = itemView.findViewById(R.id.idTVPublicaciones)
            //publicacionesTV.setOnClickListener {
            //    Log.i("ETIQUETA", "Publicaciones hit with: ${publicacionesTV.contentDescription.toString()}")
                //listener.get()?.onReadRequest()
            //    UserAdapter().release()
            //}
        }

    }

    fun addListener(listener: JumpListener){
        this.listener = WeakReference(listener)
    }


}