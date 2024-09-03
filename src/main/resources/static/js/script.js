document.addEventListener("DOMContentLoaded", function(event) {
   
    const showNavbar = (toggleId, navId, bodyId, headerId) =>{
    const toggle = document.getElementById(toggleId),
    nav = document.getElementById(navId),
    bodypd = document.getElementById(bodyId),
    headerpd = document.getElementById(headerId)
    
    // Validate that all variables exist
    if(toggle && nav && bodypd && headerpd){
    toggle.addEventListener('click', ()=>{
    // show navbar
    nav.classList.toggle('show')
    // change icon
    toggle.classList.toggle('bx-x')
    // add padding to body
    bodypd.classList.toggle('body-pd')
    // add padding to header
    headerpd.classList.toggle('body-pd')
    })
    }
    }
    
    showNavbar('header-toggle','nav-bar','body-pd','header')
    
    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')
    
    function colorLink(){
    if(linkColor){
    linkColor.forEach(l=> l.classList.remove('active'))
    this.classList.add('active')
    }
    }
    linkColor.forEach(l=> l.addEventListener('click', colorLink))
    
     // Your code to run since DOM is loaded and ready
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