package com.days.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MyJob {
	public void doWork() {  
        System.out.println("date:" + new Date().toString());  
    }  
	
	public static String date2String() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = sdf.format(new Date());

		return strDate + nextInt(1000,9999); // 年年+月月+日日+6位序号
	}

	public static int nextInt(final int min, final int max)
	{
		Random rand= new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}
	
	public static void main(String[] args) {
		String jsonString =
				"        [{" +
				"            \"batchNo\": \"T2017021401\", \n" +
				"            \"playWay\": \"复式\", \n" +
				"            \"schemeType\": \"买彩\", \n" +
				"            \"subOrderNo\": 26135843, \n" +
				"            \"userId\": 1403109\n" +
				"        },\n" +
				"          {\n" +
				"            \"batchNo\": \"T2017021402\", \n" +
				"            \"playWay\": \"复式\", \n" +
				"            \"schemeType\": \"买彩\", \n" +
				"            \"subOrderNo\": 26135844, \n" +
				"            \"userId\": 1403110\n" +
				"        }]";
		JsonTest jsonO = new JsonTest();
		try {
			List<Datas> list =
                    jsonO.jSonArrayToObjectList(jsonString,Datas.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
