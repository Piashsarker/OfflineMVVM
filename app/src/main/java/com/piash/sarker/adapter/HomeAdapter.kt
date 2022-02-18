package com.piash.sarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.piash.sarker.databinding.ItemImageListBinding
import com.piash.sarker.model.CommentEntity
import com.piash.sarker.model.Movie
import com.piash.sarker.ui.HomeFragmentDirections

class HomeAdapter : ListAdapter<CommentEntity, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlantViewHolder(
            ItemImageListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PlantViewHolder).bind(plant)
    }

    class PlantViewHolder(
        private val binding: ItemImageListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                val nav = view.findNavController()
                binding.photo?.let {
                    nav.navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(it.id.toString()))
                }
            }
        }


        fun bind(item: CommentEntity) {
            binding.apply {
                photo = item
                executePendingBindings()
            }
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<CommentEntity>() {

    override fun areItemsTheSame(oldItem: CommentEntity, newItem: CommentEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CommentEntity, newItem: CommentEntity): Boolean {
        return oldItem == newItem
    }
}