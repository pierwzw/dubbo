package com.pier.service.notify;

import com.pier.bean.Person;

/**
 * @auther zhongweiwu
 * @date 2019/3/22 18:14
 */
public interface Notify {
    void onreturn(Person msg, Integer id);
    void onthrow(Throwable ex, Integer id);
}
