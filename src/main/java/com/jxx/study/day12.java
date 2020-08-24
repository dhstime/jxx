package com.jxx.study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Strange
 * @ClassName day12.java
 * @Description TODO
 * @createTime 2020年08月15日 21:19:00
 */
public class day12 {
    private static Object File;

    public static void main(String[] args) throws Exception {
        File  a = new File("/Users/dhs/Downloads/1.txt");
        System.out.println(a.length());
        System.out.println(a.exists());
        System.out.println(a.isFile());
        System.out.println(a.isDirectory());
        System.out.println(a.getName());
        System.out.println(a.getParent());
        System.out.println(a.getAbsoluteFile());

        a = new File("/Users/dhs/Downloads/1.txt");
        String[] str = a.list();
        System.out.println(Arrays.toString(str));
        File[] fs = a.listFiles();
        System.out.println(Arrays.toString(fs));

        FileOutputStream fileOutputStream = new FileOutputStream(a);
        fileOutputStream.write("123".getBytes());
        }
    }

