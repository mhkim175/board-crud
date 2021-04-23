package com.mhkim.board.entity.user;

import com.mhkim.board.entity.BaseTimeEntity;
import com.mhkim.board.entity.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@NoArgsConstructor
@ToString
@DynamicUpdate
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String name;

    @Builder
    public User(Long userId, String email, String name) {

        checkArgument(isNotEmpty(email), "email must be provided.");
        checkArgument(email.length() >= 4 && email.length() <= 50, "email length must be between 4 and 50 characters.");

        this.userId = userId;
        this.email = email;
        this.name = name;
    }

}
