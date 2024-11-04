package com.github.kotlin_tryout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kotlin_tryout.databinding.ActivityRecyclerViewExampleBinding

class RecyclerViewExample : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecyclerViewExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val items =
            (1..40).map {
                Item("Item $it")
            }
        binding.recyclerView.adapter =
            MyAdapter(
                items,
            )
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
    }
}

data class Item(
    val text: String,
)

class MyAdapter(
    private val items: List<Item>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HEADER_VIEW_TYPE = 0
        const val ITEM_VIEW_TYPE = 1
    }

    class HeaderViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        val headerTextView: TextView = itemView.findViewById(R.id.header_text)
    }

    class ItemViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.item_text)
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) HEADER_VIEW_TYPE else ITEM_VIEW_TYPE

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        when (viewType) {
            HEADER_VIEW_TYPE -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_layout, parent, false))
            ITEM_VIEW_TYPE -> ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is HeaderViewHolder -> {
                // Bind data to the header view
                holder.headerTextView.text = "Header"
            }
            is ItemViewHolder -> {
                // Bind data to the item view
                val item = items[position - 1]
                holder.itemTextView.text = item.text
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1 // Add 1 for the header
    }
}
