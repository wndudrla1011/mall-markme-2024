package com.rootable.mallmarkme2024.dto;

import com.rootable.mallmarkme2024.domain.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class MemberDTO extends User {

    private String email;

    private String password;

    private String nickname;

    private List<String> roleList = new ArrayList<>();

    public MemberDTO(String email, String password, String nickname, List<String> roleList) {
        super(
                email,
                password,
                roleList
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList()));

        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.roleList = roleList;
    }

    public Map<String, Object> getClaims() {

        HashMap<String, Object> dataMap = new HashMap<>();

        dataMap.put("email", email);
        dataMap.put("password", password);
        dataMap.put("nickname", nickname);
        dataMap.put("roleList", roleList);

        return dataMap;

    }

}
