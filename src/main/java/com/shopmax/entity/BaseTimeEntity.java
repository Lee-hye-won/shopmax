package com.shopmax.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;

@EntityListeners(value = {AuditingEntityListener.class})  //Auditing을 적용하기
@MappedSuperclass	// 부모클래스를 상속받는 자식클래스에 매핑정보를 제공하기위해
@Getter
@Setter
public abstract class BaseTimeEntity {

	@CreatedDate	// 엔티티가 생성되서 저장될 때 시간을 자동으로 저장한다.
	@Column(updatable = false)	// 컬럼의 값을 수정하지 못하도록 막음 (최초등록일의 값만 필요하므로)
	private LocalDateTime regTime;
	
	@LastModifiedDate	// 수정될 때 시간을 자동으로 저장한다.
	private LocalDateTime updateTime;
}
