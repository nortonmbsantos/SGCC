package br.edu.utfpr.sgcc.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private int id;
	private String userName;
	private String firstName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(String userName) {
		this.userName = userName;
	}

	public MyUserDetails(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.firstName = user.getFirstName();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	public MyUserDetails() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public List<GrantedAuthority> getAuthList() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.active;
	}

	public boolean hasAuthority(String auth) {
		for (GrantedAuthority ga : getAuthList()) {
			if (ga.getAuthority().equals(auth)) {
				return true;
			}
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
