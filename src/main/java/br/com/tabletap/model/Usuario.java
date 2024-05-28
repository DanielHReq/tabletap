package br.com.tabletap.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Usuario_Type")
public class Usuario implements UserDetails {

    // Construtores

    public Usuario () {}
    public Usuario (String celular, UserRole role) {
        this.celular = celular;
        this.role = role;
    }
    public Usuario (String celular, String senha, UserRole role) {
        this.celular = celular;
        this.senha = senha;
        this.role = role;
    }
    public Usuario (String celular, String nome, String senha, UserRole role) {
        this.celular = celular;
        this.nome = nome;
        this.senha = senha;
        this.role = role;
    }

    
    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected String nome;
    protected String senha;
    protected String celular;
    protected UserRole role;


    // Métodos

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
	public int hashCode() {
    	final int prime = 31;
    	int result = 1;
    	result = prime * result + ((id == null) ? 0 : id.hashCode());
    	return result;
	}
    
	@Override
	public boolean equals(Object obj) {
    	if (this == obj)
        	return true;
    	if (obj == null)
        	return false;
    	if (getClass() != obj.getClass())
        	return false;
    	Usuario other = (Usuario) obj;
    	if (id == null) {
        	if (other.id != null)
            	return false;
    	} else if (!id.equals(other.id))
        	return false;
    	return true;
	}

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getNome();
    }


    // Get / Set

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    // Gerado automáticamente

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
        //throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
        //throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }
}