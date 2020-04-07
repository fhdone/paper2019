package com.fhdone.paper2019.graalvm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;


// https://www.ibm.com/developerworks/cn/java/j-use-graalvm-to-run-polyglot-apps/index.html

public class JsEvaluation {

    public static void main(String[] args) {
        try (Context context = Context.create()) {
            Value date = context.eval("js", "new Date().toString()");
            System.out.println(date.asString());

            Value function = context.eval("python", "print('Hello Python!')");
            System.out.println(function.asString());

            function = context.eval("python", "lambda x: x + 1");
            assert function.canExecute();
            int x = function.execute(41).asInt();
            assert x == 42;
            System.out.println(x);

        }
    }
}

