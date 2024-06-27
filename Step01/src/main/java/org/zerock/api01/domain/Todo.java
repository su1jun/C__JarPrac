package org.zerock.api01.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(schema="tasks", name="todo")
@NoArgsConstructor  @AllArgsConstructor
@Builder @Getter
@ToString
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private String writer;
    private boolean complete;

    public void changeComplete(boolean complete){
        this.complete = complete;
    }
    public void changeDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
    public void changeTitle(String title){
        this.title = title;
    }

}
