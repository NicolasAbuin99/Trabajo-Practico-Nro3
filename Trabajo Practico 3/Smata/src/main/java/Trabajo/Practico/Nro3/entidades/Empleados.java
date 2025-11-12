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
public class Empleados {

    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String rol;
    private String usuario;
    private String password;
}
