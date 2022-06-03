package ma.cam.auth.business;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

	public UserDetails findByUserNameAndPassword(String username, String password) throws UsernameNotFoundException {

		return new UserDetails() {

			@Override
			public boolean isEnabled() {
				return false;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return false;
			}

			@Override
			public boolean isAccountNonLocked() {
				return false;
			}

			@Override
			public boolean isAccountNonExpired() {
				return false;
			}

			@Override
			public String getUsername() {
				return "younes";
			}

			@Override
			public String getPassword() {
				return "nadir";
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}
		};
	}
}