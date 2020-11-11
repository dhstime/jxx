package com.jxx.sqlTest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
/**
 * @author Strange
 * @ClassName SkuImport.java
 * @Description TODO
 * @createTime 2020年11月11日 09:44:00
 */
public class SkuImport {
    static  DataSource origin = SqlUtils.datasourc("jdbc:mysql://192.168.1.53:3306/erp_ivedeng_com?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true", "", "=", "com.mysql.jdbc.Driver","98");
    static Dao dao = Dao.getInstance();
    public static void main(String[] args) throws IOException, SQLException {
        List<String>  list= Files.readAllLines(Paths.get("E:\\idea\\EI\\src\\main\\java\\goods\\update0817.txt"));
        Set<String> skuNos=new HashSet<>(   );
        List<String> skuNos2=new ArrayList<>(   );
        Set<String> modelSet=new HashSet<>(   );
        Set<String> specSet=new HashSet<>();
        Set<String> brandSet=new HashSet<>();
        Set<String> mSet=new HashSet<>();
        Set<String> aSet=new HashSet<>();
        Set<String> ERRORCATE=new HashSet<>(   );
        Set<String> errorLines=new HashSet<>(   );
        for(String line:list){

            boolean flag=true;
            String[] array1=line.split("\t");//StringUtils.split(line,"\t");
            if(array1.length<13){

                System.out.println(line);
            }else{
                //continue;
            }
            String skuNo=array1[0];
            String skuName=StringUtils.trim(array1[1]);
            String brandName=array1[2];
            String managerName=array1[3];
            String asssName=array1[4];
            String skuLeixing=array1[5];
            String xinghao=array1[6];
            String guige=array1[7];

            String cateName2=array1[9];
            String cateName3=array1[10];




            String unitName=array1.length<12?"":array1[11];

            String searchLevel=array1.length<13?"":array1[12];
            //直购商品、询价商品、寻货商品、内部商品
            if("直购商品".equals(searchLevel)){
                searchLevel="1";
            }else if("询价商品".equals(searchLevel)||"寻价商品".equals(searchLevel)||"询价产品".equals(searchLevel)
            ){
                searchLevel="2";
            }else if("寻货商品".equals(searchLevel)){
                searchLevel="3";
            }else if("询货商品".equals(searchLevel)){
                searchLevel="3";
            }else if("内部商品".equals(searchLevel)){
                searchLevel="4";
            }else{
                //默认寻获商品
                searchLevel="3";
            }


            Map<String,Object> skuInfo= selectOne("select * FROM V_CORE_SKU WHERE SKU_NO=?",
                    new Object[]{StringUtils.trim(skuNo)});
            //System.out.println(skuInfo.get("SPU_ID"));
            Map<String,Object> prop= selectOne("select A.* FROM V_BASE_CATEGORY  A LEFT JOIN V_BASE_CATEGORY B ON A.PARENT_ID=B.BASE_CATEGORY_ID  " +
                            "WHERE A.BASE_CATEGORY_NAME=? AND B.BASE_CATEGORY_NAME=?",
                    new Object[]{StringUtils.trim(cateName3),StringUtils.trim(cateName2)});
            if(prop.isEmpty()){
                flag=false;
                ERRORCATE.add(skuNo);
                continue;
            }

            if(StringUtils.contains(skuName,"*")||StringUtils.contains(skuName,"<")
                    ||StringUtils.contains(skuName,">")||
                    StringUtils.contains(skuName,"'")){
                skuNos.add(skuNo);
                flag=false;
                continue;
                //System.out.println( array[0]+ "   由于开票原因，商品名称不允许4个字符  * < > '");
            }
            if(isMoreStringlength(skuName,"GBK",68)){
                skuNos2.add(skuNo);
                flag=false;
                continue;
            }
            int spuType=1008;
            if(StringUtils.equals("耗材",StringUtils.trim(skuLeixing))){
                spuType=317;
            }
            if(StringUtils.equals("试剂",StringUtils.trim(skuLeixing))){
                spuType=318;
            }
            if(StringUtils.equals("器械设备",StringUtils.trim(skuLeixing))){
                spuType=316;
            }
            if(StringUtils.equals("配件",StringUtils.trim(skuLeixing))){
                spuType=1008;
            }
            if(spuType==317||spuType==318){
                if(isMoreStringlength(guige,"GBK",40)){
                    specSet.add(skuNo);
                    flag=false;
                    continue;
                }
            }else{
                if(isMoreStringlength(xinghao,"GBK",40)){
                    modelSet.add(skuNo);
                    flag=false;
                    continue;
                }
            }

            Map<String,Object> brand= selectOne("select * from T_BRAND WHERE BRAND_NAME=?",
                    new Object[]{StringUtils.trim(brandName)});
            if(brand.isEmpty()){
                brandSet.add(skuNo);
                flag=false;
                continue;
            }
            Map<String,Object> manager= selectOne(" select * from T_USER WHERE USERNAME= ?",
                    new Object[]{StringUtils.trim(managerName)});
            if(manager.isEmpty()){
                mSet.add(skuNo);
                flag=false;
                continue;
            }
            Map<String,Object> ass= selectOne(" select * from T_USER WHERE USERNAME= ?",
                    new Object[]{StringUtils.trim(asssName)});
            if(ass.isEmpty()){
                aSet.add(skuNo);
                flag=false;
                continue;
            }
            if(!flag){
                errorLines.add(line);
            }else{
                Map<String,Object> unit= selectOne("select * FROM T_UNIT WHERE UNIT_NAME=?  ",
                        new Object[]{StringUtils.trim(unitName)});
                int unitId=0;
                if(!unit.isEmpty()){
                    unitId= NumberUtils.toInt(unit.get("UNIT_ID")+"");
                }
                String sql="update V_CORE_SKU SET STATUS=1,CHECK_STATUS=3,SKU_NAME='"+
                        skuName+"',show_name='"+skuName+"',model='"+xinghao+"',spec='"+guige+"',base_unit_id="+unitId+",min_order="+NumberUtils.toInt(unit.get("MIN_ORDER")+"",1)+"  " +
                        "where sku_no='"+skuNo+"';";
                String spuSql="update V_CORE_SPU SET SPU_LEVEL=0,APPARATUS_TYPE=2, CATEGORY_ID= " +prop.get("BASE_CATEGORY_ID") +",SPU_TYPE="+spuType+
                        ",ASSIGNMENT_MANAGER_ID="+manager.get("USER_ID")+"," +
                        "ASSIGNMENT_ASSISTANT_ID="+ass.get("USER_ID")+",BRAND_ID="+brand.get("BRAND_ID")+" where spu_id="+skuInfo.get("SPU_ID")+";";
                Map<String,Object> skuSearchInfo= selectOne("select * FROM V_CORE_SKU_SEARCH WHERE SKU_NO=?",
                        new Object[]{StringUtils.trim(skuNo)});
                if(skuSearchInfo.isEmpty()){
                    String searchSQL="INSERT INTO  V_CORE_SKU_SEARCH (SKU_SORT_LEVEL,STATUS,CHECK_STATUS" +
                            ",SKU_NAME,SHOW_NAME,model,spec,base_unit_id,min_order,sku_No,spu_id)VALUES("+searchLevel+",1,3,'" +
                            skuName+"','"+skuName+"','"+xinghao+"'," +
                            "'"+guige+"',"+unitId+","+NumberUtils.toInt(unit.get("MIN_ORDER")+"",1)+",'"+skuNo+"',"+skuInfo.get("SPU_ID")+" );" ;
                    System.out.println(searchSQL);
                }else{
//                String searchSQL="update V_CORE_SKU_SEARCH SET SKU_SORT_LEVEL="+searchLevel+", STATUS=1,CHECK_STATUS=3,SKU_NAME='"+
//                        skuName+"',SHOW_NAME='"+skuName+"',model='"+xinghao+"',spec='"+guige+"',base_unit_id="+unitId+",min_order="+NumberUtils.toInt(unit.get("MIN_ORDER")+"",1)+"  " +
//                        "where sku_no='"+skuNo+"';";
//                 System.out.println(searchSQL);
                }
                // System.out.println(sql);
                // System.out.println(spuSql);
            }
        }
        System.out.println( " 由于开票原因，商品名称不能包含4种字符  * < > ' "+skuNos);
        System.out.println( " 商品名称长度过长 :"+skuNos2);
        System.out.println( " 商品分类找不到或者关系二三级无法匹配 :"+ERRORCATE);
        System.out.println( " 规格长度过长 :"+specSet);
        System.out.println( " 型号长度过长 :"+modelSet);
        System.out.println( " 品牌不正确 :"+brandSet);
        System.out.println( " 找不到产品经理 :"+mSet);
        System.out.println( " 找不到产品助理 :"+aSet);
        errorLines.forEach(item->{
            System.out.println(item);
        });

    }
    public static boolean isMoreStringlength(String string,String format,Integer maxLength) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(string)){
            return false;
        }
        int length = string.getBytes(format).length;
        if(length > maxLength){
            return true;
        }
        return false;
    }

