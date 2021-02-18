package com.josala

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.josala.core.Router
import com.josala.core.model.Crypto
import com.josala.features.coinmarketcap.R

class CryptoItemFragment : Fragment() {

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
            }
        }
    }
}