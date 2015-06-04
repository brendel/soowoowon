package com.soowoowon.service;

import com.soowoowon.dao.MessageDAO;
import com.soowoowon.model.Message;
import com.soowoowon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@link com.soowoowon.service.SmsService}의 까페24 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see Message
 * @see com.soowoowon.service.SmsServiceCafe24
 */
@Service
@Transactional
public class SmsServiceCafe24 implements SmsService {

    private static Pattern pattern = Pattern.compile("^01[\\d]{8,9}");
    @Autowired
    MessageDAO messageDAO;
    @Value("#{env['cafe24.sms_url']}")
    private String smsUrl;

    @Value("#{env['cafe24.secure']}")
    private String secureKey;

    @Value("#{env['cafe24.user_id']}")
    private String userId;

    @Value("#{env['cafe24.sphone1']}")
    private String sphone1;

    @Value("#{env['cafe24.sphone2']}")
    private String sphone2;

    @Value("#{env['cafe24.sphone3']}")
    private String sphone3;

    private static String base64Encode(String str) throws IOException {
        byte[] strByte = str.getBytes();
        String result = Base64.getEncoder().encodeToString(strByte);
        return result;
    }

    private static String checkNull(String str, String defaultValue) {
        String returnDefault;
        if (str == null) {
            returnDefault = defaultValue;
        } else if (str == "") {
            returnDefault = defaultValue;
        } else {
            returnDefault = str;
        }
        return returnDefault;
    }

    /**
     * SMS 문자열과 수신인 전화번호 등을 base64로 인코딩, 까페24 SMS 서버에 POST 요청을 보내고 DB에 전송 내역을 저장한다.
     *
     * @param message
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @author Kiwon Lee <bwv1050@gmail.com>
     */
    @Override
    public void send(Message message) throws IOException, NoSuchAlgorithmException {
        String msg = message.getMessage();
        msg = msg.replaceAll("#name#", message.getDisplayName());
        message.setMessage(msg);

        String rphone = phoneNumberSplitter(message.getPhone());

        // Encode variables.
        String user_id = base64Encode(userId);
        String secure = base64Encode(secureKey);
        msg = base64Encode(checkNull(message.getMessage(), ""));
        rphone = base64Encode(checkNull(rphone, ""));
        String sphone01 = base64Encode(checkNull(sphone1, ""));
        String sphone02 = base64Encode(checkNull(sphone2, ""));
        String sphone03 = base64Encode(checkNull(sphone3, ""));
        String rdate = base64Encode(checkNull("", ""));
        String rtime = base64Encode(checkNull("", ""));
        String mode = base64Encode("1");
        String testflag = base64Encode(checkNull("", ""));
        String destination = base64Encode(checkNull("", ""));
        String repeatFlag = base64Encode(checkNull("", ""));
        String repeatNum = base64Encode(checkNull("", ""));
        String repeatTime = base64Encode(checkNull("", ""));


        String[] host_info = smsUrl.split("/");
        String host = host_info[2];
        String path = "/" + host_info[3];
        int port = 80;

        String[] keys = {"user_id", "secure", "msg", "rphone", "sphone1", "sphone2", "sphone3", "rdate", "rtime"
                , "mode", "testflag", "destination", "repeatFlag", "repeatNum", "repeatTime"};
        String[] values = new String[keys.length];
        values[0] = user_id;
        values[1] = secure;
        values[2] = msg;
        values[3] = rphone;
        values[4] = sphone01;
        values[5] = sphone02;
        values[6] = sphone03;
        values[7] = rdate;
        values[8] = rtime;
        values[9] = mode;
        values[10] = testflag;
        values[11] = destination;
        values[12] = repeatFlag;
        values[13] = repeatNum;
        values[14] = repeatTime;

        String boundary = "";
        Random rnd = new Random();
        String rndKey = Integer.toString(rnd.nextInt(32000));
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytData = rndKey.getBytes();
        md.update(bytData);
        byte[] digest = md.digest();
        for (int i = 0; i < digest.length; i++) {
            boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
        }
        boundary = "---------------------" + boundary.substring(0, 10);

        String data = "";
        for (int i = 0; i < keys.length; i++) {
            data += "--" + boundary + "\r\n";
            data += "Content-Disposition: form-data; name=\"" + keys[i] + "\"\r\n";
            data += "\r\n" + values[i] + "\r\n";
            data += "--" + boundary + "\r\n";
        }

        Socket socket = new Socket(host, port);

        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        wr.write("POST " + path + " HTTP/1.0\r\n");
        wr.write("Content-Length: " + data.length() + "\r\n");
        wr.write("Content-type: multipart/form-data, boundary=" + boundary + "\r\n");
        wr.write("\r\n");

        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        String line;
        ArrayList tmpArr = new ArrayList();
        while ((line = rd.readLine()) != null) {
            tmpArr.add(line);
        }
        wr.close();
        rd.close();

        String tmpMsg = (String) tmpArr.get(tmpArr.size() - 1);
        String[] rMsg = tmpMsg.split(",");
        String result = rMsg[0];

        message.setResultCode(result);
        message.setSent(new Date());
        messageDAO.add(message);
    }

    @Override
    public void sendToUser(Message message, User user) throws IOException, NoSuchAlgorithmException {
        message.setDisplayName(user.getDisplayName());
        message.setEmail(user.getEmail());
        message.setPhone(user.getPhone());
        this.send(message);
    }

    private String phoneNumberSplitter(String phoneNumber) {
        String[] phoneNumbers = new String[3];
        phoneNumbers[0] = phoneNumber.substring(0, 3);
        if (phoneNumber.length() == 10) {
            phoneNumbers[1] = phoneNumber.substring(3, 6);
            phoneNumbers[2] = phoneNumber.substring(6, 10);
        } else {
            phoneNumbers[1] = phoneNumber.substring(3, 7);
            phoneNumbers[2] = phoneNumber.substring(7, 11);
        }
        return phoneNumbers[0] + "-" + phoneNumbers[1] + "-" + phoneNumbers[2];
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find())
            return true;
        return false;
    }

    @Override
    public List<Message> list() {
        return messageDAO.list();
    }

    @Override
    public List<Message> list(int num) {
        return messageDAO.list(num);
    }
}