package com.rootable.mallmarkme2024.repository;

import com.rootable.mallmarkme2024.domain.Member;
import com.rootable.mallmarkme2024.domain.Role;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Log4j2
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void save() throws Exception {

        Member member = Member.builder()
                .email("admin@gamil.com")
                .password(passwordEncoder.encode("1111"))
                .nickname("admin")
                .build();

        member.addRole(Role.ADMIN);

        memberRepository.save(member);

    }

}
