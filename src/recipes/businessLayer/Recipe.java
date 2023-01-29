package recipes.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recipe {
    @Id
    @SequenceGenerator(name = "recipe_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
    @JsonIgnore
    private long id;
    @NotBlank(message = "Is it vegan oyster, turkey bacon or what?")
    private String category;
    @NotBlank(message = "How can a recipe not have its name!")
    private String name;
    @NotBlank(message = "At least give your recipe a short description instead of nothing!")
    private String description;
    @ElementCollection
    @NotEmpty(message = "Where's the ingredient? Only air included?")
    private List<String> ingredients;
    @ElementCollection
    @NotEmpty(message = "How am I supposed to make this anyway ~~!")
    private List<String> directions;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime date;
}