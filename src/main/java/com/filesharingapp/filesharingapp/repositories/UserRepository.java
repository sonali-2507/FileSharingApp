package com.filesharingapp.filesharingapp.repositories;

import com.filesharingapp.filesharingapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
