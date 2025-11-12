package Trabajo.Practico.Nro3.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Coches {
    private int idCoche;
    private String marca;
    private String modelo;
    private double precio;
    private int anio;
    private String color;
    private String carroceria;
}
