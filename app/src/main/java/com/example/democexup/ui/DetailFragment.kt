package com.example.democexup.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.and2t2.secondhand.common.Status
import com.example.democexup.R
import com.example.democexup.databinding.FragmentDetailBinding
import com.example.democexup.domain.repository.DemoRepository
import com.example.democexup.local.Dao.DatabaseDemo
import com.example.democexup.local.Entities.Mahasiswa
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale


class DetailFragment : Fragment() {
    private lateinit var detailAdapter: DetailAdapter
    lateinit var courseList: ArrayList<Mahasiswa>
    private val demoViewModel : DemoViewModel by viewModel()
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
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

        courseList = ArrayList()
        val nid = arguments?.getString("nid")!!
        val nama = arguments?.getString("nama")!!
        val makulId = arguments?.getInt("matakuliah_id")!!

        binding.tvNid.text = nid
        binding.tvNamaDosen.text = nama

        initRecycler()
        getMatakuliahData(makulId)
        updateAdapter()
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
                    it.data?.let { data ->
                        updateData(data.component1().mahasiswa)
                    }
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),"Gagal Memuat Data", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateData(list : List<Mahasiswa>){
        detailAdapter.updateMahasiswa(list)
        Handler(Looper.getMainLooper()).postDelayed({
            courseList.addAll(list)
        },500)
    }
    private fun updateAdapter(){
        detailAdapter.updateMahasiswa(courseList)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // below line is to get our inflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.getActionView() as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(msg)
                return false
            }
        })
    }


    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<Mahasiswa> = ArrayList()

        // running a for loop to compare elements.
        for (item in courseList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.nama.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT)) || item.nim.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT)) ) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            detailAdapter.updateMahasiswa(filteredlist)
        }
    }

}