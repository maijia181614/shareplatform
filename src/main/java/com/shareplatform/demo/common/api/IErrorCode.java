package com.shareplatform.demo.common.api;

/**
 * API返回对象错误码接口
 */
public interface IErrorCode {
    /**
     * 返回码
     * @return
     */
    long getCode();

    /**
     * 返回信息
     * @return
     */
    String getMessage();

}
