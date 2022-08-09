package com.gmail.juliarusakevich.users.repository;

import com.gmail.juliarusakevich.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<User, UUID> {

      Optional <User> findByMail(String mail);

}
