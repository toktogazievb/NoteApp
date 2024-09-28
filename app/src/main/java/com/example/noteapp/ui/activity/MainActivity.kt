package com.example.noteapp.ui.activity

import android.os.Build
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.getBooleanExtra("request_notification_permission", false)) {
            requestNotificationPermission()
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
        if (sharedPreferences.isOnBoardingShowed) {
            navController.navigate(R.id.action_onBoardFragment_to_signUpFragment)
            if (sharedPreferences.isSignInShowed) {
                navController.navigate(R.id.action_signUpFragment_to_noteFragment)
            } else if (sharedPreferences.isSignInShowed) {
                navController.navigate(R.id.signUpFragment)
            }
        } else if (!sharedPreferences.isOnBoardingShowed) {
            navController.navigate(R.id.onBoardFragment)
        }
    }

    private fun requestNotificationPermission() {
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }}

    }
}