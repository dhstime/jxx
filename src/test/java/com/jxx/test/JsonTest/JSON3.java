package com.jxx.test.JsonTest;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.google.gson.Gson;
import com.jxx.common.model.InventoryAdjustmentDetailDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Strange
 * @ClassName JSON3.java
 * @Description TODO
 * @createTime 2020年10月20日 17:24:00
 */
public class JSON3 {
    public static void main(String[] args) {
        JSONArray item = new JSONArray("[\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"1.0\",\n" +
                "            \"lotAtt01\":\"\",\n" +
                "            \"lotAtt02\":\"2099-12-31\",\n" +
                "            \"lotAtt03\":\"2020-10-05\",\n" +
                "            \"lotAtt04\":\"NO-LOT\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"PY207925180\",\n" +
                "            \"lotAtt11\":\"LT00012457\",\n" +
                "            \"sKU\":\"V111126\",\n" +
                "            \"toQty\":-2\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"10.0\",\n" +
                "            \"lotAtt01\":\"2019-10-10\",\n" +
                "            \"lotAtt02\":\"2022-10-09\",\n" +
                "            \"lotAtt03\":\"2020-09-01\",\n" +
                "            \"lotAtt04\":\"191010235\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è\u008B\u008Fæ¢°æ³¨å\u0087\u008620152020125\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280002\",\n" +
                "            \"lotAtt11\":\"LT00006662\",\n" +
                "            \"sKU\":\"V276660\",\n" +
                "            \"toQty\":-31\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"11.0\",\n" +
                "            \"lotAtt01\":\"2019-10-12\",\n" +
                "            \"lotAtt02\":\"2022-10-11\",\n" +
                "            \"lotAtt03\":\"2020-09-01\",\n" +
                "            \"lotAtt04\":\"191012235\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è\u008B\u008Fæ¢°æ³¨å\u0087\u008620152020125\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280002\",\n" +
                "            \"lotAtt11\":\"LT00006676\",\n" +
                "            \"sKU\":\"V276660\",\n" +
                "            \"toQty\":-100\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"12.0\",\n" +
                "            \"lotAtt01\":\"2020-01-04\",\n" +
                "            \"lotAtt02\":\"2023-01-03\",\n" +
                "            \"lotAtt03\":\"2020-09-01\",\n" +
                "            \"lotAtt04\":\"200104239\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è\u008B\u008Fæ¢°æ³¨å\u0087\u008620152020125\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280002\",\n" +
                "            \"lotAtt11\":\"LT00006668\",\n" +
                "            \"sKU\":\"V276660\",\n" +
                "            \"toQty\":-42\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"13.0\",\n" +
                "            \"lotAtt01\":\"2019-12-15\",\n" +
                "            \"lotAtt02\":\"2022-12-14\",\n" +
                "            \"lotAtt03\":\"2020-08-28\",\n" +
                "            \"lotAtt04\":\"1912\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è\u008B\u008Fé\u0095\u0087æ¢°å¤\u008720151024å\u008F·\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008270002\",\n" +
                "            \"lotAtt11\":\"LT00002262\",\n" +
                "            \"sKU\":\"V277445\",\n" +
                "            \"toQty\":-24\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"14.0\",\n" +
                "            \"lotAtt01\":\"2020-05-08\",\n" +
                "            \"lotAtt02\":\"2022-05-07\",\n" +
                "            \"lotAtt03\":\"2020-09-04\",\n" +
                "            \"lotAtt04\":\"20200508\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"VB207448123\",\n" +
                "            \"lotAtt11\":\"LT00007577\",\n" +
                "            \"sKU\":\"V278229\",\n" +
                "            \"toQty\":-1\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"15.0\",\n" +
                "            \"lotAtt01\":\"2020-05-04\",\n" +
                "            \"lotAtt02\":\"2022-05-03\",\n" +
                "            \"lotAtt03\":\"2020-09-30\",\n" +
                "            \"lotAtt04\":\"20200504\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è\u008B\u008Fæ\u009D¨æ¢°å¤\u008720150138\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"VB207448123\",\n" +
                "            \"lotAtt11\":\"LT00011852\",\n" +
                "            \"sKU\":\"V278233\",\n" +
                "            \"toQty\":-10\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"16.0\",\n" +
                "            \"lotAtt01\":\"2019-11-07\",\n" +
                "            \"lotAtt02\":\"2022-10-01\",\n" +
                "            \"lotAtt03\":\"2020-09-09\",\n" +
                "            \"lotAtt04\":\"20191107A5\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"å\u009B½æ¢°æ³¨å\u0087\u008620153152006\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280001\",\n" +
                "            \"lotAtt11\":\"LT00008232\",\n" +
                "            \"sKU\":\"V278259\",\n" +
                "            \"toQty\":-8\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"17.0\",\n" +
                "            \"lotAtt01\":\"2020-03-08\",\n" +
                "            \"lotAtt02\":\"2024-03-07\",\n" +
                "            \"lotAtt03\":\"2020-09-22\",\n" +
                "            \"lotAtt04\":\"20200308\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"VB207456443\",\n" +
                "            \"lotAtt11\":\"LT00010426\",\n" +
                "            \"sKU\":\"V300070\",\n" +
                "            \"toQty\":-13\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"18.0\",\n" +
                "            \"lotAtt01\":\"2020-02-01\",\n" +
                "            \"lotAtt02\":\"2099-12-31\",\n" +
                "            \"lotAtt03\":\"2020-08-28\",\n" +
                "            \"lotAtt04\":\"200201\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è\u008B\u008Fé\u0095\u0087æ¢°å¤\u008720170015å\u008F·\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008270002\",\n" +
                "            \"lotAtt11\":\"LT00002173\",\n" +
                "            \"sKU\":\"V500856\",\n" +
                "            \"toQty\":-1\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"19.0\",\n" +
                "            \"lotAtt01\":\"2020-09-17\",\n" +
                "            \"lotAtt02\":\"2023-08-01\",\n" +
                "            \"lotAtt03\":\"2020-10-09\",\n" +
                "            \"lotAtt04\":\"20200917A2\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"èµ£æ¢°æ³¨å\u0087\u008620152150184\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"VB207448464\",\n" +
                "            \"lotAtt11\":\"LT00013013\",\n" +
                "            \"sKU\":\"V503320\",\n" +
                "            \"toQty\":-1\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"2.0\",\n" +
                "            \"lotAtt01\":\"\",\n" +
                "            \"lotAtt02\":\"2028-01-28\",\n" +
                "            \"lotAtt03\":\"2020-09-22\",\n" +
                "            \"lotAtt04\":\"J188704G\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"SH207359135\",\n" +
                "            \"lotAtt11\":\"LT00010407\",\n" +
                "            \"sKU\":\"V135445\",\n" +
                "            \"toQty\":-6\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"20.0\",\n" +
                "            \"lotAtt01\":\"2020-05-01\",\n" +
                "            \"lotAtt02\":\"2021-11-01\",\n" +
                "            \"lotAtt03\":\"2020-08-30\",\n" +
                "            \"lotAtt04\":\"20200501\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è\u008B\u008Få¸¸æ¢°å¤\u0087201600108å\u008F·\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280001\",\n" +
                "            \"lotAtt11\":\"LT00002996\",\n" +
                "            \"sKU\":\"V504811\",\n" +
                "            \"toQty\":-1\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"21.0\",\n" +
                "            \"lotAtt01\":\"2020-05-31\",\n" +
                "            \"lotAtt02\":\"2021-11-30\",\n" +
                "            \"lotAtt03\":\"2020-08-31\",\n" +
                "            \"lotAtt04\":\"20205\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è\u008B\u008Få¸¸æ¢°å¤\u0087201600108å\u008F·\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280001\",\n" +
                "            \"lotAtt11\":\"LT00005459\",\n" +
                "            \"sKU\":\"V504811\",\n" +
                "            \"toQty\":-18\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"22.0\",\n" +
                "            \"lotAtt01\":\"2020-05-07\",\n" +
                "            \"lotAtt02\":\"2022-05-06\",\n" +
                "            \"lotAtt03\":\"2020-08-30\",\n" +
                "            \"lotAtt04\":\"20200507\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"å\u009B½æ¢°æ³¨å\u0087\u008620203140267\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280001\",\n" +
                "            \"lotAtt11\":\"LT00002900\",\n" +
                "            \"sKU\":\"V504956\",\n" +
                "            \"toQty\":-1\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"23.0\",\n" +
                "            \"lotAtt01\":\"2020-04-07\",\n" +
                "            \"lotAtt02\":\"2022-04-06\",\n" +
                "            \"lotAtt03\":\"2020-10-14\",\n" +
                "            \"lotAtt04\":\"016200402\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è±«æ¢°æ³¨å\u0087\u008620192140634\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"SH207352500\",\n" +
                "            \"lotAtt11\":\"LT00014116\",\n" +
                "            \"sKU\":\"V505022\",\n" +
                "            \"toQty\":-1550\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"3.0\",\n" +
                "            \"lotAtt01\":\"\",\n" +
                "            \"lotAtt02\":\"2027-09-28\",\n" +
                "            \"lotAtt03\":\"2020-10-05\",\n" +
                "            \"lotAtt04\":\"11852280\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"PY207925184\",\n" +
                "            \"lotAtt11\":\"LT00012493\",\n" +
                "            \"sKU\":\"V142699\",\n" +
                "            \"toQty\":-5\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"4.0\",\n" +
                "            \"lotAtt01\":\"2020-04-20\",\n" +
                "            \"lotAtt02\":\"2023-04-19\",\n" +
                "            \"lotAtt03\":\"2020-09-10\",\n" +
                "            \"lotAtt04\":\"20200420\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280002\",\n" +
                "            \"lotAtt11\":\"LT00008307\",\n" +
                "            \"sKU\":\"V271584\",\n" +
                "            \"toQty\":-60\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"5.0\",\n" +
                "            \"lotAtt01\":\"2020-09-04\",\n" +
                "            \"lotAtt02\":\"2022-09-03\",\n" +
                "            \"lotAtt03\":\"2020-09-30\",\n" +
                "            \"lotAtt04\":\"20200904\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"è\u008B\u008Fæ¢°æ³¨å\u0087\u008620162660921\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"VB207455696\",\n" +
                "            \"lotAtt11\":\"LT00011862\",\n" +
                "            \"sKU\":\"V274632\",\n" +
                "            \"toQty\":-1\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"6.0\",\n" +
                "            \"lotAtt01\":\"2020-08-25\",\n" +
                "            \"lotAtt02\":\"2022-08-24\",\n" +
                "            \"lotAtt03\":\"2020-10-14\",\n" +
                "            \"lotAtt04\":\"200825\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"VB207420764\",\n" +
                "            \"lotAtt11\":\"LT00014068\",\n" +
                "            \"sKU\":\"V274739\",\n" +
                "            \"toQty\":-1\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"7.0\",\n" +
                "            \"lotAtt01\":\"2020-08-01\",\n" +
                "            \"lotAtt02\":\"2022-08-01\",\n" +
                "            \"lotAtt03\":\"2020-08-30\",\n" +
                "            \"lotAtt04\":\"NO-LOT\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280002\",\n" +
                "            \"lotAtt11\":\"LT00003549\",\n" +
                "            \"sKU\":\"V274859\",\n" +
                "            \"toQty\":-1\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"8.0\",\n" +
                "            \"lotAtt01\":\"2019-08-15\",\n" +
                "            \"lotAtt02\":\"2021-08-14\",\n" +
                "            \"lotAtt03\":\"2020-09-09\",\n" +
                "            \"lotAtt04\":\"190815\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280002\",\n" +
                "            \"lotAtt11\":\"LT00008176\",\n" +
                "            \"sKU\":\"V275684\",\n" +
                "            \"toQty\":-2\n" +
                "        },\n" +
                "        {\n" +
                "            \"aDJLineNo\":\"9.0\",\n" +
                "            \"lotAtt01\":\"2020-07-21\",\n" +
                "            \"lotAtt02\":\"2022-07-20\",\n" +
                "            \"lotAtt03\":\"2020-09-01\",\n" +
                "            \"lotAtt04\":\"200721\",\n" +
                "            \"lotAtt05\":\"\",\n" +
                "            \"lotAtt06\":\"\",\n" +
                "            \"lotAtt07\":\"\",\n" +
                "            \"lotAtt08\":\"HG\",\n" +
                "            \"lotAtt09\":\"\",\n" +
                "            \"lotAtt10\":\"ASN2008280002\",\n" +
                "            \"lotAtt11\":\"LT00006400\",\n" +
                "            \"sKU\":\"V275699\",\n" +
                "            \"toQty\":-1\n" +
                "        }\n" +
                "    ]");
        List<InventoryAdjustmentDetailDto> result = new ArrayList<>();
        Gson gson = new Gson();
        Iterator<Object> iterator = item.iterator();
        while (iterator.hasNext()){
            JSONObject jsonObject = (JSONObject) iterator.next();
            String sKU = (String) jsonObject.get("sKU");
            String lotAtt11 = (String) jsonObject.get("lotAtt11");
            String lotAtt08 = (String) jsonObject.get("lotAtt08");
            System.out.println(sKU);
            System.out.println(lotAtt11);
            System.out.println(lotAtt08);
        }
    }
}