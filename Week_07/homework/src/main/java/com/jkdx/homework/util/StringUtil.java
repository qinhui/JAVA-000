package com.jkdx.homework.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.commons.lang.StringUtils;




public class StringUtil {
	
	
	
	
	/**
	 * 
	 * @param str= ABC
	 * @param addval =1
	 * @return A1B1C1
	 */
	public static String strAddValue(final String str,final String addval) {
		
		char[] c=str.toCharArray();
		String temp_="";
		for(char cc:c){
			temp_+=cc+addval;
		} 
		return temp_;
	}
	
	
	public static String getExceptionMsg(final String[] prams) {
		String val = "pram is empty or null ---error \n";
		for(String pram : prams) {
			val+="key = "+pram +" \n";
		}
		return val;
		
	}
	/**
	 * 将对象传为String[]
	 * @param prams
	 * @return
	 */
	public static String[] convertToString(final Object [] prams) {
		if(prams.length==0)return new String[] {};
		
		String [] val = new String[prams.length];
		for(int i=0;i<prams.length;i++) {
			if(ObjectUtil.isNullOrEmpty(prams[0], false)) {
				val[i] = prams[0].toString();
			}
			else {
				val[i] = null;
			}
		}
		return val;
	}
	/***
	 * (PramSkjhSum.YEAR_EN_MENTHODS, "nvl(t.","rmb",",0)","+" ) 
	 * @param prams 
	 * @param startstr= nvl(t.
	 * @param joinchar = rmb
	 * @param endstr = ",0)" 
	 * @param spiltstr ="+"
	 * @return nvl(t.rmbJan, 0) + nvl(t.rmbFeb, 0) + nvl(t.rmbMar, 0) 
	 */
	public static String joinStr(final String[] prams,final String startstr,final String joinchar,final String endstr,final String spiltstr) {
		String sql="";
		for(String pram : prams){
			sql+=startstr+joinchar+pram+endstr+spiltstr;
		}
		sql = sql.endsWith(spiltstr)?sql.substring(0, sql.length()-1):sql;
		return sql;
	}
	
	/**
	 * 数组内容前拼接joinval值
	 * @param prams String[]{"a","b"}
	 * @param joinval "rmb_"
	 * @return armb_,brmb_
	 */
	public static String joinStrComma(final String[] prams,final String joinval) {
		String sql="";
		for(String pram : prams){
			sql+=joinval+pram+",";
		}
		sql = delLast(sql, ",");
		return sql;
	}
	/**
	 * 
	 * @param prams =String[]{"a","b"}
	 * @param startstr= sum(
	 * @param joinchar=rmb_
	 * @param endstr=)
	 * @param spiltstr=,
	 * @return sum(rmb_a),sum(rmb_b)
	 */
	public static String joinStradd(final String[] prams,final String startstr,final String joinchar,final String endstr,final String spiltstr) {
		String sql="";
		for(String pram : prams){
			sql+=startstr+joinchar+pram+endstr+" "+spiltstr;
		}
		sql = sql.endsWith(spiltstr)?sql.substring(0, sql.length()-1):sql;
		return sql;
	}
	/**
	 * 
	 * @param prams
	 * @param startstr
	 * @param joinchar
	 * @param endstr
	 * @param spiltstr
	 * @return
	 */
	public static String joinStraddAlias(final String[] prams,final String startstr,final String joinchar,final String endstr,final String spiltstr) {
		String sql="";
		for(String pram : prams){
			sql+=startstr+joinchar+pram+endstr+(joinchar+pram)+" "+spiltstr;
		}
		sql = sql.endsWith(spiltstr)?sql.substring(0, sql.length()-1):sql;
		return sql;
	}
	
	/**
	 * 
	 * @param prams=a,b
	 * @param startstr=(
	 * @param joinchar=rmb
	 * @param endstr=)
	 * @return (rmba),(rmbb)
	 */
	public static String joinStr(final String[] prams,final String startstr,final String joinchar,final String endstr){
		return joinStr(prams, startstr, joinchar, endstr, ",");
	}
	/**
	 * prams 是否含 value 时 返回 dv
	 * @param prams a,b
	 * @param value b
	 * @param dv : 
	 * @return
	 */
	public static boolean equalsOr(final String[] prams,final String value,final boolean dv) {
		for(String pram : prams) {
			if(equals(pram, value, true)) {
				return dv;
			}
		}
		return !dv;
	}
	
	public static String isNullDefault(final String pram,final String value) {
		if(isNullOrEmpty(pram, true)) {
			return value;
		}
		return pram;
	}
	public static boolean equals(final String pram,final String value,final boolean dv) {
		
		if(isNullOrEmpty(pram, true)) {
			return !dv;
		}
		
		if(pram.equals(value)) {
			return dv;
		}
		return !dv;
	}
	
