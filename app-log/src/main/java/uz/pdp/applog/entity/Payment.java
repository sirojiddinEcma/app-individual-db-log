package uz.pdp.applog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.applog.component.QuloqSolish;
import uz.pdp.applog.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * BY SIROJIDDIN on 28.12.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(QuloqSolish.class)
public class Payment extends AbsEntity {
    private Double paySum;
    private String comment;
}
