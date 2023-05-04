package com.robbyari.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.robbyari.core.ui.MatchAdapter
import com.robbyari.favorite.databinding.ActivityFavoriteBinding
import com.robbyari.footballlivescore.ui.detail.DetailActivity
import com.robbyari.footballlivescore.ui.di.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(com.robbyari.footballlivescore.R.string.favorite)

        val matchAdapter = MatchAdapter()
        matchAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMatch.observe(this) { dataMatch ->
            matchAdapter.setData(dataMatch)
            binding.viewEmpty.root.visibility =
                if (dataMatch.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvMatch) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = matchAdapter
        }

    }


}