	/**
	 * 转为大写 对比 Equals
	 * @param pram
	 * @param value
	 * @param dv
	 * @return
	 */
	public static boolean toUpperCaseEquals(final String pram,final String value,final boolean dv) {
		
		if(isNullOrEmpty(pram, true) && isNullOrEmpty(value, true) ) return dv;
		if(isNullOrEmpty(pram, true) ) return !dv;
		if(isNullOrEmpty(value, true) ) return !dv;
		
		if(pram.toUpperCase().equals(value.toUpperCase())){
			return dv;
		}
		return !dv;
		
	}
	/**
	 * 转为大写 Contains
	 * @param valuds
	 * @param value
	 * @param dv
	 * @return
	 */
	public static boolean toUpperCaseContains(final String []valuds,final String value,final boolean dv) {

		
		if(isNullOrEmpty(value, true))return !dv;
		
		if(Arrays.asList(valuds).contains(value.toUpperCase())){
			return dv;
		}
		return !dv;
		
	}
	/**
	 * 删除末位字符
	 * @param strval
	 * @param endchar
	 * @return
	 */
	public static String delLast(final String strval,final String endchar) {
		
		if(isNullOrEmpty(strval, true)) return "";
		
		return strval.endsWith(endchar)?strval.substring(0, strval.length()-1):strval;
	}
	
	
	public static boolean strContains(final String str,final String value,final boolean dv) {
		if(isNullOrEmpty(str, true)) {
			return !dv;
		}
		if(isNullOrEmpty(value, true)) {
			return !dv;
		}
		if(str.contains(value)) {
			return dv;
		}
		return !dv;
	}
	
	public static boolean strContains(final String str,final String[] convals,final boolean dv) {
		for(String v : convals) {
			if(str.contains(v)) {
				return dv;
			}
		}
		return !dv;
		
	}

	public static boolean strEndWith(final String str,final String value,final boolean dv) {
		if(str.endsWith(value)) {
			return dv;
		}
		return !dv;
		
	 }
	
	public static boolean strstartsWith(final String str,final String convals,final boolean dv) {
		if(str.startsWith(convals)) {
			return dv;
		}
		return !dv;
		
	 }
	
	public static boolean strstartsWith(final String str,final String[] convals,final boolean dv) {
		for(String v : convals) {
			if(str.startsWith(v)) {
				return dv;
			}
		}
		return !dv;
		
	 }
	/**
	 * 字符串是字 母大写
	 * @param val
	 * @param dv
	 * @return
	 */
	public static boolean strIsZmDx(final String val,final boolean dv) {
		char c = val.charAt(0);
		if( (c>='A'&&c<='Z') && Character.isUpperCase(c)) {
			return dv;
		}
		return !dv;
		
	}
	
	
	public static boolean strIsInt(final String val,final boolean dv) {
		boolean bool = StringUtils.isNumeric(val);
		if(bool)return dv;
		return !dv;
		
	}
	
	
	/**
	 * 去除重复出现的字符
	 * @param value  例 ab3,ab3,abc
	 * @param splitChar ,
	 * @return ab3,abc
	 */
	public static String DeleteDuplicate(final String value,final String splitChar) {
		
		String [] vals =value.split(splitChar);
		List<String> allVal = new ArrayList<String>();
		for (int i = 0; i <vals.length ; i++) {
            if (allVal.contains(String.valueOf(vals[i]))){
                continue;
            }else {
            	allVal.add(vals[i]);
            }
        }
		return StringUtils.join(allVal, splitChar);
		
	}
	/**
	 * 判断字符串是否为空值
	 * @param strvals
	 * @param dval
	 * @return
	 */
	public static boolean isNullOrEmpty(final String[] strvals,final boolean dval) {
		for(String v : strvals) {
			boolean bool =  isNullOrEmpty(v, dval);
			if(String.valueOf(bool).equals(String.valueOf(dval))) {
				return dval;
			}
		}
		return !dval;
		
	}
	
	public static boolean isNull(final Object strval,final boolean dval) {
		
		if(null==strval)return dval;
		
		return isNullOrEmpty(strval.toString(), dval);
		
		
	}
	
	public static boolean isNullOrEmpty(final String strval,final boolean dval) {
		if(null==strval || "".equals(strval) || strval.length()==0||"null".equals(strval)) {
			return dval;
		}
		else
			return !dval;
		
	}
	
	public static String getStrVal(final Object obj) {
		if(ObjectUtil.isNullOrEmpty(obj, true))return "";
		return obj.toString();
	}
	
	
}
