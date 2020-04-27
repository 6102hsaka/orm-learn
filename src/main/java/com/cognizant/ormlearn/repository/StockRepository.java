package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Stock;


@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	@Query("SELECT s FROM Stock s WHERE st_code = 'FB' and st_date like '2019-09%' ")
	List<Stock> findAllTillSep();
	
	@Query("SELECT s FROM Stock s WHERE st_code = 'GOOGL' AND st_close > 1250")
	List<Stock> findGoogle1250();
	
	List<Stock> findTop3ByOrderByVolumeDesc();
	List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}