package org.d3if3049.sportapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_sport.view.*
import org.d3if3049.sportapp.core.R
import org.d3if3049.sportapp.core.domain.model.Sport
import java.util.ArrayList

class SportAdapter : RecyclerView.Adapter<SportAdapter.ListViewHolder>() {

    private var listData = ArrayList<Sport>()
    var onItemClick: ((Sport) -> Unit)? = null

    fun setData(newListData: List<Sport>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_sport, parent, false))

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Sport) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(data.strSportThumb)
                    .into(iv_item_image)
                tv_item_title.text = data.strSport
                tv_item_subtitle.text = data.strFormat
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}