package com.spring.nong4.security;

import com.spring.nong4.security.model.CustomUserPrincipal;
import com.spring.nong4.user.UserMapper;
import com.spring.nong4.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper mapper;

    @Override // jsp에서
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity param = new UserEntity();
        System.out.println("email--Security-- : " + email);
        param.setEmail(email);
        UserEntity loginUser = mapper.selUser(param);
        if(loginUser == null) {
            return null; //아이디가 없는 상태
        }
        return new CustomUserPrincipal(loginUser); //아이디는 있는 상태
    }

    public UserEntity loadUserByUsernameAndProvider(String id, String provider) throws UsernameNotFoundException {
        UserEntity param = new UserEntity();
        param.setProvider(provider);
        param.setEmail(id);
        return  mapper.selUser(param);
    }

    public int join(UserEntity param) {
        if(param == null) {
            return 0;
        }
        return mapper.join(param);
    }
}
