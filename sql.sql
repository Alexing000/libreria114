-- Active: 1715674821794@@127.0.0.1@3306@libreria114
use libreria114;
create table autore(
id int primary key auto_increment,
nome varchar(100),
cognome varchar(100),
biografia varchar(5000),
rating double,
url_autore varchar(500)
);

create table libro(
id int primary key auto_increment,
titolo varchar(100),
trama varchar(1000),
autore varchar(100),
nPagine int,
genere varchar(100),
dataPubblicazione int,
rating double,
url varchar(255),
id_autore int,
foreign key (id_autore) references autore(id) 
on update cascade
on delete set null
);
select libriChallenge from utente where id=1;
create table utente(
id int primary key auto_increment,
username varchar(100),
password varchar(100),
nome varchar(100),
cognome varchar(100),
email varchar(100),
ruolo varchar(100)
);

create table associa(
id int  primary key auto_increment,
id_libro int,
foreign key (id_libro) references libro(id),
id_utente int,
foreign key (id_utente) references utente(id)
);





-- Inserimento di 20 utenti con dati realistici
INSERT INTO utente (username, password, nome, cognome, email, ruolo) VALUES
('jdoe', 'password1', 'John', 'Doe', 'jdoe@example.com', 'admin'),
('asmith', 'password2', 'Alice', 'Smith', 'asmith@example.com', 'user'),
('bwhite', 'password3', 'Bob', 'White', 'bwhite@example.com', 'user'),
('cgreen', 'password4', 'Carol', 'Green', 'cgreen@example.com', 'user'),
('dblack', 'password5', 'David', 'Black', 'dblack@example.com', 'user'),
('eknight', 'password6', 'Eva', 'Knight', 'eknight@example.com', 'user'),
('fwoods', 'password7', 'Frank', 'Woods', 'fwoods@example.com', 'user'),
('ghall', 'password8', 'Grace', 'Hall', 'ghall@example.com', 'user'),
('ijones', 'password9', 'Ivy', 'Jones', 'ijones@example.com', 'user'),
('jking', 'password10', 'Jack', 'King', 'jking@example.com', 'user'),
('kmoore', 'password11', 'Karen', 'Moore', 'kmoore@example.com', 'user'),
('llane', 'password12', 'Leo', 'Lane', 'llane@example.com', 'user'),
('mclark', 'password13', 'Mona', 'Clark', 'mclark@example.com', 'user'),
('nfox', 'password14', 'Nathan', 'Fox', 'nfox@example.com', 'user'),
('owilson', 'password15', 'Olivia', 'Wilson', 'owilson@example.com', 'user'),
('pscott', 'password16', 'Paul', 'Scott', 'pscott@example.com', 'user'),
('qyoung', 'password17', 'Quincy', 'Young', 'qyoung@example.com', 'user'),
('rhill', 'password18', 'Rachel', 'Hill', 'rhill@example.com', 'user'),
('ssanders', 'password19', 'Sam', 'Sanders', 'ssanders@example.com', 'user'),
('ttaylor', 'password20', 'Tina', 'Taylor', 'ttaylor@example.com', 'user'),
;



INSERT INTO associa (id_libro, id_utente, pagineLette,ratingPersonale, recensione) VALUES
(1,1,3,4,"wow"),
(1,2,3,4,"wow"),
(1,3,3,4,"wow"),
(1,4,3,4,"wow");

