package shop.mtcoding.deployapp._config;


import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.deployapp.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String jwt = request.getHeader(JwtTokenProvider.HEADER);

        if (jwt == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            log.debug("디버그 : 토큰 있음");
            DecodedJWT decodedJWT = JwtTokenProvider.verify(jwt);
            int id = decodedJWT.getClaim("id").asInt();
            String email = decodedJWT.getClaim("email").asString();

            User user = User.builder().id(id).email(email).build();

            HttpSession session = request.getSession();
            session.setAttribute("sessionUser", user);
            log.debug("디버그 : 인증 객체 만들어짐");
        } catch (SignatureVerificationException sve) {
            log.error("토큰 검증 실패");
        } catch (TokenExpiredException tee) {
            log.error("토큰 만료됨");
        } finally {
            chain.doFilter(request, response);
        }
    }
}
