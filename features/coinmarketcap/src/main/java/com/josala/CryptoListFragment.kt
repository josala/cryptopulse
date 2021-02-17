package com.josala

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josala.features.coinmarketcap.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CryptoListFragment: Fragment(), CryptoItemClickListener {

    private val cryptoListViewModel by viewModel<CryptoListViewModel>()
    private val cryptoListAdapter = CryptoListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crypto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Setup views
        view.findViewById<RecyclerView>(R.id.crypto_list)?.let { listView ->
            listView.layoutManager = LinearLayoutManager(context)
            listView.adapter = cryptoListAdapter
        }
        //Fetch data
        cryptoListViewModel.getCryptoList()
        //Observe live data
        cryptoListViewModel.cryptoList().observe(viewLifecycleOwner, {
            cryptoListAdapter.setData(it)
        })
        cryptoListViewModel.loadingState().observe(viewLifecycleOwner, {
            view.findViewById<ProgressBar>(R.id.list_loader)?.let { progressBar ->
                when(it) {
                    CryptoListViewModel.LoadingState.IN_PROGRESS -> progressBar.visibility = VISIBLE
                    CryptoListViewModel.LoadingState.LOADED -> progressBar.visibility = GONE
                    CryptoListViewModel.LoadingState.ERROR -> {
                        progressBar.visibility = GONE
                        Toast.makeText(context, R.string.coin_list_error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    override fun onCryptoItemClick(id: Int) {
        TODO("Not yet implemented")
    }
}