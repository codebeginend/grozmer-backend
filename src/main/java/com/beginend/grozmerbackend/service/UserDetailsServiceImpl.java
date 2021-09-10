package com.beginend.grozmerbackend.service;

import com.beginend.grozmerbackend.dao.IUserDao;
import com.beginend.grozmerbackend.dto.UserDto;
import com.beginend.grozmerbackend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private IUserDao userDao;
    private UserDto userDto = new UserDto();

    public UserDetailsServiceImpl(IUserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDto.transferEntityToModel(this.userDao.findByLogin(login));
        return new UserDetails(){
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return new ArrayList<GrantedAuthority>(Collections.singleton(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return user.getRole().getName();
                    }
                }));
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getLogin();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
    }


}
