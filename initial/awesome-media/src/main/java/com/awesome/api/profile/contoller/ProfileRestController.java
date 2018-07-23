package com.awesome.api.profile.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awesome.api.profile.service.ProfileService;
import com.awesome.api.profile.vo.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/v1")
public class ProfileRestController {
	
	private static Logger logger = LoggerFactory.getLogger(ProfileRestController.class);
	
	
	private ProfileService profileService;
	
	@Autowired
	public ProfileRestController(ProfileService profileService) {
		this.profileService = profileService;
	}

	@RequestMapping(path="/profiles", method=RequestMethod.GET, name="getProfiles")
	public List<Profile> getProfiles(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		List<Profile> profiles = profileService.getProfiles(username);
		return profiles;
	}
	
}
