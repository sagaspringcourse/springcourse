package rs.saga.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rs.saga.domain.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser extends Player implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String password;
    private Collection<GrantedAuthority> authorities  = new ArrayList<>();

    public SecurityUser(Player user, List<String> roles) {
        if (user != null) {
            this.setId(user.getId());
            this.setFirstName(user.getFirstName());
            this.setEmail(user.getEmail());
            this.setPassword(user.getCredentials().getPassword());

            //Collection<GrantedAuthority> authorities
            for (String role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                authorities.add(authority);
            }
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
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

    public void setPassword(String password) {
        this.password = password;
    }
}