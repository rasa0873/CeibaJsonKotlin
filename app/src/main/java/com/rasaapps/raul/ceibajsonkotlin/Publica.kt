package com.rasaapps.raul.ceibajsonkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class Publica : AppCompatActivity() {

    var publicaRV : RecyclerView? = null

    var publicacionesArrayList : ArrayList<String>? = null

    var urlNew = "https://jsonplaceholder.typicode.com/posts?userId="

    private var progressBar : ProgressBar? = null

    var idFromMain : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publica)

        val i2 : Intent = intent
        idFromMain = i2.getStringExtra("id")

        urlNew = urlNew.plus(idFromMain)

        progressBar = findViewById(R.id.idPB2)

        publicaRV = findViewById(R.id.idRVPublica)

        publicacionesArrayList = ArrayList()

        getData()

        buildRecyclerView()
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(this)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, urlNew,
            null, { response ->
                progressBar!!.visibility = View.GONE
                publicaRV!!.visibility = View.VISIBLE
                for (i in 0 until response.length()) {
                    try {
                        val responseObj = response.getJSONObject(i)

                        val titleName = responseObj.getString("title")

                        publicacionesArrayList!!.add(titleName)
                        buildRecyclerView()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

            }
        ) { Toast.makeText(this, "Fail to get the data..", Toast.LENGTH_SHORT).show() }
        queue.add(jsonArrayRequest)
    }


    fun buildRecyclerView() {

        var adapter = PublicacionesAdapter(publicacionesArrayList, this)

        val manager = LinearLayoutManager(this)
        publicaRV!!.setHasFixedSize(true)

        // setting layout manager
        // to our recycler view.
        publicaRV!!.layoutManager = manager

        // setting adapter to
        // our recycler view.
        publicaRV!!.adapter = adapter


    }
}