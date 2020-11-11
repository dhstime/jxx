package com.jxx.test.JsonTest;


/**
 * @author Strange
 * @ClassName Json4.java
 * @Description TODO
 * @createTime 2020年10月21日 14:07:00
 */
public class Json4 {
    public static void main(String[] args) throws Exception {
        Process exec = Runtime.getRuntime().exec("wkhtmltopdf  http://172.16.3.128:8080/warehouse/warehousesout/printOutOrder.do?expressId=1240665&orderId=151638&bussinessType=496&bussinessNo=HC207408347&type_f=3&wms_client_key=f37f8e0b-afde-4532-a383-85badac42d49&wms_client_userName=gaosl   /Users/dhs/Downloads/HC207408347.pdf");
        String str="abdasdfwrgasdfasdfafweadfasdfasd";

    }
}
