package com.yf.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 生成唯一编号类
 * <p>Title: UUIDGenerator.java</p>
 * <p>Description:语联网</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company:武汉传神网络技术有限公司</p>
 * @author jay.liu
 * @date Dec 24, 2013 10:06:55 AM
 * @version V1.0
 */
public class UUIDGenerator{
    
    /**
     * 获得唯一编号 
     * @author jay.liu
     * @param lenght 编号长度
     * @return 
     * String 返回唯一编号
     * @date Dec 24, 2013 10:08:43 AM
     */
    public static String getUniqueId(int lenght){
        String[] randomValues = new String[]{"0","1","2","3","4","5","6","7","8","9",
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n","u",
                "t","s","o","x","v","p","q","r","w","y","z",
                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","U",
                "T","S","O","X","V","P","Q","R","W","Y","Z"};
            
            StringBuffer str = new StringBuffer();
            for(int i = 0;i < lenght; i++)
            {
                    Double number=Math.random()*(randomValues.length-1);
                    str.append(randomValues[number.intValue()]);
            }
            
            return str.toString();
    }
    
    /**
     * 获得订单编号 
     * @author jay.liu
     * @return 
     * String 
     * @date Dec 26, 2013 2:05:51 PM
     */
    public static synchronized String getNumId(){
        Random rad=new Random();
        StringBuffer str = new StringBuffer();
        str.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        str.append(rad.nextInt(10000));
        str.append("");
        
        return str.toString();
    }

    /**
     * 获得订单编号
     * @author chenm
     * @return
     * String
     * @date 2015-5-6 16:18:55
     */
    public static synchronized String getOrderId(){
        return getNumId();
    }
    
    /**
     * 32位uuid
     * @return
     */
    public static String getUUID(){
    	UUID uuid  =  UUID.randomUUID(); 
    	String s = uuid.toString();
    	String a = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    	return a;
    }
    
    public static void main(String[] args){
//        System.out.println(UUIDGenerator.getUniqueId(16));
//        System.out.println(UUIDGenerator.getUUID());
//    	System.out.println(UUIDGenerator.getUUID());
    	System.out.println(UUIDGenerator.getNumId());

    }
}
