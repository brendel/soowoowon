package com.soowoowon.service;

import com.soowoowon.model.Message;
import com.soowoowon.model.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * {@link Message}의 서비스 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see Message
 * @see com.soowoowon.service.SmsServiceCafe24
 */
public interface SmsService {

    void send(Message message) throws IOException, NoSuchAlgorithmException;

    void sendToUser(Message message, User user) throws IOException, NoSuchAlgorithmException;

    List<Message> list();

    List<Message> list(int num);

}
