package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.data.ApiFactory.apiService
import com.moskovsky.searchitspecialists.databinding.ItemCategoriesInfoBinding
import com.moskovsky.searchitspecialists.domain.Categories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesAdapter(
    private val categoriesList: List<Categories>,
    private val onItemClick: (Categories) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    inner class CategoriesViewHolder(private val binding: ItemCategoriesInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categories: Categories) {
            binding.tvCategories.text = categories.specialist
            binding.root.setOnClickListener { onItemClick(categories) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemCategoriesInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val spec = categoriesList[position]
        holder.bind(categoriesList[position])
        holder.itemView.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Log.d("TextBookAdapter", "Fetching books for grade: ${spec.specialist}")
                    val books = apiService.getUsersBySpecialist(spec.specialist)
                    Log.d("TextBookAdapter", "Books received: $books")
                    val fragment = CategoriesSpecialistsFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("books", ArrayList(books))
                    fragment.arguments = bundle
                    (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .addToBackStack(null)
                        .commit()
                } catch (e: Exception) {
                    Log.e("TextBookAdapter", "Error fetching books: ${e.message}")
                }
            }
        }
    }

    override fun getItemCount(): Int = categoriesList.size
}