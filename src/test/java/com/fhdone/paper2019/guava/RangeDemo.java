package com.fhdone.paper2019.guava;

import com.google.common.collect.Range;
import com.google.common.math.BigIntegerMath;
import com.google.common.math.IntMath;
import org.junit.Test;

import java.math.RoundingMode;

public class RangeDemo {

    @Test
    public void test1(){
        Range range0 = Range.closedOpen(0,4);//[0,4)
        Range range1 = Range.closedOpen(4,6);//[0,4)
        boolean isConnected = range0.isConnected(range1);
        System.out.println("isConnected:"+isConnected);

        int i0 = Integer.MAX_VALUE + 2;
        System.out.println(i0);

//        int i1 = IntMath.checkedAdd(Integer.MAX_VALUE, 2);

        System.out.println(IntMath.divide(7,3, RoundingMode.HALF_UP));


    }
}
