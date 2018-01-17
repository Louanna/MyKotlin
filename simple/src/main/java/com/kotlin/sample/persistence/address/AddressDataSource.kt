package com.kotlin.sample.persistence.address

import io.reactivex.Flowable

/**
 * Created by anna on 17/01/2018.
 */
class AddressDataSource : IAddressDataSource {

    private var mIAddressDao: IAddressDao

    constructor(iAddressDao: IAddressDao) {
        this.mIAddressDao = iAddressDao
    }

    override fun getAddressById(id: String): Flowable<Address> {
        return mIAddressDao.getAddressById(id)
    }

    override fun getAllAddress(): Flowable<Address> {
        return mIAddressDao.getAllAddress()
    }

    override fun insertAddress(address: Address) {
        mIAddressDao.insertAddress(address)
    }

    override fun deleteAllAddress() {
        mIAddressDao.deleteAllAddress()
    }
}