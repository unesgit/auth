package ma.cam.auth.config.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ma.cam.auth.business.UserServiceImpl;

/**
 * @author ChaK'S
 * @project pom-parent
 * @date 02/05/2020
 */

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public Authentication authenticate(final Authentication authentication) {

		log.debug("Processing authentication request for user: {}", authentication.getPrincipal());

		UserDetails user = userService.findByUserNameAndPassword(authentication.getName(),
				authentication.getCredentials().toString());

		if (user == null) {
			throw new BadCredentialsException("Username/Password does not match for " + authentication.getPrincipal());
		}
		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
