package com.oneb.common.annotation.resolver;

import com.oneb.common.annotation.HeaderInfo;
import com.oneb.common.domain.user.UserInfoService;
import com.oneb.common.domain.user.dto.UserInfo;
import com.oneb.common.domain.user.enums.Role;
import com.oneb.common.exception.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class HeaderInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserInfoService userInfoService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(HeaderInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) {
            throw new BadRequestException("Unable to access HTTP request");
        }

        HeaderInfo annotation = parameter.getParameterAnnotation(HeaderInfo.class);

        return switch (annotation.value()) {
            case X_USER_ID -> getUserId(request);
            case USER -> getUser(request);
            case X_USER_ROLES -> getRoles(request);
            case USER_INFO -> getUserInfo(request);
            default -> getHeader(request, annotation.value());
        };
    }

    private UserInfo getUserInfo(HttpServletRequest request) {
        Long userId = getUserId(request);
        String tenant = getHeader(request, HeaderKey.X_USER_TENANT);
        List<Role> roles = getRoles(request);
        return UserInfo.builder()
                .id(userId)
                .tenant(tenant)
                .roles(roles)
                .build();
    }

    private List<Role> getRoles(HttpServletRequest request) {
        String roles = getHeader(request, HeaderKey.X_USER_ROLES);
        return Stream.of(roles.split(","))
                .map(Role::valueOf)
                .toList();
    }

    @SneakyThrows
    private Object getUser(HttpServletRequest request) {
        UserInfo userInfo = getUserInfo(request);
        if (userInfoService == null) {
            throw new BadRequestException("User info service not found");
        }
        return userInfoService.findByUserInfo(userInfo)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    private Long getUserId(HttpServletRequest request) {
        String userIdHeader = getHeader(request, HeaderKey.X_USER_ID);
        return Long.parseLong(userIdHeader);
    }

    private String getHeader(HttpServletRequest request, HeaderKey headerKey) {
        String userIdHeader = request.getHeader(headerKey.getValue());
        if (userIdHeader == null || userIdHeader.trim().isEmpty()) {
            throw new BadRequestException("Missing required header: " + headerKey.getValue());
        }
        return userIdHeader.trim();
    }
}
