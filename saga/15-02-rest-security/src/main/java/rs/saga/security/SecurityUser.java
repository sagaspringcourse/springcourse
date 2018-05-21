package rs.saga.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rs.saga.domain.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SecurityUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String password;
    private Collection<GrantedAuthority> authorities = new ArrayList<>();
    private Long id;
    private String email;
    private String username;
    private Date lastPasswordResetDate;

    public SecurityUser(Player user, List<String> roles) {
        if (user != null) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.password = user.getCredentials().getPassword();
            this.username = user.getCredentials().getUsername();

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

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}