package com.robert.springtutorial.Student;

import javax.persistence.*;

@Entity(name = "StudentCard")
@Table(
        name = "student_card",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_card_number_unique",columnNames = "card_number")
        }
)
public class StudentCard {
    @Id
    @SequenceGenerator(name="student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_fk"
            )
    )
    private Student student;

    @Column(
            name = "card_number",
            nullable = false,
            columnDefinition = "VARCHAR",
            length = 200
    )
    private String cardNumber;
    @Column(
            name = "credits",
            nullable = false
    )
    private int credits;
    @Column(
            nullable = false,
            name="semester"
    )
    private int semester;
    @Column(
            name="career",
            nullable = false
    )
    private String career;

    public StudentCard() {

    }

    public StudentCard(Student student, String cardNumber, int credits, int semester, String career) {
        this.student = student;
        this.cardNumber = cardNumber;
        this.credits = credits;
        this.semester = semester;
        this.career = career;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    @Override
    public String toString() {
        return "StudentCard{" +
                "id=" + id +
                ", student=" + student +
                ", cardNumber='" + cardNumber + '\'' +
                ", credits=" + credits +
                ", semester=" + semester +
                ", career='" + career + '\'' +
                '}';
    }
}
