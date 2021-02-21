package com.josala.cryptopulse

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.josala.core.Router

class MainActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_coinmarket, R.id.navigation_defi, R.id.navigation_fav))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun navigateToCryptoDetail(crypto: com.josala.core.model.Crypto) {
        val navHostFragment = supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment?.let { navHostFragment ->
            val bundle = Bundle()
            bundle.putSerializable(Router.CRYPTO_ITEM_KEY, crypto)
            navHostFragment.navController.navigate(R.id.action_navigation_main_to_cryptoItemFragment, bundle)
        }
    }
}