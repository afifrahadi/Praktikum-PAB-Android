package com.l0122006.afifimam.responsi1.ui.daftar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l0122006.afifimam.responsi1.R

class DaftarFragment : Fragment(), ListTeamAdapter.OnItemClickListener {
    private lateinit var rvTeams: RecyclerView
    private val list = ArrayList<Team>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_daftar, container, false)

        rvTeams = rootView.findViewById(R.id.rv_teams)
        rvTeams.setHasFixedSize(true)

        list.addAll(getListTeams())
        showRecyclerList()

        return rootView
    }

    private fun getListTeams(): ArrayList<Team> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val listTeam = ArrayList<Team>()
        for (i in dataName.indices) {
            val team = Team(dataName[i], dataDesc[i], dataImg.getResourceId(i, -1))
            listTeam.add(team)
        }
        dataImg.recycle()
        return listTeam
    }

    private fun showRecyclerList() {
        rvTeams.layoutManager = LinearLayoutManager(requireContext())
        val listTeamAdapter = ListTeamAdapter(list)
        listTeamAdapter.setOnItemClickCallback(this)
        rvTeams.adapter = listTeamAdapter
    }
    override fun onItemClicked(data: Team) {
        Toast.makeText(requireContext(), "Clicked: ${data.name}", Toast.LENGTH_SHORT).show()
    }
}
