package com.liyametrics;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public ObjectMapper xmlObjectMapper()
    {
        JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
        return new ObjectMapper().registerModule(jaxbAnnotationModule);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate result = new RestTemplate();

        for (final Iterator<HttpMessageConverter<?>> iterator = result.getMessageConverters().iterator(); iterator.hasNext();) {
            HttpMessageConverter<?> next = iterator.next();
            if (next instanceof Jaxb2RootElementHttpMessageConverter) {
                Jaxb2RootElementHttpMessageConverter jaxbConverter = (Jaxb2RootElementHttpMessageConverter) next;
                jaxbConverter.setSupportDtd(true);
            }
        }

        return result;
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        MappingJackson2XmlHttpMessageConverter xmlMessageConverter = new MappingJackson2XmlHttpMessageConverter();
//        xmlMessageConverter.setObjectMapper(xmlObjectMapper());
//        messageConverters.add(xmlMessageConverter);
//        restTemplate.setMessageConverters(messageConverters);
//
//        return restTemplate;
//    }

}