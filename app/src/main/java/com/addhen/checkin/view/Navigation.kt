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

package com.addhen.checkin.view

import android.app.Activity
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.addhen.checkin.data.model.Post
import javax.inject.Inject

class Navigation @Inject constructor(activity: AppCompatActivity) {
  private val activity: Activity

  init {
    this.activity = activity
  }

  fun navigateToPostDetails(@NonNull post: Post,
                            @Nullable parentClass: Class<out Activity>) {
    //activity.startActivity(PostDetailsActivity.createIntent(activity, parentClass, post.key))
  }

  fun navigateToUserProfile() {
    //val intent = UserProfileActivity.createIntent(activity, MainActivity::class.java)
    //activity.startActivity(intent)
  }

  /* fun toLogin() {
     activity.startActivityForResult(AuthUI.getInstance()
         .createSignInIntentBuilder()
         .setLogo(R.mipmap.ic_launcher)
         .setAvailableProviders(Arrays.asList(AuthUI.IdpConfig.GoogleBuilder().build(),
             AuthUI.IdpConfig.EmailBuilder().build()))
         .setTheme(R.style.AppTheme_Login_Dark)
         .setIsSmartLockEnabled(!BuildConfig.DEBUG)
         .build(), RC_SIGN_IN)
   }*/

  companion object {
    const val RC_SIGN_IN = 123
  }
}
