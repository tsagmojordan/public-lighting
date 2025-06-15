package publiclighting.cm.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final String jwtSecret ="SecretKeyForJWTAuthenticationWithMinimum256BitsLengthRequired";
    private final String jwtPrefix = "Bearer";

//    @Value("${jwt.secret}")
//    private final String secret;
//    @Value("${jwt.prefix}")
//    private String prefix;
//    @Value("${jwt.expirationMs}")
//    private long expirationMs;

//    public JwtAuthFilter(String jwtSecret, String jwtPrefix) {
//        this.jwtSecret = jwtSecret;
//        this.jwtPrefix = jwtPrefix;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        
        String header = request.getHeader("Authorization");
        
        if (header == null || !header.startsWith(jwtPrefix)) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String token = header.replace(jwtPrefix, "");
        
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            
            String username = claims.getSubject();
            List<String> authorities = claims.get("authorities", List.class);
            
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );
            
            SecurityContextHolder.getContext().setAuthentication(auth);
            
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
        
        filterChain.doFilter(request, response);
    }
}