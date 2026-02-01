package com.oneb.common.config;

import com.oneb.common.annotation.resolver.HeaderInfoArgumentResolver;
import com.oneb.common.domain.user.UserInfoService;
import com.oneb.common.exception.handler.ExceptionAdvice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@ConditionalOnBean(ExceptionAdvice.class)
public class HeaderInfoResolverConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    private UserInfoService userInfoService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HeaderInfoArgumentResolver(userInfoService));
    }
}
