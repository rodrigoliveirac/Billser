package com.rodrigo.billser.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodrigo.billser.core.BillsersRepository

/**
 * @see [https://developer.android.com/topic/libraries/architecture/viewmodel]
 */
class BillserListViewModel(private val repository: BillsersRepository) : ViewModel() {

    /**
     * Mutable Live Data that initialize with the current list of saved Habits.
     */
    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(billserItemList = repository.fetchBillsers()))
    }

    private val uiStateTotal: MutableLiveData<UiSateTotal> by lazy {
        MutableLiveData<UiSateTotal>(UiSateTotal(total = 10.0))
    }

    /**
     * Expose the uiState as LiveData to UI.
     */
    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    /**
     * Expose the uiState as LiveData to UI.
     */
    fun stateOnceAndStreamTotal(): LiveData<UiSateTotal> {
        return uiStateTotal
    }

    /**
     * Toggle a Habit complete status.
     */
    fun toggleHabitCompleted(id: String) {
        repository.toggleHabitCompleted(id)
        refreshHabitList()
    }

    /**
     * Add new Random Habit.
     * TODO replace this by a addHabit() with data coming from a form.
     */
    fun addRandomHabit() {
        refreshTotalValue()
        repository.addRandomNewBillser()
        refreshHabitList()
    }

    private fun refreshTotalValue() {
        uiStateTotal.value?.let {
            uiStateTotal.value = it.copy(
                total = checkType(uiStateTotal.value?.total)
            )
        }

    }

    private fun checkType(value: Double?): Double? {
        return if (repository.getRandomHabit().type.type == "income")
            value?.plus(repository.getRandomHabit().value) else
            value?.minus(repository.getRandomHabit().value)
    }

    private fun refreshHabitList() {
        uiState.value?.let { currentUiState ->
            uiState.value = currentUiState.copy(
                billserItemList = repository.fetchBillsers()
            )
        }
    }

    /**
     * UI State containing every data needed to show Habits.
     */
    data class UiState(val billserItemList: List<BillserItem>)

    data class UiSateTotal(var total: Double?)

    /**
     * ViewModel Factory needed to provide Repository injection to ViewModel.
     */
    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: BillsersRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BillserListViewModel(repository) as T
        }
    }
}
