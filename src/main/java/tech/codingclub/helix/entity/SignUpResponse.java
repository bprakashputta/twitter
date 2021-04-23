package tech.codingclub.helix.entity;

public class SignUpResponse {
    public String message;
    public Boolean userCreated;

    public SignUpResponse(String message, boolean user_created) {
        this.message = message;
        this.userCreated = user_created;
    }
}
