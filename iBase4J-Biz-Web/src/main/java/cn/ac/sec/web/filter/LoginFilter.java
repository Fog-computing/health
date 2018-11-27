package cn.ac.sec.web.filter;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.ibase4j.core.exception.LoginException;
import org.ibase4j.core.util.CacheUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class LoginFilter {

    @Pointcut("execution(* cn.ac.sec.web.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes ();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest ();
        String token = request.getHeader ("TOKEN");
        if (token == null) throw new LoginException ("尚未登录");
        if (CacheUtil.getCache ().exists (token))
            CacheUtil.getCache ().expire (token, 60 * 60 * 24 * 7);
        else throw new LoginException ("登录超时");
        return pjp.proceed ();
    }

}
