package com.rodrigo.billser.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rodrigo.billser.databinding.BillserItemBinding

/**
 * RecyclerView adapter for displaying a list of Habits.
 *
 * The UI is based on the [BillserItemBinding].
 * We use the [BillserItem] as a model for the binding.
 */
class BillserListAdapter(
  private val viewModel: BillserListViewModel
) : RecyclerView.Adapter<BillserListAdapter.ViewHolder>() {

  private val asyncListDiffer: AsyncListDiffer<BillserItem> = AsyncListDiffer(this, DiffCallback)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding =  BillserItemBinding.inflate(layoutInflater, parent, false)
    return ViewHolder(binding, viewModel)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(asyncListDiffer.currentList[position])
  }

  override fun getItemCount(): Int = asyncListDiffer.currentList.size

  fun updateHabits(habits: List<BillserItem>) {
    asyncListDiffer.submitList(habits)
  }

  class ViewHolder(
    private val binding: BillserItemBinding,
    private val viewModel: BillserListViewModel
  ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(billser: BillserItem) {
      binding.categoryTextView.text = billser.category.category
      binding.valueTextView.text = billser.value.toString()
      binding.completeCheckBox.isChecked = billser.payed

      binding.completeCheckBox.setOnClickListener {
        viewModel.toggleHabitCompleted(billser.id)
      }
    }
  }

  object DiffCallback : DiffUtil.ItemCallback<BillserItem>() {

    override fun areItemsTheSame(oldItem: BillserItem, newItem: BillserItem): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BillserItem, newItem: BillserItem): Boolean {
      return oldItem.value == newItem.value
    }
  }
}
