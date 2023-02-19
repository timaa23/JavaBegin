package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 500, nullable = false)
    private String name;
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    public Question() {
        answers = new ArrayList<>();
    }

    @Override
    public String toString() {
        String str = "";
        str += this.name + "\n";

        int i = 1;
        for (Answer answer : answers){
            str += i + ". " + answer.getText() + "\n";
            i++;
        }
        return str;
    }
}
