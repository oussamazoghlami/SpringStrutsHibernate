package com.tunisiana.tutorials.util;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.tunisiana.tutorials.model.UserAuthority;

/**
 * Spring security utils
 * 
 * @author oussama.zoghlami.ext
 * 
 */
public class SpringSecurityUtil {

	/**
	 * Method allowing to return the spring security user associated to our
	 * model user
	 * 
	 * @param user
	 * @return
	 */
	public static User getSpringSecurityUser(com.tunisiana.tutorials.model.User user) {
		// get the user properties
		User springUser = null;
		String username = user.getUsername();
		String password = user.getPassword();
		boolean enabled = user.isEnabled();
		boolean accountNonExpired = user.isAccountNonExpired();
		boolean credentialsNonExpired = user.isCredentialsNonExpired();
		boolean accountNonLocked = user.isAccountNonLocked();

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (UserAuthority userAuthority : user.getUserAuthorithies()) {
			authorities.add(new GrantedAuthorityImpl(userAuthority.getAuthority().getRole()));
		}

		springUser = new User(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		return springUser;
	}

	/**
	 * This method allow to return the spring authenticated user
	 * 
	 * @return Authenticated userDetails
	 */
	public static UserDetails getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return userDetails;
		}
		return null;
	}
}
