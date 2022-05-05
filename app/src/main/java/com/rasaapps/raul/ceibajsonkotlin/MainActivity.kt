package com.rasaapps.raul.ceibajsonkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity(), JumpListener {

    var usersRV: RecyclerView? = null

    var userModalArrayList : ArrayList<UserModal>? = null

    var url = "https://jsonplaceholder.typicode.com/users"

    private var progressBar: ProgressBar? = null

    var userNames: List<String>? = null
    var userPhones: List<String>? = null
    var userMails: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.idPB)

        usersRV = findViewById(R.id.idRVUsers) // RecycleView

        // below line we are creating a new array list
        userModalArrayList = ArrayList()

        getData()

        buildRecyclerView()

    }

    fun copyArrayList() {
        userNames = ArrayList()
        userPhones = ArrayList()
        userMails = ArrayList()
        Log.i("ETIQUETA", "list size: " + userModalArrayList!!.size)
        for (i in userModalArrayList!!.indices) {
            Log.i("ETIQUETA", userModalArrayList!![i].getUserName()!!)
            (userNames as ArrayList<String>).add(userModalArrayList!![i].getUserName()!!)
            (userPhones as ArrayList<String>).add(userModalArrayList!![i].getUserPhone()!!)
            (userMails as ArrayList<String>).add(userModalArrayList!![i].getUserEmail()!!)
        }
    }

    private fun getData() {
        val queue = Volley.newRequestQueue(this)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url,
            null, { response ->
                progressBar!!.visibility = View.GONE
                usersRV!!.visibility = View.VISIBLE
                for (i in 0 until response.length()) {
                    try {
                        val responseObj = response.getJSONObject(i)

                        val userName = responseObj.getString("name")
                        val userPhone = responseObj.getString("phone")
                        val userEmail = responseObj.getString("email")

                        userModalArrayList!!.add(UserModal(userName, userEmail, userPhone))
                        buildRecyclerView()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                copyArrayList()
            }
        ) { Toast.makeText(this, "Fail to get the data..", Toast.LENGTH_SHORT).show() }
        queue.add(jsonArrayRequest)
    }

    fun buildRecyclerView() {
        // initializing our adapter class.
        var adapter = UserAdapter(userModalArrayList, this)
        adapter.addListener(this)

        val manager = LinearLayoutManager(this)
        usersRV!!.setHasFixedSize(true)

        // setting layout manager
        // to our recycler view.
        usersRV!!.layoutManager = manager

        // setting adapter to
        // our recycler view.
        usersRV!!.adapter = adapter

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_bar_search_menu, menu)
        val searchViewItem = menu.findItem(R.id.app_bar_menu_search)
        val searchViewAndroidActionBar = searchViewItem.actionView as SearchView
        searchViewAndroidActionBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewAndroidActionBar.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                userModalArrayList!!.clear() // clear the list first
                for (x in userNames!!.indices) {
                    if (userNames!![x].contains(newText)) userModalArrayList!!.add(
                        UserModal(
                            userNames!![x], userMails!![x],
                            userPhones!![x]
                        )
                    )
                }
                buildRecyclerView()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onReadRequest(id: String) {
        // Intent jump to Publica
        val i = Intent(this, Publica::class.java)
        i.putExtra("id", id)
        startActivity(i)
   }


}