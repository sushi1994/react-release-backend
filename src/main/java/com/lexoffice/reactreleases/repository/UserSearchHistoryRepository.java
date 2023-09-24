package com.lexoffice.reactreleases.repository;

import com.lexoffice.reactreleases.model.UserSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSearchHistoryRepository extends JpaRepository<UserSearchHistory, String> {
    Optional<UserSearchHistory> findByLoginUser(String loginUser);
}
