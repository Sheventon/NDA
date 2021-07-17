package ru.itis.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * created: 17-07-2021 - 17:45
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public class JwtAuthentication implements Authentication {

    private final String token;

    private boolean isAuthenticated;

    private UserDetails userDetails;

    public JwtAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getUsername();
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails == null ? null : userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean state) throws IllegalArgumentException {
        this.isAuthenticated = state;
    }

    @Override
    public String getName() {
        return token;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}
