package com.rodrigo.billser.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.rodrigo.billser.R
import com.rodrigo.billser.databinding.FragmentBillserListBinding
import com.rodrigo.billser.dummy.MockBillsers

/**
 * A [Fragment] that displays a list of habits.
 */
class BillserListFragment : Fragment() {

    private var _binding: FragmentBillserListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: BillserListAdapter

    private val viewModel: BillserListViewModel by activityViewModels {
        BillserListViewModel.Factory(MockBillsers)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = BillserListAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBillserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the adapter
        binding.habitRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.habitRecyclerView.adapter = adapter
        viewModel.stateOnceAndStreamTotal().observe(viewLifecycleOwner) {
            binding.totalValue.text = it.total.toString()
        }
        // Adding decorations to our recycler view
        addingDividerDecoration()

        // Observer UI State for changes.
        viewModel
            .stateOnceAndStream()
            .observe(viewLifecycleOwner) {
                bindUiState(it)
            }


    }

    /**
     * Bind UI State to View.
     *
     * Update list of habits according to updates.
     */
    private fun bindUiState(uiState: BillserListViewModel.UiState) {
        adapter.updateHabits(uiState.billserItemList)
    }

//    private fun bindUiStateTotal(uiState: BillserListViewModel.UiSateTotal) : BillserTotal {
//        return  uiState.total.toString()
//    }

    private fun addingDividerDecoration() {
        // Adding Line between items with MaterialDividerItemDecoration
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        // Adding the line at the end of the list
        divider.isLastItemDecorated = true

        val resources = requireContext().resources

        // Adding start spacing
        divider.dividerInsetStart = resources.getDimensionPixelSize(R.dimen.horizontal_margin)

        // Defining size of the line
        divider.dividerThickness = resources.getDimensionPixelSize(R.dimen.divider_height)
        divider.dividerColor = ContextCompat.getColor(requireContext(), R.color.primary_200)

        binding.habitRecyclerView.addItemDecoration(divider)
    }

    private fun addingDividerSpace() {
        binding.habitRecyclerView.addItemDecoration(BillserListItemDecoration(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
