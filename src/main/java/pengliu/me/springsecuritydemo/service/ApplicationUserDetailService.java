package pengliu.me.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pengliu.me.springsecuritydemo.ApplicationUserRepository;

@Service
public class ApplicationUserDetailService implements UserDetailsService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.applicationUserRepository.findByUserName(username).stream().findFirst()
            .orElseThrow(() ->
                new UsernameNotFoundException(String.format("The user %s is not found!!", username))
            );
    }
}
