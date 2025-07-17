package com.example.test2.interceptor;

import com.example.test2.data.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    /**
     * 컨트롤러에 요청이 도달하기 전, 로그인 여부를 조사한다.
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO loginUser = (UserDTO)request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            log.warn("로그인을 안한 상태입니다.");
            response.sendRedirect("/login/login");
            return false;
        }

        return true;
    }
}
