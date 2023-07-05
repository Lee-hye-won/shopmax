package com.shopmax.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopmax.entity.Member;
import com.shopmax.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional	// 쿼리문 수행시 에러가 발생하면 변경된 데이터를 이전상태로 콜백시켜줌
@RequiredArgsConstructor	// @Autowired를 사용하지않고 의존성주입을 시켜준다.
public class MemberService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	// 회원가입 데이터를 DB에 저장
	public Member saveMember(Member member) {
		validateDuplicateMember(member);	// 중복체크
		Member savedMember = memberRepository.save(member);	// insert
		return savedMember;	// 회원가입된 데이터를 return
	}
	
	// 이메일 중복체크
	public void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// 사용자가 입력한 email이 DB에 존재하는지 쿼리문을 사용한다.
		Member member = memberRepository.findByEmail(email);	
		
		if(member == null) {	// 사용자가 없다면
			throw new UsernameNotFoundException(email);
		}
		
		// 사용자가 있다면 userDetails 객체를 만들어서 반환
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}

}
