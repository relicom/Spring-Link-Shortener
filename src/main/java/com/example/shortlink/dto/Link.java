package com.example.shortlink.dto;

import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Link {

    private String url;
    private String customLink;

    @NotNull
    @Size(min = 11,max = 300)
    @URL
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Nullable
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,44}",message = "custom short link is not valid")
    public String getCustomLink() {
        return customLink;
    }

    public void setCustomLink(String customLink) {
        this.customLink = customLink;
    }

    @Override
    public String toString() {
        return "Link{" +
                "url='" + url + '\'' +
                ", customLink='" + customLink + '\'' +
                '}';
    }
}
