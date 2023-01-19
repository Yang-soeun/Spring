package com.cos.security1.config.oauth;

import com.cos.security1.Repository.UserRepository;
import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.config.oauth.provider.FacebookUserInfo;
import com.cos.security1.config.oauth.provider.GoogleUserInfo;
import com.cos.security1.config.oauth.provider.OAuth2UserInfo;
import com.cos.security1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    //구글로 부터 받은 userRequest 데이터에 관한 후처리가 되는 함수
    /**
     * userRequest 정보
     * 구글 로그인 버튼 클릭 -> 구글 로그인 창-> 로그인 완료 -> code를 리턴 받음(OAuth-Client 라이브러리가 받음)
     * -> AccessToken 요청 -> 받음 여기까지가 userRequest
     * userRequest 정보로 회원 프로필을 받아야함 -> 이때 사용되는 함수가 loadUser 함수를 통해서 회원 프로필을 받음
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //서버의 기본 정보들, registrationId로 어떤 OAuth로 로그인 했는지 확인가능
        System.out.println("getClientRegistration() = " + userRequest.getClientRegistration());
        //
        System.out.println("getAccessToken = " + userRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes = " + super.loadUser(userRequest).getAttributes());

        OAuth2User oauth2User = super.loadUser(userRequest);
        System.out.println("getAttributes" + oauth2User.getAttributes());

        //후처리 로그인 완성
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
        }else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            System.out.println("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
        }else{
            System.out.println("구글과 페이스북 지원");
        }


        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();//구글 로그인일때는 "sub", 페이스북 로그인일때는 "id"
        String username = provider + "_" +providerId;//충돌방지를 위해 ex) google_4164912864345
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String role = "ROLE_USER";
        String email = oAuth2UserInfo.getEmail();

        //중복 체크
        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null){
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        }else{
            System.out.println("로그인을 이미 한적이 있습니다. 당신은 자동회원가입이 되어 있습니다.");
        }

        return new PrincipalDetails(userEntity, oauth2User.getAttributes());
    }
}
