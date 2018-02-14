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
import java.util.List;

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
	public Page<User> getAllUsers(Direction sort, int page,String search,int size) {
				
		if(search!=null) {
			Page<User> user=userRepository.findByFirstName(search,new PageRequest(page, size, sort, "firstName"));
			
			if(user==null) {
				throw new NullPointerException("No user found of firstname:"+search);
			}
			return user;
		}
		Page<User> user=(Page<User>) userRepository.findAll();
		return user;
	}
	public List<UserResponse> getItems(Page<User> user) {
		List<UserResponse> userResponse=new ArrayList<>();
		for(User r: user.getContent()) {
			UserResponse ur =new UserResponse();
			ur.setEmail(r.getEmail());
			ur.setFirstName(r.getFirstName());
			ur.setLastName(r.getLastName());
			ur.setMiddleName(r.getMiddleName());
			ur.setPhoneNumber(r.getPhoneNumber());
			userResponse.add(ur);
		}
		return userResponse;
	}
}
