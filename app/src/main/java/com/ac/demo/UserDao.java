package com.ac.demo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by anna on 2017/11/29.
 */

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    @Insert
    void insertBothUsers(User user1, User user2);

    @Insert
    void insertUsersAndFriends(User user, List<User> friends);

    @Update
    void updateUsers(User... users);

    @Delete
    void deleteUsers(User... users);

    @Query("SELECT * FROM user")
    User[] loadAllUsers();

    @Query("SELECT * FROM user WHERE birthday BETWEEN :from AND :to")
    List<User> findUsersBornBetweenDates(Date from, Date to);

    @Query("SELECT * FROM user WHERE age > :minAge")
    User[] loadAllUsersOlderThan(int minAge);

    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    User[] loadAllUsersBetweenAges(int minAge, int maxAge);

    @Query("SELECT * FROM user WHERE first_name LIKE :search "
            + "OR last_name LIKE :search")
    List<User> findUserWithName(String search);

    @Query("SELECT first_name, last_name FROM user")
    List<NameTuple> loadFullName();

    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    List<NameTuple> loadUsersFromRegions(List<String> regions);

    @Query("SELECT * FROM user WHERE region IN (:regions)")
    LiveData<List<User>> loadUsersFromRegionsSync(List<String> regions);


    @Query("SELECT * FROM user WHERE id = :userId")
    Maybe<User> getUserByIdMaybe(String userId);

    @Query("SELECT * FROM user WHERE id = :userId")
    Single<User> getUserByIdSingle(String userId);

    @Query("SELECT * from user where id = :id LIMIT 1")
    Flowable<User> getUserByIdFlowable(int id);


    @Query("SELECT * FROM user WHERE age > :minAge LIMIT 5")
    Cursor loadRawUsersOlderThan(int minAge);

    @Query("SELECT book.* FROM book "
            + "INNER JOIN user ON user.id = book.user_id "
            + "WHERE user.first_name LIKE :userName")
    List<Book> findBooksBorrowedByNameSync(String userName);

    @Query("SELECT user.first_name AS userName, book.title AS petName "
            + "FROM user, book "
            + "WHERE user.id = book.user_id")
    LiveData<List<UserPet>> loadUserAndPetNames();

    // You can also define this class in a separate file, as long as you add the
    // "" access modifier.
    static class UserPet {
        String userName;
        String petName;
    }
}

