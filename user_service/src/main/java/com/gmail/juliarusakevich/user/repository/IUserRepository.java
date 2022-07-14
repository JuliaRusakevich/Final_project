package com.gmail.juliarusakevich.user.repository;

import com.gmail.juliarusakevich.user.repository.model.UserCreate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserCreate, UUID> {

    UserCreate findByNick(String nick);

    UserCreate findByMail(String mail);
}
