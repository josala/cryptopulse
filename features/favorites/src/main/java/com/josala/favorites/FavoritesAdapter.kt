package com.josala.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.josala.core.model.Crypto

class FavoritesAdapter : RecyclerView.Adapter<FavoriteItemViewHolder>() {

    private var data = ArrayList<Crypto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.fav_item, parent, false)
        return FavoriteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteItemViewHolder, position: Int) {
        with(data[position]) {
            holder.setFavItemData(this)
        }
    }

    override fun getItemCount() = data.size

    fun setData(cryptoList: List<Crypto>) {
        data = ArrayList(cryptoList)
        notifyDataSetChanged()
    }
}

class FavoriteItemViewHolder(private val favItemView: View) : RecyclerView.ViewHolder(favItemView) {

    fun setFavItemData(cryptoItem: Crypto) {

        with(favItemView) {
            findViewById<TextView>(R.id.fav_name)?.text = cryptoItem.name
            findViewById<TextView>(R.id.fav_info)?.text = cryptoItem.price
        }
    }
}