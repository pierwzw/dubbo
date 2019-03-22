package com.pier.service.impl;

import com.pier.bean.Person;
import com.pier.service.notify.Notify;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther zhongweiwu
 * @date 2019/3/22 18:15
 */
@Service("notify")
public class NotifyImpl implements Notify {

    private Logger log = Logger.getLogger(this.getClass());

    public Map<Integer, Person>    ret    = new HashMap<>();
    public Map<Integer, Throwable> errors = new HashMap<>();

    @Override
    public void onreturn(Person msg, Integer id) {
        log.info("onreturn:" + msg);
        ret.put(id, msg);
    }

    @Override
    public void onthrow(Throwable ex, Integer id) {
        errors.put(id, ex);
    }
}
