package org.rodneyparshall.rightrx.repo;


import org.rodneyparshall.rightrx.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
