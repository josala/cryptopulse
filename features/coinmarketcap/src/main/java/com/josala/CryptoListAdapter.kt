package com.josala

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.josala.domain.model.CryptoItem
import com.josala.features.coinmarketcap.R

interface CryptoItemClickListener {
    fun onCryptoItemClick(id: Int)
}

class CryptoListAdapter(private val cryptoItemClickListener: CryptoItemClickListener) :
    RecyclerView.Adapter<CryptoItemViewHolder>() {

    private var data = ArrayList<CryptoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.crypto_item, parent, false)
        return CryptoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoItemViewHolder, position: Int) {
        with(data[position]) {
            holder.setCryptoItemData(this, cryptoItemClickListener)
        }
    }

    override fun getItemCount() = data.size

    fun setData(cryptoList: List<CryptoItem>) {
        data = ArrayList(cryptoList)
        notifyDataSetChanged()
    }
}

class CryptoItemViewHolder(private val cryptoItemView: View) :
    RecyclerView.ViewHolder(cryptoItemView) {

    fun setCryptoItemData(
        cryptoItem: CryptoItem,
        cryptoItemClickListener: CryptoItemClickListener
    ) {
        with(cryptoItemView) {
            findViewById<TextView>(R.id.crypto_item_name)?.text = cryptoItem.name
            findViewById<TextView>(R.id.crypto_item_symbol)?.text = cryptoItem.symbol
            findViewById<TextView>(R.id.max_supply)?.text = "Max supply: ${cryptoItem.supply}"
            findViewById<TextView>(R.id.price)?.text = "${cryptoItem.price} USD"
            this.setOnClickListener {
                cryptoItemClickListener.onCryptoItemClick(cryptoItem.id)
            }
        }
    }
}