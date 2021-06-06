package com.bside.breadgood.sample.domain;

import com.bside.breadgood.sample.domain.member.Member;
import com.bside.breadgood.sample.domain.member.Role;
import com.bside.breadgood.sample.domain.member.Social;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 스프링 시큐리티 OAuth 인증을 위한 속성 객체
 * ofNaver, ofKakao 등 of플랫폼명으로 된 메소드들의 리팩토링 여지가 있음.
 */
@Slf4j
@Getter
@ToString
@Log
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String registrationId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String registrationId){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.registrationId = registrationId;
    }

    /**
     * 카카오, 네이버, 페이스북, 구글, 깃허브 등에 따른 속성을 만들어줌
     * @param registrationId 소셜 타입, 즉 네이버, 카카오, 페이스북, 구글, 깃허브
     * @param userNameAttributeName Principal.getName 하게 되면 나오는 로그인한 유저의 이름으로 등록할 필드명
     * @param attributes 각 플랫폼에서 반환받은 유저 정보
     * @return 인증 객체
     */
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        log.info("요청 :: "+registrationId);
        log.info("유저이름 :: "+userNameAttributeName);
        log.info("속성 :: "+attributes);
        switch(registrationId){
            case "kakao":
                return ofKakao(registrationId, "nickname", attributes);
            default:
                throw new IllegalArgumentException("해당 로그인을 찾을 수 없습니다.");
        }
    }
    private static OAuthAttributes ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        System.out.println("여기오나--"+ properties);
        properties.put("id", attributes.get("id"));
        return OAuthAttributes.builder()
                .name((String) properties.get("nickname"))
                .email((String) properties.get("email"))
                .picture((String) properties.get("profile_image"))
                .attributes(properties)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }
    public Member toEntity(){
        return Member.builder().name(name).email(email).picture(picture).role(Role.USER).social(Social.valueOf(registrationId.toUpperCase())).build();
    }
}