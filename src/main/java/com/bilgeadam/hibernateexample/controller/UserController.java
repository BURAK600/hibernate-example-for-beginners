package com.bilgeadam.hibernateexample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bilgeadam.hibernateexample.repository.UserDao;
import com.bilgeadam.hibernateexample.repository.UserRepository;
import com.bilgeadam.hibernateexample.repository.entity.Address;
import com.bilgeadam.hibernateexample.repository.entity.EAddressType;
import com.bilgeadam.hibernateexample.repository.entity.EGenger;
import com.bilgeadam.hibernateexample.repository.entity.Name;
import com.bilgeadam.hibernateexample.repository.entity.User;
import com.bilgeadam.hibernateexample.repository.entity.UserDetail;

public class UserController {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		UserRepository userRepository = new UserRepository();

		// user = new User("burak ozer1", "mlatyaozer", "Erkek");

		// userDao.save(user);

		// User user1 = userDao.findById(1);

		// userDao.delete(1);
		// userDao.update(new User("Gamze", "asasasasevveva", "Kadın"), 3);

		// userDao.findAll();

		// userRepository.save(user);

		// System.out.println(userRepository.findById(2));
		// userRepository.findAll().forEach(s -> System.out.println(s));

		// userRepository.update(new User("burakdddd ozer1123", "Malatyaburakozer",
		// "Erkek"), 1);

		// userRepository.delete(4);

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

		// userRepository.save1(userDetail);

		userRepository.save(user2);

		// userRepository.save(user2);

		// userDao.save(userDetail);

		// userRepository.startLike("s");

		// userRepository.postNumberGreaterThanValue(20);

	}

}
