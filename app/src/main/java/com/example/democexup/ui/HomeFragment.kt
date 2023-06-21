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
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val demoViewModel : DemoViewModel by viewModel()

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
            bundle.putInt("matakuliah_id",it.matakuliah_id)
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
            Dosen("DS1901","Joko, S.Kom",1),
            Dosen("DS1902","Ervan, M.Kom",2),
            Dosen("DS1903","Yance, S.Kom",3),
            Dosen("Ds1904","Ronal, M.Kom",4)

        )

        val mahasiswa = listOf<Mahasiswa>(
            Mahasiswa("2001","afif rifki",1),
            Mahasiswa("2002","fujia zaid",1),
            Mahasiswa("2003","rahman attaqi",1),
            Mahasiswa("2004","lutfi rahman",2),
            Mahasiswa("2005","hidayat taufik",2),
            Mahasiswa("2006","rohim farhan",2),
            Mahasiswa("2007","nabila jenanda",3),
            Mahasiswa("2008","farisa nurhayati",3),
            Mahasiswa("2009","zeini fatimah",4)

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