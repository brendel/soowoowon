package com.soowoowon.initializer;

import com.soowoowon.dao.UserDAO;
import com.soowoowon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by jake on 5/30/15.
 */
@Component
public class DummyDataInitializer {
    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;
    @Autowired
    private UserDAO userDAO;

    @PostConstruct
    public void init() {
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                User user1 = new User();
                user1.setPhone("01073691070");
                user1.setEmail("bwv1050@gmail.com");
                user1.setDisplayName("SYSADMIN");
                user1.setJoined(new Date());
                user1.setPassword(new BCryptPasswordEncoder().encode("123123"));
                user1.setUsername("jake");
                user1.setLastVisited(new Date());
                user1.setRole((byte) 9);

                User user2 = new User();
                user2.setPhone("01064731070");
                user2.setEmail("keonheelee17@gmail.com");
                user2.setDisplayName("LeeKeonHee");
                user2.setUsername("keon");
                user2.setJoined(new Date());
                user2.setPassword(new BCryptPasswordEncoder().encode("123123"));
                user2.setLastVisited(new Date());
                user2.setRole((byte) 9);

                userDAO.add(user1);
                userDAO.add(user2);
            }
        });
    }
}
