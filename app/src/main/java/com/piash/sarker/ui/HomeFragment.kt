package com.piash.sarker.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.piash.sarker.adapter.HomeAdapter
import com.piash.sarker.api.Status
import com.piash.sarker.databinding.HomeFragmentBinding
import com.piash.sarker.model.CommentEntity
import com.piash.sarker.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var adapter: HomeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.swipeRefresh.isRefreshing = true
        initUI(binding)
        return binding.root
    }

    private fun initUI(binding: HomeFragmentBinding) {
        adapter = HomeAdapter()
        binding.apply {
            recyclerView.adapter = adapter
            swipeRefresh.setOnRefreshListener {
                if (viewModel.photos.value != null)
                    swipeRefresh.isRefreshing = false
                else
                    viewModel.getImageList()
            }


            btnSubmit.setOnClickListener {
                val text = etComment.text.toString()
                if (text.isNotEmpty()) {
                    val comment = CommentEntity(text)
                    viewModel.addComment(requireContext(), comment)
                }
            }
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.helloData.observe(viewLifecycleOwner, {
            // Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })

        viewModel.comments.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> binding.swipeRefresh.isRefreshing = true
                Status.SUCCESS -> loadUI(it.data)
                Status.ERROR -> showErrorMessage(it.message)
            }
        })


    }

    private fun showErrorMessage(message: String?) {
        binding.swipeRefresh.isRefreshing = false
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun loadUI(data: List<CommentEntity>?) {
        binding.swipeRefresh.isRefreshing = false
        adapter.submitList(data)
    }


}
