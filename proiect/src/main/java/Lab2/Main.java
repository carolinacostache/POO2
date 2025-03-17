package main.java.Lab2;

import java.util.*;

class Departamente {
    public static List<Departament> departamente = new ArrayList<>();
}

class Departament {
    private final String numeDepartament;
    private int numarAngajati;
    private List<Angajat> membri = new ArrayList<>();

    public Departament(String numeDepartament) {
        this.numeDepartament = numeDepartament;
        this.numarAngajati = getNumarAngajati();
        Departamente.departamente.add(this);
    }

    public String getNumeDepartament() {
        return numeDepartament;
    }

    public int getNumarAngajati() {
        return membri.size();
    }

    public List<Angajat> getMembri() {
        return membri;
    }

    public void adaugaAngajat(Angajat angajat) {
        membri.add(angajat);
        numarAngajati++;
    }

    public void stergeAngajat(Angajat angajat) {
        membri.remove(angajat);
        numarAngajati--;
    }

}


class Angajat {
    private final String nume;
    private final String prenume;
    private final int anulAngajarii;
    private int salary;
    protected Departament departament;

    public Angajat(String nume, String prenume, int anulAngajarii, int salary, Departament departament) {
        this.nume = nume;
        this.prenume = prenume;
        this.anulAngajarii = anulAngajarii;
        this.salary = salary;
        this.departament = departament;
        departament.adaugaAngajat(this);
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public int getAnulAngajarii() {
        return anulAngajarii;
    }

    public int getSalary() {
        return salary;
    }

    public Departament getDepartament() {
        return departament;
    }


    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartament(Departament nouDepartament) {
        this.departament.stergeAngajat(this);
        nouDepartament.adaugaAngajat(this);
        this.departament = nouDepartament;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        final Angajat other = (Angajat) obj;
        return Objects.equals(this.nume, other.nume) && Objects.equals(this.prenume, other.prenume) && this.anulAngajarii == other.anulAngajarii && Objects.equals(this.departament, other.departament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, anulAngajarii, departament);
    }

    @Override
    public String toString() {
        return nume + " " + prenume + " | " + anulAngajarii + " | Salariu: " + salary;
    }
}

class ManagementService {
    private static ManagementService instance;
    private List<Angajat> angajati;
    private List<Departament> departamente;

    private ManagementService() {
        angajati = new ArrayList<>();
        departamente = new ArrayList<>();
    }

    public static ManagementService getInstance() {
        if (instance == null) {
            instance = new ManagementService();
        }
        return instance;
    }

    public void inregistreazaAngajat(Angajat angajat) {
        Departament dep = angajat.getDepartament();
        if (!dep.getMembri().contains(angajat)) {
            dep.adaugaAngajat(angajat);
        }
    }

    public void eliminaAngajat(Angajat angajat) {
        Departament dep = angajat.getDepartament();
        if (dep.getMembri().contains(angajat)) {
            dep.stergeAngajat(angajat);
        }
    }

    public void schimbaDepartament(Angajat angajat, Departament nouDepartament) {
        if (angajat != null && nouDepartament != null) {
            angajat.setDepartament(nouDepartament);
            System.out.println("Departamentul a fost schimbat cu succes!");
            System.out.println(angajat.getNume() + " "+ angajat.getPrenume() + " " + angajat.getDepartament().getNumeDepartament());
        }
    }

    public void schimbaSalary(Angajat angajat, int nouSalary) {
        if (angajat != null && nouSalary > 0) {
            angajat.setSalary(nouSalary);
            System.out.println("Salariul a fost schimbat cu succes!");
            System.out.println(angajat.getNume() + " "+ angajat.getPrenume() + " " + angajat.getSalary());
        }
    }

    public void afiseazaDepartamente() {
        System.out.println("Departamentele sunt:");
        for (Departament dep : Departamente.departamente) {
            System.out.println("-" + dep.getNumeDepartament());
        }
    }

    public void afiseazaMembriiDepartament() {
        for (Departament dep : Departamente.departamente) {
                System.out.println("In departamentul " + dep.getNumeDepartament() + "sunt" + dep.getNumarAngajati() + " angajati.");
                System.out.println("Membrii departamentului sunt:");
                for (Angajat angajat : dep.getMembri()) {
                    System.out.println(angajat);
                    System.out.println("\n");
                }
        }
    }

    public void calculeazaSalaryMediu(Departament departament) {
        double totalSalary = 0;
        int numarAngajati = departament.getNumarAngajati();
        for (Angajat angajat : departament.getMembri()) {
            totalSalary += angajat.getSalary();
        }
        if (numarAngajati == 0) {
            System.out.println("Nu se poate calcula pentru ca nu exista angajati.");
            return;
        }
        double salaryMediu= (totalSalary / numarAngajati);
        if (numarAngajati > 0) {
            System.out.println("Salariu mediu in " + departament.getNumeDepartament() + ": " + salaryMediu);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        ManagementService management = ManagementService.getInstance();


        Departament IT = new Departament("IT");
        Departament PR = new Departament("PR");

        Angajat angajat1 = new Angajat("Costache", "Carolina", 2025, 1000, PR);
        Angajat angajat2 = new Angajat("Costache", "Felix", 2020, 5500, IT);
        Angajat angajat3 = new Angajat("Sava", "Antonia", 2025, 200, IT);
        Angajat angajat4 = new Angajat("Tapai", "Delia", 2024, 4000, PR);

        management.schimbaDepartament(angajat3, PR );
        management.schimbaSalary(angajat2, 6000);

        management.afiseazaDepartamente();
        System.out.print("\n");
        management.calculeazaSalaryMediu(IT);
        System.out.print("\n");
        management.calculeazaSalaryMediu(PR);
    }

    }
