package com.l0122006.afifimam.week7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NBAFragment : Fragment() {
    private lateinit var rvNbas: RecyclerView
    private val list = ArrayList<Nba>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_n_b_a, container, false)

        rvNbas = rootView.findViewById(R.id.rv_nba)
        rvNbas.setHasFixedSize(true)

        list.addAll(getListNbas())
        showRecyclerList()

        return rootView
    }

    private fun getListNbas(): ArrayList<Nba> {
        val dataName = resources.getStringArray(R.array.data_name_nba)
        val dataDesc = resources.getStringArray(R.array.data_desc_nba)
        val dataImg = resources.obtainTypedArray(R.array.data_img_nba)
        val listNba = ArrayList<Nba>()
        for (i in dataName.indices) {
            val nba = Nba(dataName[i], dataDesc[i], dataImg.getResourceId(i, -1))
            listNba.add(nba)
        }
        return listNba
    }

    private fun showRecyclerList() {
        rvNbas.layoutManager = LinearLayoutManager(requireContext())
        val listNbaAdapter = ListNbaAdapter(list)
        rvNbas.adapter = listNbaAdapter
    }

    companion object {
        fun newInstance() = NBAFragment()
    }
}