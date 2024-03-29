package com.eksad.latihanrest.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.eksad.latihanrest.model.Transaction;

public interface TransactionDao extends CrudRepository<Transaction, Long> {
	//yg dipake nama enitity bukan dana table
	@Query("select sum(quantity) from TransactionDetail "
			+ "where transaction.id = ?1")
	public Integer findTotalItemByTransactionId(Long id); // penamaan ga pengaruh karana ada di @query
	
	@Query("select td.transaction.id as transactionId, "
			+ "sum(td.quantity * pr.price) as total "
			+ "from TransactionDetail td "
			+ "join td.product pr "
			+ "group by td.transaction.id")
	public List<Object[]> findTotalNominalPerTransaction();

// yg dibawah kalau mau copas lgsung query yg dibuat di database
	@Query(value =
			"select td.transaction.id, sum(td.quantity * pr.price)"
			+ "from TransactionDetail td "
			+ "join td.product pr ON pr.id = td.product_id "
			+ "group by td.transaction.id ", nativeQuery = true)
	public List<Object[]> findTotalNominalPerTransactionNative();
}
