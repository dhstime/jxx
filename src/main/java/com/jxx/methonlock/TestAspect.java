package com.jxx.methonlock;

import com.jxx.methonlock.annotations.SetNx;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

@Aspect
@Component
public class TestAspect {

    private static HashMap<String, Class> map = new HashMap<String, Class>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };
    @Pointcut("@annotation(com.jxx.methonlock.annotations.SetNx)")
    public void setnex(){}
    /**
    * 设置锁
    * @Author:strange
    * @Date:19:57 2019-12-29
    */
    @Around("setnex()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标方法的名称
        String methodName = joinPoint.getSignature().getName();
        Signature signature = joinPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        System.out.println("获取目标方法的名称:"+methodName);
        // 获取方法传入参数
        Object[] params = joinPoint.getArgs();
        System.out.println("获取方法传入参数:"+params);
        SetNx declaredAnnotation = getDeclaredAnnotation(joinPoint);
        String field = declaredAnnotation.field();
        field = captureName(field);
//        MethodSignature signature1= (MethodSignature) joinPoint.getSignature();

        int time = declaredAnnotation.time();

        Class aClass = declaredAnnotation.className();
        boolean flag = false;
        if(map.containsKey(aClass.getName())){
            flag = true;
        }
        if(params!=null && params.length >0 ){

            for (int i = 0; i < params.length; i++) {
                Object param = params[i];
                if(param.getClass().getName().equals(aClass.getName())) {
                    Method declaredMethod = param.getClass().getDeclaredMethod(field);
                    String id = (String) declaredMethod.invoke(param, null);
                    System.out.println("获取id:"+id);
                }
            }
        }
        System.out.println("field:"+field+",time:"+time);
        System.out.println("before");
        joinPoint.proceed();
        System.out.println("after");
    }
    /**
     * 获取方法中声明的注解
     *
     * @param joinPoint
     * @return
     * @throws NoSuchMethodException
     */
    public SetNx getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        SetNx annotation = objMethod.getDeclaredAnnotation(SetNx.class);
        // 返回
        return annotation;
    }
    /**
     * 将字符串的首字母转大写
     * @param str 需要转换的字符串
     * @return
     */
    private static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return "get"+String.valueOf(cs);
    }
    /**
     *获取有@MethodLockParam注解参数下标值
     * @Author:strange
     * @Date:17:36 2020-01-07
     */
    private Integer getparamsInedx(ProceedingJoinPoint joinPoint) {
        Integer paramsInedx = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            int i = ArrayUtils.indexOf(parameterAnnotations, parameterAnnotation);
            for (Annotation annotation : parameterAnnotation) {
                if(annotation instanceof com.jxx.methonlock.annotations.SetNxParam){
//                  Object param = params[i];
                    paramsInedx = i;
                }
            }
        }
        return paramsInedx;
    }
}
