package com.rodrigo.billser.dummy

import com.rodrigo.billser.collections.BillserItem
import com.rodrigo.billser.collections.BillserTotal
import com.rodrigo.billser.collections.Category
import com.rodrigo.billser.collections.Type
import com.rodrigo.billser.core.BillsersRepository
import java.util.*

/**
 * Mock data with [BillserItem] for the collection.
 */
object MockBillsers : BillsersRepository {

  private val randomBillserList = listOf(
    BillserItem(
      id = UUID.randomUUID().toString(),
      category = Category.FOOD,
      value = 10.0,
      type = Type.OUTCOME,
      payed = false
    ),
    BillserItem(
      id = UUID.randomUUID().toString(),
      category = Category.FOOD,
      value = 10.0,
      type = Type.OUTCOME,
      payed = false
    ),
    BillserItem(
      id = UUID.randomUUID().toString(),
      category = Category.FOOD,
      value = 10.0,
      type = Type.OUTCOME,
      payed = false
    ),
    BillserItem(
      id = UUID.randomUUID().toString(),
      category = Category.FOOD,
      value = 10.0,
      type = Type.OUTCOME,
      payed = false
    ),
    BillserItem(
      id = UUID.randomUUID().toString(),
      category = Category.FOOD,
      value = 10.0,
      type = Type.OUTCOME,
      payed = false
    ),
    BillserItem(
      id = UUID.randomUUID().toString(),
      category = Category.FOOD,
      value = 10.0,
      type = Type.OUTCOME,
      payed = false
    )
  )

  private val billserItemLists: MutableList<BillserItem> = mutableListOf(
    BillserItem(
      id = UUID.randomUUID().toString(),
      category = Category.FOOD,
      value = 10.0,
      type = Type.OUTCOME,
      payed = false
    )
  )

  override fun getRandomHabit(): BillserItem {
    return randomHabit()
  }

  override fun fetchBillsers() = billserItemLists.map {
    it.copy()
  }

  override fun addRandomNewBillser() {
    billserItemLists.add(getRandomHabit())
    getRandomHabit()
  }

  override fun toggleHabitCompleted(id: String) {
    val habitIndex = findHabitIndexById(id)
    val habit = billserItemLists[habitIndex]
    billserItemLists[habitIndex] = habit.copy(payed = !habit.payed)
  }



  private fun randomHabit() = randomBillserList.random().copy(
    id = UUID.randomUUID().toString()
  )

  private fun findHabitIndexById(id: String) = billserItemLists.indexOfFirst { habitItem ->
    habitItem.id == id
  }
}
