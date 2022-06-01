package com.qzhp.config;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * description
 *
 * @author qcb
 * @date 2022/05/28 15:42.
 */
@Configuration
public class WebConfig {

    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Value("${spring.jackson.datetime-format:yyyy-MM-dd HH:mm:ss}")
    private String datetimeFormat;

    @Value("${spring.jackson.date-format:yyyy-MM-dd}")
    private String dateFormat;

    @Value("${spring.jackson.time-format:HH:mm:ss}")
    private String timeFormat;

    /**
     * 自定义Bean
     *
     * @return
     */
    @Bean
    @Primary
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(datetimeFormat)))
                .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateFormat)))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormat)))
                .serializerByType(BigInteger.class, ToStringSerializer.instance)
                .serializerByType(Long.class, ToStringSerializer.instance)
                .deserializerByType(Long.class, new NumberDeserializers.LongDeserializer(Long.class, null));
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

}
