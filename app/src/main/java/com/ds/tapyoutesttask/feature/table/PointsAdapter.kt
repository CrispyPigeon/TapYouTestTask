package com.ds.tapyoutesttask.feature.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ds.tapyoutesttask.R
import com.ds.tapyoutesttask.databinding.ItemPointBinding
import com.ds.tapyoutesttask.domain.model.Point

class PointsAdapter(private val points: List<Point>) :
    RecyclerView.Adapter<PointsAdapter.PointViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val binding = ItemPointBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PointViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(points[position])
    }

    override fun getItemCount(): Int = points.size

    class PointViewHolder(private val binding: ItemPointBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(point: Point) {
            binding.textViewX.text = String.format(
                binding.root.context.getString(R.string.table_item_x),
                point.x.toString()
            )
            binding.textViewY.text = String.format(
                binding.root.context.getString(R.string.table_item_y),
                point.y.toString()
            )
        }
    }
}
