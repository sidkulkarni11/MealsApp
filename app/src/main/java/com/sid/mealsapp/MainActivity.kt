package com.sid.mealsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Intents
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.brt.common.entity.AppError
import com.brt.common.entity.AppLoading
import com.brt.common.entity.AppSuccess
import com.sid.mealsapp.adapter.MealsAdapter
import com.sid.mealsapp.databinding.ActivityMainBinding
import com.sid.mealsapp.instructions.InstructionActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MealsViewModel by viewModels()

    @Inject
    lateinit var mealsAdapter: MealsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        viewModel.mealLiveData.observe(this) {
            it?.let {
                when (it) {
                    is AppError -> {
                        showProgress(false)
                    }

                    is AppLoading -> {
                        showProgress(true)
                    }

                    is AppSuccess -> {

                        if (binding.swiperefresh.isRefreshing)
                            binding.swiperefresh.isRefreshing = false

                        val list = it.data
                        if (list.isNotEmpty()) {
                            mealsAdapter.setData(list)
                        }

                        showProgress(false)

                    }
                }
            }
        }
    }

    private fun init() {
        binding.rvMeals.layoutManager = LinearLayoutManager(this)
        binding.rvMeals.adapter = mealsAdapter

        binding.swiperefresh.setOnRefreshListener {
            viewModel.fetchMeals()
        }


        lifecycleScope.launch(Dispatchers.IO) {

            mealsAdapter.onClickSharedFlow.collect {
                val instructionIntent = Intent(this@MainActivity,InstructionActivity::class.java)
                instructionIntent.putExtra(getString(R.string.meal_detail), it)
                startActivity(instructionIntent)
            }

        }
    }

    fun showProgress(loading: Boolean) {
        binding.progressView.visibility =
            (if (loading == true) View.VISIBLE else View.GONE)
    }
}
