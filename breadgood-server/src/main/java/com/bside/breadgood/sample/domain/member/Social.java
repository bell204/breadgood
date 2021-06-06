package com.bside.breadgood.sample.domain.member;

import lombok.RequiredArgsConstructor;

/**
 * 소셜 로그인 관리 enum
 */
@RequiredArgsConstructor
public enum Social {
    KAKAO("카카오");

    private final String title;
}