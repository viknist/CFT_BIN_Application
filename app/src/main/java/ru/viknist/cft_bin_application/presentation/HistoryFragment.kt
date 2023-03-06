package ru.viknist.cft_bin_application.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.viknist.cft_bin_application.R
import ru.viknist.cft_bin_application.recycler.HistoryAdapter

class HistoryFragment : DialogFragment() {

    var list: MutableList<String> = mutableListOf()
    lateinit var historyAdapter: HistoryAdapter
    lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.dialog_recycler_fragment, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        historyAdapter = HistoryAdapter(requireContext(), list) { position ->
            viewModel.searchBin(list[position])
            this.dismiss()
        }
        recyclerView.adapter = historyAdapter
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        decoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.color.black)!!)
        recyclerView.addItemDecoration(decoration)

        this.dialog?.setTitle("BIN search history")
        return rootView
    }

}