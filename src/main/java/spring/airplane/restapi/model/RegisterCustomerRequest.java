
package spring.airplane.restapi.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterCustomerRequest {

    @NotBlank
    public String email;
    @NotBlank
    public String fullname;
    @NotBlank
    public String hobby;
    @NotBlank
    public String password;
}
