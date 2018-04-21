package rs.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rs.saga.config.SecurityUser;
import rs.saga.domain.Player;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  private final IPlayerService userService;

  @Autowired
  public CustomUserDetailsService(IPlayerService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String userName)
      throws UsernameNotFoundException {
    Player user = userService.findByUsername(userName);
    List<String> roles = userService.findPlayerRoles(userName);
    if(user == null){
      throw new UsernameNotFoundException("UserName "+userName+" not found");
    }
    return new SecurityUser(user, roles);
  }
}