package br.edu.utfpr.sgcc.controllers;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.sgcc.models.MyUserDetails;

@Controller
public class IndexController {
	@GetMapping("/")
	public ModelAndView welcome() throws SQLException {
//		int i = 10/0;
		try {
			
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (user == null) {
				ModelAndView modelAndView = new ModelAndView("index");
//				AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
//						DataBaseConfig.class);
//				SessionFactory factory = DBConfig.getSessionFactory();

				return modelAndView;

			} else {

				Map<String, String> roleTargetUrlMap = new HashMap<>();
				roleTargetUrlMap.put("ROLE_USER", "/user/dashboard");
				roleTargetUrlMap.put("ROLE_ADMIN", "/admin");
				roleTargetUrlMap.put("ROLE_RESIDENT", "/resident/dashboard");

				final Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
				for (final GrantedAuthority grantedAuthority : authorities) {

					String authorityName = grantedAuthority.getAuthority();
					if (roleTargetUrlMap.containsKey(authorityName)) {
						return new ModelAndView("redirect:" + roleTargetUrlMap.get(authorityName));
					}
				}
			}
			return new ModelAndView("index");
		} catch (Exception e) {
			return new ModelAndView("index");
		}
	}
}
