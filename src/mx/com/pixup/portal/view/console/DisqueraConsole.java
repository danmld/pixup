/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.view.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import mx.com.pixup.portal.model.Disquera;
import mx.com.pixup.portal.service.DisqueraService;
import mx.com.pixup.portal.service.DisqueraServiceImpl;

/**
 *
 * @author JAVA-13
 */
public class DisqueraConsole {
    
    private DisqueraService disqueraService;
    
    public DisqueraConsole() {
        this.disqueraService = new DisqueraServiceImpl();
    }
    
    public static void main(String[] args) {
        System.out.println("BIENVENIDO A PIXUP");
        System.out.println("Catálogo Disquera");
        DisqueraConsole disqueraConsole = new DisqueraConsole();
        System.out.println("Seleccione la opción que desea: ");
        System.out.println("1 \t Crear");
        System.out.println("2 \t Eliminar");
        System.out.println("3 \t Consultar");
        System.out.println("4 \t Actualizar");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            String idOpcion = br.readLine();
            switch(idOpcion) {
                case "1": 
                    disqueraConsole.insert();
                    break;
                case "2": 
                    disqueraConsole.delete();
                    break;
                case "3": 
                    disqueraConsole.select();
                    break;
                case "4": 
                    disqueraConsole.update();
                    break;
            }
        } catch(Exception e) {
            System.out.println("No disponible, intente más tarde!!");
        }
    }
    
    public void update() {
        select();
        System.out.println("Dame el id de la disquera a actualizar: ");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            String idDisquera = br.readLine();
            Disquera disquera = new Disquera();
            disquera.setId(Integer.valueOf(idDisquera));
            System.out.println("Dame el nuevo nombre de la disquera: ");
            String nombreDisquera = br.readLine();
            disquera.setNombre(nombreDisquera);
            this.disqueraService.actualizarDisquera(disquera);
            System.out.println("Disquera actualizada de manera exitosa: " + idDisquera);
            select();
        } catch (Exception e) {
            System.out.println("No disponible, intente más tarde!!");
        }
    }
    
    public void select() {
        List<Disquera> disqueras = this.disqueraService.obtenerDisqueras();
        System.out.println("Id: \t Nombre Disquera");
        for(Disquera disquera : disqueras) {
            System.out.println(disquera.getId() + " \t " + disquera.getNombre());
        }
    }
    
    public void delete() {
        System.out.println("Dame el id de la disquera a eliminar: ");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            String idDisquera = br.readLine();
            Disquera disquera = new Disquera();
            disquera.setId(Integer.valueOf(idDisquera));
            this.disqueraService.borrarDisquera(disquera);
            System.out.println("Disquera eliminada de manera exitosa: " + idDisquera);
        } catch (Exception e) {
            System.out.println("No disponible, intente más tarde!!");
        }
    }
    
    public void insert() {
        System.out.println("Inserte el nombre de la nueva disquera: ");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            String nombreDisquera = br.readLine();
            Disquera disquera = new Disquera();
            disquera.setNombre(nombreDisquera);
            Disquera newDisquera = this.disqueraService.guardarDisquera(disquera);
            if(newDisquera != null) {
                System.out.println("Disquera insertada de manera exitosa: " + newDisquera.getId());
            } else {
                System.out.println("No fue posible insertar la disquera. Intente más tarde!!!");
            }
        } catch (Exception e) {
            System.out.println("No disponible, intente más tarde!!");
        }
    }
    
    
}
