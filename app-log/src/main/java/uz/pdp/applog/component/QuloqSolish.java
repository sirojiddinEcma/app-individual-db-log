package uz.pdp.applog.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import uz.pdp.applog.entity.History;
import uz.pdp.applog.repository.HistoryRepository;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * BY SIROJIDDIN on 28.12.2020
 */


@Component
public class QuloqSolish {
    final
    HistoryRepository historyRepository;

    static final ObjectMapper objectMapper = new ObjectMapper();

    public QuloqSolish(@Lazy HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @PrePersist
    public void checkInsert(Object object) {
        System.out.println(object.toString());
    }

    @PreUpdate
    public void checkUpdate(Object object) {
        System.out.println(object.toString());
        try {
            historyRepository.save(new History(
                    object.getClass().getName(),
                    objectMapper.writeValueAsString(object)
            ));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PreRemove
    public void checkDelete(Object object) {
        System.out.println(object.toString());
    }


}
