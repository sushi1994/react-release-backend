package com.lexoffice.reactreleases.service;

import com.lexoffice.reactreleases.model.UserSearchHistory;
import com.lexoffice.reactreleases.repository.UserSearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSearchHistoryService {

    private final UserSearchHistoryRepository repository;

    public Optional<UserSearchHistory> findUserSearchHistory(String loginUser) {
        return repository.findByLoginUser(loginUser);
    }

    public void saveUserSearchHistory(UserSearchHistory userSearchHistory) {
        repository.save(userSearchHistory);
    }
}
