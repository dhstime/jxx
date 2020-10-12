package com.jxx.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import com.jxx.common.model.Order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Strange
 * @ClassName TestAviator.java
 * @Description TODO
 * @createTime 2020年09月07日 09:27:00
 */
public class TestAviator {
    public static void main(String[] args) throws Exception{
        AviatorEvaluator.getInstance()
                .setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE);
        //传入脚本语言方法
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/hello.av");
//        expression.execute();
//
//        Expression script = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');");
//        script.execute();
        //变量类型
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/type.av");
//        expression.execute();

        //条件语句
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/if.av");
//        expression.execute();
        //循环语句
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/for_range1.av");
//        expression.execute();
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/for_seq.av");
//        expression.execute();

        //Statement 语句和值
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/statement_value.av");
//        expression.execute();

        //异常处理
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/handle_exception.av");
//        expression.execute();

        //函数定义和调用
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/function.av");
//        expression.execute();
        //匿名函数 lambda
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/lambda.av");
//        expression.execute();

        //函数式一等公民
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/function_first_class.av");
//        expression.execute();

        //闭包 Closure
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/closure.av");
//        expression.execute();

        //闭包模拟 OOP
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/closure_opp.av");
//        expression.execute();

        //函数和Runnable,Callable (报错没成功)
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/runnable.av");
//        expression.execute();

        //入参,变量计算
//        String expression1 = "a-(b-c) ";
//        Expression compiledExp = AviatorEvaluator.compile(expression1);
////        AviatorEvaluator.validate(expression1);
//        // Execute with injected variables.
//        double result = (double) compiledExp.execute(compiledExp.newEnv("a",100.32, "b", 45, "c",-199.101));
//        System.out.println(result);

        //多行表达式和return 返回值
//        Expression exp = AviatorEvaluator.getInstance().compileScript("examples/if_return.av");
//        Object result = exp.execute(exp.newEnv("a", 9, "b", 1));
//        System.out.println("result: " + result);
//        result = exp.execute(exp.newEnv("a", 1, "b", 9));
//        System.out.println("result: " + result);

        //对象取值
        Order order1 = new Order();
        order1.setId(100);
        order1.setNum(0);
        Map<String,Object> map = new HashMap<>();
        map.put("foo",order1);
        System.out.println(AviatorEvaluator.execute("foo.id", map));

        //三步运算,入参判断
//        String aa = "name != nil ? ('hello,' + name) : 'who are u ?'";
//        Expression compile = AviatorEvaluator.compile(aa);
//        String s = (String) compile.execute();
//        System.out.println(s);
//        s = (String) compile.execute(compile.newEnv("name","da"));
//        System.out.println(s);

        //作用域
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/scope.av");
//        expression.execute();

        //创建对象和new
//        Expression expression = AviatorEvaluator.getInstance().compileScript("examples/new.av");
//        String execute = (String) expression.execute();
//        System.out.println(execute);
    }
}
