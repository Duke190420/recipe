package recipes.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
public class User {
    @Id
    @JsonIgnore
    private Long id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
    private String role;
}
