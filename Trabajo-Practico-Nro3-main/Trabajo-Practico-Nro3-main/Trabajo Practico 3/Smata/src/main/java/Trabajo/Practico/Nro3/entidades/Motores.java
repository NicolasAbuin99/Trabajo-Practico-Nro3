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
public class Motores {

    private int idMotor; 
    private String nombre;
    private String cilindrada;
}
