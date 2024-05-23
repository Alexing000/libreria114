fetch('/api/libro/byTitolo')
  .then(response => response.json())
  .then(data => {
    // Dopo aver ricevuto i dati, elaborali e renderizzali nel tuo box HTML
    const box = document.getElementById('libri-recenti');
    data.forEach(oggetto => {
      const item = document.createElement('div');
      item.innerHTML = `
        <h3>${oggetto.titolo}</h3>
        <p>${oggetto.descrizione}</p>
        <!-- Aggiungi qui altre informazioni sull'oggetto -->
      `;
      box.appendChild(item);
    });
  })
  .catch(error => console.error('Errore durante il recupero dei dati:', error));


  fetch('/api/libro/byGenere')
  .then(response => response.json())
  .then(data => {
    // Dopo aver ricevuto i dati, elaborali e renderizzali nel tuo box HTML
    const box = document.getElementById('libri-consigliati');
    data.forEach(oggetto => {
      const item = document.createElement('div');
      item.innerHTML = `
        <h3>${oggetto.titolo}</h3>
        <p>${oggetto.descrizione}</p>
        <!-- Aggiungi qui altre informazioni sull'oggetto -->
      `;
      box.appendChild(item);
    });
  })
  .catch(error => console.error('Errore durante il recupero dei dati:', error));


  function aggiungiRec(oggetto){
   
    document.getElementById('form-aggiungiRecensione').style.display = 'block';




  }
    

  function updateProgress() {
    const pagineLette = parseInt(document.getElementById('nPagineLette').value, 10);
const maxPagine = parseInt(document.getElementById('progress').getAttribute('max'), 10);


    document.getElementById('progress').value = pagineLette;

    if (pagineLette >= maxPagine) {
        alert('Hai completato il libro!');
    }
}



  
