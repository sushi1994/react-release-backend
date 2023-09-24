package com.lexoffice.reactreleases.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReactReleaseDto extends BaseReactReleaseDto{
    private String assets_url;
    private String upload_url;
    private String html_url;
    private AuthorDto author;
    private String tag_name;
    private String target_commitish;
    private String name;
    private boolean draft;
    private boolean prerelease;
    private LocalDateTime created_at;
    private LocalDateTime published_at;
    private List<AssetsDto> assets;
    private String tarball_url;
    private String zipball_url;
    private String body;
    private ReactionsDto reactions;
}
