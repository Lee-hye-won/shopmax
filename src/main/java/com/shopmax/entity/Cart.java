package com.shopmax.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@Entity
@Table(name= "cart")
public class Cart {
	
	@Id
	@Column(name= "cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "member_id")
	private Member member;
	
	
}
