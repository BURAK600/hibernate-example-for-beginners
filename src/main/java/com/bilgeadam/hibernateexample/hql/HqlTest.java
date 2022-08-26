package com.bilgeadam.hibernateexample.hql;

import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.bilgeadam.hibernateexample.repository.entity.User;
import com.bilgeadam.hibernateexample.repository.entity.UserDetail;
import com.bilgeadam.hibernateexample.utility.HibernateUtility;

public class HqlTest {

	public static void main(String[] args) {

		// findAll();

		// startLike("s");

		// GreaterThan(10);

	}

	public static void findAll() {

		String hql = "select u from User as u";
		Session session = HibernateUtility.getSessionFactory().openSession();

		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);

		List<User> users = typedQuery.getResultList();

		users.forEach(System.out::println);

	}

	public static void startLike(String value) {

		String hql = "select u from UserDetail as u  where  u.name.firstName   like :x  ";
		Session session = HibernateUtility.getSessionFactory().openSession();
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql, UserDetail.class);
		typedQuery.setParameter("x", value + "%");
		List<UserDetail> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}

	public static void greaterThan(int number) {

		String hql = "select u from UserDetail as u where u.postNumber >:key";
		Session session = HibernateUtility.getSessionFactory().openSession();
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql, UserDetail.class);
		typedQuery.setParameter("key", number);
		List<UserDetail> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}

	public static void sumPostNumber() {

		String hql = "select sum(u.postNumber) from UserDetail as u   ";
		Session session = HibernateUtility.getSessionFactory().openSession();
		long sum = session.createQuery(hql, Long.class).getSingleResult();
		System.out.println(sum);
	}

	public static void minPostNumber() {
		String hql = "select min(u.postNumber) from UserDetail as u";
		Session session = HibernateUtility.getSessionFactory().openSession();
		Integer user = session.createQuery(hql, Integer.class).getSingleResult();
		System.out.println(user);
	}

	public static void maxPostNumber() {
		String hql = "select max(u.postNumber) from UserDetail as u   ";
		Session session = HibernateUtility.getSessionFactory().openSession();
		Integer user = session.createQuery(hql, Integer.class).getSingleResult();
		System.out.println(user);
	}

	public static void maxPostNumberUser() {
		String hql = "select  max(ud.postNumber),ud from UserDetail as ud group by ud.postNumber,ud";
		Session session = HibernateUtility.getSessionFactory().openSession();
		TypedQuery<Object[]> typedQuery = session.createQuery(hql, Object[].class);
		List<Object[]> list = session.createQuery(hql).getResultList();
		for (Object[] arr : list) {
			System.out.println(Arrays.toString(arr));
		}

	}

	public static void avgPostNumber() {
		String hql = "select avg(u.postNumber) from UserDetail as u   ";
		Session session = HibernateUtility.getSessionFactory().openSession();
		Double user = session.createQuery(hql, Double.class).getSingleResult();
		System.out.println(user);
	}

	public static void betweenPostNumber(int number1, int number2) {
		String hql = "select u from UserDetail as u  where  u.postNumber between :number1 and :number2";
		Session session = HibernateUtility.getSessionFactory().openSession();
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql, UserDetail.class);
		typedQuery.setParameter("number1", number1);
		typedQuery.setParameter("number2", number2);
		List<UserDetail> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}

	public static void getTwoUser() {
		String hql = "select u from UserDetail as u  order by u.name.firstName asc";
		Session session = HibernateUtility.getSessionFactory().openSession();
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql, UserDetail.class);
		typedQuery.setMaxResults(2);
		List<UserDetail> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}

}