//           String[] array=StringUtils.split(line,"\t");
//           String sku=array[0];
//           String m=array[1];
//           String s=array[2];
//
//           Map<String,Object> prop= selectOne(" select user_id from T_USER WHERE USERNAME=?",
//                   new Object[]{m});
//           String mi=prop.get("USER_ID")+"";
//           Map<String,Object> prop2= selectOne(" select user_id from T_USER WHERE USERNAME=?",
//                   new Object[]{s});
//           String si=prop2.get("USER_ID")+"";
//
//           Map<String,Object> SPU= selectOne(" select SPU_ID from V_CORE_SKU  WHERE SKU_NO=?",
//                   new Object[]{sku});
//           String spui=SPU.get("SPU_ID")+"";
//
//           String sql="update V_CORE_SPU SET ASSIGNMENT_MANAGER_ID="+mi+",ASSIGNMENT_ASSISTANT_ID="+si+" where SPU_ID="+spui+";";
//           System.out.println(sql);
//       }
    //  prop();
    //}



    //    public static void prop() throws SQLException, IOException {
//        List<String>  list= Files.readAllLines(Paths.get("/Users/hepeng/Desktop/workspace/ez/ez/ez-list/src/test/java/528.txt"));
//        for(String line:list){
//            String[] array=StringUtils.split(line,"\t");
//            String name=array[3];
//            String value=array[4];
//
//            // System.out.println(line);
//            Map<String,Object> obj= selectOne("select A.BASE_CATEGORY_ID,A.BASE_CATEGORY_NAME from V_BASE_CATEGORY A LEFT JOIN V_BASE_CATEGORY B " +
//                            "ON A.PARENT_ID=B.BASE_CATEGORY_ID " +
//                            "LEFT JOIN  V_BASE_CATEGORY C " +
//                            "ON B.PARENT_ID=C.BASE_CATEGORY_ID " +
//                            "WHERE A.BASE_CATEGORY_NAME=? AND B.BASE_CATEGORY_NAME=? AND C.BASE_CATEGORY_NAME=? ",
//                    new Object[]{array[2],array[1],array[0]});
//            if(obj==null||obj.isEmpty()){
//                System.out.println("分类是空的"+line);
//            }else{
//                String catId=obj.get("BASE_CATEGORY_ID")+"";
//                //看有没有属性名
//                Map<String,Object> prop= selectOne("select BASE_ATTRIBUTE_ID,BASE_ATTRIBUTE_NAME from V_BASE_ATTRIBUTE WHERE BASE_ATTRIBUTE_NAME=? AND IS_DELETED=0 ",
//                        new Object[]{name});
//                long propId=0;
//                if(prop==null||prop.isEmpty()){
//                    propId= dao.executeUpdate(origin,"insert into V_BASE_ATTRIBUTE (BASE_ATTRIBUTE_NAME,IS_UNIT,IS_DELETED,CREATOR,ADD_TIME,new_flag) values" +
//                                    "(?,0,0,1,now(),1)",
//                            new Object[]{name});
//                    System.out.println("插入属性："+name);
//                }else{
//                    propId= Long.parseLong(prop.get("BASE_ATTRIBUTE_ID")+"");
//                }
//                //看看有没有属性值
//                Map<String,Object> propValue= selectOne("select BASE_ATTRIBUTE_VALUE_ID,ATTR_VALUE from V_BASE_ATTRIBUTE_VALUE WHERE ATTR_VALUE=? AND BASE_ATTRIBUTE_ID=? AND IS_DELETED=0",
//                        new Object[]{value,propId});
//                long valueId=0;
//                if(propValue==null||propValue.isEmpty()){
//                    valueId= dao.executeUpdate(origin,"insert into V_BASE_ATTRIBUTE_VALUE (BASE_ATTRIBUTE_ID,SORT,ATTR_VALUE,UNIT_ID,IS_DELETED,CREATOR,ADD_TIME,NEW_FLAG) " +
//                                    " VALUES(?,0,?,0,0,1,NOW(),1) ",
//                            new Object[]{propId,value});
//                    System.out.println("插入value:"+value);
//                }else{
//                    valueId= Long.parseLong(propValue.get("BASE_ATTRIBUTE_VALUE_ID")+"");
//                }
//
//
//                //判断有没有三者关系
//                Map<String,Object> relation= selectOne("select BASE_CATEGORY_ID from V_CATEGORY_ATTR_VALUE_MAPPING where  BASE_CATEGORY_ID=? and BASE_ATTRIBUTE_ID=? and BASE_ATTRIBUTE_VALUE_ID=? and is_deleted=0",
//                        new Object[]{catId,propId,valueId});
//                if(relation==null||relation.isEmpty()){
//                    dao.executeUpdate(origin,"INSERT INTO V_CATEGORY_ATTR_VALUE_MAPPING (BASE_CATEGORY_ID,BASE_ATTRIBUTE_ID,BASE_ATTRIBUTE_VALUE_ID,IS_DELETED" +
//                                    "                       ,CREATOR,ADD_TIME,NEW_FLAG) VALUES (?,?,?,0,1,NOW(),1)",
//                            new Object[]{catId,propId,valueId});
//                    System.out.println("插入新的关系");
//                }
//                System.out.println("------------------------完成");
//            }
//            //   System.out.println(obj.get("BASE_CATEGORY_NAME").equals(array[0]));
//        }
//    }
    public static Map<String,Object> selectOne(String sql,Object[] obj) throws SQLException {
        return dao.executeQueryOne(origin,sql,obj);
    }
    public static Map<String,Object> selectList(String sql,Object[] obj) throws SQLException {
        return dao.executeQueryOne(origin,sql,obj);
    }
}

