package com.example.practicarepaso.modelos;

import java.time.LocalDate;
import java.time.Period;

public class Profesor extends Usuario{

    public static double SALARIO_BASE=1500;
    public String especialidad;
    public LocalDate fecha_comienzo;
    public int irpf;
    public Cargo cargo;

    public Profesor(int id, String nombre, String email, String password, String especialidad, LocalDate fecha_comienzo, int irpf, Cargo cargo) {
        super(id, nombre, email, password);
        this.especialidad = especialidad;
        this.fecha_comienzo = fecha_comienzo;
        this.irpf = irpf;
        this.cargo = cargo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getFecha_comienzo() {
        return fecha_comienzo;
    }

    public void setFecha_comienzo(LocalDate fecha_comienzo) {
        this.fecha_comienzo = fecha_comienzo;
    }

    public int getIrpf() {
        return irpf;
    }

    public void setIrpf(int irpf) {
        this.irpf = irpf;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public double calcular_salario_bruto(LocalDate fecha){
        double complementoCargo;
        double complementoAntiguedad;
        double salarioSinDeducciones;

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
        complementoAntiguedad=complementoAntiguedad(this.getFecha_comienzo());
        salarioSinDeducciones=SALARIO_BASE+complementoCargo+complementoAntiguedad;
        return salarioSinDeducciones;
    }

    public double calcular_salario_neto(double salarioBruto){
        return salarioBruto*(1-(this.getIrpf()/100.0));
    }

    public int complementoAntiguedad(LocalDate fecha_comienzo){
        int trienos;
        Period aniosCompletos=Period.between(fecha_comienzo,LocalDate.now());
        int antiguedad=aniosCompletos.getYears();
        trienos=antiguedad/3;
        return 90*trienos;
    }



    @Override
    public String toString(){

        return "ID: "+super.id+"\nNombre: "+this.nombre+"\nEmail: "+super.email+"\nCargo:"+this.cargo;
    }



}
