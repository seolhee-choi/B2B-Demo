package com.example.backend.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class Intercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("Intercepter Handler START !!!!!!!!!!!!!!!");
//        log.info("----------------------------------");
//        log.info("RequestURI : " + request.getRequestURI());
//        log.info("----------------------------------");
//        Set<String> keySet = request.getParameterMap().keySet();
//        for(String key: keySet) {
//            log.info(key + " : " + request.getParameter(key));
//        }

//        if (req.getRequestURI().startsWith(Constants.CONTEXT_PATH+"/api")) {
//            Map<String, Object> resultMap = new HashMap<String, Object>();
//            Map<String, Object> returnMap = new HashMap<String, Object>();
//            ObjectMapper mapper = new ObjectMapper();
//
//            resultMap.put("code", "403");
//            resultMap.put("message", "Forbidden");
//
//            returnMap.put("data", "");
//            returnMap.put("result", resultMap);
//
//            res.setContentType("application/json");
//            res.setStatus(403);
//            res.getWriter().write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnMap));
//
//            return false;
//        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        log.info("Intercepter Handler END !!!!!!!!!!!!!!!");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//        log.info("Intercepter Handler END !!!!!!!!!!!!!!!");
    }

}
