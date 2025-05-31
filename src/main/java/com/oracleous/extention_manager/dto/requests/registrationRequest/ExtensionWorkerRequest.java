package com.oracleous.extention_manager.dto.requests.registrationRequest;

import com.oracleous.extention_manager.data.model.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload to register an extension worker")
public class ExtensionWorkerRequest {

    @Schema(description = "First name of the extension worker", example = "Chidi")
    private String firstName;

    @Schema(description = "Last name of the extension worker", example = "Nwafor")
    private String lastName;

    @Schema(description = "Email address of the extension worker", example = "chidi.nwafor@example.com")
    private String email;

    @Schema(description = "Account password for the extension worker", example = "securePass456")
    private String Password;

    @Schema(description = "Phone number of the extension worker", example = "07098765432")
    private String phoneNumber;

    @Schema(description = "Brief biography or description of the extension worker", example = "Experienced extension worker with 3 years in agricultural support")
    private String shortBio;

    @Schema(description = "URL to the passport photograph of the extension worker", example = "https://example.com/passport.jpg")
    private String passportPhotograph;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public String getPassportPhotograph() {
        return passportPhotograph;
    }

    public void setPassportPhotograph(String passportPhotograph) {
        this.passportPhotograph = passportPhotograph;
    }
}
