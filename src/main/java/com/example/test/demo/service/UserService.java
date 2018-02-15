/*************************************************************************
 * 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained here in is, and remains
 * the property of Texas Imaginology and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here in are proprietary to Texas Imaginology. Dissemination of this
 * information or reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Texas Imaginology.
 * 
 */
package com.example.test.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.test.demo.dto.UserDto;
import com.example.test.demo.model.User;
import com.example.test.demo.repository.UserRepository;
import com.example.test.demo.response.UserResponse;

/**
 * <<Description Here>>
 * @author Munal
 * @version 
 * @since , 12 Feb 2018
 */
@Service
public class UserService{
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserRepository userRepository;
	public void createUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setFirstName(userDto.getFirstName());
		user.setMiddleName(userDto.getMiddleName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPhoneNumber(userDto.getPhoneNumber());
		userRepository.save(user);
	}
	public UserResponse getUser(Long id) {
		User user  = userRepository.getOne(id);
		UserResponse userResponse=new UserResponse();
		userResponse.setEmail(user.getEmail());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setMiddleName(user.getMiddleName());
		userResponse.setLastName(user.getLastName());
		userResponse.setPhoneNumber(user.getPhoneNumber());
		return userResponse;
	}
	/**
	 *<<Add description here>>
	 * @return
	 * @author
	 * @since , Modified In: @version, By @author
	 */
	public Map<Object, Object> getAllUsers(Direction sort, int page,String search,int size) {
		if(sort==null) {
			sort=Direction.ASC;
		}
		
		if(search!=null && search!="") {
			Page<User> user=userRepository.findByFirstName(search, new PageRequest(page,size,sort,"firstName"));
			return getResponse(user);
		}
		Page<User>user=userRepository.findAll(new PageRequest(page,size,sort,"firstName"));
		return getResponse(user);		
	}
	public Map<Object, Object> getResponse(Page<User> user){
		List<User> u=user.getContent();
		List<UserResponse> userResponse=new ArrayList<>();
		for(User ur:u) {
			UserResponse response=new UserResponse();
			response.setFirstName(ur.getFirstName());
			response.setEmail(ur.getEmail());
			response.setLastName(ur.getLastName());
			response.setMiddleName(ur.getMiddleName());
			response.setPhoneNumber(ur.getPhoneNumber());
			userResponse.add(response);
		}
		Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("response", userResponse);
		responseMap.put("noOfItems",user.getNumberOfElements());
		responseMap.put("totalNoOfItems",user.getTotalElements());
		responseMap.put("noOfPages",user.getTotalPages());
		responseMap.put("pageNumber",user.getNumber());
		return responseMap;
	}
}