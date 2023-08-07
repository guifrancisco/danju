package org.github.guifrancisco.danju.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterUser(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotBlank
        String role
) {
}
