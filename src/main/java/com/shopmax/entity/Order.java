package com.shopmax.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.shopmax.constant.OrderStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order extends BaseEntity{

	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "order_date")
	private LocalDateTime order_date;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	// order에서도 orderItem을 참조할 수 있도록 양방향 관계를 만든다 
	// 다만 orderItem은 자식테이블이 되므로 List로 만든다.
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) // 연관관계의 주인 설정(외래키 지정)
	private List<OrderItem> orderItems = new ArrayList<>();
	

}
