package yjs.tyust.edu.cn.jiewei.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5 {

    public static String getMD5(String strText){
        // 返回值
        String strResult = null;
        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密类型
                MessageDigest messageDigest = MessageDigest.getInstance("md5");
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes("UTF-8"));
                // 得到 byte 类型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 转为 string
                StringBuffer strHexString = new StringBuffer();
                // 遍历 byte buffer
                for (int i = 0; i < byteBuffer.length; i++)
                {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1)
                    {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回结果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }

    public static void main(String[] args) {

        String secret = "1461dd9025fd39cf";
        String str = "14020219691013254X"+secret;

        String md5 = getMD5(str);
        System.out.println(md5);
    }
}