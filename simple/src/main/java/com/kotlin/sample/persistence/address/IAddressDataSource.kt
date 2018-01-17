package com.kotlin.sample.persistence.address

import io.reactivex.Flowable

/**
 * Created by anna on 17/01/2018.
 */
interface IAddressDataSource {

    fun getAddressById(id: String): Flowable<Address>

    fun getAllAddress(): Flowable<Address>

    fun insertAddress(address: Address)

    fun deleteAllAddress()
}