package com.example.bt_def

import android.service.autofill.FieldClassification.Match
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bt_def.databinding.ListItemBinding
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonDisposableHandle.parent

class ItemAdapter: ListAdapter<ListItem, ItemAdapter.MyHolder>(Comparator()) {
    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val b = ListItemBinding.bind(view)
        fun bind(item: ListItem) = with(b) {
            name.text = item.name
            mac.text = item.mac

        }
    }

    class Comparator :
        DiffUtil.ItemCallback<ListItem>() { // Передали, что будем сравнивать (ListItem)
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(getItem(position))
    }


}