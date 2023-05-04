package com.robbyari.footballlivescore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.robbyari.footballlivescore.databinding.ActivityMainBinding
import com.robbyari.footballlivescore.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, HomeFragment())
                .commit()
            supportActionBar?.title = getString(R.string.home)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title = getString(R.string.home)
        when (item.itemId) {
            R.id.nav_home -> {
                fragment = HomeFragment()
                title = getString(R.string.home)
            }

            R.id.nav_favorite -> {
                try {
                    installFavoriteModule()
                } catch (e: Exception) {
                    Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
                }
            }
        }
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragment)
                .commit()
        }
        supportActionBar?.title = title

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun installFavoriteModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleFavorite = "favorite"
        if (splitInstallManager.installedModules.contains(moduleFavorite)) {
            moveToFavoriteActivity()
            Toast.makeText(this, getString(R.string.open_module), Toast.LENGTH_SHORT).show()
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleFavorite)
                .build()

            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        getString(R.string.success_installing_module),
                        Toast.LENGTH_SHORT
                    ).show()
                    moveToFavoriteActivity()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        getString(R.string.error_installing_module),
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun moveToFavoriteActivity() {
        val uri = Uri.parse("footballapp://favorite")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}