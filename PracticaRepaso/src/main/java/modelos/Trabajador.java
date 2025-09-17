package modelos;

import java.time.LocalDate;
import java.time.Period;

public class Trabajador extends Usuario{

    public static double SALARIO_BASE=1500;
    public String especialidad;
    public LocalDate fecha_comienzo;
    public int irpf;
    public Cargo cargo;

    public Trabajador(String especialidad,LocalDate fecha_comienzo, int irpf){
        super();
        this.especialidad=especialidad;
        this.fecha_comienzo=fecha_comienzo;
        this.irpf=irpf;
        this.cargo=Cargo.Profesor;
    }

    public Cargo getCargo(){
        return this.cargo;
    }

    public double calcular_salario(LocalDate fecha){
        double complementoCargo;
        double complementoAntiguedad;
        double salarioSinDeducciones;
        double salarioFinal=0;

        int antiguedad=0;
        int trienos=0;

        Period aniosCompletos;
        if(getCargo().equals(Cargo.Director)){
            complementoCargo=500;
        }else{
            complementoCargo=300;
        }
        aniosCompletos=Period.between(fecha,LocalDate.now());
        antiguedad=aniosCompletos.getYears();

        trienos=antiguedad/3;

        complementoAntiguedad=90*trienos;

        salarioSinDeducciones=SALARIO_BASE+complementoCargo+complementoAntiguedad;

        salarioFinal=salarioSinDeducciones*(1-this.irpf);
        return salarioFinal;


    }

    @Override
    public String toString(){

        return "ID: "+super.id+"\nNombre: "+this.nombre+"\nEmail: "+super.email+"\nCargo:"+this.cargo;
    }



}
