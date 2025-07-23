package com.crm.api.model;

import java.sql.Timestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O nome do cliente não pode estar vazio")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message = "O CPF deve ser preenchido")
	@Column(nullable = false)
	@Size(max = 11, message = "O CPF deve conter Somente números e no máximo 11 caracteres")
	private String cpf;
	
	@Column(nullable = false)
	private Long idade;

	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false)
	private Long numero;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	@Size(max = 2, message = "A UF deve conter Somente letras e no máximo 2 caracteres")
	private String uf;		
	
	@CreatedDate 
    @Column(nullable = false, updatable = false) 
    private Timestamp  createdDate;
    
    @LastModifiedDate 
    private Timestamp lastModifiedDate; 
}
