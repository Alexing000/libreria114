//metodo per eliminare l'account
function eliminaAccount() {
    var conferma = confirm("Sei sicuro di voler eliminare il tuo account?");

    if (conferma) {
        $.ajax({
            url: '/utente/eliminaAccount',
            type: 'POST',
            success: function (data) {
                alert("Account eliminato con successo");
                window.location.href = "/logout";
            },
            error: function (data) {
                alert("Errore nell'eliminazione dell'account");
            }
        });
    }
}