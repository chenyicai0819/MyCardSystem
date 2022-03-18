package com.chen.mycardsystembackstage.utils;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.utils
 * @date 2022/3/18 23:15
 * @since 1.0
 */
@Component
public class StringToUtf8 {
    public String getUTF8XMLString(String xml) {
        // A StringBuffer Object
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "";
        String xmlUTF8="";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return to String Formed
        return xmlUTF8;
    }
}
