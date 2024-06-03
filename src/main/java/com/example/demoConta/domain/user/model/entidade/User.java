package com.example.demoConta.domain.user.model.entidade;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * @author Dionízio Inácio on 03/06/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "users")
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotEmpty
		@Column(unique = true)
		private String username;

		@NotEmpty
		private String password;

		@ElementCollection(fetch = FetchType.EAGER)
		private Set<String> roles;
}
