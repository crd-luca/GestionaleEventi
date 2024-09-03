document.addEventListener("DOMContentLoaded", function(event) {
    const showNavbar = (toggleId, navId, bodyId, headerId) => {
        const toggle = document.getElementById(toggleId),
              nav = document.getElementById(navId),
              bodypd = document.getElementById(bodyId),
              headerpd = document.getElementById(headerId);

        // Verifica che tutte le variabili esistano
        if (toggle && nav && bodypd && headerpd) {
            toggle.addEventListener('click', () => {
                // Mostra/nascondi la navbar
                nav.classList.toggle('show');
                // Cambia l'icona
                toggle.classList.toggle('bx-x');
                // Aggiunge/rimuove il padding al body
                bodypd.classList.toggle('body-pd');
                // Aggiunge/rimuove il padding al header
                headerpd.classList.toggle('body-pd');
            });
        }
    };

    showNavbar('header-toggle', 'nav-bar', 'body-pd', 'header');

    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link');

    function colorLink() {
        if (linkColor) {
            linkColor.forEach(l => l.classList.remove('active'));
            this.classList.add('active');
        }
    }
    linkColor.forEach(l => l.addEventListener('click', colorLink));
});

function openUpdateModal(id, nome, orario, giorno, prezzo) {
    document.querySelector("#update-modal").hidden = false;;
    
     
        var modId = document.querySelector("#mod-id");
       var modNome = document.querySelector("#mod-nome");
      var modOrario = document.querySelector("#mod-orario");
      var modGiorno = document.querySelector("#mod-giorno");
      var modPrezzo=  document.querySelector("#mod-prezzo");
     modId.value = id;
     modNome.value = nome;
     modOrario.value = orario;
     modGiorno.value = giorno;
     modPrezzo.value = prezzo;
}
