package com.lexoffice.reactreleases.service;

import com.lexoffice.reactreleases.MockData;
import com.lexoffice.reactreleases.client.ReactReleasesClient;
import com.lexoffice.reactreleases.dto.ReactReleaseDto;
import com.lexoffice.reactreleases.dto.ReactReleasesRequest;
import com.lexoffice.reactreleases.model.UserSearchHistory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WithMockUser(username = "admin")
class ReactReleasesServiceTest {

    @InjectMocks
    ReactReleasesService cut;

    @Mock
    ReactReleasesClient reactReleasesClient;

    @ParameterizedTest
    @CsvSource({
        "           ,           ,    , 3",
        " 2022-01-01,           ,    , 2",
        " 2022-01-01, 2022-12-31,    , 1",
        "           ,           , sam, 2",
        " 2022-01-01, 2022-12-31, sam, 0",
    })
    void getReactReleaseByReactReleasesRequest(LocalDate startDate, LocalDate endDate, String author, int expectedListSize) {
        ReactReleasesRequest reactReleasesRequest = ReactReleasesRequest.builder()
                .startCreatedAt(startDate)
                .endCreatedAt(endDate)
                .author(author)
                .build();
        when(reactReleasesClient.getReactReleases()).thenReturn(MockData.getReactReleases());

        List<ReactReleaseDto> reactReleaseDtoList = cut.getReactRelease(reactReleasesRequest);

        assertThat(reactReleaseDtoList.size()).isEqualTo(expectedListSize);

    }

    @ParameterizedTest
    @CsvSource({
            "           ,           ,    , 3",
            " 2022-01-01,           ,    , 2",
            " 2022-01-01, 2022-12-31,    , 1",
            "           ,           , sam, 2",
            " 2022-01-01, 2022-12-31, sam, 0",
    })
    void getReactReleaseByUserSearchHistory(LocalDate startDate, LocalDate endDate, String author, int expectedListSize) {
        UserSearchHistory userSearchHistory = UserSearchHistory.builder()
                .loginUser("admin")
                .author(author)
                .startCreatedAt(startDate)
                .endCreatedAt(endDate)
                .build();
        when(reactReleasesClient.getReactReleases()).thenReturn(MockData.getReactReleases());

        List<ReactReleaseDto> reactReleaseDtoList = cut.getReactRelease(userSearchHistory);

        assertThat(reactReleaseDtoList.size()).isEqualTo(expectedListSize);

    }

}
