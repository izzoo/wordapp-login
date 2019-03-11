package com.example.application.repository;

import com.example.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserId(String userId);

    @Modifying
    @Query(value = "update users u set u.points = u.points + ? where u.user_id = ?",
            nativeQuery = true)
    int updateUserSetPoints(Integer points, String userId);

    @Query(value = "select u.user_name from users u where u.user_id = ?",
            nativeQuery = true)
    String findUserNameByUserId(String userId);


}
