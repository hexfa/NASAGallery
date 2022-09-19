package com.nasagallery.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nasagallery.model.local.NASAPhotoItem
import com.nasagallery.model.local.NASAPhotos
import com.nasagallery.model.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "NasaItemViewModel"

@HiltViewModel
class NasaItemViewModel @Inject constructor(private val photoRepository: PhotoRepository) :
    BaseViewModel() {


    private var _photoItemList: MutableLiveData<NASAPhotos> = MutableLiveData()
    var mediaItem: MutableLiveData<NASAPhotoItem> = MutableLiveData()


    fun getPhoto(): LiveData<NASAPhotos> {
        photoRepository.getPhoto()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<NASAPhotos> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: NASAPhotos) {
                    _photoItemList.value = t
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError: " + e.message)
                }

            })

        return _photoItemList
    }
}
