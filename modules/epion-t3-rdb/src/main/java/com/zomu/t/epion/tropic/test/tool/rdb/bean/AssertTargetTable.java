package com.zomu.t.epion.tropic.test.tool.rdb.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AssertTargetTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String table;

    private List<String> ignoreColumns = new ArrayList<>();
}
