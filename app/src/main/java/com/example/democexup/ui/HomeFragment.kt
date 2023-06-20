package com.example.democexup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.and2t2.secondhand.common.Status
import com.example.democexup.R
import com.example.democexup.databinding.FragmentHomeBinding
import com.example.democexup.domain.repository.DemoRepository
import com.example.democexup.local.Dao.DatabaseDemo
import com.example.democexup.local.Entities.Dosen
import com.example.democexup.local.Entities.Mahasiswa
import com.example.democexup.local.Entities.Matakuliah


class HomeFragment : Fragment() {

    private val demoRepository : DemoRepository by lazy { DemoRepository(DatabaseDemo.getInstance(requireContext())!!) }
    private val demoViewModel : DemoViewModel by lazy { DemoViewModel(demoRepository) }

    private lateinit var homeAdapter: HomeAdapter
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
        initRecycler()
        getMatakuliahData()
        insertData()
    }

    private fun initRecycler(){
        homeAdapter = HomeAdapter{
            val bundle = Bundle()
            bundle.putString("nid",it.nid)
            bundle.putString("nama",it.nama)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }
        binding.rvHome.apply {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }
    }

    private fun getMatakuliahData(){
        demoViewModel.getAlldata().observe(viewLifecycleOwner){
            when(it.status){
                Status.LOADING ->{

                }

                Status.SUCCESS ->{
                    it.data?.let { data -> homeAdapter.updateDataHome(data) }
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),"Gagal Memuat Data",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun insertData(){
        val matakuliah = listOf(
            Matakuliah(1,"Matematika"),
            Matakuliah(2,"Fisika"),
            Matakuliah(3,"Biologi"),
            Matakuliah(4,"Kimia")
        )
        val dosen = listOf(
            Dosen("1901","Joko",1),
            Dosen("1902","Ervan",2),
            Dosen("1903","Yance",3),
            Dosen("1904","Ronal",4)

        )

        val mahasiswa = listOf<Mahasiswa>(
            Mahasiswa("2001","afif",1),
            Mahasiswa("2002","fujia",1),
            Mahasiswa("2003","rahman",1),
            Mahasiswa("2004","lutfi",2),
            Mahasiswa("2005","hidayat",2),
            Mahasiswa("2006","rohim",2),
            Mahasiswa("2007","nabila",3),
            Mahasiswa("2008","farisa",3),
            Mahasiswa("2009","zeini",4)

        )
        matakuliah.forEach {
            demoViewModel.insertMatakuliah(it)
        }
        dosen.forEach {
            demoViewModel.insertDosen(it)
        }
        mahasiswa.forEach {
            demoViewModel.insertMahasiswa(it)
        }
    }



}