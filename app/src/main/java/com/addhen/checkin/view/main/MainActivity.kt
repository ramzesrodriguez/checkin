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

package com.addhen.checkin.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.MenuItem
import com.addhen.checkin.R
import com.addhen.checkin.databinding.MainActivityBinding
import com.addhen.checkin.view.Navigation
import com.addhen.checkin.view.disableShiftMode
import com.addhen.checkin.view.posts.PostsFragment
import com.addhen.checkin.view.snackbar
import com.firebase.ui.auth.ErrorCodes
import com.hellofresh.barcodescanner.presentation.view.base.BaseActivity


class MainActivity : BaseActivity<MainActivityViewModel, MainActivityBinding>(
    R.layout.main_activity,
    R.menu.menu_main,
    MainActivityViewModel::class.java) {

  private lateinit var postsFragment: Fragment
  private lateinit var postsMapFragment: Fragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.shouldSignInOrNot()
    setSupportActionBar(binding!!.toolbar)
    initView()
    initFragments(savedInstanceState)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.menu_user_profile -> {/*TODO navigate to user profile*/
      }
      else -> {
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onBackPressed() {
    if (switchFragment(postsFragment!!, PostsFragment.TAG)) {
      binding!!.bottomNavigation.menu.findItem(R.id.menu_list).isChecked = true
      binding!!.toolbar.title = getString(R.string.bottom_nav_menu_list)
      return
    }
    super.onBackPressed()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == Navigation.RC_SIGN_IN) {
      if (resultCode == RESULT_CANCELED) {
        binding.fragmentMainContent.snackbar(getString(R.string.sign_in_cancelled))
        finish()
        return
      }
      if (resultCode == ErrorCodes.NO_NETWORK) {
        binding.fragmentMainContent.snackbar(getString(R.string.exception_message_error_network))
        finish()
        return
      }
    }
  }

  private fun initView() {
    binding!!.bottomNavigation.disableShiftMode()
    binding!!.bottomNavigation.setOnNavigationItemSelectedListener({ item ->
      binding!!.title.text = item.title
      item.isChecked = true
      when (item.itemId) {
        R.id.menu_list -> switchFragment(postsFragment, PostsFragment.TAG)
        R.id.menu_map -> switchFragment(postsFragment, PostsFragment.TAG)
        else -> switchFragment(postsFragment!!, PostsFragment.TAG)
      }
      false
    })
  }

  private fun initFragments(savedInstanceState: Bundle?) {
    val manager = supportFragmentManager
    /*postsFragment = manager.findFragmentByTag(PostsFragment.TAG)
    postsMapFragment = manager.findFragmentByTag(PostsFragment.TAG)
    if (postsFragment == null) {
      postsFragment = PostsFragment.newInstance()
    }
    if (postsMapFragment == null) {
      postsMapFragment = PostsFragment.newInstance()
    }*/
    postsFragment = PostsFragment.newInstance()
    if (savedInstanceState == null) {
      switchFragment(postsFragment, PostsFragment.TAG)
    }
  }

  private fun switchFragment(@NonNull fragment: Fragment, @NonNull tag: String): Boolean {
    if (fragment.isAdded) {
      return false
    }
    val manager = supportFragmentManager
    val ft = manager.beginTransaction()
    val currentFragment = manager.findFragmentById(R.id.fragment_main_content)
    if (currentFragment != null) {
      ft.detach(currentFragment)
    }
    if (fragment.isDetached) {
      ft.attach(fragment)
    } else {
      ft.add(R.id.fragment_main_content, fragment, tag)
    }
    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    // NOTE: When this method is called by user's continuous hitting at the same time,
    // transactions are queued, so necessary to reflect commit instantly before next
    // transaction starts.
    manager.executePendingTransactions()
    return true
  }

  companion object {

    fun createIntent(context: Context): Intent {
      return Intent(context, MainActivity::class.java)
    }
  }
}
