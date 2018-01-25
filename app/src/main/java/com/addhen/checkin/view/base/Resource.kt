package com.hellofresh.barcodescanner.presentation.view.base

class Resource<out T>(val status: Status = Status.LOADING, val message: String? = null,
    val data: T? = null) {
  
  companion object {
    
    fun <T> success(data: T): Resource<T> = Resource(Status.SUCCESS, null, data)
    
    fun <T> error(message: String): Resource<T> = Resource(Status.ERROR, message, null)
    
    fun <T> loading(): Resource<T> = Resource(message = null, data = null)
  }
  
  enum class Status {
    SUCCESS,
    ERROR,
    LOADING
  }
}
