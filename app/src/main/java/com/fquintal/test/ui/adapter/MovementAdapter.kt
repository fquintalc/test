package com.fquintal.test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fquintal.model.Movement
import com.fquintal.test.R
import com.fquintal.test.databinding.AdapterMovementBinding

class MovementAdapter : RecyclerView.Adapter<MovementAdapter.Holder>() {

    val data = ArrayList<Movement>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_movement, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding : AdapterMovementBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        fun bind(movement: Movement){
            binding!!.movement = movement
        }

    }
}