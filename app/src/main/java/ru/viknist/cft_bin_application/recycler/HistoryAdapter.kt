package ru.viknist.cft_bin_application.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.viknist.cft_bin_application.R

class HistoryAdapter(
    Context: Context, private val bins: List<String>,
    private val clickListener: (position: Int) -> Unit
) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(Context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_layout, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = bins.size

    private fun getItem(position: Int): String = bins[position]

    class ViewHolder(
        itemView: View,
        listener: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.findViewById(R.id.binTextView)

        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }

        fun bind(bin: String) {
            text.text = bin
        }
    }
}