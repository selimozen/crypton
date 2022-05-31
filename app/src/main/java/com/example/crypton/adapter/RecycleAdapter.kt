package com.example.crypton.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crypton.R
import com.example.crypton.model.crytomodel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecycleAdapter(private val cryptoList : ArrayList<crytomodel>, private val listener : Listener) : RecyclerView.Adapter<RecycleAdapter.RowHolder>() {
    interface Listener{
        fun onItemClick(cryptomodel: crytomodel)
    }
    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(cryptomodel : crytomodel, colors : Array<String>, position: Int, listener: Listener){

            itemView.setOnClickListener {
                listener.onItemClick(cryptomodel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.text_name.text = cryptomodel.currency
            itemView.text_price.text = cryptomodel.price

        }



    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapter.RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position], colors, position, listener)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}