package com.bilgeadam.hibernateexample.repository;

import java.util.List;

public interface ICrud<T> {

	void save(T t);

	void update(T t, long id);

	void delete(long id);

	List<T> findAll();

	T findById(long id);

}
