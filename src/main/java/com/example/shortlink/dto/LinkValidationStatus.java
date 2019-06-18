package com.example.shortlink.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public enum LinkValidationStatus {

    FREE(1,"custom link is free for registration"),
    EXIST_AND_CHOOSE_ANOTHER(2,"custom link is exist ,you could to check another one"),
    EXIST_AND_NEW(3,"custom link is exist ,new short link is offered ,you could to check another one"),
    NEW(4,"new short link is offered");

    private final int statusCode;
    private final String statusMsg;
    private LinkValidationStatus(int statusCode,String statusMsg){
        this.statusCode=statusCode;
        this.statusMsg=statusMsg;
    }

    @Override
    public String toString() {
        return "( LinkValidationStatus{" +
                "statusCode=" + statusCode +
                ", statusMsg='" + statusMsg + '\'' +
                "} )";
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode rootNode = mapper.createObjectNode();
        ObjectNode nestedNode = mapper.createObjectNode();

        nestedNode.put("code", this.statusCode);
        nestedNode.put("msg", this.statusMsg);
        rootNode.set("status",nestedNode);

        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);

        return jsonString;
    }
}
