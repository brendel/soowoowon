package com.soowoowon.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ContentTypeMultipartFileValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
public @interface ContentType {

    String message() default "{soowoowon.ContentType.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 허용되는 콘텐츠 타입
     * <p/>
     * 예:
     * <ul>
     * <li>application/pdf</li>
     * <li>application/msword</li>
     * <li>images/png</li>
     * </ul>
     *
     * @return 허용되는 콘텐츠 타입
     */
    String[] value();
}