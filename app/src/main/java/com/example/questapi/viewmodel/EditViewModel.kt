package com.example.questapi.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi.modeldata.DetailSiswa
import com.example.questapi.modeldata.UIStateSiswa
import com.example.questapi.modeldata.toDataSiswa
import com.example.questapi.modeldata.toUiStateSiswa
import com.example.questapi.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch
import retrofit2.Response
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.questapi.uicontroller.route.DestinasiEdit

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiEdit.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateSiswa = repositoryDataSiswa
                .getDataSiswa(idSiswa)
                .toUiStateSiswa(true)
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    private fun validasiInput(
        uiState: DetailSiswa = uiStateSiswa.detailSiswa
    ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telepon.isNotBlank()
        }
    }

    suspend fun editDataSiswa() {
        if (validasiInput(uiStateSiswa.detailSiswa)) {
            val call: Response<Void> =
                repositoryDataSiswa.editDataSiswa(
                    idSiswa,
                    uiStateSiswa.detailSiswa.toDataSiswa()
                )

            if (call.isSuccessful) {
                println("Update Sukses : ${call.message()}")
            } else {
                println("Update Error : ${call.errorBody()}")
            }
        }
    }
}
