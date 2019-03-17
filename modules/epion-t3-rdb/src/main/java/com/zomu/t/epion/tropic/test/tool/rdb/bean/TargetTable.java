package com.zomu.t.epion.tropic.test.tool.rdb.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TargetTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String table;

    private String query;


}
