package com.example.shortlink.dto;

import com.example.shortlink.util.Couple;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class LinkValidation {

    private String url;
    private String shortLink;
    private LinkValidationStatus linkValidationStatus;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public LinkValidationStatus getLinkValidationStatus() {
        return linkValidationStatus;
    }

    public void setLinkValidationStatus(LinkValidationStatus linkValidationStatus) {
        this.linkValidationStatus = linkValidationStatus;
    }

    @Override
    public String toString() {
        return "LinkValidation{" +
                "url='" + url + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", linkValidationStatus=" + linkValidationStatus +
                '}';
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode linkValidationNode = mapper.createObjectNode();

        linkValidationNode.put("url", url);
        linkValidationNode.put("shortLink", shortLink);

        String linkValidationJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(linkValidationNode);
        String statusJson = linkValidationStatus.toJson();

        String resultJson =
                linkValidationJsonString.substring(0, linkValidationJsonString.lastIndexOf("}"))
                        + ',' +
                        statusJson.substring(statusJson.indexOf('{')+1);

        return resultJson;
    }

}
