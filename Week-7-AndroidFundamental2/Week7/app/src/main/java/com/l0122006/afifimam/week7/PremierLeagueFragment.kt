package com.l0122006.afifimam.week7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PremierLeagueFragment : Fragment() {
    private lateinit var rvPremierLeagues: RecyclerView
    private val list = ArrayList<PremierLeague>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_premier_league, container, false)

        rvPremierLeagues = rootView.findViewById(R.id.rv_premier_league)
        rvPremierLeagues.setHasFixedSize(true)

        list.addAll(getListPremierLeagues())
        showRecyclerList()

        return rootView
    }

    private fun getListPremierLeagues(): ArrayList<PremierLeague> {
        val dataName = resources.getStringArray(R.array.data_name_premier_league)
        val dataDesc = resources.getStringArray(R.array.data_desc_premier_league)
        val dataImg = resources.obtainTypedArray(R.array.data_img_premier_league)
        val listPremierLeague = ArrayList<PremierLeague>()
        for (i in dataName.indices) {
            val premierLeague = PremierLeague(dataName[i], dataDesc[i], dataImg.getResourceId(i, -1))
            listPremierLeague.add(premierLeague)
        }
        return listPremierLeague
    }

    private fun showRecyclerList() {
        rvPremierLeagues.layoutManager = LinearLayoutManager(requireContext())
        val listPremierLeagueAdapter = ListPremierLeagueAdapter(list)
        rvPremierLeagues.adapter = listPremierLeagueAdapter
    }

    companion object {
        fun newInstance() = PremierLeagueFragment()
    }
}