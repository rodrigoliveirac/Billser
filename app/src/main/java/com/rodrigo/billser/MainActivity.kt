package com.rodrigo.billser

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.rodrigo.billser.dummy.MockBillsers
import com.rodrigo.billser.collections.BillserListViewModel
import com.rodrigo.billser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding

  private val viewModel: BillserListViewModel by viewModels {
    BillserListViewModel.Factory(MockBillsers)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)

    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)
    setupNavigation()

    binding.fab.setOnClickListener { _ ->
      viewModel.addRandomHabit()
    }
  }

  private fun setupNavigation() {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConfiguration)
        || super.onSupportNavigateUp()
  }
}
