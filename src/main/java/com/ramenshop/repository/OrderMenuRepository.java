package com.ramenshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ramenshop.data.OrderMenu;

@Repository
public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
	
	@Query(value = "SELECT d.name, d.price, SUM(b.count) 판매수량, d.price*SUM(b.count) 판매액, SUM(b.order_menu_price)-d.price*SUM(b.count) 옵션판매액, SUM(b.order_menu_price) 총판매액 FROM order_menu b\r\n"
			+ "INNER JOIN menu d ON b.menu_id =d.menu_id\r\n"
			+ "INNER JOIN ORDERs a ON a.order_id = b.order_id\r\n"
			+ "WHERE a.order_time BETWEEN :fromdate and :todate AND d.name = :menu\r\n"
			+ "GROUP BY d.menu_id", nativeQuery = true)
	public List<Object> selectAllSQL(@Param("fromdate") String fromdate,@Param("todate") String todate
			,@Param("menu") String menu);
	
//	@Query(value = "SELECT d.name, d.price, SUM(b.count) 판매수량, d.price*SUM(b.count) 판매액, SUM(b.order_menu_price)-d.price*SUM(b.count) 옵션판매액, SUM(b.order_menu_price) 총판매액 FROM order_menu b\r\n"
//			+ "INNER JOIN menu d ON b.menu_id =d.menu_id\r\n"
//			+ "INNER JOIN ORDERs a ON a.order_id = b.order_id\r\n"
//			+ "GROUP BY d.menu_id", nativeQuery = true)
//	public List<Object> selectAllSQL();
	
	@Query(value = "SELECT d.name, d.price, SUM(b.count) 판매수량, d.price*SUM(b.count) 판매액, SUM(b.order_menu_price)-d.price*SUM(b.count) 옵션판매액, SUM(b.order_menu_price) 총판매액 FROM order_menu b\r\n"
			+ "INNER JOIN menu d ON b.menu_id =d.menu_id\r\n"
			+ "INNER JOIN ORDERs a ON a.order_id = b.order_id\r\n"
			+ "WHERE a.order_time BETWEEN :fromdate and :todate\r\n"
			+ "GROUP BY d.menu_id", nativeQuery = true)
	public List<Object> selectAllSQL(@Param("fromdate") String fromdate,@Param("todate")String todate);
	
	@Query(value = "SELECT SUM(b.order_menu_price) 총판매액 FROM order_menu b\r\n"
			+ "INNER JOIN ORDERs a ON a.order_id = b.order_id\r\n"
			+ "WHERE DATE(a.order_time) >= DATE_SUB(NOW(), INTERVAL :length DAY)", nativeQuery = true)
	public List<Object> selectAllSQL(@Param("length")String length);
	
}
