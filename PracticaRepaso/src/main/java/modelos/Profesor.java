package modelos;

import java.io.*;
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

        complementoAntiguedad=complementoAntiguedad(this.getFecha_comienzo());

        salarioSinDeducciones=SALARIO_BASE+complementoCargo+complementoAntiguedad;

        salarioFinal=salarioSinDeducciones*(1-this.irpf);
        return salarioFinal;


    }
    public int complementoAntiguedad(LocalDate fecha_comienzo){
        int trienos;
        Period aniosCompletos=Period.between(fecha_comienzo,LocalDate.now());
        int antiguedad=aniosCompletos.getYears();
        trienos=antiguedad/3;
        return 90*trienos;
    }

    public void generarNomina(LocalDate fecha){
        String nombreFichero=getID()+getNombre()+getFecha_comienzo();
        File fichero=new File("src/main/resources/nominas");
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter(fichero));
            bw.write("Nombre: "+getNombre()+"\t\tEspecialidad: "+getEspecialidad());
            bw.write("Cargo: "+getCargo()+"\t\tAntigüedad: "+getFecha_comienzo());
            bw.write("Mes: "+LocalDate.now().getMonth()+"  Año: "+LocalDate.now().getYear());
            bw.write("CONCEPTO"+"\tImporte");
            bw.write("--------------------------------------------------");
            bw.write("Salario base"+"\t1500€");
            if(getCargo().equals(Cargo.Director)){
                bw.write("Complemento cargo: "+"\t500€");
            } else if (getCargo().equals(Cargo.Secretario)) {
                bw.write("Complemento cargo: "+"\t300€");
            }
            bw.write("Antigüedad: "+"\t"+complementoAntiguedad(this.fecha_comienzo));
            bw.write("--------------------------------------------------");
            bw.write("TOTAL(Bruto)"+"\t(Suma de los valores anteriores)");
            bw.write("IRPF"+"\t"+getIrpf());
            bw.write("TOTAL(Neto,a percibir"+"\t(Bruto *1-"+getIrpf()+")");
            bw.close();



        } catch (FileNotFoundException e){
            System.out.println("Fichero no encontrado");
        } catch (IOException e){
            System.out.println("Error entrada/salida");
        }
    }

    @Override
    public String toString(){

        return "ID: "+super.id+"\nNombre: "+this.nombre+"\nEmail: "+super.email+"\nCargo:"+this.cargo;
    }



}
