package com.example.gateway.exception;

import java.text.MessageFormat;

/**
 * Created by hrhrh on 2020/4/29 15:00
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        //String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, this.getMessage());
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        //String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, this.getMessage(), t);
    }
}
