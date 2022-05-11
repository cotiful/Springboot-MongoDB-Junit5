package site.metacoding.mongotest.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NaverRepository extends MongoRepository<Naver, String> {
    @Query("{title: ?0, comapny: ?1, createdAt: ?2}") // 몽고 DB쿼리
    List<Naver> mFindByTitleAndCompany(String title, String company);
}
