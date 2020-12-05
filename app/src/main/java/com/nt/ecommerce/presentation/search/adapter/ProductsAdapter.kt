package com.nt.ecommerce.presentation.search.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nt.ecommerce.R
import kotlinx.android.synthetic.main.product_list_item.view.*


class ProductsAdapter(
    private var queryString: String = ""

) : PagingDataAdapter<String, ProductsAdapter.ProductViewHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let { holder.bindProduct(it) }
    }

    fun setQueryStringValue(query: String){
        queryString = query
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.product_name

        fun bindProduct(product: String) {
            productNameTextView.text = product
            highlightSearchQueryInsideTextView(productNameTextView, queryString)
        }
        private fun highlightSearchQueryInsideTextView(searchTextView: TextView, query: String){
            val startIndex: Int = searchTextView.text.indexOf(query)
            val endIndex: Int = startIndex + query.length
            val wordToSpan: Spannable = SpannableString(searchTextView.text)
            wordToSpan.setSpan(
                ForegroundColorSpan(Color.BLUE),
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            searchTextView.text = wordToSpan
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}





