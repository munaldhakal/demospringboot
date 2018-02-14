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
package com.example.test.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.demo.model.User;


/**
 * <<Description Here>>
 * @author Munal
 * @version 
 * @since , 12 Feb 2018
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 *<<Add description here>>
	 * @return
	 * @author
	 * @since , Modified In: @version, By @author
	 */
	Page<User> findByFirstName(String search,Pageable pageable);


}
