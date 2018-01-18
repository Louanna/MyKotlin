package com.kotlin.sample.viewmodel

import android.arch.lifecycle.ViewModel
import com.kotlin.sample.persistence.address.Address
import com.kotlin.sample.persistence.address.IAddressDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by anna on 17/01/2018.
 */
class VMAddress(private val dataSource: IAddressDataSource) : ViewModel() {
    fun getAllAddress(): Flowable<Address> {
        return dataSource.getAllAddress()
    }

    fun insertAddress(): Completable {
        return Completable.fromAction {
            val address = Address("2", "street2", "state2", "city2", "216000")
            address.userId = "1"
            dataSource.insertAddress(address)
        }
    }
}