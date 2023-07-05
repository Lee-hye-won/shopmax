package com.shopmax.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopmax.constant.Role;
import com.shopmax.dto.MemberFormDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity{
	
	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true) // 중복된 값이 들어올 수 없다.
	private String email;
	
	@Column
	private String name;
	
	@Column
	private String password;
	
	@Column
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member createMember(MemberFormDto memberForDto, PasswordEncoder passwordEncoder) {
		// 패스워드 암호화 
		String password = passwordEncoder.encode(memberForDto.getPassword());

		// MemberForDto를 -> Member 엔티티 객체로 변환
		Member member = new Member();
		member.setName(memberForDto.getName());
		member.setEmail(memberForDto.getEmail());
		member.setAddress(memberForDto.getAddress());
		member.setPassword(password);
		//member.setRole(Role.ADMIN);
		member.setRole(Role.USER);
		
		return member;
	}
}
