package com.lexoffice.reactreleases.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssetsDto extends BaseReactReleaseDto{
    private String name;
    private String label;
    private UploaderDto uploader;
    private String content_type;
    private String state;
    private int size;
    private int download_count;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String browser_download_url;
}
