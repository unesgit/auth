package ma.cam.auth.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class JwtTokenCreator {

	private static final String GROUPS_LABEL = "GROUPS";
	private static final String SITE_ID = "SITE_ID";
	private static final String USER = "USER";
	private static final String ROLES_LABEL = "ROLES";

	private long validityInMilliseconds;

	private String username;
	private String siteCode;
	@Getter
	private String token;
	private List<String> roles;
	private List<String> subroles;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Key privateKey;

	@SuppressWarnings("unchecked")
	public JwtTokenCreator(Authentication authentication, Key privateKey, long tokenExpirationTime) {
		final Map<String, Object> details = (Map<String, Object>) authentication.getDetails();
		username = authentication.getName();
		roles = authentication.getAuthorities().stream().map(ga -> ga.getAuthority()).collect(Collectors.toList());
		this.privateKey = privateKey;
		validityInMilliseconds = tokenExpirationTime;
	}

	/**
	 * Generate a Token
	 * 
	 * @param username
	 * @param role
	 * @return
	 */
	public JwtTokenCreator create() {

		Claims claims = Jwts.claims().setSubject(username);
		claims.put(GROUPS_LABEL, roles);
		claims.put(SITE_ID, siteCode);
		claims.put(USER, !StringUtils.isEmpty(username) ? username.toLowerCase() : "");
		claims.put(ROLES_LABEL, subroles);

		token = buildToken(claims);
		return this;
	}

	/**
	 * @param claims
	 * @return
	 */
	private String buildToken(Claims claims) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.RS512, privateKey)//
				.compact();
	}

}
