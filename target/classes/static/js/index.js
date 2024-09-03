document.addEventListener('DOMContentLoaded', function() {
    const scrollContainer = document.querySelector('.event-cards');
    const scrollLeft = document.getElementById('scroll-left');
    const scrollRight = document.getElementById('scroll-right');

    let autoScrollInterval;

    function startAutoScroll() {
        autoScrollInterval = setInterval(() => {
            scrollContainer.scrollBy({ left: 300, behavior: 'smooth' });
            if (scrollContainer.scrollLeft + scrollContainer.clientWidth >= scrollContainer.scrollWidth) {
                scrollContainer.scrollTo({ left: 0, behavior: 'smooth' });
            }
        }, 3000);
    }

    function stopAutoScroll() {
        clearInterval(autoScrollInterval);
    }

    scrollLeft.addEventListener('click', () => {
        scrollContainer.scrollBy({ left: -300, behavior: 'smooth' });
    });

    scrollRight.addEventListener('click', () => {
        scrollContainer.scrollBy({ left: 300, behavior: 'smooth' });
    });

    scrollContainer.addEventListener('mouseover', stopAutoScroll);
    scrollContainer.addEventListener('mouseout', startAutoScroll);

    startAutoScroll();
});

function openUpdateModal(id, nome, orario, giorno,prezzo){
    document.querySelector("#update-modal").hidden = false;
    var modId = document.querySelector("#mod-id");
    var modNome = document.querySelector("#mod-nome");
    var modOrario = document.querySelector("#mod-orario");
    var modGiorno = document.querySelector("#mod-giorno");
    var modPrezzo = document.querySelector("#mod-prezzo");

    modId.value = id;
    modNome.value = nome;
    modOrario.value = orario;
    modGiorno.value = giorno;
    modPrezzo.value = prezzo;
}