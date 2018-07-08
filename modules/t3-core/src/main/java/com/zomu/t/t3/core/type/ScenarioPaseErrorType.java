package com.zomu.t.t3.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum ScenarioPaseErrorType {

    /**
     * 単純なYAML->Objectの変換時のエラー.
     */
    PARSE_ERROR,

    /**
     * BeanValidationによるチェックエラー.
     */
    VALIDATION_ERROR


}
