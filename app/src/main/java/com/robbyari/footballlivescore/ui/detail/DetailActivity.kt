package com.robbyari.footballlivescore.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.robbyari.core.domain.model.Match
import com.robbyari.footballlivescore.R
import com.robbyari.footballlivescore.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.detail_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailMatch = intent.getParcelableExtra<Match>(EXTRA_DATA)
        showDetailMatch(detailMatch)
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailMatch(detailMatch: Match?) {
        detailMatch.let {
            binding.apply {
                tvLiga.text = detailMatch?.leagueName
                tvHomeName.text = detailMatch?.matchHometeamName
                tvAwayName.text = detailMatch?.matchAwayteamName
                tvDate.text = detailMatch?.matchDate
                tvTime.text = detailMatch?.matchTime + getString(R.string.wib)
                tvStadium.text = detailMatch?.matchStadium
                tvScoreHome.text =
                    detailMatch?.matchHometeamScore?.takeIf { it.trim().isNotEmpty() } ?: getString(
                        R.string.empty
                    )
                tvScoreAway.text =
                    detailMatch?.matchAwayteamScore?.takeIf { it.trim().isNotEmpty() } ?: getString(
                        R.string.empty
                    )
                tvMatchStatus.text =
                    detailMatch?.matchStatus?.takeIf { it.trim().isNotEmpty() }
                        ?: getString(R.string.not_started)
                tvFormationHome.text =
                    detailMatch?.matchHometeamSystem?.takeIf { it.trim().isNotEmpty() }
                        ?: getString(
                            R.string.empty
                        )
                tvFormationAway.text =
                    detailMatch?.matchAwayteamSystem?.takeIf { it.trim().isNotEmpty() }
                        ?: getString(
                            R.string.empty
                        )
                tvHalftimeHome.text =
                    detailMatch?.matchHometeamHalftimeScore?.takeIf { it.trim().isNotEmpty() }
                        ?: getString(
                            R.string.empty
                        )
                tvHalftimeAway.text =
                    detailMatch?.matchAwayteamHalftimeScore?.takeIf { it.trim().isNotEmpty() }
                        ?: getString(
                            R.string.empty
                        )
                tvFulltimeHome.text =
                    detailMatch?.matchHometeamFtScore?.takeIf { it.trim().isNotEmpty() }
                        ?: getString(
                            R.string.empty
                        )
                tvFulltimeAway.text =
                    detailMatch?.matchAwayteamFtScore?.takeIf { it.trim().isNotEmpty() }
                        ?: getString(
                            R.string.empty
                        )
                tvRoundHome.text = detailMatch?.matchRound
                tvRoundAway.text = detailMatch?.matchRound
                tvYearHome.text = detailMatch?.leagueYear
                tvYearAway.text = detailMatch?.leagueYear
                tvReferee.text =
                    detailMatch?.matchReferee?.takeIf { it.trim().isNotEmpty() }
                        ?: getString(R.string.no_yet)
                Glide.with(this@DetailActivity)
                    .load(detailMatch?.teamHomeBadge)
                    .into(imgHome)
                Glide.with(this@DetailActivity)
                    .load(detailMatch?.teamAwayBadge)
                    .into(imgAway)
                Glide.with(this@DetailActivity)
                    .load(detailMatch?.leagueLogo)
                    .into(imgLiga)

                var statusFavorite = detailMatch?.isFavorite
                setStatusFavorite(statusFavorite)
                btnFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite!!
                    detailViewModel.setFavoriteMatch(detailMatch, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }

            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean?) {
        if (statusFavorite == true) {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bookmark_full
                )
            )
        } else {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bookmark_border
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}