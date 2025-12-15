package com.jsantosdevjava.navereda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_preferencias")
@Data
@NoArgsConstructor
public class Preferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BigDecimal orcamento;

    @Enumerated(EnumType.STRING)
    private EstiloViagem estilo;
    private Integer distancia;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    @JsonIgnore
    private User usuario;

    public Preferencia(BigDecimal orcamento, EstiloViagem estilo, Integer distancia) {
        this.orcamento = orcamento;
        this.estilo = estilo;
        this.distancia = distancia;
    }
}