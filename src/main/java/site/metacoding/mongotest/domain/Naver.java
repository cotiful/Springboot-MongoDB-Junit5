package site.metacoding.mongotest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "navers")
public class Naver {
    // 몽고는 String 으로 id
    @Id
    private String _id;
    private String company;
    private String title;
}
