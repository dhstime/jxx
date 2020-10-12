package com.jxx.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.jxx.common.MoneyUtil;

import java.util.Map;

/**
 * @author Strange
 * @ClassName FunctionAviator.java
 * @Description TODO
 * @createTime 2020年09月14日 17:03:00
 */
public class FunctionAviator {
    public static void main(String[] args) throws Exception{
        //注册函数
        //自定义函数
        AviatorEvaluator.addFunction(new AddFunction());
        System.out.println(AviatorEvaluator.execute("add1(1, 2)"));
        System.out.println(AviatorEvaluator.execute("add1(add1(1, 2), 100)"));
        //导入 java 方法
        AviatorEvaluator.addStaticFunctions("money", MoneyUtil.class);
        System.out.println(AviatorEvaluator.execute("money.toChinese('1124.1')"));
        //导入实例方法
        AviatorEvaluator.addInstanceFunctions("s", String.class);
        System.out.println(AviatorEvaluator.execute("s.indexOf('hello', 'l')"));
        //调用可变参数方法
        AviatorEvaluator.addStaticFunctions("test", FunctionAviator.class);
        System.out.println(AviatorEvaluator.execute("test.join(seq.array(java.lang.String, 'hello','dennis'))"));

        //批量导入方法和注解支持
        //默认的 namespace 是类名 StringUtils，因此就可以在表达式里这样用 StringUtils.isBlank('hello world')。
        //想要定制导入的 namespace 和范围可以使用  @Import(ns = "test", scopes = {ImportScope.Static}),ns 指定导入后的 namespace,scopes 指定导入的方法范围。
        //如果想忽略某个方法，可以对方法用 Ignore 标注：
        //同时可以用 Function 标注导入的函数名字，默认都是原来的方法名：
        AviatorEvaluator.importFunctions(MoneyUtil.class);
    }

    static class AddFunction extends AbstractFunction {
        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorDouble(left.doubleValue() + right.doubleValue());
        }

        @Override
        public String getName() {
            return "add1";
        }
    }
    public static String join(final String... args) {
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean wasFirst = true;
        for (int i = 0; i < args.length; i++) {
            if (wasFirst) {
                sb.append(args[i]);
                wasFirst = false;
            } else {
                sb.append(",").append(args[i]);
            }
        }
        return sb.toString();
    }
}
