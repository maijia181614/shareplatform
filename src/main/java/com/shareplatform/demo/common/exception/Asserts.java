package com.shareplatform.demo.common.exception;

import com.shareplatform.demo.common.api.IErrorCode;

public class Asserts {
    public static void fail(String message){throw new ApiException(message);}

    public static void fail(IErrorCode iErrorCode){throw new ApiException(iErrorCode);}
}
