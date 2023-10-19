package com.mypet.petmily.member.dto;

import com.mypet.petmily.fileUpload.dto.FileUploadDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO implements UserDetails {

    private int memberNo;                            // 회원코드
    private String memberId;                         // 사용자 ID
    private String memberPwd;                        // 패스워드
    private String memberName;                       // 이름
    private String nickName;                         // 닉네임
    private String phone;                            // 연락처
    private String gender;                           // 성별
    private Integer postNo;                          // 우편번호
    private String address;                          // 주소
    private String address2;                         // 상세주소
    private int point;                               // 적립금
    private String memberStat;                       // 상태
    private Date memberStatDate;                     // 상태 변경일
    private Date registDate;                         // 가입일
    private int warningCount;                        // 경고 횟수
    private int signupPathCode;                      // 가입경로 코드
    private List<MemberRoleDTO> memberRoleList;      // 한 멤버는 여러 권한을 가질 수 있다
    private AuthorityDTO memberCode;
    private List<PetDTO> petList;                    // 회원의 반려견
    private List<ReviewDTO> reviewList;
    private List<ReservationDTO> reservationList;
    private FileUploadDTO memberImg;

    private List<PetSitterDTO> petSitter;


    /* 나중에 GrantedAuthority 객체 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (MemberRoleDTO role : memberRoleList) {
            roles.add(new SimpleGrantedAuthority(role.getAuthority().getName()));
        }
        return roles;
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
