package com.hellofresh.barcodescanner.presentation.di.scope

import javax.inject.Scope

/**
 * Activity linked dependencies scope. Used to provide dependencies linked to activities lifecycle.
 *
 * All dagger components that should live depending on the lifecycle of the activity should be
 * annotated with this scope.
 */
@Scope
@Retention
annotation class ActivityScope

