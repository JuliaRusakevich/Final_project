package com.gmail.juliarusakevich.user.repository;

import com.gmail.juliarusakevich.user.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
