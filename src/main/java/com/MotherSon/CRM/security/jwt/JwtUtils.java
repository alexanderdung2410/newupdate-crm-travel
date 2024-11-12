package com.MotherSon.CRM.security.jwt;

import java.util.Date;
import java.util.TimeZone;

//import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.MotherSon.CRM.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${bezkoder.app.jwtSecret}")
  private String jwtSecret;

  @Value("${bezkoder.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {
	  TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

	  
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    long expirationTime = 1000 * 60 * 60 * 24 * 7;
    
    System.out.println(new Date((new Date()).getTime()));
    return Jwts.builder()
        .setSubject((userPrincipal.getUsername()))
        .setIssuedAt(new Date())
       // .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
        
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
    	
    	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

    	System.out.println("========== 1==============");
    	
    	Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken)
                .getBody();

// Get the issued at and expiration times
Date issuedAt = claims.getIssuedAt();
Date expiration = claims.getExpiration();
    	
System.out.println("JWT Issued At: " + issuedAt);
System.out.println("JWT Expiration Time: " + expiration);
      Jwts.parser().setSigningKey(jwtSecret).setAllowedClockSkewSeconds(60).parseClaimsJws(authToken);
      return true;
    } /*catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
    	
    	
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());*/
    catch (Exception e) {
    	e.printStackTrace();
        
      }

    return false;
  }

  public static String getJwtFromRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 String bearerToken = request.getHeader("Authorization");
		    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
		        return bearerToken.substring(7);
		    }
		    return null;
	}
}
