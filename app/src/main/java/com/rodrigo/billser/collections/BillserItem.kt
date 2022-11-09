package com.rodrigo.billser.collections

/**
 * Habit Model representing an Item in a ListView.
 *
 * @param category the title of the habit
 * @param value whether the habit is checked or not
 */
data class BillserItem(
    val id: String,
    val category: Category,
    val value: Double,
    val type: Type,
    val payed: Boolean
)
data class BillserTotal(
    val total: Double? = 10.0,
//    val incomes: Double = 0.0,
//    val outcomes: Double = 0.0
)

enum class Type(val type: String) {
    INCOME("income"),
    OUTCOME("outcome")
}

enum class Category(val category: String) {
    FOOD("food"),
    ENTERTAINMENT("entertainment"),
    JOB("job")
}
