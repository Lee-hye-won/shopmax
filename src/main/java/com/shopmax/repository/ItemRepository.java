package com.shopmax.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.shopmax.constant.itemSellStatus;
import com.shopmax.entity.Item;
													// <해당 repository에서 사용할 Entity, Entity클래스의 기본키 타입>
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	// select * from item where item_nm = ? 
	List<Item> findByItemNm(String itemNm);
	
	// select * from item where item_nm=? and item_sell_status = ?
	List<Item> findByItemNmAndItemSellStatus(String itemNm, itemSellStatus itemSellStatus);
	
	// select * from item where price between 10004 and 10008
	List<Item> findByPriceBetween(int price1, int price2);
	
	// select * from item where regTime > 20230101121244
	List<Item> findByRegTimeAfter(LocalDateTime regTime);
	
	// select * from item where itemSellStatus not null;
	List<Item> findByItemSellStatusNotNull();
	
	// select * from item where itemNm like "%설명1"
	List<Item> findByItemDetailLike(String itemDetail);
	
	// select * from item where itenNm ="테스트상품1" or itemDetail ="테스트상품 상세설명5"
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	// select * from item where price>10005 order by desc;
	List<Item> findByPriceLessThanOrderByPriceDesc(int price);
	
	// JPQL(non native 쿼리) -> 컬럼명, 테이블명은 반드시 엔티티클래스 기준 
	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	
	// JPQL(native 쿼리) -> h2 데이터베이스를 기준으로한 쿼리문 작성
	@Query(value="select * from item where item_detail like %:itemDetail% order by price desc" , nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

	// select * from item where price >10005
	@Query("select i from Item i where i.price >= :price")
	List<Item> findByGreaterThanEqual(@Param("price") int price);
	
	// select * from item where itemNm="테스트 상품1" and itemStatus = SELL
	@Query("select i from Item i where i.itemNm =:itemNm and i.itemSellStatus =:itemSellStatus")
	List<Item> findByItemNmAndItemSellStatus1(@Param("itemNm") String itemNm, @Param("itemSellStatus") itemSellStatus itemSellStatus);
}
