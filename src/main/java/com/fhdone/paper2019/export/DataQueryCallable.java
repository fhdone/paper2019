package com.fhdone.paper2019.export;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据分页查询
 */
public abstract class DataQueryCallable<V> implements Callable<V> {

    //保证返回顺序，和pageNum保持一致，处理后+1
    protected AtomicInteger token;
    
    protected Integer pageNum;

    @Override
    public V call() throws Exception {
        V list = this.queryPageData();
        while (token.get() != pageNum) {
            TimeUnit.MILLISECONDS.sleep(100);
        }
        return list;
    }
    
    public void afterProcess() {
    	token.incrementAndGet();
    }

    public void dataProcess(V v) {
    	this.afterProcess();
    }
    
    public abstract V queryPageData();
    

}