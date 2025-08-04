package com.ichin23.salbum.repositories;

import com.ichin23.salbum.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    User findByEmail(String email);

}
