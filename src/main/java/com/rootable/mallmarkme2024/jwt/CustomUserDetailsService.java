package com.rootable.mallmarkme2024.jwt;

import com.rootable.mallmarkme2024.domain.Member;
import com.rootable.mallmarkme2024.dto.MemberDTO;
import com.rootable.mallmarkme2024.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /*
     * UserDetails == MemberDTO (return type)
     * username == email (primary value)
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("----------------loadUserByUsername----------------" + username);

        Member member = memberRepository.getWithRoles(username);

        if (member == null) throw new UsernameNotFoundException("Not found the Member");

        MemberDTO memberDTO = new MemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getRoleList()
                        .stream()
                        .map(Enum::name).collect(Collectors.toList()) //List<String>
        );

        log.info(memberDTO);

        return memberDTO;

    }

}
