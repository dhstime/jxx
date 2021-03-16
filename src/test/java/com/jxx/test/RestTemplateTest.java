package com.jxx.test;

import com.jxx.common.ResultInfo;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Strange
 * @ClassName RestTemplateTest.java
 * @Description TODO
 * @createTime 2021年01月10日 17:51:00
 */
public class RestTemplateTest {

    @Test
    public void test() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
//        ResultInfo resultInfo = restTemplate.getForObject("http://localhost:8090/index/get", ResultInfo.class);
//        System.out.println(resultInfo.toString());
        try {
            ResponseEntity<String> entity = restTemplate.getForEntity("https://file1.vedeng.com/upload/default/2020-08/06/image/1596683959000_7795_bf.png",String.class);
            HttpStatus statusCode = entity.getStatusCode();
            System.out.println(statusCode);
        }catch (Exception e){
            System.out.println("111");
            e.printStackTrace();
        }


//        ResultInfo resultInfo = entity.getBody();
//
//        ResponseEntity.BodyBuilder status = ResponseEntity.status(statusCode);
//        status.contentLength(100);
//        status.body("adajdsan");
//        ResponseEntity<Class<ResultInfo>> body = status.body(ResultInfo.class);
//        Class<ResultInfo> body1 = body.getBody();
//        System.out.println(body1.toString());
    }

    @Test
    public void exchangetest1(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/index/getId";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED,MediaType.ALL));
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("id","150019");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<ResultInfo> exchange = restTemplate.exchange(url, HttpMethod.POST, request, ResultInfo.class);
        ResultInfo body = exchange.getBody();
        System.out.println(body.toString());

    }

    @Test
    public void executeTest(){
        String domainAndUri = "http://file.ivedeng.com/file/display?resourceId=0cde84c7170014acb9ed734dc6bcf328".split("http://")[1];
        int domainIndex = domainAndUri.indexOf("/");
        String domain = domainAndUri.substring(0,domainIndex);
        String uri = domainAndUri.substring(domainIndex);

        System.out.println(domain);
        System.out.println(uri);
    }
}
