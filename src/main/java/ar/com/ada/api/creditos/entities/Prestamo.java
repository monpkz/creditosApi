package ar.com.ada.api.creditos.entities;

import java.math.*;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @Column(name = "prestamo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prestamoId;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_prestamo")
    private Date fecha;

    private BigDecimal importe;

    private int coutas;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "estado_id")
    private int estadoId; //Por como int

    @ManyToOne // De muchos a uno
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    // JoinColumns van donde esta la FK
    private Cliente cliente;

    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public int getCoutas() {
        return coutas;
    }

    public void setCoutas(int coutas) {
        this.coutas = coutas;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.cliente.agregarPrestamo(this);
        // this.cliente.getPrestamo().add(this);relacion bidireccional
    }

    // ENUMERADO

    public EstadoPrestamoEnum getEstadoId() {

        return EstadoPrestamoEnum.parse(this.estadoId);
    }

    public void setEstadoId(EstadoPrestamoEnum estadoId) {
        this.estadoId = estadoId.getValue();

    }

    // enumerado
    public enum EstadoPrestamoEnum {
        SOLICITADO(1),
        RECHAZADO(2), 
        PENDIENTE_APROBRACION(3),
        APROBADO(4),
        INCOBRABLE(5),
        CANCELADO(6),
        PREAPROBADO(100);

        private final int value;

        // NOT: Enum constructor tiene que estar en priv
        private EstadoPrestamoEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static EstadoPrestamoEnum parse(int id) {
            EstadoPrestamoEnum status = null; // Default
            for (EstadoPrestamoEnum item : EstadoPrestamoEnum.values()) {
                if (item.getValue() == id) {
                    status = item;
                    break;
                }
            }
            return status;
        }

    }
}
