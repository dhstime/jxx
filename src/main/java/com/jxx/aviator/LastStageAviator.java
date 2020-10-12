package com.jxx.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import javafx.application.Application;
import javafx.stage.Stage;
import org.openqa.selenium.opera.OperaOptions;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LastStageAviator  {

    public static void main(String[] args) throws Exception{
        AviatorEvaluator.getInstance().setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE);

        Expression compile = AviatorEvaluator.getInstance().compileScript("stageExamples/LastStage.av");
        BigDecimal amount = new BigDecimal("100.1");
        BigDecimal price = new BigDecimal("20.0");
        BigDecimal result = (BigDecimal) compile.execute(
                compile.newEnv("allNum",5,"invoiceNum",3,"nowNum",2,"amount",amount,"price",price));
        System.out.println("本次开票金额:"+result);

    }

}
