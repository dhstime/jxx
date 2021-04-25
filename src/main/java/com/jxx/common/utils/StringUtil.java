
package com.jxx.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * <b>Description:String操作的一些工具方法</b><br> 
 * <b>Author: Franlin</b> 
 * @fileName StringUtil.java
 * <br><b>Date: 2018年4月25日 下午1:20:43 </b> 
 *
 */
public class StringUtil
{
	
	/**
	 *
	 * <b>Description:字符串替换</b><br>示例: repaceAll("aa|bb|cc", "|", "->") 输出为aa->bb->cc
	 * @param repaceStr 替换字符串
	 * @param oldStr    替换字段
	 * @param newStr    新字段
	 * @return
	 */
	public static String repaceAll(String repaceStr, String oldStr, String newStr)
	{
		if(null == repaceStr || null == oldStr || null == newStr)
		{
			throw new IllegalArgumentException("非法参数,该方法入参不可为null");
		}
		
		int index = repaceStr.indexOf(oldStr);
		
		// 存在 oldStr
		if(index != -1)
		{
			repaceStr = repaceStr.substring(0, index) + newStr + repaceStr.substring(index + oldStr.length(), repaceStr.length());
			return repaceAll(repaceStr, oldStr, newStr);
		}
		return repaceStr;
	}
	
	/**
	 *
	 * <b>Description: 判断空白字符串</b><br>
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str)
	{
		if(null == str || str.trim().length() == 0)
			return true;
		
		return false;
	}
	
	/**
	 *
	 * <b>Description: 判断非空白字符串</b><br>
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str)
	{
		return !isBlank(str);
	}
	
	/**
	 *
	 * <b>Description: 判断空字符串</b><br>
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		if(null == str || str.length() == 0)
			return true;
		return false;
	}
	
	/**
	 *
	 * <b>Description: 判断非空字符串</b><br>
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str)
	{
		return !isEmpty(str);
	}
	
	/**
	 *
	 * <b>Description:对 oldStr字符串若开头和结尾存在beginOrEndStr,则去除</b><br>
	 * @param oldStr
	 * @param beginOrEndStr
	 * @return
	 */
	public static String removeStrBeginStrOrEndStr(String oldStr, String beginOrEndStr)
	{
		if(null == oldStr || null == beginOrEndStr)
			throw new IllegalArgumentException("非法参数,该方法入参不可为null");
		
		if(oldStr.startsWith(beginOrEndStr))
		{			
			oldStr = oldStr.substring(beginOrEndStr.length(), oldStr.length());
		}
		if(oldStr.endsWith(beginOrEndStr))
		{
			oldStr = oldStr.substring(0 , oldStr.length() - beginOrEndStr.length());
		}
		if(oldStr.startsWith(beginOrEndStr) || oldStr.endsWith(beginOrEndStr))
		{
			oldStr = removeStrBeginStrOrEndStr(oldStr, beginOrEndStr);
		}
		return oldStr;
	}
	
	/**
	 *
	 * <b>Description:</b><br>
	 * @param param 计算字符串的字符长度[一个汉字2个字符]
	 * @return
	 */
	public static int sumCharacterLength(String param)
	{
		int len = 0;
		if(null == param)
			return len;
		
		for (int i = 0; i < param.length(); i++)
		{
			char c = param.charAt(i);
			// 单字节加1
			if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f))
			{
				len++;
			}
			// 汉字+2
			else
			{
				len += 2;
			}
		}
		return len;
	}

	/**
	 * 验证字符串是否为数字
	 */

	public static boolean isNumeric(String str){
		if(isBlank(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static String doubleToString(Double d){
		if (d != null) {
			if (d.doubleValue() != 0.00) {
				DecimalFormat df = new DecimalFormat("##0.00##");
				return df.format(d.doubleValue());
			}else { return "0.00"; } }
		return "0.00";
	}

	/**
	 * 匹配0133xxxxxxxx,0533xxxxxxxx,0333xxxxxxxx,0933xxxxxxxx
	 */
	private final static String PHONE_NET_REG = "^(0|3|5|9)1[3456789]{1}\\d{9}$";
	private final static String PHONE_000_REG = "^(000)1[3456789]{1}\\d{9}$";
	private final static String PHONE_00_REG = "^(00)1[3456789]{1}\\d{9}$";
	private final static String PHONE_REG = "^1[3456789]{1}\\d{9}$";

	public static String cleanPhone(String phone){
		if(isBlank(phone)){
			return phone;
		}
		phone = phone.replaceAll("-", "");
		phone = phone.replaceAll(" ", "");
		if(phone.matches(PHONE_NET_REG)){
			phone=phone.substring(1);
		}
		if(phone.matches(PHONE_000_REG)){
			phone=phone.substring(3);
		}
		if(phone.matches(PHONE_00_REG)){
			phone=phone.substring(2);
		}
		return phone ;
	}
	public static boolean matchPhone(String phone){
		if(isBlank(phone)){
			return false;
		}
		return phone.matches(PHONE_REG);
	}

	public static String extractNumber(String numberStr) {

		if(StringUtils.isBlank(numberStr)){
			return Strings.EMPTY;
		}

		StringBuffer resultBuffter = new StringBuffer();

		char index;

		for (int i = 0; i < numberStr.length(); i++) {

			index = numberStr.charAt(i);

			if (index >= 48 && index <= 57) {
				resultBuffter.append(index);
			}
		}

		return resultBuffter.toString();
	}

	/**
	 * @description: 拼接sql
	 * @return: StringBuilder
	 * @author: Strange
	 * @date: 2021/4/25
	 **/
	public static StringBuilder appenSql(StringBuilder sql, String value, String filed) {
		if(value == null || value.isEmpty()){
			return sql;
		}
		return sql.append(filed + " = " + value +",");
	}
}

