package com.example.democexup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.democexup.R
import com.example.democexup.databinding.FragmentHomeBinding
import com.example.democexup.domain.repository.DemoRepository
import com.example.democexup.local.Dao.DatabaseDemo
import com.example.democexup.local.Entities.Matakuliah


class HomeFragment : Fragment() {

    private val demoRepository : DemoRepository by lazy { DemoRepository(DatabaseDemo.getInstance(requireContext())!!) }
    private val demoViewModel : DemoViewModel by lazy { DemoViewModel(demoRepository) }

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    private fun insertData(){
        val matakuliah = listOf(
            Matakuliah(1,"Matematika"),
            Matakuliah(2,"Fisika"),
            Matakuliah(3,"Biologi"),
            Matakuliah(4,"Kimia")
        )
        matakuliah.forEach {
            demoViewModel.insertMatakuliah(it)
        }
    }



}