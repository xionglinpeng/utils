package com.util.utils.conversion;

import org.apache.commons.lang3.RandomStringUtils;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

/**
 * 数据格式转换
 * @author xlp
 *
 */
public class ConversionDataFormatUtils {
	
    /**
     * xml字符串转为json。
     * 此工具函数依赖json-lib-2.4-jdk15.jar、xom-1.2.10.jar
     * @param xml xml字符串.
     * @return net.sf.json.JSON对象
     */
    public static JSON xmlToJson(String xml){
        XMLSerializer serializer = new XMLSerializer();
        serializer.setTypeHintsEnabled(false);
        JSON json = serializer.read(xml);
        return json;
    }
    
}
