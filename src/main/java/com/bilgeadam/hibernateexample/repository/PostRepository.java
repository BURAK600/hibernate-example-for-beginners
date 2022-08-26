package com.bilgeadam.hibernateexample.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bilgeadam.hibernateexample.repository.entity.Post;
import com.bilgeadam.hibernateexample.utility.HibernateUtility;

public class PostRepository implements ICrud<Post> {

	private Session session;
	private EntityManager entityManager;
	private CriteriaBuilder criteriaBuilder;
	private Transaction transaction;

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

	public void save(Post t) {

		try {
			openSession();

			session.save(t);

			successClose();

		} catch (Exception e) {

			System.out.println("Baglantı hatası");

			errorSession();

		}

	}

	@Override
	public void update(Post t, long id) {

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

			Post post = findById(id);

			openSession();

			session.delete(post);

			successClose();

			System.out.println(post + " basariyla silindi");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	@Override
	public List<Post> findAll() {

		// select * from tblmusteri

		entityManager = HibernateUtility.getSessionFactory().createEntityManager();
		criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
		Root<Post> root = criteriaQuery.from(Post.class);
		criteriaQuery.select(root);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Post findById(long id) {

		// select * from tblpost where id = 'id'

		entityManager = HibernateUtility.getSessionFactory().createEntityManager();
		criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);

		Root<Post> root = criteriaQuery.from(Post.class);

		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		return entityManager.createQuery(criteriaQuery).getSingleResult();

	}

	public Optional<Post> findByIdOptional(long id) {
		Post post = null;
		CriteriaQuery<Post> criteria = criteriaBuilder.createQuery(Post.class);
		Root<Post> root = criteria.from(Post.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get("id"), id));
		try {
			post = entityManager.createQuery(criteria).getSingleResult();
			return Optional.of(post);
		} catch (Exception e) {
			return Optional.ofNullable(null);
		}

	}

}
