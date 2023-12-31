package com.shopmax.repository;

import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopmax.constant.itemSellStatus;
import com.shopmax.entity.Item;
import com.shopmax.entity.QItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest // bin 객체로 만든다 -> 스프링 컨테이너에 등록된다.
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;

	@PersistenceContext // 영속성 컨텍스트
	EntityManager em; // 엔티티 매니저(엔티티 관리)

	@Disabled
	@Test
	@DisplayName("상품 저장 테스트")
	public void createItemTest() {
		Item item = new Item();
		item.setItemNm("테스트 상품");
		item.setPrice(10000);
		item.setItemDetail("테스트 상품 상세설명");
		item.setItemSellStatus(itemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());

		// insert => save함수 이용
		Item savedItem = itemRepository.save(item); // 엔티티 객체에 값을 담아놓고 저장
		System.out.println(savedItem.toString());
	}

	// 여러개 저장하기
	public void createItemList() {

		for (int i = 1; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세설명" + i);
			item.setItemSellStatus(itemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());

			// insert => save함수 이용
			Item savedItem = itemRepository.save(item); // 엔티티 객체에 값을 담아놓고 저장
		}
	}

	// 여러개 저장하기
	public void createItemList2() {

		for (int i = 1; i <= 5; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세설명" + i);
			item.setItemSellStatus(itemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());

			// insert => save함수 이용
			Item savedItem = itemRepository.save(item); // 엔티티 객체에 값을 담아놓고 저장
		}

		for (int i = 6; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세설명" + i);
			item.setItemSellStatus(itemSellStatus.SOLD_OUT);
			item.setStockNumber(0);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());

			// insert => save함수 이용
			Item savedItem = itemRepository.save(item); // 엔티티 객체에 값을 담아놓고 저장
		}
	}

	@Disabled
	@Test
	@DisplayName("상품 조회 테스트")
	public void findByItemNmTest() {
		this.createItemList(); // 데이터 10개 insert
		List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("퀴즈1-1")
	public void quiz1_1Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1", itemSellStatus.SELL);

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("퀴즈1-2")
	public void quiz1_2Test() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceBetween(10004, 10008);

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("퀴즈1-3")
	public void quiz1_3Test() {
		this.createItemList();
		LocalDateTime regTime = LocalDateTime.of(2023, 1, 1, 12, 12, 44);
		List<Item> itemList = itemRepository.findByRegTimeAfter(regTime);

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("퀴즈1-4")
	public void quiz1_4Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemSellStatusNotNull();

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("퀴즈1-5")
	public void quiz1_5Test() {
		List<Item> itemList = itemRepository.findByItemDetailLike("%설명1");

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("퀴즈1-6")
	public void quiz1_6Test() {
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세설명1");

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("퀴즈1-7")
	public void quiz1_7Test() {
		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("@Query를 이용한 상품 조회 테스트")
	public void findByItemDetailTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세");

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("@Query(native)를 이용한 상품 조회 테스트")
	public void findByItemDetailByNativeTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세");

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("@Query를 이용한 퀴즈1")
	public void findByGreaterThanEqual() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByGreaterThanEqual(10005);

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("@Query를 이용한 퀴즈2")
	public void findByItemNmAndItemSellStatus1() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus1("테스트 상품1", itemSellStatus.SELL);

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("querydsl 조회 테스트")
	public void queryDslTest() {
		this.createItemList();
		JPAQueryFactory qf = new JPAQueryFactory(em); // 쿼리를 동적으로 생성하기 위한 객체
		QItem qItem = QItem.item;

		// 쿼리문을 실행했을 때 결과값을 담을 타입을 제네릭에 선언
		// select * from Item where item_sell_status = 'SELL'
		// and item_detail like "%테스트 상품 전체%"
		// order by price desc;
		JPAQuery<Item> query = qf.selectFrom(qItem).where(qItem.itemSellStatus.eq(itemSellStatus.SELL))
				.where(qItem.itemDetail.like("%테스트 상품 상세%")).orderBy(qItem.price.desc());

		List<Item> itemList = query.fetch(); // 쿼리문 실행

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("querydsl 조회 테스트2")
	public void queryDslTest2() {
		this.createItemList2();

		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;

		// 0부터 페이지 번호가 시작된다.
		Pageable page = PageRequest.of(0, 3); // of(조회할 페이지의 번호, 한 페이지 당 조회할 데이터의 갯수)

		// select * from item where item_sell_status = 'SELL'
		// and item_detail like '%테스트 상품 상세%'
		// and price > 10003;
		JPAQuery<Item> query = qf.selectFrom(qItem).where(qItem.itemSellStatus.eq(itemSellStatus.SELL))
				.where(qItem.itemDetail.like("%테스트 상품 상세%"))
				// .where(qItem.price.gt(10003)) // ">10003"
				.offset(page.getOffset()).limit(page.getPageSize());

		List<Item> itemList = query.fetch();

		for (Item item : itemList) {
			System.out.println(item.toString());
		}

	}

	@Disabled
	@Test
	@DisplayName("querydsl 조회 퀴즈1")
	public void queryDslQuiz1() {
		this.createItemList2();

		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qitem = QItem.item;

		JPAQuery<Item> query = qf.selectFrom(qitem).where(qitem.itemNm.like("%테스트 상품 1%"))
				.where(qitem.itemSellStatus.eq(itemSellStatus.SELL));

		List<Item> itemList = query.fetch(); // 쿼리문 실행

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("querydsl 퀴즈2")
	public void queryDslQuiz2() {
		this.createItemList2();

		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;

		JPAQuery<Item> query = qf.selectFrom(qItem).where(qItem.price.between(10004, 10008));

		List<Item> itemList = query.fetch(); // 쿼리문 실행

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Disabled
	@Test
	@DisplayName("querydsl 퀴즈3")
	public void queryDslQuiz3() {
		this.createItemList2();

		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;

		LocalDateTime regTime = LocalDateTime.of(2023, 1, 1, 12, 12, 44);
		JPAQuery<Item> query = qf.selectFrom(qItem).where(qItem.regTime.gt(regTime));

		List<Item> itemList = query.fetch(); // 쿼리문 실행

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Disabled
	@Test
	@DisplayName("querydsl 퀴즈4")
	public void queryDslQuiz4() {
		this.createItemList2();
		
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem).where(qItem.itemSellStatus.isNotNull());
		
		List<Item> itemList = query.fetch(); // 쿼리문 실행

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("querydsl 퀴즈5")
	public void queryDslQuiz5() {
		this.createItemList2();
		
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem).where(qItem.itemDetail.like("설명 1%"));
		
		List<Item> itemList = query.fetch(); // 쿼리문 실행

		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
}
