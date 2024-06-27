package org.zerock.api01.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(schema="auth", name="user")
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
@ToString
public class APIUser {

    @Id
    private String mid;
    private String mpw;

    public void changePw(String mpw){
        this.mpw = mpw;
    }
}
