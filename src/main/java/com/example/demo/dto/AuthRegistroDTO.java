package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotBlank;

public record AuthRegistroDTO(@NotBlank String nome, @NotBlank String email, @NotBlank String senha, Role role) {
}
