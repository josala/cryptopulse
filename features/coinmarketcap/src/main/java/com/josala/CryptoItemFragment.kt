package com.josala

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.josala.core.Router
import com.josala.core.model.Crypto
import com.josala.features.coinmarketcap.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CryptoItemFragment : Fragment() {

    private val cryptoItemViewModel by viewModel<CryptoItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crypto_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            val cryptoItem = bundle.getSerializable(Router.CRYPTO_ITEM_KEY) as Crypto
            with(cryptoItem) {
                view.findViewById<TextView>(R.id.crypto_title)?.text = name
                view.findViewById<TextView>(R.id.crypto_info)?.text = price
                view.findViewById<Button>(R.id.btn_save).setOnClickListener {
                    cryptoItemViewModel.saveCrypto(this)
                }
            }
        }
        cryptoItemViewModel.saveState().observe(viewLifecycleOwner, {
            when (it) {
                CryptoItemViewModel.SaveState.SAVED -> Toast.makeText(
                    context,
                    R.string.coin_saved,
                    Toast.LENGTH_LONG
                ).show()
                CryptoItemViewModel.SaveState.ERROR -> Toast.makeText(
                    context,
                    R.string.coin_save_error,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}