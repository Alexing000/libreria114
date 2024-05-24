document.addEventListener('DOMContentLoaded', function () {
    const errorModal = document.getElementById('errorModal');
    const closeBtn = errorModal.querySelector('.close-btn');

    // Chiudi il modal quando si fa clic sul pulsante di chiusura
    closeBtn.addEventListener('click', function () {
        errorModal.style.display = 'none';
    });

    // Chiudi il modal quando si fa clic al di fuori del modal
    window.addEventListener('click', function (event) {
        if (event.target == errorModal) {
            errorModal.style.display = 'none';
        }
    });

    // Mostra il modal di errore all'avvio della pagina
    errorModal.style.display = 'block';

    // Aggiungi un event listener al pulsante "Torna alla pagina del libro"
    if(goBackBtn) { // Assicurati che il pulsante esista prima di aggiungere l'event listener
        goBackBtn.addEventListener('click', function () {
            window.history.back();
        });
    }
});