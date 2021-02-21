package com.josala.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment: Fragment() {

    private val favoritesViewModel by viewModel<FavoritesViewModel>()
    private val favListAdapter = FavoritesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Setup list
        view.findViewById<RecyclerView>(R.id.fav_list)?.let { listView ->
            listView.layoutManager = LinearLayoutManager(context)
            listView.adapter = favListAdapter
        }
        //Fetch data
        favoritesViewModel.getSavedCryptos()
        //Observe live data
        favoritesViewModel.cryptoList().observe(viewLifecycleOwner, {
            favListAdapter.setData(it)
        })
    }
}