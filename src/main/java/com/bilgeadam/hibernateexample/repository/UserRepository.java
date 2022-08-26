package com.bilgeadam.hibernateexample.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bilgeadam.hibernateexample.repository.entity.User;
import com.bilgeadam.hibernateexample.utility.HibernateUtility;

public class UserRepository implements ICrud<User> {

	private Session session;
	private EntityManager entityManager;
	private CriteriaBuilder criteriaBuilder;
	private Transaction transaction;

	public UserRepository() {

		entityManager = HibernateUtility.getSessionFactory().createEntityManager();
		criteriaBuilder = entityManager.getCriteriaBuilder();

	}

	private void openSession() {
		session = HibernateUtility.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}

	private void successClose() {
		transaction.commit();
		session.close();

	}

	private void errorSession() {
		transaction.rollback();
		session.close();

	}

	@Override
	public void update(User t, long id) {

		try {

			openSession();

			t.setId(id);

			session.update(t);
			successClose();

			System.out.println("Kullanici basariyla guncellendi");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	@Override
	public void delete(long id) {

		try {

			User user = findById(id);

			openSession();

			session.delete(user);

			successClose();

			System.out.println(user + " basariyla silindi");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	@Override
	public List<User> findAll() {

		// select * from tblmusteri

		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public User findById(long id) {

		// select * from tbluser where id = 'id'

		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

		Root<User> root = criteriaQuery.from(User.class);

		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		return entityManager.createQuery(criteriaQuery).getSingleResult();

	}

	public Optional<User> findByIdOptional(long id) {
		User user = null;
		CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get("id"), id));
		try {
			user = entityManager.createQuery(criteria).getSingleResult();
			return Optional.of(user);
		} catch (Exception e) {
			return Optional.ofNullable(null);
		}

	}

	public void startLike(String value) {

		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		Root<User> root = query.from(User.class);

		query.select(root).where(criteriaBuilder.like(root.get("name").get("firstName"), value + "%"));

		List<User> users = entityManager.createQuery(query).getResultList();

		users.forEach(System.out::println);

	}

	public void postNumberGreaterThanValue(int value) {

		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		Root<User> root = query.from(User.class);

		query.select(root).where(criteriaBuilder.greaterThan(root.get("postNumber"), value));

		List<User> users = entityManager.createQuery(query).getResultList();

		users.forEach(System.out::println);

	}

	public void postNumberSum() {

		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<User> root = query.from(User.class);

		query.multiselect(criteriaBuilder.sum(root.get("postNumber")));

		long sumNumber = entityManager.createQuery(query).getSingleResult();

		System.out.println(sumNumber);

	}

	@Override
	public void save(User t) {
		try {
			openSession();

			session.save(t);

			successClose();

		} catch (Exception e) {

			System.out.println("Baglantı hatası");

			errorSession();

		}

	}

}
