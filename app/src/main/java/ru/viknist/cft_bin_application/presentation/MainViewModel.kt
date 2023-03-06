package ru.viknist.cft_bin_application.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.viknist.cft_bin_application.models.CardInfoModel
import ru.viknist.cft_bin_application.network.Api
import ru.viknist.cft_bin_application.network.ServiceBuilder

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val service = ServiceBuilder.buildService(Api::class.java)
    private val sharedPreferences =
        application.getSharedPreferences("history", Context.MODE_PRIVATE)

    private var binInfoMutableLiveData = MutableLiveData<CardInfoModel>()
    var binInfoLiveData: LiveData<CardInfoModel> = binInfoMutableLiveData
    private var historyListMutableLiveData = MutableLiveData<MutableList<String>>()
    var historyListLiveData: LiveData<MutableList<String>> = historyListMutableLiveData

    fun searchBin(id: String) {
        viewModelScope.launch {
            try {
                binInfoMutableLiveData.value = service.getCardInfo(id)
                historyListMutableLiveData.value?.add(id)
                sharedPreferences.edit()
                    .putStringSet("historySet", historyListMutableLiveData.value?.toSet()).apply()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    init {
        var set: Set<String> = setOf()
        set = sharedPreferences.getStringSet("historySet", set) as Set<String>
        historyListMutableLiveData.value = set.toMutableList()
    }

}