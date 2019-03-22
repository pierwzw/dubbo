package com.pier.service.callback;

/**
 * @auther zhongweiwu
 * @date 2019/3/22 17:28
 */
public interface CallbackService {
    void addListener(String key, CallbackListener listener);
}
