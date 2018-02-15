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

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.test.demo.response.UserResponse;
import com.example.test.demo.service.UserService;

import io.swagger.annotations.ApiOperation;

/**
 * <<Description Here>>
 * @author Munal
 * @version 
 * @since , 12 Feb 2018
 */
@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
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
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value = "Search with the appropriate values ")
	public ResponseEntity<Object> getAllUsers(
			@RequestParam(required = false,value = "search") String search,
			@RequestParam(value = "sort", required = false) Direction sort,
			@RequestParam(value = "page", required = false,defaultValue="0") int page,
			@RequestParam(value = "size", required = false,defaultValue="5") int size)
			{
		Map<Object, Object> responseMap=userService.getAllUsers(sort,page,search,size);
		return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
	}

}
