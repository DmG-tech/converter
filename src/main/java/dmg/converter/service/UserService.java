package dmg.converter.service;

import dmg.converter.entity.Role;
import dmg.converter.entity.User;
import dmg.converter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    public User save(User user) {
        if (user != null) {
            user.setRoles(Set.of(Role.USER));
        }
        return repository.save(user);
    }

    public User get(int id) {
        return repository.get(id);
    }

    public User getByUsername(String username) {
        return repository.getByUsername(username);
    }
}
