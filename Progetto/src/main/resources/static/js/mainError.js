document.addEventListener('DOMContentLoaded', function () {
    console.log("Codice JavaScript eseguito correttamente.");

    // Funzionalità per il modal di registrazione utente
    var utenteEsistente = /*[[${utenteEsistente}]]*/ false;
    var passwordNonValida = /*[[${passwordNonValida}]]*/ false;
    var passwordNonCorrispondenti = /*[[${passwordNonCorrispondenti}]]*/ false;

    console.log("utenteEsistente:", utenteEsistente);
    console.log("emailEsistente:", emailEsistente);
    console.log("passwordNonValida:", passwordNonValida);
    console.log("passwordNonCorrispondenti:", passwordNonCorrispondenti);
    

    // Verifica se il modal degli utenti esistenti viene selezionato correttamente
    const utenteEsistenteModal = document.getElementById('utenteEsistenteModal');
    console.log("utenteEsistenteModal:", utenteEsistenteModal);

    // Verifica se il modal esiste e se è visualizzato
    if (utenteEsistenteModal) {
        console.log("Il modal esiste.");

        // Mostra il modal solo se uno dei flag è true
        if (utenteEsistente || passwordNonValida || passwordNonCorrispondenti) {
            console.log("Mostra il modal.");
            $('#utenteEsistenteModal').modal('show');
        }
    } else {
        console.log("Il modal non esiste o non è visualizzato correttamente.");
    }
});