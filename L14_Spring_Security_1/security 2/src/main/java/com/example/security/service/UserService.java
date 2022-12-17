package com.example.security.service;

import com.example.security.entity.UserInfo;
import com.example.security.repository.UserInfoRepository;
import com.example.security.request.CreateNewUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static com.example.security.configuration.MyAppPersistedSecurity.USER_SERVICE;

@Service(USER_SERVICE)
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public UserInfo createNewUser(CreateNewUserRequest request){
        UserInfo newUser = request.toUserInfo();
        Optional<UserInfo> existingUser = userInfoRepository.findUserInfoByEmail(newUser.getEmail());
        if(existingUser.isPresent()){
            throw new RuntimeException("User Already Exists");
        }
        // set encoded password
        newUser.setPassword(passwordEncoder.encode(request.getPasswordRaw()));
        return saveOrUpdate(newUser);
    }


    public UserInfo saveOrUpdate(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> existingUser = userInfoRepository.findUserInfoByEmail(username);
        if(existingUser.isPresent()){
            return existingUser.get();
        }

        /**
         * return anonymous user
         */
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton(new SimpleGrantedAuthority("ANONYMOUS"));
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return "ANONYMOUS";
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
