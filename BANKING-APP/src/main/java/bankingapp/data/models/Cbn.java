package bankingapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
@Data
@Document
public class Cbn {
    @Id
    private String id;
    private Map<String,Bank> banks;
    private Nibbs nibbs;

    private  static Cbn instance;
    private Cbn(){
        banks = new HashMap<>();
    }
    public static Cbn getInstance(){
        if(instance == null){
            instance = new Cbn();
        }
        return instance;
    }

}
