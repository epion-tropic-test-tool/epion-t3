package com.zomu.t.epion.tropic.test.tool.rest.bean;

import java.util.List;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.AssertCommandResult;

import lombok.Getter;
import lombok.Setter;

/**
 * AssertResponseBodyJsonコマンドの結果クラス.
 * 
 * @author 
 */
@Getter
@Setter
public class AssertResultResponseBodyJson extends AssertCommandResult {

	/**
	 * default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Json差分の結果.
	 */
	private List<JsonDiff> jsonDifflist;
	
}
