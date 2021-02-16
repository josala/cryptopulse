package com.josala

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.josala.features.coinmarketcap.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CryptoListFragment: Fragment() {

    private val cryptoListViewModel by viewModel<CryptoListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crypto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO list
        cryptoListViewModel.getCryptoList()
    }
}