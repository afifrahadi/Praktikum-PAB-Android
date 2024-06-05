package com.l0122006.afifimam.linelite.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l0122006.afifimam.linelite.ListNewsAdapter
import com.l0122006.afifimam.linelite.News
import com.l0122006.afifimam.linelite.R

class NewsFragment : Fragment() {

    private lateinit var rvNews: RecyclerView
    private val list = ArrayList<News>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_news, container, false)

        rvNews = rootView.findViewById(R.id.rv_news)
        rvNews.setHasFixedSize(true)

        list.addAll(getListNews())
        showRecyclerList()

        return rootView
    }

    private fun getListNews(): ArrayList<News> {
        val dataName = resources.getStringArray(R.array.data_name_news)
        val dataDesc = resources.getStringArray(R.array.data_desc_news)
        val dataImg = resources.obtainTypedArray(R.array.data_img_news)
        val listNews = ArrayList<News>()
        for (i in dataName.indices) {
            val news = News(dataName[i], dataDesc[i], dataImg.getResourceId(i, -1))
            listNews.add(news)
        }
        return listNews
    }

    private fun showRecyclerList() {
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        val listNewsAdapter = ListNewsAdapter(list)
        rvNews.adapter = listNewsAdapter
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}