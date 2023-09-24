package com.lexoffice.reactreleases.controller;

import com.lexoffice.reactreleases.MockData;
import com.lexoffice.reactreleases.dto.ReactReleasesRequest;
import com.lexoffice.reactreleases.facade.ReactReleasesFacade;
import com.lexoffice.reactreleases.model.UserSearchHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReactReleaseController.class)
@WithMockUser(username = "admin")
class ReactReleaseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReactReleasesFacade reactReleasesFacade;

    @Test
    void getReactReleases() throws Exception {
        ReactReleasesRequest reactReleasesRequest = ReactReleasesRequest.builder().build();
        when(reactReleasesFacade.getReactRelease(reactReleasesRequest)).thenReturn(MockData.getReactReleases());

        mockMvc.perform(get("/reactRelease/releases").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getLastSearchAsXML() throws Exception {
        when(reactReleasesFacade.getLastSearchAsXML()).thenReturn(MockData.getReactReleases());

        mockMvc.perform(get("/reactRelease/lastSearch").contentType(MediaType.APPLICATION_XML))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
