package com.l0122006.afifimam.linelite.ui.chats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l0122006.afifimam.linelite.Chat
import com.l0122006.afifimam.linelite.ListChatAdapter
import com.l0122006.afifimam.linelite.R


class ChatsFragment : Fragment() {

    private lateinit var rvChats: RecyclerView
    private val list = ArrayList<Chat>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_chats, container, false)

        rvChats = rootView.findViewById(R.id.rv_chats)
        rvChats.setHasFixedSize(true)

        list.addAll(getListChats())
        showRecyclerList()

        return rootView
    }

    private fun getListChats(): ArrayList<Chat> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_desc_chats)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val listChat = ArrayList<Chat>()
        for (i in dataName.indices) {
            val chat = Chat(dataName[i], dataDesc[i], dataImg.getResourceId(i, -1))
            listChat.add(chat)
        }
        return listChat
    }

    private fun showRecyclerList() {
        rvChats.layoutManager = LinearLayoutManager(requireContext())
        val listChatAdapter = ListChatAdapter(list)
        rvChats.adapter = listChatAdapter
    }

    companion object {
        fun newInstance() = ChatsFragment()
    }
}
