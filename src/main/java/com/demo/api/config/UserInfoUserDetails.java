package com.demo.api.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static com.demo.api.constants.CommonConstant.COMMA;
import com.demo.api.entities.UserInfo;

import lombok.Data;

/**
 * User Infor User Details.
 * 
 * @author QuynhNN
 */
@Data
public class UserInfoUserDetails implements UserDetails {
    
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(UserInfo userInfo) {
        name = userInfo.getUsername();
        password = userInfo.getPassword();
        authorities = Arrays.stream(userInfo.getRole().split(COMMA)).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
