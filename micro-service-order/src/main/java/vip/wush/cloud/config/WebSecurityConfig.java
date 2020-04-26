//package vip.wush.cloud.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
///**
// * @ClassName: WebSecurityConfig
// * @Description: spring security 配置类
// * @Author: wush
// * @Date: 2020/4/23 21:26
// */
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity.httpBasic();
//        httpSecurity.authorizeRequests().anyRequest().authenticated().and().addFilterAt(new MyFilter(), CsrfFilter.class).httpBasic();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        //明文编码器, 这是一个不做任何操作的密码编码器，spring提供给我们做明文测试的
//        return NoOpPasswordEncoder.getInstance();
////        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    private CustomUserDetailService userDetailService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)  throws Exception{
//        auth.userDetailsService(this.userDetailService);
//    }
//
//    @Component
//    class CustomUserDetailService implements UserDetailsService{
//
//        /**
//         * 模拟两个账号：
//         * ① 账号是 user， 密码是 user, 角色是 user-role
//         * ② 账号是 admin， 密码是 admin, 角色是 admin-role
//         *
//         * @param userName 用户名
//         * @return
//         * @throws UsernameNotFoundException
//         */
//        @Override
//        public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//            if("user".equals(userName)){
//
//                return new User("user", "password1",true,true,true,true,getAuthorities("user-role"));
////                return new SecurityUser("user", "password1", "user-role");
//            }else if ("admin".equals(userName)){
//
//                return new User("admin", "password1",true,true,true,true,getAuthorities("admin-role"));
////                return new SecurityUser("admin", "password1", "admin-role");
//            }else {
//                return null;
//            }
//        }
//
//        public Collection<? extends GrantedAuthority> getAuthorities(String role) {
//            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
//            authorities.add(authority);
//            return authorities;
//        }
//    }
//
//
//    class SecurityUser implements UserDetails{
//
//        private static final long serialVersionUID = 1L;
//
//        private Long id;
//        private String username;
//        private String password;
//        private String role;
//
//
//        public SecurityUser(String username, String password, String role) {
//            this.username = username;
//            this.password = password;
//            this.role = role;
//        }
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role);
//            authorities.add(authority);
//            return authorities;
//        }
//
//        @Override
//        public String getPassword() {
//            return null;
//        }
//
//        @Override
//        public String getUsername() {
//            return null;
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return false;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return false;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return false;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return false;
//        }
//    }
//
//
//}
//
