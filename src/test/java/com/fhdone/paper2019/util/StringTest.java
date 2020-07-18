package com.fhdone.paper2019.util;

import com.fhdone.paper2019.service.StudentServiceTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class StringTest {

    private Logger logger = LogManager.getLogger(StringTest.class);

    private Map<String, Function<String, String>> checkResultDispatcher = new HashMap<>();

    private Map<String, Consumer<String>> checkResultDispatcher2 = new HashMap<>();


    @Before
    public void checkResultDispatcherInit() {
        checkResultDispatcher.put("校验1", order -> String.format("对%s执行业务逻辑1", order));
        checkResultDispatcher.put("校验2", order -> String.format("对%s执行业务逻辑2", order));
        checkResultDispatcher.put("校验3", order -> String.format("对%s执行业务逻辑3", order));
        checkResultDispatcher.put("校验4", order -> String.format("对%s执行业务逻辑4", order));
        checkResultDispatcher.put("校验5", order -> String.format("对%s执行业务逻辑5", order));
        checkResultDispatcher.put("校验6", order -> String.format("对%s执行业务逻辑6", order));
        checkResultDispatcher.put("校验7", order -> String.format("对%s执行业务逻辑7", order));
        checkResultDispatcher.put("校验8", order -> String.format("对%s执行业务逻辑8", order));
        checkResultDispatcher.put("校验9", order -> String.format("对%s执行业务逻辑9", order));

        checkResultDispatcher2.put("校验1", order -> {
            logger.info(String.format("对%s执行业务逻辑1", order));
        });
        checkResultDispatcher2.put("校验2", order -> {
            logger.info(String.format("对%s执行业务逻辑2", order));
        });
        checkResultDispatcher2.put("校验3", order -> {
            logger.info(String.format("对%s执行业务逻辑3", order));
        });
        checkResultDispatcher2.put("校验4", order -> {
            logger.info(String.format("对%s执行业务逻辑4", order));
        });
        checkResultDispatcher2.put("校验5", order -> {
            logger.info(String.format("对%s执行业务逻辑5", order));
        });

    }

    @Test
    public void test(){
        for(int i=0; i<10; i++){
            Function<String, String> result = checkResultDispatcher.get("校验"+i);
            if (result != null) {
                //执行这段表达式获得String类型的结果
                logger.info( result.apply("order"));
            }else {
                logger.info("不在处理的逻辑中返回业务错误");
            }
        }
    }

    @Test
    public void test2(){
        for(int i=0; i<10; i++){
            Consumer<String> result = checkResultDispatcher2.get("校验"+i);
            if (result != null) {
                //执行这段表达式获得String类型的结果
                result.accept("order");
            }else {
                logger.info("不在处理的逻辑中返回业务错误");
            }
        }
    }


}
