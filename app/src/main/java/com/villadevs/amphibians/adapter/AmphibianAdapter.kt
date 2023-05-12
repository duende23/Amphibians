package com.villadevs.amphibians.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.villadevs.amphibians.R
import com.villadevs.amphibians.databinding.ListViewItemBinding
import com.villadevs.amphibians.model.Amphibian

class AmphibianAdapter(private val onItemClicked: (Amphibian) -> Unit):
    ListAdapter<Amphibian, AmphibianAdapter.AmphibianViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmphibianViewHolder {
        return AmphibianViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AmphibianViewHolder, position: Int) {
        val currentAmphibian = getItem(position)
        holder.bind(currentAmphibian)

        holder.itemView.setOnClickListener {
            onItemClicked(currentAmphibian)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Amphibian>() {
        override fun areItemsTheSame(oldItem: Amphibian, newItem: Amphibian): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Amphibian, newItem: Amphibian): Boolean {
            return oldItem.type == newItem.type && oldItem.description == newItem.description
        }
    }



    class AmphibianViewHolder (private var binding: ListViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(amphibian: Amphibian) {
           /* binding.ivAmphibian.load(amphibian.imageResourceId.toUri().buildUpon().scheme("https").build()){
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }*/
            binding.tvAmphibianName.text = amphibian.name
            // Load the images into the ImageView using the Coil library.
            binding.ivAmphibian.setImageResource(R.drawable.ic_broken_image)

        }
    }

}