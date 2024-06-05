package com.l0122006.afifimam.responsi1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.l0122006.afifimam.responsi1.R
import com.l0122006.afifimam.responsi1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTentangNba.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_tentangNBAFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}