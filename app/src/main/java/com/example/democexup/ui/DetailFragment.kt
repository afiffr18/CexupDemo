package com.example.democexup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.and2t2.secondhand.common.Status
import com.example.democexup.R
import com.example.democexup.databinding.FragmentDetailBinding
import com.example.democexup.domain.repository.DemoRepository
import com.example.democexup.local.Dao.DatabaseDemo


class DetailFragment : Fragment() {
    private lateinit var detailAdapter: DetailAdapter

    private val demoRepository : DemoRepository by lazy { DemoRepository(DatabaseDemo.getInstance(requireContext())!!) }
    private val demoViewModel : DemoViewModel by lazy { DemoViewModel(demoRepository) }
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nid = arguments?.getString("nid")!!
        val nama = arguments?.getString("nama")!!
        val makulId = arguments?.getInt("matakuliah_id")!!

        binding.tvNid.text = nid
        binding.tvNamaDosen.text = nama

        initRecycler()
        getMatakuliahData(makulId)
    }

    private fun initRecycler(){
        detailAdapter = DetailAdapter()
        binding.rvDetail.apply {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun getMatakuliahData(id : Int){
        demoViewModel.getAllMahasiswa(id).observe(viewLifecycleOwner){
            when(it.status){
                Status.LOADING ->{

                }

                Status.SUCCESS ->{
                    it.data?.let { data -> detailAdapter.updateMahasiswa(data.component1().mahasiswa) }
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),"Gagal Memuat Data", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}