package com.ac.demo;

import android.arch.lifecycle.LiveData;
import android.database.Cursor;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by anna on 2017/12/7.
 */

public class MyUserDao implements UserDao {
    @Override
    public void insertUsers(User... users) {

    }

    @Override
    public void insertBothUsers(User user1, User user2) {

    }

    @Override
    public void insertUsersAndFriends(User user, List<User> friends) {

    }

    @Override
    public void updateUsers(User... users) {

    }

    @Override
    public void deleteUsers(User... users) {

    }

    @Override
    public User[] loadAllUsers() {
        return new User[0];
    }

    @Override
    public List<User> findUsersBornBetweenDates(Date from, Date to) {
        return null;
    }

    @Override
    public User[] loadAllUsersOlderThan(int minAge) {
        return new User[0];
    }

    @Override
    public User[] loadAllUsersBetweenAges(int minAge, int maxAge) {
        return new User[0];
    }

    @Override
    public List<User> findUserWithName(String search) {
        return null;
    }

    @Override
    public List<NameTuple> loadFullName() {
        return null;
    }

    @Override
    public List<NameTuple> loadUsersFromRegions(List<String> regions) {
        return null;
    }

    @Override
    public LiveData<List<User>> loadUsersFromRegionsSync(List<String> regions) {
        return null;
    }

    @Override
    public Maybe<User> getUserByIdMaybe(String userId) {
        return null;
    }

    @Override
    public Single<User> getUserByIdSingle(String userId) {
        return null;
    }

    @Override
    public Flowable<User> getUserByIdFlowable(int id) {
        return null;
    }

    @Override
    public Cursor loadRawUsersOlderThan(int minAge) {
        return null;
    }

    @Override
    public List<Book> findBooksBorrowedByNameSync(String userName) {
        return null;
    }

    @Override
    public LiveData<List<UserPet>> loadUserAndPetNames() {
        return null;
    }
}
