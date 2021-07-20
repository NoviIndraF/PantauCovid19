package com.nifcompany.pantaucovid19.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nifcompany.pantaucovid19.R
import com.nifcompany.pantaucovid19.core.databinding.ItemProvinceBinding
import com.nifcompany.pantaucovid19.core.domain.model.Province
import java.util.ArrayList

class ProvinceAdapter : RecyclerView.Adapter<ProvinceAdapter.ListViewHolder>() {

    private var listData = ArrayList<Province>()
    var onItemClick: ((Province) -> Unit)? = null

    fun setData(newListData: List<Province>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_province, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemProvinceBinding.bind(itemView)
        fun bind(data: Province) {
            with(binding) {
                TvItemProvince.text = data.name
                TvItemKasus.text = formatNumber(data.kasus)
                TvItemMeninggal.text = formatNumber(data.meninggal)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    private fun formatNumber(number : Int?): String {
        return String.format("%,d", number)
    }
}