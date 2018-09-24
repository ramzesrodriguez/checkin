/*
 * Copyright (c) 2015 - 2018 Henry Addo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.addhen.checkin.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.addhen.checkin.base.view.BaseActivity
import com.addhen.checkin.main.R
import com.addhen.checkin.main.databinding.MainActivityBinding
import com.addhen.checkin.main.extension.disableShiftMode

class MainActivity : BaseActivity<MainActivityViewModel, MainActivityBinding>(
    R.layout.main_activity,
    0,
    MainActivityViewModel::class.java) {

  private lateinit var postsFragment: Fragment
  private lateinit var postsMapFragment: Fragment
  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.shouldSignInOrNot()
    setSupportActionBar(binding.toolbar)
    initView()
  }

  override fun onSupportNavigateUp() = navController.navigateUp()

  private fun initView() {
    binding.bottomNavigation.disableShiftMode()
    navController = findNavController(R.id.main_nav_host_fragment)
    setupActionBarWithNavController(navController)
    binding.bottomNavigation.setupWithNavController(navController)
  }

  companion object {

    fun createIntent(context: Context): Intent {
      return Intent(context, MainActivity::class.java)
    }
  }
}
