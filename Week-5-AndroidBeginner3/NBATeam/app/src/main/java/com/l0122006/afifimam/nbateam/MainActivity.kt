package com.l0122006.afifimam.nbateam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvTeams: RecyclerView
    private val list = ArrayList<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvTeams = findViewById(R.id.rv_teams)
        rvTeams.setHasFixedSize(true)

        list.addAll(getListTeams())
        showRecyclerList()
    }

    fun getListTeams() : ArrayList<Team> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val listHero = ArrayList<Team>()
        for (i in dataName.indices) {
            val hero = Team(dataName[i], dataDesc[i], dataImg.getResourceId(i, -1))
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvTeams.layoutManager = LinearLayoutManager(this)
        val listTeamAdapter = ListTeamAdapter(this@MainActivity,list)
        rvTeams.adapter = listTeamAdapter

        listTeamAdapter.setOnItemClickCallback(object : ListTeamAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Team) {
                showSelectedTeam(data)
            }

        })
    }

    private fun showSelectedTeam(Team: Team) {
        Toast.makeText(this, Team.name + " is selected", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvTeams.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvTeams.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
