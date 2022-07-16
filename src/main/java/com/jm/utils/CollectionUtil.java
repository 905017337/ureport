package com.jm.utils;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CollectionUtil {


    public static Boolean  isEmpty(List list){
        if(null == list || list.size() == 0){
            return true;
        }
        return false;
    }


    public static Boolean  isNotEmpty(List list){
        if(null != list && list.size() != 0){
            return true;
        }
        return false;
    }


}
