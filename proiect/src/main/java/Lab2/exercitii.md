## Exercițiul 1
Creați structura unui sistem de managment al angajaților.
- o clasă Departamente cu o listă statică “departamente” (Listă de departamente) [done]
- o clasă Departament cu numeDepartament (final), o valoare privată numărAngajați și o listă privată statică “membri” (List<Angajat>) [done]
- o clasă Angajat cu câmpurile nume, prenume, anul angajării (int), salary (int) și departament [done]
- o clasă service (ManagementService, singleton) de unde se pot executa instrucțiuni

## Exercițiul 2
Adăugați funcționalitate la structura anterioară
- Clasa Departament
  - metode pentru modificarea numărului de angajați, a listei de angajați și afișarea lor
- Clasa Angajat
  - metode necesare pentru lucrul cu acest tip de obiect
    - faceți override la metoda equals() luând în considerare câmpurile nume, prenume, anul angajării și departamentul
- Clasa ManagementService
  - înregistrează un nou angajat (verificare lista de membri din departament înainte de înregistrare)
    - List.get(angajat) returnează null dacă angajatul nu există sau List.contains(angajat) cu return de tip boolean
  - schimbă departamentul unui angajat
  - schimbă salariul unui angajat
  - afișează toate departamentele din companie
  - afișează numărul de membri și membri din fiecare departament
  - calculează și afișează salariul mediu dintr-un departament
    - folosiți bucla for() pentru parcurgerea listei


## Exercițiul 3
Executați instrucțiuni pe funcționalitatea implementată la exerciuțiul anterior
- Implementați clasa Main din care să executați următoarele:
  - creați cel puțin 2 departamente în companie
  - creați câte cel puțin 2 angajati în fiecare departament
  - executați cel puțin o instrucțiune de modificare a departamentului unui angajat
  - executați cel puțin o instrucțiune de modificare a salariului unui angajat
  - afișați salariile medii din fiecare departament