package com.mypet.petmily.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@ToString
public class MemberDTO implements UserDetails {

    private Long memberNo;          // 회원코드
    private String memberId;        // 사용자 ID
    private String memberPwd;       // 패스워드
    private String memberName;      // 이름
    private String nickName;        // 닉네임
    private int phone;              // 연락처
    private String gender;          // 성별
    private Integer postNo;             // 우편번호
    private String address;         // 주소
    private String address2;        // 상세주소
    private int point;              // 적립금
    private String memberStat;      // 상태
    private Date memberStatDate;    // 상태 변경일
    private int warningCount;       // 경고 횟수
    private int sighupPathCode;     // 가입경로 코드


    /* 나중에 GrantedAuthority 객체 */


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
