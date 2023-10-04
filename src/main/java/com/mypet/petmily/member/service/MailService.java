package com.mypet.petmily.member.service;

import com.mypet.petmily.member.dao.MemberMapper;
import com.mypet.petmily.member.dto.MailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    private final MemberMapper memberMapper;
    private final JavaMailSender mailSender;

    public MailService(MemberMapper memberMapper, JavaMailSender mailSender) {
        this.memberMapper = memberMapper;
        this.mailSender = mailSender;

    }

    // 메일 내용을 생성하고 임시 비밀번호로 회원 비밀번호 변경
    public MailDTO createEmailContent(String email) {
        String str = getTempPwd();
        MailDTO mail = new MailDTO();
        mail.setAddress(email);
        mail.setTitle("안녕하세요. 펫밀리 임시 비밀번호 관련 이메일 입니다.");
        mail.setMessage("회원님의 임시 비밀번호는 " + str + "입니다.");
        updatePwd(str, email);
        log.info("mail : {}", mail);
        return mail;
    }

    // 임시 비밀번호 업데이트
    private void updatePwd(String str, String email) {
        String memberPwd = str;
        Long memberId = memberMapper.findByMemberEmail(email).getId();
        memberMapper.updatePwd(memberId, memberPwd);
    }

    // 임시 비밀번호 생성
    private String getTempPwd() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    // 메일 전송
    public void mailSend(MailDTO dto) {
        System.out.println("전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getAddress());
        message.setSubject(dto.getTitle());
        message.setText(dto.getMessage());
        message.setFrom("hipetmily@gmail.com");
        message.setReplyTo("hipetmily@gmail.com");
        System.out.println("message"+message);
        mailSender.send(message);
    }

}
