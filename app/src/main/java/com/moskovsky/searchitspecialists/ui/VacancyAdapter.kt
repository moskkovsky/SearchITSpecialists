package com.moskovsky.searchitspecialists.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moskovsky.searchitspecialists.databinding.ItemVacancyInfoBinding
import com.moskovsky.searchitspecialists.domain.Vacancy

class VacancyAdapter(private val vacancies: List<Vacancy>) : RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val binding = ItemVacancyInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    override fun getItemCount(): Int = vacancies.size

    inner class VacancyViewHolder(private val binding: ItemVacancyInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vacancy: Vacancy) {
            binding.tvProduct.text = if (vacancy.company) "Компания" else "Стартап"
            binding.tvFullExp.text = vacancy.workSchedule
            binding.tvSpec.text = vacancy.specialist
            binding.tvCity.text = vacancy.city
            binding.tvOffice.text = vacancy.employment_type
            binding.tvExpAge.text = vacancy.experience
            binding.tvCash.text = "120.000₽" // предполагаемое поле зарплаты
            binding.tvDescription.text = vacancy.description
        }
    }
}