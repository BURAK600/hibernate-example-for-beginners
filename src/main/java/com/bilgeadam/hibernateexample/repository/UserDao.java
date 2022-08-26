package com.bilgeadam.hibernateexample.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bilgeadam.hibernateexample.repository.entity.User;
import com.bilgeadam.hibernateexample.repository.entity.UserDetail;
import com.bilgeadam.hibernateexample.utility.HibernateUtility;

public class UserDao implements ICrud<User> {

	private Session session;
	private EntityManager entityManager;
	private CriteriaBuilder criteriaBuilder;
	private Transaction transaction;

	private void openSession() {
		session = HibernateUtility.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}

	public void save(UserDetail userDetail) {

		try {
			openSession();

			session.save(userDetail);

			session.getTransaction().commit();

		} catch (Exception e) {

			System.out.println("Baglantı hatası");

			session.getTransaction().rollback();

		}

	}

	public void save(User user) {

		try {
			openSession();

			session.save(user);

			session.getTransaction().commit();

		} catch (Exception e) {

			System.out.println("Baglantı hatası");

			session.getTransaction().rollback();

		}

	}

	public void update(User t, long id) {

		try {
			User user = findById(id);

			if (user != null) {
				user.setUserName(t.getUserName());
				user.setPassword(t.getPassword());

				openSession();

				session.update(user);

				session.getTransaction().commit();

				System.out.println("Kullanici basariyla guncellendi");
			}

		} catch (Exception e) {

			System.out.println("Baglantı hatası");

		}

	}

	public void delete(long id) {

		try {

			openSession();

			User user = findById(id);

			if (user != null) {

				session.remove(user);
				session.getTransaction().commit();

				System.out.println(user.getUserName() + " silindi");
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			session.close();
		}

	}

	public List<User> findAll() {

		try {

			openSession();
			String query = "select users from User as users ";
			TypedQuery<User> typedQuery = session.createQuery(query, User.class);
			List<User> userList = typedQuery.getResultList();

			for (User user : userList) {
				System.out.println(user);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public User findById(long id) {

		openSession();
		User user;

		try {

			user = session.find(User.class, id);

			if (user != null) {
				System.out.println("user bulundu " + user);
				return user;

			} else {
				System.out.println("user bulunamdı");

				return user;
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			session.close();
		}

		return null;
	}

}
