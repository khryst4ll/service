package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.userAddress FROM User u WHERE u.userStringId = :userId")
    Optional<String> findAddressByUserId(@Param("userId") String userId);

    Optional<User> findByUserStringId(String userStringId);
}