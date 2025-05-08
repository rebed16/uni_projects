[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/31XZyb90)
# Titlu proiect: Sistem de gestionare al unui magazin de muzică
### Student(i): Detari Rebeca

## Descriere

Aplicația are în vedere păstrarea datelor cu privire la stocul unui magazin de muzică, pentru a gestiona eficient aprovizionarea magazinului cu produsele necesare. Această aplicație ia în vedere un magazin de muzică ce vinde atât instrumente muzicale cât și albume muzicale. În cazul instrumentelor muzicale se vor păstra informații despre: denumirea instrumentului, tipul de instrument (de suflat, de percuție, cu coarde, cu clape), brandul instrumentului, materialul din care este confecționat  (lemn, plastic, alamă, argint, aur), culoarea și prețul. În ce privește albumele muzicale va oferi informații despre: numele albumului, genul muzical (rock, pop, jazz, muzică clasică, muzică instrumentală, muzică vocală), numele artistului, prețul. 


## Obiective

1.	Gestionarea eficientă a stocului. Posibilitatea de a adăuga, șterge și actualiza informații referitoare la un anumit produs. Acest aspect ajută vânzătorul să poată oferi informații cât mai utile și corecte cumpărătorului.
   
2.	Îmbunătățirea experienței de căutare și filtrare a produselor.  Oferirea posibilității de a căuta rapid produse dintr-o anumită categorie (instrument muzical, album muzical).  Oferirea posibilității unei căutări avansate (după numele artistului, brandul instrumentului, genul de muzică, tipul de instrument).
   
3.	Vizualizarea unor detalii avansate. Oferirea de informații complete pentru fiecare produs. Acest aspect ajută clienții să decidă dacă un anumit instrument sau album muzical li se potrivește sau respecta anumite criterii dorite de ei.
   


## Arhitectura
Identificarea claselor:


Clasa AlbumMuzical 
Atribute: id, nume album, nume artist, gen, format (CD, vinil etc.), preț


Clasa InstrumentMuzical
Atribute: id, denumirea instrumentului, tipInstrument (cu coarde, de suflat, de percuție, cu clape), brand, material, culoare, preț.


Clasa ClientMagazin:
Atribute: id, username, parola

Clasa Manager
Atribute: id, username, parola



Identificarea Tabelelor 



Tabelul albume
Coloane: idAlbum, nume_album, artist, gen, format, preț

Tabelul instrumente 
Coloane: idInstrument, instrument, tip, brand, culoare, preț

Tabelul ClientMagazin
Coloane: id, username, parola

## Functionalitati/Exemple utilizare

1.	Adăugarea de produse. Aplicația pemrite adăugarea a noi produse în stocul magazinului, prin completarea unui formular cu informațiile aferente produsului respective. Formularul diferă în funcție de produsul care urmează adăugat, deoarece nu avem nevoie de exact aceleași detalii pentru un instrument și pentru un album.
   
2.	Ștergerea de produse. Dacă un produs nu mai este în stoc, acesta trebuie șters, sau trebuie marcat acest lucru. Ar fi utilă implementarea unei metode care necesită confirmarea ștergerii produsului, pentru a nu exista ștergeri accidentale.
   

3.	Căutare și filtrarea produselor. Această funcționlitate oferă posibilitatea unei navigări rapide în stocul magazinului, pentru a găsi un anumit produs. Aplicarea unor filtre mai specifice ajută la restrângerea numărului de produse prin care trebuie trecut pentru a ajunge la produsul dorit. Filtrele personalizate pot fi: tipul instrumentului (cu coarde, de suflat, de percuție, cu clape), materialul din care este confecționat instrumentul (lemn, argint, aur, plastic), genul muzical al unui album(clasic, rock, jazz). O altă filtrare utilă este cea după preț.
   

