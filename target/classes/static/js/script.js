// script.js

document.addEventListener('DOMContentLoaded', function() {
    const scrollContainer = document.querySelector('.scroll-container');
    const eventCards = document.querySelector('.event-cards');
    const scrollLeft = document.getElementById('scroll-left');
    const scrollRight = document.getElementById('scroll-right');

    let currentIndex = 0;
    const cardWidth = 240; // Width of the card including margin
    const visibleCards = Math.floor(scrollContainer.clientWidth / cardWidth);

    function updateScrollButtons() {
        scrollLeft.style.display = currentIndex === 0 ? 'none' : 'block';
        scrollRight.style.display = currentIndex >= eventCards.children.length - visibleCards ? 'none' : 'block';
    }

    scrollRight.addEventListener('click', () => {
        if (currentIndex < eventCards.children.length - visibleCards) {
            currentIndex++;
            eventCards.style.transform = `translateX(-${currentIndex * cardWidth}px)`;
        }
        updateScrollButtons();
    });

    scrollLeft.addEventListener('click', () => {
        if (currentIndex > 0) {
            currentIndex--;
            eventCards.style.transform = `translateX(-${currentIndex * cardWidth}px)`;
        }
        updateScrollButtons();
    });

    updateScrollButtons();
});
