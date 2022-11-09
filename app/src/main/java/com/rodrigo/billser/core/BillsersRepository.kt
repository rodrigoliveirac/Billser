package com.rodrigo.billser.core

import com.rodrigo.billser.collections.BillserItem
import com.rodrigo.billser.collections.BillserTotal

interface BillsersRepository {

  fun getRandomHabit(): BillserItem

  fun fetchBillsers(): List<BillserItem>

  fun addRandomNewBillser()

  fun toggleHabitCompleted(id: String)
}
