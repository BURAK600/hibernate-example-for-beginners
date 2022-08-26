package com.bilgeadam.hibernateexample.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bilgeadam.hibernateexample.repository.PostRepository;
import com.bilgeadam.hibernateexample.repository.entity.Address;
import com.bilgeadam.hibernateexample.repository.entity.EAddressType;
import com.bilgeadam.hibernateexample.repository.entity.EGenger;
import com.bilgeadam.hibernateexample.repository.entity.Name;
import com.bilgeadam.hibernateexample.repository.entity.Post;
import com.bilgeadam.hibernateexample.repository.entity.User;
import com.bilgeadam.hibernateexample.repository.entity.UserDetail;

public class PostController {

	public static void main(String[] args) {

		PostRepository postRepository = new PostRepository();

		Date date = new Date();

		Address address1 = new Address("Fathidc", "Turkiye", "Ankara");
		Address address2 = new Address("cankata", "Turkiye", "Ankara");

		Map<EAddressType, Address> map = new HashMap<EAddressType, Address>();

		map.put(EAddressType.BUSINESS, address1);
		map.put(EAddressType.BUSINESS, address2);

		List<String> interest = new ArrayList<String>();
		interest.add("Sinema");
		interest.add("Muzik");

		UserDetail userDetail = new UserDetail(EGenger.MAN, new Name("burak", null, "ÖZER"), 50, map, interest);

		userDetail.setAreasOfInterest(interest);
		userDetail.setAddress(map);

		User user2 = new User("assadadacscfrrgrdadadwq1", "sscscsc", userDetail);

		postRepository.save(new Post("içerbik1", date, user2));

		System.out.println("-------------------------------------------------------------------");

		postRepository.findAll().forEach(System.out::println);

	}

}
