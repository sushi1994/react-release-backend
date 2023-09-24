package com.lexoffice.reactreleases.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lexoffice.reactreleases.MockData;
import com.lexoffice.reactreleases.dto.ReactReleaseDto;
import com.lexoffice.reactreleases.dto.ReactReleasesRequest;
import com.lexoffice.reactreleases.integration.PostgreSQLTestBase;
import com.lexoffice.reactreleases.model.UserSearchHistory;
import com.lexoffice.reactreleases.repository.UserSearchHistoryRepository;
import com.lexoffice.reactreleases.service.ReactReleasesService;
import org.hibernate.type.jackson.JacksonXmlFormatMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin")
public class ReactReleaseControllerIntegrationTest extends PostgreSQLTestBase {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReactReleasesService reactReleasesService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserSearchHistoryRepository userSearchHistoryRepository;

    @Test
    void getReactReleasesIntegration() throws Exception {
        ReactReleasesRequest reactReleasesRequest = ReactReleasesRequest.builder().build();
        when(reactReleasesService.getReactRelease(reactReleasesRequest)).thenReturn(MockData.getReactReleases());

        ResultActions resultActions = mockMvc.perform(get("/reactRelease/releases")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult mvcResult = resultActions.andReturn();

        ReactReleaseDto[] responses = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ReactReleaseDto[].class);

        assertThat(responses).isNotEmpty();
        assertThat(responses[0].getAuthor().getLogin()).isEqualTo("Otto");

        Optional<UserSearchHistory> userSearchHistory = userSearchHistoryRepository.findByLoginUser("admin");

        assertThat(userSearchHistory).isNotEmpty();
        assertThat(userSearchHistory.get().getLoginUser()).isEqualTo("admin");
        assertThat(userSearchHistory.get().getStartCreatedAt()).isNull();
        assertThat(userSearchHistory.get().getEndCreatedAt()).isNull();
        assertThat(userSearchHistory.get().getAuthor()).isNull();
    }

}
