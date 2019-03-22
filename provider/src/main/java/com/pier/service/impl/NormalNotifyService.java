package com.pier.service.impl;

import com.pier.bean.Person;
import com.pier.service.INotifyService;
import org.springframework.stereotype.Service;

/**
 * @auther zhongweiwu
 * @date 2019/3/22 18:07
 */
@Service("notifyService")
public class NormalNotifyService implements INotifyService {
    @Override
    public Person get(int age) {
        return new Person("charles`son", age);
    }
}
