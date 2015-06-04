package com.soowoowon.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * JSON String을 생성하는 클래스.
 * <p>도메인 객체나 도메인 객체의 콜렉션을 인코딩하여 JSON String을 을 생성하는 객체.
 * 3개의 정적 메소드를 포함한다. 클라이언트에 불필요하거나 감추고 싶은 필드들(예: 유저 비밀번호)을 필터링해서
 * 필요한 정보만 남겨진 JSON 문자열을 리턴한다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
public class Writer {
    public static final ObjectWriter reservationDetailWriter = new ObjectMapper()
            .registerModule(new Hibernate4Module().enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING))
            .writer(new SimpleFilterProvider()
                            .addFilter("ReservationFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("id", "user", "title", "content", "replies", "date", "part", "state"))
                            .addFilter("UserFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("id", "displayName", "role"))
            );

    public static final ObjectWriter reservationListWriter = new ObjectMapper()
            .registerModule(new Hibernate4Module())
            .writer(new SimpleFilterProvider()
                            .addFilter("ReservationFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("id", "user", "title", "published"))
                            .addFilter("UserFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("id", "displayName", "role"))
            );

    public static final ObjectWriter newReplyWriter = new ObjectMapper()
            .registerModule(new Hibernate4Module())
            .writer(new SimpleFilterProvider()
                            .addFilter("ReplyFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("id", "user", "content", "published"))
                            .addFilter("UserFilter",
                                    SimpleBeanPropertyFilter.filterOutAllExcept("id", "displayName", "role"))
            );
}
