package ninja.tuxtech.envers.model;


import com.sun.istack.internal.NotNull;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Table
@Audited
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    @Column
    @NotEmpty
    private String nome;

    @Version
    @NotNull
    private Long version;

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        return id != null ? id.equals(produto.id) : produto.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
