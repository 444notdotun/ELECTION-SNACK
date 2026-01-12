package bankingapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
@Data
@Document
public class Nibbs {
    @Id
    private String id;
    private Map<String,String> bvns;
    private int count;
    private static Nibbs instance;
    public Nibbs(){
        bvns = new HashMap<>();
    }

    public static Nibbs getInstance(){
        if(instance == null){
            instance = new Nibbs();
        }
        return instance;
    }
}
