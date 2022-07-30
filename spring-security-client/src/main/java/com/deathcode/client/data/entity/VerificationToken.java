package com.deathcode.client.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static com.deathcode.client.helper.AppConstants.EXPIRATION_TIME;

@Entity
@Table(name = "verification_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime expirationTime;

    @OneToOne
    @JoinColumn(
            name = "id",
            nullable=false,
            foreignKey = @ForeignKey(name = "FK_USER_VERIFICATION_TOKEN")
    )
    private User user;

    public VerificationToken(User user , String token)
    {
        super();
        this.token=token;
        this.user=user;
        this.expirationTime=LocalDateTime.now().plusSeconds(EXPIRATION_TIME);
    }

    public VerificationToken(String token)
    {
        super();
        this.token=token;
        this.expirationTime=LocalDateTime.now().plusSeconds(EXPIRATION_TIME);
    }
}
