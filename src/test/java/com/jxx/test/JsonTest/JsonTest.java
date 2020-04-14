package com.jxx.test.JsonTest;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jxx.crawler.model.ChnRegister;
import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class JsonTest {

//    @Test
    public void paseJosn() throws IOException {
        File file = new File("/Users/dhs/Downloads/data_3878.json");

        FileInputStream fis = new FileInputStream("/Users/dhs/Downloads/data_3878.json");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String json = br.readLine();
//        System.out.println(s);
        JSONArray jsonArray  = JSONUtil.parseArray(json);
        Iterator<Object> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            JSONArray attr = (JSONArray) next.get("attr");
            paseChileJson(attr);
            break;
        }
        br.close();
    }

    private void paseChileJson(JSONArray attr) {
        Iterator<Object> iterator1 = attr.iterator();
        while (iterator1.hasNext()) {
            JSONObject next = (JSONObject) iterator1.next();
            System.out.println(next);
        }
    }


}
