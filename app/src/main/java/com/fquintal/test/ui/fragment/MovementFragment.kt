package com.fquintal.test.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fquintal.test.R
import com.fquintal.test.databinding.FragmentMovementsBinding
import com.fquintal.test.ui.adapter.MovementAdapter
import com.fquintal.test.viewmodel.MovementFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException


@AndroidEntryPoint
class MovementFragment : Fragment(R.layout.fragment_movements) {

    private val viewModel: MovementFragmentViewModel by viewModels()
    private var snackBar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = DataBindingUtil.bind<FragmentMovementsBinding>(view)
        val adapter = MovementAdapter()
        binding!!.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.movements.observe(viewLifecycleOwner) {
            if (it != null && it.data.isNotEmpty()) {
                val data = viewModel.consumeData()
                var init = adapter.data.size

                adapter.data.addAll(data.data)
                viewModel.size = adapter.data.size
                adapter.notifyItemRangeInserted(init, init + data.data.size)
            }
        }
        viewModel.loadMovements()

        binding.username = viewModel.getEmail()

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadMovements()
                }
            }
        })

        snackBar = Snackbar.make(view, R.string.label_syncrhonizing, Snackbar.LENGTH_INDEFINITE)
        viewModel.synchronizing.observe(viewLifecycleOwner) {
            if (it == null) {
                snackBar!!.dismiss()
            } else {
                if (it) {
                    snackBar!!.show()
                } else {
                    snackBar!!.dismiss()
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {

                    is UnknownHostException -> {
                        Snackbar.make(
                            view,
                            R.string.error_connect_to_server,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}