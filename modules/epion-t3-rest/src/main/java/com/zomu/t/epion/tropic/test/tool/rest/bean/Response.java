package com.zomu.t.epion.tropic.test.tool.rest.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private int statusCode;

    private Map<String, List<String>> headers;

    //private Map<String, Cookie> cookies;

    private String body;

    private long receivedResponseAtMillis;

    private long sentRequestAtMillis;
}
