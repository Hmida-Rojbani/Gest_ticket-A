package de.tekup.rst.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.rst.security.entites.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
