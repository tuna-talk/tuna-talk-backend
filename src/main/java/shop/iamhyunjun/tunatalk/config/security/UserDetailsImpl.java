package shop.iamhyunjun.tunatalk.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shop.iamhyunjun.tunatalk.entity.user.User;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;
    private final String username;

    public UserDetailsImpl(User user, String username) {
        this.user = user;
        this.username = username;
    }

    public User getUser() {
        return user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return null;
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
}
