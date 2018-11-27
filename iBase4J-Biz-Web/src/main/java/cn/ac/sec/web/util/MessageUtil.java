package cn.ac.sec.web.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MessageUtil {
    private static final String name = "18633099360";
    private static final String pwd = "61AC892C799309AFA48024F5D431";
    private static final String sign = "云护健康";
    private static final StringBuffer extno = new StringBuffer ();
    private static final String stime = "";


    public static void sendMessage(String phoneNum, String code) {
        StringBuffer mobileString = new StringBuffer (phoneNum);
        StringBuffer contextString = new StringBuffer ();
        contextString.append ("您的短信验证码是").append (code).append (",请于15分钟内进行正确输入。如非本人操作，请忽略本信息");
        try {
            System.out.println (doPost (name, pwd, mobileString, contextString, sign, stime, extno));
        } catch (Exception e) {
            e.printStackTrace ();
        }

    }

    public static void main(String[] args) throws Exception {
        // 电话号码字符串，中间用英文逗号间隔
        StringBuffer mobileString = new StringBuffer ("18695193289");
        // 内容字符串
        StringBuffer contextString = new StringBuffer ("短信内容");
        // 签名
        // 追加发送时间，可为空，为空为及时发送
        // 扩展码，必须为数字 可为空
        System.out.println (doPost (name, pwd, mobileString, contextString, sign, stime, extno));
    }


    /**
     * 发送短信
     *
     * @param name          用户名
     * @param pwd           密码
     * @param mobileString  电话号码字符串，中间用英文逗号间隔
     * @param contextString 内容字符串
     * @param sign          签名
     * @param stime         追加发送时间，可为空，为空为及时发送
     * @param extno         扩展码，必须为数字 可为空
     * @return
     * @throws Exception
     */
    public static String doPost(String name, String pwd,
                                StringBuffer mobileString, StringBuffer contextString,
                                String sign, String stime, StringBuffer extno) throws Exception {
        StringBuffer param = new StringBuffer ();
        param.append ("name=" + name);
        param.append ("&pwd=" + pwd);
        param.append ("&mobile=").append (mobileString);
        param.append ("&content=").append (URLEncoder.encode (contextString.toString (), "UTF-8"));
        param.append ("&stime=" + stime);
        param.append ("&sign=").append (URLEncoder.encode (sign, "UTF-8"));
        param.append ("&type=pt");
        param.append ("&extno=").append (extno);

        URL localURL = new URL ("http://web.cr6868.com/asmx/smsservice.aspx?");
        URLConnection connection = localURL.openConnection ();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput (true);
        httpURLConnection.setRequestMethod ("POST");
        httpURLConnection.setRequestProperty ("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty ("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty ("Content-Length", String.valueOf (param.length ()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        String resultBuffer = "";

        try {
            outputStream = httpURLConnection.getOutputStream ();
            outputStreamWriter = new OutputStreamWriter (outputStream);

            outputStreamWriter.write (param.toString ());
            outputStreamWriter.flush ();

            if (httpURLConnection.getResponseCode () >= 300) {
                throw new Exception ("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode ());
            }

            inputStream = httpURLConnection.getInputStream ();
            resultBuffer = convertStreamToString (inputStream);

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close ();
            }

            if (outputStream != null) {
                outputStream.close ();
            }

            if (reader != null) {
                reader.close ();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close ();
            }

            if (inputStream != null) {
                inputStream.close ();
            }

        }

        return resultBuffer;
    }


    /**
     * 转换返回值类型为UTF-8格式.
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder ();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read (bytes)) > 0) {
                String str = new String (bytes, 0, size, StandardCharsets.UTF_8);
                sb1.append (str);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            try {
                is.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return sb1.toString ();
    }

}
