package site.metacoding.mongotest.web;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.mongotest.domain.Naver;
import site.metacoding.mongotest.domain.NaverRepository;

@RequiredArgsConstructor
@RestController
public class NaverApiController {
    private final NaverRepository naverRepository;

    @GetMapping("/navers")
    public ResponseEntity<? extends Object> findAll() {
        return new ResponseEntity<>(naverRepository.findAll(), HttpStatus.OK); // body, status
    }

    @PostMapping("/navers")
    public ResponseEntity<?> save(@RequestBody Naver naver) {
        return new ResponseEntity<>(naverRepository.save(naver), HttpStatus.CREATED);
    }
}
