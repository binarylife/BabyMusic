package com.beifeng.babymusic.http;

import rx.functions.Func1;

/**
 * Created by bei on 2017/4/26.
 */

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> tHttpResult) {
        if (tHttpResult.isError()) {
            throw new ApiException("网络异常");
        }
        return tHttpResult.getResults();
    }
}
