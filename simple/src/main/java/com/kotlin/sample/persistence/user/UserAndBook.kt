package com.kotlin.sample.persistence.user

import android.arch.persistence.room.Relation
import com.kotlin.sample.persistence.book.Book

/**
 * Created by anna on 17/01/2018.
 */
class UserAndBook {
    var id: Int = 0
    var name: String? = null

    @Relation(parentColumn = "id", entityColumn = "user_id")
    var books: List<Book>? = null
}