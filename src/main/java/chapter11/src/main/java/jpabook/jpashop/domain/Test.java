package jpabook.jpashop.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

public class Test {

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTransaction() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
