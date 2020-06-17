package com.jxx.compute;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public enum symbol {
    Article();

    public static final Integer ONE = 1;
//    public static Map<Enum,Integer> map=ImmutableMap.<Enum,Integer>builder().put(Article,ONE).build() ;
    public static Map<Enum,Integer> map= map = new HashMap<Enum,Integer>();
    static {
        map.put(Article,ONE);
    }



}
