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
package com.example.test.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.demo.dto.UserDto;
import com.example.test.demo.model.User;
import com.example.test.demo.response.UserResponse;
import com.example.test.demo.service.UserService;

/**
 * <<Description Here>>
 * @author Munal
 * @version 
 * @since , 12 Feb 2018
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody UserDto userDto){
		userService.createUser(userDto);		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable Long id){
		UserResponse user = userService.getUser(id);
		Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("response", user);
		return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method=RequestMethod.GET,  params= {"search","page","sort","size"})
	public ResponseEntity<Object> getAllUsers(
			@RequestParam(value = "sort", required = false) Direction sort,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size,
			@RequestParam(value = "search", required = false) String search){
		Page<User> user=userService.getAllUsers(sort,page,search,size);
		Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("response", user.getContent());
		responseMap.put("noOfItems",user.getNumberOfElements());
		responseMap.put("totalNoOfItems",user.getTotalElements());
		responseMap.put("noOfPages",user.getTotalPages());
		responseMap.put("pageNumber",user.getNumber());
		
		return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
	}

}