-- Inserimento di 20 autori con dati realistici
INSERT INTO autore (nome, cognome, biografia, rating, url_autore) VALUES
('F. Scott', 'Fitzgerald', 'Francis Scott Fitzgerald (Saint Paul, 24 settembre 1896 – Los Angeles, 21 dicembre 1940) è stato uno scrittore e sceneggiatore statunitense, autore di romanzi e racconti. È considerato uno fra i maggiori autori dell\'Età del jazz e dei cosiddetti ruggenti anni Venti e, più in generale, per la sua opera complessiva del XX secolo.', 4.4, 'https://upload.wikimedia.org/wikipedia/commons/5/5c/F_Scott_Fitzgerald_1921.jpg'),
('Harper', 'Lee', 'Nelle Harper Lee (Monroeville, 28 aprile 1926 – Monroeville, 19 febbraio 2016) è stata una scrittrice statunitense. Nel 1962 dal suo bestseller Il buio oltre la siepe il regista Robert Mulligan trasse l\'omonimo film che ricevette 8 nomination al premio oscar.', 4.8, 'https://upload.wikimedia.org/wikipedia/commons/5/5f/HarperLee_2007Nov05.jpg'),
('George', 'Orwell','George Orwell, pseudonimo di Eric Arthur Blair (Motihari, 25 giugno 1903 – Londra, 21 gennaio 1950), è stato uno scrittore, giornalista, saggista, attivista e critico letterario britannico.', 4.7, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/800px-George_Orwell_press_photo.jpg'),
('Jane', 'Austen', 'Jane Austen (Steventon, 16 dicembre 1775 – Winchester, 18 luglio 1817) è stata una scrittrice britannica, figura di spicco della narrativa neoclassica del periodo della reggenza inglese, nonché della transizione tra il romanticismo e il realismo', 4.5, 'https://4.bp.blogspot.com/-3vzNh1f0qhw/U_SQqwYQS_I/AAAAAAAAEPs/4rjWybOm_vc/s1600/Jane%2BAusten.jpg'),
('J.R.R.', 'Tolkien', 'John Ronald Reuel Tolkien (3 gennaio 1892 – Bournemouth, 2 settembre 1973) è stato uno scrittore, filologo, glottoteta e linguista britannico. Importante studioso della lingua inglese antica.', 4.9, 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/J._R._R._Tolkien%2C_ca._1925.jpg/800px-J._R._R._Tolkien%2C_ca._1925.jpg'),
('Gabriel', 'García Márquez', 'Gabriel José de la Concordia García Márquez, noto semplicemente come Gabriel García Márquez (Aracataca, 6 marzo 1927 – Città del Messico, 17 aprile 2014), è stato uno scrittore, giornalista e saggista colombiano naturalizzato messicano, insignito del Premio Nobel per la letteratura nel 1982.', 4.5, 'https://upload.wikimedia.org/wikipedia/commons/6/61/Gabriel_Garcia_Marquez_1984.jpg'),
('Umberto', 'Eco', 'Umberto Eco (Alessandria, 5 gennaio 1932 – Milano, 19 febbraio 2016) è stato un semiologo, filosofo, scrittore, traduttore, bibliofilo e medievista italiano. Scrisse numerosi testi di semiotica, estetica medievale, linguistica e filosofia, oltre a romanzi di successo.', 4.6, 'https://upload.wikimedia.org/wikipedia/commons/6/64/Italiaanse_schrijver_Umberto_Eco%2C_portret.jpg'),
('Fëdor', 'Dostoevskij','Fëdor Michajlovič Dostoevskij (Mosca, 11 novembre 1821– San Pietroburgo, 9 febbraio 1881) è stato uno scrittore e filosofo russo. È considerato, insieme a Tolstoj, uno dei più grandi romanzieri e pensatori russi di tutti i tempi.', 4.3, 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Dostoevskij_1872.jpg/800px-Dostoevskij_1872.jpg'),
('Lev', 'Tolstoj', 'Lev Nikolàevič Tolstòj (9 settembre 1828 – Astàpovo, 20 novembre 1910) è stato uno scrittore, filosofo, educatore e attivista sociale russo. La fama di Tolstoj è legata al suo pensiero pedagogico, filosofico e religioso, da lui espresso in numerosi saggi e lettere che ispirarono, in particolare, la condotta nonviolenta dei tolstoiani e del Mahatma Gandhi.', 4.8, 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/L.N.Tolstoy_Prokudin-Gorsky.jpg/800px-L.N.Tolstoy_Prokudin-Gorsky.jpg'),
('Franz', 'Kafka', 'Franz Kafka (Praga, 3 luglio 1883 – Kierling, 3 giugno 1924) è stato uno scrittore boemo di lingua tedesca. Viene ritenuto una delle maggiori figure della letteratura del XX secolo e tra i maggiori esponenti del modernismo, nonché anticipatore del surrealismo e del realismo magico.', 4.4, 'https://upload.wikimedia.org/wikipedia/commons/d/d3/Franz_Kafka_1917.jpg'),
('Herman', 'Melville', 'Herman Melville (New York, 1 agosto 1819 – New York, 28 settembre 1891) è stato uno scrittore, poeta e critico letterario statunitense, autore nel 1851 del romanzo Moby Dick, considerato uno dei capolavori della letteratura americana.', 4.1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Herman_Melville_by_Joseph_O_Eaton.jpg/800px-Herman_Melville_by_Joseph_O_Eaton.jpg'),
('Miguel', 'de Cervantes', 'Miguel de Cervantes Saavedra (Alcalá de Henares, 29 settembre 1547 – Madrid, 22 aprile 1616) è stato uno scrittore, poeta, drammaturgo e militare spagnolo. La sua influenza sulla letteratura spagnola è stata tale che lo spagnolo è stato definito la lingua di Cervantes e a lui è stato dedicato l\'Istituto di lingua e cultura spagnola.', 4.3, 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Cervantes_J%C3%A1uregui.jpg/800px-Cervantes_J%C3%A1uregui.jpg'),
('J.K.', 'Rowling', 'Joanne Rowling (Yate, 31 luglio 1965) è una scrittrice, sceneggiatrice e produttrice cinematografica britannica. Nel 2013 pubblica la sua prima opera con lo pseudonimo di Robert Galbraith. Nel 2011 è stata inserita da Forbes nella classifica delle donne più ricche del Regno Unito.', 4.8, 'https://grupoaristeo.com/wp-content/uploads/2019/09/J.K.-Rowling--1024x1024.jpg'),
('George', 'Martin', 'George Raymond Richard Martin (Bayonne, 20 settembre 1948) è uno scrittore, sceneggiatore e produttore televisivo statunitense.', 4.5, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Portrait_photoshoot_at_Worldcon_75%2C_Helsinki%2C_before_the_Hugo_Awards_%E2%80%93_George_R._R._Martin.jpg/800px-Portrait_photoshoot_at_Worldcon_75%2C_Helsinki%2C_before_the_Hugo_Awards_%E2%80%93_George_R._R._Martin.jpg'),
('Suzanne', 'Collins', 'Suzanne Collins (Hartford, 10 agosto 1962) è una scrittrice e sceneggiatrice statunitense, nota per aver scritto la serie di Hunger Games.', 4.2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Suzanne_Collins_David_Shankbone_2010.jpg/800px-Suzanne_Collins_David_Shankbone_2010.jpg'),
('Veronica', 'Roth', 'Veronica Roth (New York, 19 agosto 1988) è una scrittrice statunitense conosciuta per aver scritto la trilogia Divergent. Divergent è il suo romanzo d\'esordio, pubblicato negli Stati Uniti nel 2012, ed è rimasto nella top ten dei libri più venduti per tre mesi consecutivi.', 4.5, 'https://upload.wikimedia.org/wikipedia/commons/6/67/Veronica_Roth_March_18%2C_2014_%28cropped%29.jpg'),
('James', 'Joyce', 'James Augustine Aloysius Joyce (Dublino, 2 febbraio 1882 – Zurigo, 13 gennaio 1941) è stato uno scrittore, poeta e drammaturgo irlandese. Benché la sua produzione letteraria non sia molto vasta, è stato di fondamentale importanza per lo sviluppo della letteratura del XX secolo, in particolare della corrente modernista.', 4.3, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/James_Joyce_by_Alex_Ehrenzweig%2C_1915_cropped.jpg/800px-James_Joyce_by_Alex_Ehrenzweig%2C_1915_cropped.jpg'),
('Gustave', 'Flaubert', 'Gustave Flaubert (Rouen, 12 dicembre 1821 – Croisset, 8 maggio 1880) è stato uno scrittore francese. Considerato il maestro del realismo nella letteratura francese, è conosciuto soprattutto per essere l\'autore del romanzo Madame Bovary e per l\'accusa di immoralità che questa opera gli procurò.', 4.1, 'https://upload.wikimedia.org/wikipedia/commons/4/4f/Gustave_Flaubert.jpg'),
('Ernest', 'Hemingway', 'Ernest Miller Hemingway (Oak Park, 21 luglio 1899 – Ketchum, 2 luglio 1961) è stato uno scrittore e giornalista statunitense. Ricevette il Premio Pulitzer nel 1953 per Il vecchio e il mare e vinse il premio Nobel per la letteratura nel 1954.', 4.5, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Ernest_Hemingway_Writing_at_Campsite_in_Kenya_-_NARA_-_192655.jpg/800px-Ernest_Hemingway_Writing_at_Campsite_in_Kenya_-_NARA_-_192655.jpg'),
('William', 'Shakespeare', 'William Shakespeare (Stratford-upon-Avon, 23 aprile 1564 – Stratford-upon-Avon, 23 aprile 1616) è stato un drammaturgo e poeta inglese, considerato il più importante scrittore inglese e generalmente ritenuto il più eminente drammaturgo della cultura occidentale.', 4.8, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/John_Swaine_-_Shakspeare_from_the_First_Folio_Edition_-_B1976.1.232_-_Yale_Center_for_British_Art_%28cropped%29.jpg/800px-John_Swaine_-_Shakspeare_from_the_First_Folio_Edition_-_B1976.1.232_-_Yale_Center_for_British_Art_%28cropped%29.jpg'),
('Alexandre', 'Dumas', 'Alexandre Dumas (Villers-Cotterêts, 24 luglio 1802 – Neuville-lès-Dieppe, 5 dicembre 1870), è stato uno scrittore e drammaturgo francese. Dai suoi libri sono stati tratti numerosi adattamenti cinematografici e televisivi.', 4.6, 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Nadar_-_Alexander_Dumas_p%C3%A8re_%281802-1870%29_-_Google_Art_Project_2.jpg/800px-Nadar_-_Alexander_Dumas_p%C3%A8re_%281802-1870%29_-_Google_Art_Project_2.jpg'),
('Oscar', 'Wilde', 'Oscar Fingal O\'Flahertie Wills Wilde (Dublino, 16 ottobre 1854 – Parigi, 30 novembre 1900) è stato uno scrittore, aforista, poeta, drammaturgo, giornalista, saggista e critico letterario irlandese dell\'età vittoriana, esponente del decadentismo e dell\'estetismo britannico. Le sue opere, tra le quali – in particolare – i suoi testi teatrali, sono considerate dai critici dei capolavori del teatro dell\'Ottocento.', 4.7, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Oscar_Wilde_portrait_by_Napoleon_Sarony_-_albumen.jpg/800px-Oscar_Wilde_portrait_by_Napoleon_Sarony_-_albumen.jpg'),
('Victor', 'Hugo', 'Victor-Marie Hugo (Besançon, 26 febbraio 1802 – Parigi, 22 maggio 1885) è stato uno scrittore, poeta, drammaturgo e politico francese, considerato il padre del Romanticismo in Francia. Si cimentò in numerosi campi, divenendo noto anche come saggista, aforista, artista visivo, statista e attivista per i diritti umani.', 4.7, 'https://upload.wikimedia.org/wikipedia/commons/7/7c/Victor_Hugo.jpg'),
('Stendhal', ' ', 'Stendhal, pseudonimo di Marie-Henri Beyle (Grenoble, 23 gennaio 1783 – Parigi, 23 marzo 1842), è stato uno scrittore francese. I suoi protagonisti sono giovani romantici che aspirano alla felicità attraverso la realizzazione di sé, il desiderio della gloria e l\'espansione di sentimenti d\'amore appassionati.', 4.0, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Stendhal.jpg/800px-Stendhal.jpg'),
('Herman', 'Hesse', 'Hermann Hesse (Calw, 2 luglio 1877 – Montagnola, 9 agosto 1962) è stato uno scrittore, poeta e pittore tedesco naturalizzato svizzero, insignito del premio Nobel per la letteratura nel 1946. La sua produzione, in versi e in prosa, è vastissima e consta di quindici raccolte di poesie e trentadue tra romanzi e raccolte di racconti.', 4.3, 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Hermann_Hesse_2.jpg/800px-Hermann_Hesse_2.jpg'),
('Milan', 'Kundera', 'Milan Kundera (1 aprile 1929 – Parigi, 11 luglio 2023) è stato uno scrittore, poeta, saggista e drammaturgo francese di origine cecoslovacca ed etnia ceca. La sua opera più nota è il romanzo L\'insostenibile leggerezza dell\'essere.', 3.9, 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Milan_Kundera_redux.jpg/800px-Milan_Kundera_redux.jpg'),
('Emily', 'Brontë', 'Emily Jane Brontë (Thornton, 30 luglio 1818 – Haworth, 19 dicembre 1848) è stata una scrittrice britannica dell\'età vittoriana, nonché una delle sorelle Brontë, famosa per il suo unico romanzo Cime tempestose del 1847.', 4.4, 'https://alastensas.com/wp-content/uploads/2023/01/emily-bronte-e1637246193236-768x576.jpg'),
('Michail','Bulgàkov', 'Michail Bulgàkov (Kiev, 15 maggio 1891 – Mosca, 10 marzo 1940) è stato uno scrittore e drammaturgo russo della prima metà del XX secolo. È considerato uno dei maggiori romanzieri del Novecento. Molti suoi scritti sono stati pubblicati postumi.', 4.1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/%D0%9C%D0%B8%D1%85%D0%B0%D0%B8%D0%BB_%D0%91%D1%83%D0%BB%D0%B3%D0%B0%D0%BA%D0%BE%D0%B2_1937.jpg/800px-%D0%9C%D0%B8%D1%85%D0%B0%D0%B8%D0%BB_%D0%91%D1%83%D0%BB%D0%B3%D0%B0%D0%BA%D0%BE%D0%B2_1937.jpg'),
('Jerome David', 'Salinger','J. D. Salinger (all\'anagrafe Jerome David Salinger (New York, 1º gennaio 1919 – Cornish, 27 gennaio 2010) è stato uno scrittore statunitense. I temi principali nei lavori di Salinger sono la descrizione dei pensieri e delle azioni di giovani disadattati, adolescenti sempre silenziosi che non esprimono ciò che provano, la capacità di redenzione che i bambini hanno su questi, e il disgusto per la società borghese e convenzionale.', 4.2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/J._D._Salinger_%28Catcher_in_the_Rye_portrait%29.jpg/800px-J._D._Salinger_%28Catcher_in_the_Rye_portrait%29.jpg'),
('Antoine', 'de Saint-Exupéry','Antoine Jean Baptiste Marie Roger de Saint-Exupéry, noto anche con lo pseudonimo di Tonio (Lione, 29 giugno 1900 – Riou, 31 luglio 1944), è stato uno scrittore, aviatore e militare francese. È conosciuto per essere l\'autore del romanzo Il piccolo principe e per i suoi racconti sul mondo dei primi voli aerei.', 4.7, 'https://i.pinimg.com/originals/d7/ea/f8/d7eaf8a253e686850fee3d42af868a8e.jpg'),
('Paulo', 'Coelho', 'Paulo Coelho de Souza, noto come Paulo Coelho (Rio de Janeiro, 24 agosto 1947) è uno scrittore, poeta e blogger brasiliano. L\'Alchimista vende appena 900 copie nella sua prima edizione, per poi esplodere nel 1990, raggiungendo 100 milioni di copie vendute nel mondo; è stato fin\'ora il libro di maggior successo mai scritto in lingua portoghese.', 4.5, 'https://upload.wikimedia.org/wikipedia/commons/3/3a/Paulo_Coelho_2013-10-01_001.jpg'),
('Dan', 'Brown', 'Daniel Gerhard Brown conosciuto come Dan Brown (Exeter, 22 giugno 1964) è uno scrittore statunitense di thriller. Con più di 200 milioni di copie vendute, è tra gli autori thriller più popolari e di maggior successo del XXI secolo.', 4.6, 'https://upload.wikimedia.org/wikipedia/commons/8/8b/Dan_Brown_bookjacket_cropped.jpg'),
('Tracy', 'Chevalier', 'Tracy Chevalier (Washington, 19 ottobre 1962) è una scrittrice statunitense naturalizzata britannica di romanzi storici.', 3.9, 'https://upload.wikimedia.org/wikipedia/commons/a/aa/Tracy_Chevalier_tree.jpg'),
('Anna', 'Frank', 'Annelies Marie Frank (Francoforte sul Meno, 12 giugno 1929 – Bergen-Belsen, febbraio o marzo 1945), è stata una giovane ebrea tedesca, divenuta un simbolo della Shoah per il suo diario, scritto nel periodo in cui lei e la sua famiglia si nascondevano dai nazisti, e per la sua tragica morte nel campo di concentramento di Bergen-Belsen.', 4.4, 'https://upload.wikimedia.org/wikipedia/commons/f/fe/Anne_Frank_lacht_naar_de_schoolfotograaf.jpg'),
('Arthur', 'Golden','Arthur Golden (Chattanooga, 6 dicembre 1956) è uno scrittore e saggista statunitense.', 4.5, 'https://www.okurpuani.com/admin/orta/golden.jpg'),
('Patrick', 'Rothfuss', 'Patrick Rothfuss (Madison, 6 giugno 1973) è uno scrittore statunitense. Secondo il suo sito, Patrick Rothfuss è cresciuto leggendo una grande quantità di libri, anche a causa del cattivo tempo e dell\'assenza della televisione.', 3.9, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Patrick-rothfuss-2014-kyle-cassidy.jpg/800px-Patrick-rothfuss-2014-kyle-cassidy.jpg'),
('Gregory David', 'Roberts', 'Gregory David Roberts, nato Gregory John Peter Smith (Melbourne, 21 giugno 1952), è uno scrittore australiano.', 4.4, 'https://i.pinimg.com/originals/f6/3e/a3/f63ea3054ddb36bc80c72d0831eeee85.jpg'),
('Carlos', 'Ruiz Zafón', 'Carlos Ruiz Zafón (Barcellona, 25 settembre 1964 – Los Angeles, 19 giugno 2020) è stato uno scrittore spagnolo', 4.5, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Carlos_Ruiz_Zaf%C3%B3n_-_002.jpg/800px-Carlos_Ruiz_Zaf%C3%B3n_-_002.jpg'),
('Khaled', 'Hosseini', 'Khaled Hosseini (Kabul, 4 marzo 1965) è uno scrittore e medico afghano naturalizzato statunitense. È noto globalmente per aver scritto il best seller internazionale Il cacciatore di aquiloni (2003)', 4.6, 'https://upload.wikimedia.org/wikipedia/commons/f/f4/Khaled_Hosseini_%289037470275%29.jpg'),
('Paulina', 'Simons', 'Paullina Simons (Leningrado, 1963) è una scrittrice russa. Il suo nome da nubile è Paullin Handler. Il libro di maggior successo che ha scritto è stato Il cavaliere d\'inverno ambientato nell\'Unione Sovietica durante l\'Assedio di Leningrado.', 4.4, 'https://hillsfocus.com.au/wp-content/uploads/2019/08/Paullina-Simons-7543-credit-Robert-Frith-Acorn-Photo-2012-768x1151.jpg'),
('Haruki', 'Murakami', 'Haruki Murakami (Kyoto, 12 gennaio 1949) è uno scrittore e traduttore giapponese. È stato tradotto in circa cinquanta lingue e i suoi best seller hanno venduto milioni di copie. Le sue opere di narrativa si sono guadagnate il consenso della critica e numerosi premi, sia in Giappone che a livello internazionale', 4.2, 'https://upload.wikimedia.org/wikipedia/commons/e/eb/Murakami_Haruki_%282009%29.jpg'),
('Kahlil', 'Gibran', 'Khalil Gibran, noto anche come Kahlil Gibran, nato Gibran Khalil Gibran (Bsharre, 6 dicembre 1883 – New York, 11 aprile 1931), è stato un poeta, pittore e aforista libanese naturalizzato statunitense.', 4.5, 'https://upload.wikimedia.org/wikipedia/commons/3/34/Kahlil_Gibran_1913.jpg'),
('Primo', 'Levi', 'Primo Levi (Torino, 31 luglio 1919 – Torino, 11 aprile 1987) è stato uno scrittore, chimico e partigiano italiano, superstite dell\'Olocausto e autore di saggi, romanzi, racconti, memorie e poesie. Partigiano antifascista, il 13 dicembre 1943 fu arrestato dai fascisti e l\'anno successivo deportato nel campo di concentramento di Auschwitz in quanto ebreo. Scampato al lager, tornò in Italia, dove si dedicò con impegno al compito di raccontare le atrocità viste e subite. ', '4.7', 'https://upload.wikimedia.org/wikipedia/it/c/c2/Primo_Levi_bianco_e_nero.JPG'),
('Fred', 'Uhlman', 'Fred Uhlman, noto anche come Manfred Uhlmann (Stoccarda, 19 gennaio 1901 – Londra, 11 aprile 1985), è stato uno scrittore, avvocato e pittore tedesco naturalizzato britannico.', 4.3, 'https://i.pinimg.com/736x/48/10/8b/48108bf264c1189ab7cbf1f221334f2b.jpg'),
('Helen', 'Fielding', 'Helen Fielding (Morley, 19 febbraio 1958) è una scrittrice, produttrice televisiva, giornalista e sceneggiatrice inglese, autrice de Il diario di Bridget Jones, pubblicato nel 1996, dal quale è stato tratto il film omonimo campione d\'incassi.', 4.1, 'https://upload.wikimedia.org/wikipedia/commons/b/bd/HF-purple-with-chair.jpg'),
('Ken', 'Follett', 'Ken Follett, all\'anagrafe Kenneth Martin Follett (Cardiff, 5 giugno 1949), è uno scrittore britannico. Considerato uno dei più grandi narratori al mondo, ha raggiunto la prima posizione del New York Times best seller list con molti dei suoi romanzi.', 4.3, 'https://upload.wikimedia.org/wikipedia/commons/4/4c/Ken_Follett_official.jpg'),
('E.', 'L. James', 'E. L. James, pseudonimo di Erika Leonard (Londra, 7 marzo 1963), è una scrittrice britannica. È l\'autrice del romanzo erotico best seller Cinquanta sfumature di grigio.', 4.0, 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/E._L._James_-_2019_%28cropped%29.jpg/800px-E._L._James_-_2019_%28cropped%29.jpg'),
('Mary', 'Shelley', 'Mary Shelley (Londra, 30 agosto 1797 – Londra, 1 febbraio 1851), è stata una scrittrice, saggista e filosofa britannica.', 4.3, 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Mary_Wollstonecraft_Shelley_Rothwell.tif/lossy-page1-800px-Mary_Wollstonecraft_Shelley_Rothwell.tif.jpg'),
('Bram', 'Stoker', 'Abraham Stoker, detto Bram (Dublino, 8 novembre 1847 – Londra, 20 aprile 1912), è stato uno scrittore irlandese, divenuto celebre come autore di Dracula, uno fra i più conosciuti romanzi gotici del terrore.', 4.2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Bram_Stoker_1906.jpg/800px-Bram_Stoker_1906.jpg'),
('Dante', 'Alighieri', 'Dante Alighieri, o Alighiero (Firenze 1265 – Ravenna 1321), è stato un poeta, scrittore e politico italiano. È considerato il padre della lingua italiana; la sua fama è dovuta alla paternità della Comedìa, divenuta celebre come Divina Commedia e universalmente considerata la più grande opera scritta in lingua italiana e uno dei maggiori capolavori della letteratura mondiale', 4.9, 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Portrait_de_Dante.jpg/800px-Portrait_de_Dante.jpg');



INSERT INTO libro (titolo, trama, autore, nPagine, genere, dataPubblicazione, rating, url, id_autore) VALUES
('Il Grande Gatsby', 'Un romanzo sul sogno americano.', 'F. Scott Fitzgerald', 180, 'Classico', '1925', 4.3, 'https://m.media-amazon.com/images/I/61L+g7s2rdL._SL1500_.jpg', 1),
('Il Buio Oltre la Siepe', 'Una storia di ingiustizia razziale nel profondo sud.', 'Harper Lee', 281, 'Classico', '1960', 4.8, 'https://m.media-amazon.com/images/I/713Q8cZg6KL._SL1500_.jpg', 2),
('1984', 'Un romanzo distopico sul totalitarismo.', 'George Orwell', 328, 'Distopico', '1949', 4.7, 'https://m.media-amazon.com/images/I/71GuYZ9CinL._SL1481_.jpg', 3),
('Orgoglio e Pregiudizio', 'Un romanzo romantico di buone maniere.', 'Jane Austen', 279, 'Romantico', '1813', 4.5, 'https://m.media-amazon.com/images/I/71EoYkXesEL._SL1500_.jpg', 4),
('Il Signore degli Anelli', 'Una epica storia di avventura e amicizia.', 'J.R.R. Tolkien', 1216, 'Fantasy', '1954', 4.9, 'https://m.media-amazon.com/images/I/81HXjSgmsvL._SL1491_.jpg', 5),
('Cento Anni di Solitudine', 'La storia della famiglia Buendía in Macondo.', 'Gabriel García Márquez', 417, 'Realismo Magico', '1967', 4.6, 'https://m.media-amazon.com/images/I/81nxsT-8NWS._SL1500_.jpg', 6),
('Il Nome della Rosa', 'Un giallo ambientato in un monastero medievale.', 'Umberto Eco', 502, 'Giallo Storico', '1980', 4.3, 'https://m.media-amazon.com/images/I/61WOxzGgv5L._SL1498_.jpg', 7),
('Delitto e Castigo', 'Un romanzo psicologico sul crimine e la punizione.', 'Fëdor Dostoevskij', 671, 'Classico', '1866', 4.4, 'https://m.media-amazon.com/images/I/412J90c9hRL.jpg', 8),
('I Fratelli Karamazov', 'Una storia complessa di famiglia e moralità.', 'Fëdor Dostoevskij', 796, 'Classico', '1880', 4.6, 'https://m.media-amazon.com/images/I/81VQB9HWDWL._SL1481_.jpg', 8),
('Anna Karenina', 'Un romanzo sul amore e la tragedia.', 'Lev Tolstoj', 864, 'Classico', '1877', 4.5, 'https://m.media-amazon.com/images/I/71DL12R0soL._SL1500_.jpg', 9),
('Il Processo', 'Un uomo viene accusato di un crimine sconosciuto.', 'Franz Kafka', 252, 'Distopico', '1925', 4.2, 'https://m.media-amazon.com/images/I/615vK62feZL._SL1500_.jpg', 10),
('Il Castello', 'La storia di un uomo e la sua lotta contro la burocrazia.', 'Franz Kafka', 340, 'Distopico', '1926', 4.0, 'https://m.media-amazon.com/images/I/8157zXUmcvL._SL1500_.jpg', 10),
('Moby Dick', 'La caccia di un capitano ossessionato da una balena bianca.', 'Herman Melville', 635, 'Avventura', '1851', 4.3, 'https://m.media-amazon.com/images/I/71SbstoztmL._SL1270_.jpg', 11),
('Don Chisciotte della Mancia', 'Le avventure comiche di un cavaliere errante.', 'Miguel de Cervantes', 863, 'Classico', '1605', 4.4, 'https://m.media-amazon.com/images/I/810xM-Q+JbL._SL1484_.jpg', 12),
('Harry Potter e la Pietra Filosofale', 'Le avventure di un giovane stregone alla Scuola di Magia e Stregoneria di Hogwarts.', 'J.K. Rowling', 332, 'Fantasy', '1997', 4.7, 'https://m.media-amazon.com/images/I/71mw-YBVOuL._SL1500_.jpg', 13),
('Harry Potter e la Camera dei Segreti', 'Le avventure di un giovane stregone alla Scuola di Magia e Stregoneria di Hogwarts.', 'J.K. Rowling', 360, 'Fantasy', '1998', 4.6, 'https://m.media-amazon.com/images/I/716aYxhlUiL._SL1500_.jpg', 13),
('Harry Potter e il Prigioniero di Azkaban', 'Le avventure di un giovane stregone alla Scuola di Magia e Stregoneria di Hogwarts.', 'J.K. Rowling', 464, 'Fantasy', '1999', 4.7, 'https://m.media-amazon.com/images/I/71LUEWuecxL._SL1500_.jpg', 13),
('Harry Potter e il Calice di Fuoco', 'Le avventure di un giovane stregone alla Scuola di Magia e Stregoneria di Hogwarts.', 'J.K. Rowling', 636, 'Fantasy', '2000', 4.8, 'https://m.media-amazon.com/images/I/71WfG8FffsL._SL1500_.jpg', 13),
('Harry Potter e il Principe Mezzosangue', 'Le avventure di un giovane stregone alla Scuola di Magia e Stregoneria di Hogwarts.', 'J.K. Rowling', 560, 'Fantasy', '2005', 4.7, 'https://m.media-amazon.com/images/I/710OqzcvPML._SL1500_.jpg', 13),
('Harry Potter e i Doni della Morte', 'Le avventure di un giovane stregone alla Scuola di Magia e Stregoneria di Hogwarts.', 'J.K. Rowling', 608, 'Fantasy', '2007', 4.8, 'https://m.media-amazon.com/images/I/71DCa-1brTL._SL1500_.jpg', 13),
('Il Trono di Spade', 'Le vicende di nobili famiglie che lottano per il controllo del Trono di Spade.', 'George R.R. Martin', 694, 'Fantasy', '1996', 4.7, 'https://m.media-amazon.com/images/I/81RqZfV0AiL._SL1316_.jpg', 14),
('Hunger Games', 'La lotta di una ragazza per la sopravvivenza in un futuro distopico.', 'Suzanne Collins', 374, 'Distopia', '2008', 4.6, 'https://m.media-amazon.com/images/I/61HbFOC4uHL._SL1500_.jpg', 15),
('Insurgent', 'La continuazione delle avventure della ragazza dopo gli eventi di Hunger Games.', 'Veronica Roth', 592, 'Distopia', '2012', 4.5, 'https://m.media-amazon.com/images/I/81FwFzRXEML._SL1470_.jpg', 16),
('Divergent', 'La storia di una ragazza che sfida la società distopica in cui vive.', 'Veronica Roth', 496, 'Distopia', '2011', 4.4, 'https://m.media-amazon.com/images/I/517YSANlHUL.jpg', 16),
('Allegiant', 'Il culmine della saga Divergent, con la rivelazione dei segreti della società.', 'Veronica Roth', 526, 'Distopia', '2013', 4.3, 'https://m.media-amazon.com/images/I/81tT6XXfghL._SL1500_.jpg', 16),
('Ulisse', 'Una giornata nella vita di un uomo a Dublino.', 'James Joyce', 730, 'Modernista', '1922', 4.1, 'https://m.media-amazon.com/images/I/71TxeJtlRbL._SY466_.jpg', 17),
('Madame Bovary', 'La storia tragica di una donna insoddisfatta.', 'Gustave Flaubert', 328, 'Realismo', '1857', 4.0, 'https://m.media-amazon.com/images/I/419NxOEYLkL._SY445_SX342_.jpg', 18),
('Guerra e Pace', 'Un vasto romanzo su amore, guerra e storia.', 'Lev Tolstoj', 1225, 'Storico', '1869', 4.7, 'https://m.media-amazon.com/images/I/413XKgLvFHL._SY445_SX342_.jpg', 9),
('Il Vecchio e il Mare', 'La lotta di un vecchio pescatore con un grande pesce.', 'Ernest Hemingway', 127, 'Avventura', '1952', 4.5, 'https://m.media-amazon.com/images/I/516uHGbFojL._SY445_SX342_.jpg', 19),
('Amleto', 'Una tragedia shakespeariana sulla vendetta.', 'William Shakespeare', 342, 'Teatro', '1603', 4.4, 'https://m.media-amazon.com/images/I/51QTtouJkyL._SY445_SX342_.jpg', 20),
('Il Conte di Montecristo', 'Una storia di vendetta e redenzione.', 'Alexandre Dumas', 1243, 'Avventura', '1844', 4.6, 'https://m.media-amazon.com/images/I/41QffKDd8LL._SY445_SX342_.jpg', 21),
('Il Ritratto di Dorian Gray', 'Un giovane uomo vende la sua anima per l\'eterna giovinezza.', 'Oscar Wilde', 254, 'Gotico', '1890', 4.2, 'https://m.media-amazon.com/images/I/41TPD-CFMbL._SY445_SX342_.jpg', 22),
('I Miserabili', 'Le lotte e le vittorie di un ex galeotto.', 'Victor Hugo', 1463, 'Storico', '1862', 4.6, 'https://m.media-amazon.com/images/I/41XOuFdyYoL._SY445_SX342_.jpg', 23),
('Il Rosso e il Nero', 'L\'ambizione di un giovane provinciale nella Francia post-napoleonica.', 'Stendhal', 576, 'Storico', '1830', 4.3, 'https://m.media-amazon.com/images/I/41hokY-avTL._SY445_SX342_.jpg', 24),
('La Fattoria degli Animali', 'Una satira della dittatura stalinista.', 'George Orwell', 112, 'Satira', '1945', 4.4, 'https://m.media-amazon.com/images/I/51EE1hpVRgL._SY445_SX342_.jpg', 3),
('Il Lupo della Steppa', 'Un uomo in lotta con la sua doppia natura.', 'Hermann Hesse', 237, 'Psicologico', '1927', 4.3, 'https://m.media-amazon.com/images/I/5185o7+5iWL._SY445_SX342_.jpg', 25),
('L\'Insostenibile Leggerezza dell\'Essere', 'Una storia di amore e identità durante la Primavera di Praga.', 'Milan Kundera', 314, 'Romantico', '1984', 4.1, 'https://m.media-amazon.com/images/I/41XWUuLXZOL._SY445_SX342_.jpg', 26),
('Cime Tempestose', 'Una storia di passione e vendetta nello Yorkshire.', 'Emily Brontë', 416, 'Romantico', '1847', 4.3, 'https://m.media-amazon.com/images/I/41jS5Th0Y7L._SY445_SX342_.jpg', 27),
('Il Maestro e Margherita', 'Una satira della società sovietica e una storia di amore e magia.', 'Michail Bulgakov', 384, 'Fantastico', '1967', 4.4, 'https://m.media-amazon.com/images/I/41jbdldWA7L._SY445_SX342_.jpg', 28),
('Il Giovane Holden', 'Un adolescente ribelle cerca il suo posto nel mondo.', 'J.D. Salinger', 214, 'Romanzo di Formazione', '1951', 4.0, 'https://m.media-amazon.com/images/I/51EGz4hH+aL._SY466_.jpg', 29),
('Il Piccolo Principe', 'Una storia filosofica travestita da libro per bambini.', 'Antoine de Saint-Exupéry', 96, 'Fiaba', '1943', 4.5, 'https://m.media-amazon.com/images/I/61zglmwA9WL._SL1500_.jpg', 30),
('L\'Alchimista', 'Un pastore alla ricerca della sua Leggenda Personale.', 'Paulo Coelho', 208, 'Avventura', '1988', 4.2, 'https://m.media-amazon.com/images/I/81uLEGiTVuL._SL1432_.jpg', 31),
('Il Codice da Vinci', 'Un professore di simbologia scopre un segreto nascosto per secoli.', 'Dan Brown', 689, 'Thriller', '2003', 4.0, 'https://m.media-amazon.com/images/I/71WlWEKzX8L._SL1500_.jpg', 32),
('La Ragazza con l\'Orecchino di Perla', 'La storia dietro un famoso dipinto di Vermeer.', 'Tracy Chevalier', 233, 'Storico', '1999', 4.1, 'https://m.media-amazon.com/images/I/71R+suJ3I3L._SL1500_.jpg', 33),
('Il Diario di Anna Frank', 'Il diario di una ragazza ebrea durante l\'occupazione nazista.', 'Anna Frank', 283, 'Biografia', '1947', 4.7, 'https://m.media-amazon.com/images/I/61YV+THCFuL._SL1481_.jpg',34),
('Memorie di una Geisha', 'La vita di una geisha giapponese.', 'Arthur Golden', 428, 'Storico', '1997', 4.5, 'https://m.media-amazon.com/images/I/61BjzK++dcL._SL1500_.jpg', 35),
('Il Nome del Vento', 'La storia di un mago raccontata dal protagonista.', 'Patrick Rothfuss', 662, 'Fantasy', '2007', 4.6, 'https://m.media-amazon.com/images/I/81Nu-Q5Qr1L._SL1445_.jpg', 36),
('Shantaram', 'La storia di un fuggitivo australiano in India.', 'Gregory David Roberts', 936, 'Avventura', '2003', 4.4, 'https://m.media-amazon.com/images/I/717YwWoQsBL._SL1500_.jpg', 37),
('L\'ombra del Vento', 'Un giovane scopre un mistero legato a un libro dimenticato.', 'Carlos Ruiz Zafón', 487, 'Mistero', '2001', 4.5, 'https://m.media-amazon.com/images/I/71xn4uTd8RL._SL1441_.jpg', 38),
('Il Cacciatore di Aquiloni', 'Un uomo afgano ritorna nella sua terra natale per fare ammenda.', 'Khaled Hosseini', 371, 'Dramma', '2003', 4.6, 'https://m.media-amazon.com/images/I/61cWKfWoKzL._SL1500_.jpg', 39),
('Il Cavaliere d\'Inverno', 'Una storia d\'amore ambientata durante l\'assedio di Leningrado.', 'Paullina Simons', 656, 'Storico', '2000', 4.4, 'hhttps://m.media-amazon.com/images/I/91nmf8XuJnL._SL1500_.jpg', 40),
('Norwegian Wood', 'Un uomo ricorda il suo primo amore durante gli anni dell\'università.', 'Haruki Murakami', 296, 'Romantico', '1987', 4.2, 'https://m.media-amazon.com/images/I/81zqVhvbHbL._SL1500_.jpg', 41),
('Il Profeta', 'Una serie di saggi poetici su vari temi della vita.', 'Kahlil Gibran', 128, 'Filosofico', '1923', 4.5, 'https://m.media-amazon.com/images/I/51GBqLNiFML.jpg', 42),
('Se Questo è un Uomo', 'Le memorie di un sopravvissuto ai campi di concentramento nazisti.', 'Primo Levi', 206, 'Biografia', '1947', 4.7, 'https://m.media-amazon.com/images/I/61dZugeFuJL._SL1261_.jpg', 43),
('L\'amico Ritrovato', 'La storia di un\'amicizia interrotta dal nazismo.', 'Fred Uhlman', 121, 'Dramma', '1971', 4.3, 'https://m.media-amazon.com/images/I/814Sk8sqyGL._SL1349_.jpg', 44),
('Il Diario di Bridget Jones', 'Le divertenti disavventure di una trentenne single.', 'Helen Fielding', 310, 'Commedia', '1996', 4.1, 'https://m.media-amazon.com/images/I/61y27OBMqpL._SL1500_.jpg', 45),
('I Pilastri della Terra', 'Un vasto romanzo storico sulla costruzione di una cattedrale.', 'Ken Follett', 973, 'Storico', '1989', 4.6, 'https://m.media-amazon.com/images/I/81hgwrXUkJL._SL1500_.jpg', 46),
('Cinquanta Sfumature di Grigio', 'La storia di una relazione complessa tra una giovane donna e un uomo d\'affari.', 'E.L. James', 514, 'Romantico', '2011', 4.0, 'https://m.media-amazon.com/images/I/71GiXLGkdmL._SL1500_.jpg', 47),
('Frankenstein', 'Uno scienziato crea una creatura mostruosa in un esperimento audace.', 'Mary Shelley', 280, 'Horror', '1818', 4.3, 'https://m.media-amazon.com/images/I/51yL0ZK0bJL._SY445_SX342_.jpg', 48),
('Dracula', 'Il conte Dracula cerca di trasferirsi dall\'Europa orientale all\'Inghilterra per trovare nuove vittime.', 'Bram Stoker', 418, 'Horror', '1897', 4.2, 'https://m.media-amazon.com/images/I/41jK2P8TVPL._SY445_SX342_.jpg', 49),
('La Metamorfosi', 'Un uomo si sveglia trasformato in un gigantesco insetto.', 'Franz Kafka', 102, 'Fantascienza', '1915', 4.4, 'https://m.media-amazon.com/images/I/41H762kXuVL._SY445_SX342_.jpg', 10),
('La Divina Commedia', 'Un viaggio epico attraverso Inferno, Purgatorio e Paradiso.', 'Dante Alighieri', 798, 'Poesia', '1320', 4.9, 'https://m.media-amazon.com/images/I/518lqcwEBwL._SY445_SX342_.jpg', 50),
('Il Codice Rebecca', 'Un thriller ambientato durante la Seconda Guerra Mondiale.', 'Ken Follett', 422, 'Thriller', '1980', 4.1, 'https://m.media-amazon.com/images/I/71bHwNwpJmL._SL1500_.jpg', 46);

-- Inserimento di 20 associazioni con dati realistici
INSERT INTO associa (id_libro, id_utente) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20);
create table utente(
id int primary key auto_increment,
username varchar(100),
password varchar(100),
nome varchar(100),
cognome varchar(100),
email varchar(100),
ruolo varchar(100),
libriChallenge int,
libriLetti int
);

create table associa(
id_libro int,
foreign key (id_libro) references libro(id),
id_utente int,
foreign key (id_utente) references utente(id),
pagineLette int,
ratingPersonale double,
recensione varchar(5000)
);

SELECT COUNT(*)
FROM associa
WHERE ratingPersonale IS NOT NULL and id_utente=1;

select sum(ratingPersonale) from associa where id_libro=1;
update associa set ratingPersonale= null where id_utente=1;
select rating from libro where id=1;
update libro set rating=0.0 where id=1;
select * from associa where id_utente=1;
update utente set libriChallenge=0 where id=1;
update utente set libriLetti=0 where id=1;
update associa set id_utente=null where id_libro=2 and id_utente=1;
select * from associa where id_utente=1;
select*from associa where id_utente is null and id_libro=2;
SELECT recensione,id_utente as username FROM associa WHERE id_libro = 2;
delete from associa where id_libro=6;
select *from associa where id_libro =1 and id_utente is null;

select* from associa where id_libro=2;
select u.id,u.username ,a.recensione from libro as l join associa as a on a.id_libro=l.id join utente as u on a.id_utente=u.id where l.id=2;
-- Inserimento di 20 utenti con dati realistici
INSERT INTO utente (username, password, nome, cognome, email, ruolo, libriChallenge,libriLetti) VALUES
('jdoe', 'password1', 'John', 'Doe', 'jdoe@example.com', 'admin', 1,0),
('asmith', 'password2', 'Alice', 'Smith', 'asmith@example.com', 'user',10,0),
('bwhite', 'password3', 'Bob', 'White', 'bwhite@example.com', 'user', 20,0),
('cgreen', 'password4', 'Carol', 'Green', 'cgreen@example.com', 'user', 25,0),
('dblack', 'password5', 'David', 'Black', 'dblack@example.com', 'user', 30,0),
('eknight', 'password6', 'Eva', 'Knight', 'eknight@example.com', 'user', 35,0),
('fwoods', 'password7', 'Frank', 'Woods', 'fwoods@example.com', 'user', 40,0),
('ghall', 'password8', 'Grace', 'Hall', 'ghall@example.com', 'user', 45,0),
('ijones', 'password9', 'Ivy', 'Jones', 'ijones@example.com', 'user', 50,0),
('jking', 'password10', 'Jack', 'King', 'jking@example.com', 'user', 55,0),
('kmoore', 'password11', 'Karen', 'Moore', 'kmoore@example.com', 'user', 60,0),
('llane', 'password12', 'Leo', 'Lane', 'llane@example.com', 'user', 15,0),
('mclark', 'password13', 'Mona', 'Clark', 'mclark@example.com', 'user', 5,0),
('nfox', 'password14', 'Nathan', 'Fox', 'nfox@example.com', 'user', 7,0),
('owilson', 'password15', 'Olivia', 'Wilson', 'owilson@example.com', 'user', 13,0),
('pscott', 'password16', 'Paul', 'Scott', 'pscott@example.com', 'user', 27,0),
('qyoung', 'password17', 'Quincy', 'Young', 'qyoung@example.com', 'user', 32,0),
('rhill', 'password18', 'Rachel', 'Hill', 'rhill@example.com', 'user', 48,0),
('ssanders', 'password19', 'Sam', 'Sanders', 'ssanders@example.com', 'user', 33,0),
('ttaylor', 'password20', 'Tina', 'Taylor', 'ttaylor@example.com', 'user', 17,0);

INSERT INTO associa (id_libro, id_utente, pagineLette,ratingPersonale, recensione) VALUES
(1, 1, 25,4.5,"Il Grande Gatsby di F. Scott Fitzgerald è un capolavoro senza tempo che esplora i sogni, l'illusione e la disillusione nell'America degli anni '20. La storia di Jay Gatsby, con il suo amore incondizionato e la sua ricerca disperata della felicità attraverso la ricchezza e lo status sociale, è struggente e profondamente umana. Fitzgerald dipinge un ritratto vivido dell'età del jazz, con personaggi complessi e una prosa lirica che incanta ad ogni pagina. Il finale è tanto tragico quanto inevitabile, lasciando il lettore a riflettere sulla natura effimera del sogno americano. Un must-read assoluto che continua a risuonare con lettori di tutte le generazioni."),
(2, 2, 30,4.5, "Il Buio Oltre la Siepe di Harper Lee è un romanzo potente e commovente che affronta temi di razzismo, ingiustizia e moralità attraverso gli occhi innocenti della giovane Scout Finch. Ambientato nel profondo Sud degli Stati Uniti durante gli anni '30, il libro segue le vicende del padre di Scout, Atticus Finch, un avvocato che difende un uomo nero accusato ingiustamente di stupro. La narrazione è toccante e ricca di dettagli vividi che catturano perfettamente l'atmosfera del tempo. I personaggi sono indimenticabili e il messaggio di coraggio e integrità è incredibilmente rilevante ancora oggi. Un capolavoro della letteratura americana che tutti dovrebbero leggere almeno una volta."),
(3, 3, 100,4.5, "1984 di George Orwell è un romanzo distopico inquietante e profetico che esplora i temi della sorveglianza, del totalitarismo e della manipolazione della verità. Ambientato in un mondo governato dal Partito e dal suo leader, il Grande Fratello, il libro segue la vita di Winston Smith, un uomo che lotta per mantenere la sua individualità in un regime oppressivo. La narrazione è avvincente e il mondo che Orwell ha creato è allo stesso tempo affascinante e terrificante. La critica sociale è incisiva e il messaggio è ancora straordinariamente pertinente. Un capolavoro che provoca riflessioni profonde sulla libertà e la natura del potere. Imperdibile per chiunque ami la letteratura e la filosofia politica."),
(4, 4, 90,4.5, "Orgoglio e Pregiudizio di Jane Austen è un classico intramontabile che incanta con il suo spirito arguto e la sua esplorazione delle relazioni umane. La storia di Elizabeth Bennet e Mr. Darcy è un perfetto equilibrio di romanticismo e critica sociale. Austen descrive con maestria le dinamiche familiari e le convenzioni sociali dell'Inghilterra del XIX secolo, creando personaggi vividi e indimenticabili. Elizabeth è una protagonista forte e intelligente, mentre la crescita e il cambiamento di Darcy sono affascinanti da seguire. Il dialogo è brillante e spesso ironico, rendendo la lettura un vero piacere. Un libro che merita assolutamente di essere letto e riletto."),
(5, 5, 70,4.5, "Il Signore degli Anelli di J.R.R. Tolkien è un'epica avventura fantasy che trasporta i lettori in un mondo ricco di magia, amicizia e coraggio. La storia segue Frodo Baggins e la sua Compagnia mentre tentano di distruggere l'Unico Anello, un potente artefatto che potrebbe portare alla rovina della Terra di Mezzo. L'universo creato da Tolkien è incredibilmente dettagliato e vivo, con una profondità storica e culturale che rende ogni pagina affascinante. I personaggi sono complessi e memorabili, e il viaggio che intraprendono è tanto emotivo quanto epico. Una lettura imperdibile per chi ama il fantasy, la mitologia e le storie avvincenti di bene contro male. Un vero capolavoro letterario."),
(6, 6, 120,4.5, "Cento Anni di Solitudine di Gabriel García Márquez è un capolavoro del realismo magico che racconta la saga della famiglia Buendía nel villaggio di Macondo. La narrazione è ricca e poetica, intrecciando realtà e fantasia in modo magistrale. I personaggi sono complessi e indimenticabili, ognuno con le proprie storie di amore, tragedia e speranza. Márquez esplora temi universali come il tempo, la solitudine e il destino, creando un mondo così vivido che sembra reale. Questo libro è una celebrazione dell'immaginazione e un'esperienza di lettura unica che rimane con te a lungo dopo aver girato l'ultima pagina. Assolutamente imperdibile per gli amanti della letteratura."),
(7, 7, 89,4.5, "Il Nome della Rosa di Umberto Eco è un affascinante mix di romanzo storico, giallo e riflessione filosofica. Ambientato in un'abbazia benedettina del XIV secolo, il libro segue il frate Guglielmo da Baskerville e il suo giovane novizio Adso mentre indagano su una serie di omicidi misteriosi. La trama è avvincente e ben costruita, ricca di dettagli storici e riferimenti culturali che danno vita al Medioevo. Il personaggio di Guglielmo, con la sua mente acuta e il suo approccio razionale, è indimenticabile. La prosa di Eco è densa e stimolante, rendendo la lettura un'esperienza profondamente gratificante. Un capolavoro che affascina e sfida il lettore in egual misura."),
(8, 8, 56,4.5, "Delitto e Castigo di Fëdor Dostoevskij è un'opera magistrale che esplora la complessità della mente umana e la lotta tra il bene e il male. La storia di Raskolnikov, un giovane studente che commette un omicidio e poi affronta le conseguenze psicologiche e morali delle sue azioni, è intensa e profondamente coinvolgente. Dostoevskij dipinge un quadro vivido della società russa del XIX secolo, e i suoi personaggi sono straordinariamente complessi e realistici. La prosa è potente e la trama è ricca di suspense e riflessioni filosofiche. Un romanzo che sfida il lettore a riflettere sulla giustizia, la redenzione e la natura umana. Assolutamente da leggere."),
(9, 9, 15,4.5, "I Fratelli Karamazov di Fëdor Dostoevskij è un capolavoro epico che esplora le profondità dell'animo umano, intrecciando temi di fede, morale, amore e redenzione. La storia dei fratelli Karamazov e del loro padre, un uomo dissoluto e crudele, è ricca di tensioni filosofiche e conflitti emotivi. I personaggi sono straordinariamente complessi e vividi, ognuno rappresentando diverse visioni del mondo e della vita. La narrazione è densa e stimolante, invitando il lettore a riflettere su questioni esistenziali profonde. Un'opera maestosa e coinvolgente, che lascia un'impressione duratura. Un must-read per chiunque sia interessato alla letteratura e alla filosofia."),
(10, 10, 55,4.5, "Anna Karenina di Lev Tolstoj è un'opera epica che affronta le complessità dell'amore, della morale e della società nella Russia dell'Ottocento. La trama intrecciata segue le vicende di Anna Karenina, una donna intrappolata in un matrimonio infelice che si lascia coinvolgere in una relazione proibita con il Conte Vronskij. Tolstoj offre una profonda analisi psicologica dei personaggi, esplorando le loro lotte interiori e i conflitti morali. La descrizione dettagliata della società russa dell'epoca aggiunge profondità e contesto alla storia. La prosa di Tolstoj è straordinariamente ricca e coinvolgente, trasportando il lettore in un mondo vibrante di emozioni e intrighi. Un classico senza tempo che continua a incantare e ispirare i lettori di ogni generazione."),
(11, 11, 91,4.5, "Il Processo di Franz Kafka è un viaggio affascinante e claustrofobico nella mente di Josef K., un uomo che si trova improvvisamente coinvolto in un processo giudiziario senza sapere di cosa sia accusato o quali siano le prove contro di lui. La prosa di Kafka è intrisa di paranoia e alienazione, trasportando il lettore in un labirinto di burocrazia e assurdità. L'atmosfera oppressiva e surreale è resa con maestria, e la sensazione di impotenza di Josef K. risuona potente fino all'ultima pagina. Sebbene la trama possa risultare enigmatica e aperta a molte interpretazioni, la riflessione sul potere, la colpa e la ricerca della verità rende questo romanzo un'esperienza letteraria indimenticabile. Un classico moderno che merita di essere letto e discusso."),
(12, 12, 117,4.5, "Il Castello di Franz Kafka è un'opera che affascina e confonde in egual misura. Kafka ci trasporta in un mondo surreale e labirintico, dove il protagonista K. cerca disperatamente di ottenere accesso e riconoscimento nel misterioso Castello. La narrazione è intrisa di una tensione costante e di un senso di alienazione, che risuona potente anche ai giorni nostri. Tuttavia, la mancanza di una chiara risoluzione e la natura oscura e enigmatica della trama possono risultare frustranti per alcuni lettori. Nonostante ciò, Il Castello è un'opera letteraria che stimola la riflessione e offre una prospettiva unica sull'alienazione umana e il potere istituzionale. Da leggere con pazienza e apertura mentale."),
(13, 13, 48,4.5, "Moby Dick di Herman Melville è un'epica avventura marina che affronta temi di ossessione, destino e il conflitto tra l'uomo e la natura. La storia del capitano Ahab e della sua implacabile caccia alla balena bianca incarna la lotta dell'umanità contro forze più grandi di sé stesse. Melville crea un mondo marino ricco di dettagli e personaggi memorabili, offrendo anche approfondimenti filosofici sul significato della vita e della morte. Anche se alcuni passaggi possono essere densi, la potenza emotiva di questo romanzo è innegabile. Moby Dick è un classico senza tempo che continua a ispirare e affascinare i lettori di tutte le epoche."),
(14, 14, 71,4.5, "Don Chisciotte della Mancia di Miguel de Cervantes è un capolavoro senza tempo che mescola brillantemente comicità e tragedia, avventura e introspezione. La storia del cavaliere errante Don Chisciotte e del suo devoto scudiero Sancho Panza è un viaggio epico attraverso la Spagna del XVI secolo e, allo stesso tempo, un'indagine profonda sulla natura umana e sulla follia del mondo che li circonda. La prosa di Cervantes è vivace e ricca di sfumature, con personaggi indimenticabili e una trama che oscilla tra il comico e il commovente. Questo romanzo ci spinge a riflettere sulla ricerca della nobiltà d'animo in un mondo che sembra averla dimenticata, e ci ricorda che i sogni possono essere fonte di grande ispirazione, anche se spesso si rivelano illusori. Un classico immortale che continua a incantare i lettori di ogni epoca."),
(15, 15, 36,4.5, "Harry Potter e la Pietra Filosofale di J.K. Rowling è un incantevole viaggio nel mondo della magia che cattura l'immaginazione sin dalle prime pagine. Attraverso la scoperta del magico mondo di Hogwarts insieme a Harry, un giovane mago destinato a grandi cose, il lettore viene trasportato in un'avventura avvincente e piena di misteri. Rowling crea un universo ricco di personaggi indimenticabili, ambientazioni affascinanti e una trama intricata che tiene incollati alle pagine fino all'ultima parola. Con una scrittura brillante e un mix perfetto di humor e suspense, questo libro è adatto a lettori di tutte le età. Una lettura che ti rapisce e ti fa desiderare di vivere nel mondo magico di Harry Potter per sempre."),
(16, 16, 92,4.5, "Harry Potter e la Camera dei Segreti è un'altra magica avventura nell'universo incantato creato da J.K. Rowling. In questo secondo libro della serie, Harry ritorna a Hogwarts per il suo secondo anno, affrontando nuove sfide e misteri che minacciano la sicurezza della scuola. Rowling dimostra ancora una volta la sua maestria nel creare mondi ricchi di dettagli e personaggi indimenticabili. La trama è avvincente e piena di colpi di scena, mentre i temi di amicizia, coraggio e sacrificio continuano a toccare il cuore dei lettori di tutte le età. Una lettura coinvolgente che ti trasporta in un'avventura indimenticabile, lasciandoti desiderare di più. Harry Potter e la Camera dei Segreti è un'altra prova del genio letterario di Rowling e della magia intramontabile della saga di Harry Potter."),
(17, 17, 11,4.5, "Harry Potter e il prigioniero di Azkaban è un'altra brillante aggiunta alla serie magica di J.K. Rowling. In questo terzo libro, Rowling continua a sorprenderci con la sua creatività e il suo talento per l'intrigo. Seguiamo Harry, Hermione e Ron nel loro terzo anno a Hogwarts, mentre si scontrano con i misteri del passato di Harry e l'emergere di nuovi pericoli.

Ciò che rende questo libro così speciale è l'approfondimento del mondo magico di Rowling. Da Azkaban, la prigione dei maghi, ai destini intrecciati dei personaggi, ogni pagina è ricca di dettagli affascinanti. I nuovi personaggi, come Sirius Black e il professore Lupin, sono perfettamente integrati nella trama e aggiungono un nuovo livello di complessità alla storia.

Ma ciò che rende questo libro davvero memorabile è il suo tema centrale di perdono e redenzione. Attraverso la storia di Sirius Black, Rowling ci mostra che anche coloro che sembrano irrimediabilmente corrotti possono trovare la via verso la redenzione.

Con una scrittura vivida e un ritmo avvincente, Il prigioniero di Azkaban è un'altra tappa emozionante nel viaggio di Harry Potter. Consigliato a tutti, dai lettori più giovani ai fan più accaniti della serie."),
(18, 18, 33,4.5, "Harry Potter e il Calice di Fuoco è un'avventura mozzafiato che porta i lettori ancora più in profondità nel magico mondo di Hogwarts. In questo quarto libro della serie di J.K. Rowling, Harry si trova coinvolto nel Torneo Tremaghi, una competizione magica pericolosa e avvincente che mette alla prova la sua abilità e il suo coraggio. Tra misteri, intrighi e nuove amicizie, il libro tiene incollati alla pagina fino all'ultimo capitolo. Rowling continua a stupire con la sua creatività e la sua capacità di creare personaggi vividi e ambientazioni magiche. Una lettura imperdibile per i fan di Harry Potter e per chiunque ami l'avventura e la magia."),
(19, 19, 114,4.5, "Harry Potter e il Principe Mezzosangue di J.K. Rowling è un'altra magistrale aggiunta alla serie epica di Harry Potter. In questo sesto libro, Rowling ci porta più a fondo nel mondo della magia, rivelando oscure verità sul passato di Voldemort e i suoi legami con il giovane Harry. La trama è avvincente, piena di mistero e azione, ma è il carattere più oscuro e maturo di questo volume che lo distingue dagli altri. I personaggi crescono e affrontano sfide personali mentre il destino del mondo magico si fa sempre più tenebroso. Rowling dimostra ancora una volta la sua abilità nell'intrecciare narrativa avvincente, umorismo e profondità emotiva. Un must-read per tutti gli appassionati di magia e avventura, sia giovani che adulti."),
(20, 20, 20,4.5, "Harry Potter e i Doni della Morte è un'epica conclusione che cattura perfettamente l'essenza dell'universo magico creato da J.K. Rowling. In questo libro, Harry affronta la sua missione più pericolosa e emozionante: trovare e distruggere gli Horcrux di Lord Voldemort. La narrazione è avvincente e piena di azione, ma ciò che rende questo libro davvero speciale è il modo in cui Rowling esplora il tema del sacrificio, dell'amicizia e dell'amore. I personaggi crescono e si sviluppano in modo significativo, e ogni pagina è permeata di una tensione palpabile che tiene il lettore incollato fino all'ultima parola. Un finale epico che soddisfa pienamente e lascia un'impronta duratura nel cuore di chiunque abbia seguito le avventure di Harry, Hermione e Ron fin dall'inizio."),(4,1,30,4.5,"wow che bello");

update utente set `libriLetti`=0 where id=1;