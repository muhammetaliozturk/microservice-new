package kaplanistan.learnmicro.kaplanmicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity(name = "user_detail")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @JsonProperty("Adı")
    @NotNull(message = " boş olamaz.")
    @Size(min = 4, message = "4 karakterden az olamaz.")
    private String name;

    @Past(message = "Doğum günü gelecekten bir gün olamaz.")
    @NotNull(message = " boş olamaz.")
    private LocalDate birthDate;

    public User() {
    }

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
