package com.example.questapi.repositori

import com.example.questapi.apiservice.ServiceApiSiswa
import com.example.questapi.modeldata.DataSiswa

interface RepositoryDataSiswa {
    suspend fun getDataSiswa() : List<DataSiswa>

    suspend fun postDataSiswa(dataSiswa: DataSiswa) :retrofit2.Response<Void>

    suspend fun getDataSiswa(id: Int): DataSiswa

    suspend fun editDataSiswa(id: Int, dataSiswa: DataSiswa): retrofit2.Response<Void>

    suspend fun hapusDataSiswa(id: Int): retrofit2.Response<Void>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
): RepositoryDataSiswa{
    override suspend fun getDataSiswa() : List<DataSiswa> = serviceApiSiswa
        .getSiswa()
    override suspend fun postDataSiswa(dataSiswa: DataSiswa):retrofit2
    .Response<Void> = serviceApiSiswa.postSiswa(dataSiswa)
    override suspend fun getDataSiswa(id: Int): DataSiswa =
        serviceApiSiswa.getDataSiswa(id)
    override suspend fun editDataSiswa(id: Int, dataSiswa: DataSiswa): retrofit2.Response<Void> =
        serviceApiSiswa.editDataSiswa(id, dataSiswa)
    override suspend fun hapusDataSiswa(id: Int): retrofit2.Response<Void> =
        serviceApiSiswa.hapusDataSiswa(id)
}