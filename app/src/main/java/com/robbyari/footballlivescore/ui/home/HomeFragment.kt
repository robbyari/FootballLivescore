package com.robbyari.footballlivescore.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.robbyari.core.data.Resource
import com.robbyari.core.ui.LastMatchAdapter
import com.robbyari.core.ui.MatchAdapter
import com.robbyari.footballlivescore.R
import com.robbyari.footballlivescore.databinding.FragmentHomeBinding
import com.robbyari.footballlivescore.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()

    private val matchAdapterToday = MatchAdapter()
    private val matchAdapterLast = LastMatchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilter()

        homeViewModel.filterTodayMatch.observe(viewLifecycleOwner) { match ->
            val isMatchDataEmpty = match.data.isNullOrEmpty()

            binding.tvTodayEmpty.visibility = if (isMatchDataEmpty) View.VISIBLE else View.GONE
            binding.bgTodayEmpty.visibility = if (isMatchDataEmpty) View.VISIBLE else View.GONE
            matchAdapterToday.setData(match.data ?: emptyList())
        }
        homeViewModel.filterMatch.observe(viewLifecycleOwner) { match ->
            matchAdapterLast.setData(match.data)
        }

        if (activity != null) {
            matchAdapterToday.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }
            matchAdapterLast.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.lastMatch.observe(viewLifecycleOwner) { match ->
                if (match != null) {
                    when (match) {
                        is Resource.Loading -> {
                            binding.progressbarLast.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.progressbarLast.visibility = View.GONE
                            matchAdapterLast.setData(match.data)
                        }

                        is Resource.Error -> {
                            binding.progressbarLast.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.oops),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            homeViewModel.todayMatch.observe(viewLifecycleOwner) { match ->
                if (match != null) {
                    when (match) {
                        is Resource.Loading -> {
                            binding.progressbarToday.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.progressbarToday.visibility = View.GONE
                            matchAdapterToday.setData(match.data)
                        }

                        is Resource.Error -> {
                            binding.progressbarToday.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.oops_today),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            with(binding.rvTodayMatch) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = matchAdapterToday
            }

            with(binding.rvLastMatch) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = matchAdapterLast
            }
        }
    }

    private fun setFilter() {
        binding.apply {
            val buttonId =
                arrayOf(btnPremier, btnSerieA, btnLaLiga, btnBundes, btnLeague1, btnBriLiga)
            val leagueId = arrayOf("152", "207", "302", "175", "168", "194")

            buttonId.forEachIndexed { index, button ->
                button.setOnClickListener {
                    homeViewModel.setLeagueId(leagueId[index])

                    buttonId.forEach { button ->
                        button.setBackgroundResource(R.drawable.bg_text)
                    }
                    button.setBackgroundResource(R.drawable.bg_text_second)
                }
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query.isNotEmpty()) {
                        homeViewModel.setSearchName(query)
                        homeViewModel.searchTeam.observe(viewLifecycleOwner) { searchResult ->
                            matchAdapterLast.setData(searchResult.data)
                        }

                        val inputMethodManager =
                            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)

                        scrollView.post {
                            binding.scrollView.smoothScrollTo(0, 1500)
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}