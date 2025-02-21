function creerSuggestionAutocomplete(items, input) {
    const container = document.createElement("div");
    container.classList.add("autocomplete");

    items.forEach(item => {
        const div = document.createElement("div");
        div.textContent = item;

        div.addEventListener('click', () => {
            input.val(item);
        });

        container.appendChild(div);
    });

    return container;
}