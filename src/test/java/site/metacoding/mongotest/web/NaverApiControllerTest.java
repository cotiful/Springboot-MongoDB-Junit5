package site.metacoding.mongotest.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;
import site.metacoding.mongotest.domain.Naver;

@RequiredArgsConstructor
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // 통합테스트
public class NaverApiControllerTest {

    @Autowired // 필요한 의존 객체의 "타입"에 해당하는 빈을 주입
    private TestRestTemplate rt;

    private static HttpHeaders headers;

    @BeforeAll // Junit의 기본 어노테이션 static 선언과,해당 클래스를 초기화할 때 딱 한 번 사용
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void save_테스트() throws JsonProcessingException {

        // given : 내가 이런 데이터를 주어지고
        Naver naver = new Naver();
        naver.setTitle("스프링1강");
        naver.setCompany("재밌어요");

        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(naver); // 오브젝트를 JSON으로 변환 , Json->Object는 readValue
        HttpEntity<String> httpEntity = new HttpEntity<>(content, headers);

        // when : 이걸 실행할때
        ResponseEntity<String> response = rt.exchange("/navers", HttpMethod.POST, httpEntity, String.class);

        // then: 어떤 결과를 원하느냐
        System.out.println("==========================================");
        System.out.println("바디데이터:" + response.getBody());
        System.out.println("헤더데이터:" + response.getHeaders());
        System.out.println("상태코드:" + response.getStatusCode()); // 컨트롤러는 상태코드만 확인해봐도 된다. 201
        // CREATED
        // System.out.println(response.getStatusCode().is2xxSuccessful());
        // System.out.println("==========================================");
        // assertTrue(response.getStatusCode().is2xxSuccessful());

        DocumentContext dc = JsonPath.parse(response.getBody()); // junit jsonPath
        System.out.println("dc_json" + dc.jsonString());
        String title = dc.read("$.title");
        System.out.println("타이틀:" + title);
        assertEquals("스프링1강", title); // title 자리는 기대하는 값 (key)
    }

    @Test
    public void findAll_테스트() {
        // given

        // when : 이걸 실행할때
        // header 넣어줄게 get이라 없으나, null로 넣어주니깐 compile시에 문제 없어짐
        ResponseEntity<String> response = rt.exchange("/navers", HttpMethod.GET, null, String.class);

        // then
        // String title = dc.read("$.[0].title");
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

}
