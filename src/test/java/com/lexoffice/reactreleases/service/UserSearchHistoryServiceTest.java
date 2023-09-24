package com.lexoffice.reactreleases.service;

import com.lexoffice.reactreleases.client.ReactReleasesClient;
import com.lexoffice.reactreleases.model.UserSearchHistory;
import com.lexoffice.reactreleases.repository.UserSearchHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserSearchHistoryServiceTest {

    @InjectMocks
    UserSearchHistoryService cut;

    @Mock
    UserSearchHistoryRepository userSearchHistoryRepository;

    @Test
    void findUserSearchHistoryTest() {
        UserSearchHistory userSearchHistory = new UserSearchHistory();
        userSearchHistory.setLoginUser("admin");

        when(userSearchHistoryRepository.findByLoginUser(anyString())).thenReturn(Optional.of(userSearchHistory));

        Optional<UserSearchHistory> response = cut.findUserSearchHistory("admin");

        assertThat(response).contains(userSearchHistory);
    }
}
