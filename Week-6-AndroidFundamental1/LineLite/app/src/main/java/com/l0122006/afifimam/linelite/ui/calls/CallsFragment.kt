package com.l0122006.afifimam.linelite.ui.calls

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l0122006.afifimam.linelite.Call
import com.l0122006.afifimam.linelite.ListCallAdapter
import com.l0122006.afifimam.linelite.R

class CallsFragment : Fragment() {

    private lateinit var rvCalls: RecyclerView
    private val list = ArrayList<Call>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_calls, container, false)

        rvCalls = rootView.findViewById(R.id.rv_calls)
        rvCalls.setHasFixedSize(true)

        list.addAll(getListCalls())
        showRecyclerList()

        return rootView
    }

    private fun getListCalls(): ArrayList<Call> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDate = resources.getStringArray(R.array.data_date_calls)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val listCall = ArrayList<Call>()
        for (i in dataName.indices) {
            val call = Call(dataName[i], dataDate[i], dataImg.getResourceId(i, -1))
            listCall.add(call)
        }
        return listCall
    }

    private fun showRecyclerList() {
        rvCalls.layoutManager = LinearLayoutManager(requireContext())
        val listCallAdapter = ListCallAdapter(list)
        rvCalls.adapter = listCallAdapter
    }

    companion object {
        fun newInstance() = CallsFragment()
    }
}
