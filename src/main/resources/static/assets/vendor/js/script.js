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

let sortDirection = {};

function sortTable(idTable, columnIndex) {
    const table = document.getElementById(idTable);
    const tbody = table.tBodies[0];
    const rows = Array.from(tbody.rows);

    // Toggle sort direction
    sortDirection[columnIndex] = !sortDirection[columnIndex];
    const direction = sortDirection[columnIndex] ? 1 : -1;

    // Remove previous sorted class
    Array.from(table.querySelectorAll('th')).forEach(th => th.classList.remove('sorted-asc', 'sorted-desc'));

    // Sort rows
    rows.sort((a, b) => {
        const aText = a.cells[columnIndex].textContent.trim();
        const bText = b.cells[columnIndex].textContent.trim();

        // Check if the column is numeric
        const aNum = parseFloat(aText);
        const bNum = parseFloat(bText);

        if (!isNaN(aNum) && !isNaN(bNum)) {
            return (aNum - bNum) * direction;
        } else {
            return aText.localeCompare(bText) * direction;
        }
    });

    // Append sorted rows
    rows.forEach(row => tbody.appendChild(row));

    // Add sorted class to header
    const header = table.querySelectorAll('th')[columnIndex];
    header.classList.add(sortDirection[columnIndex] ? 'sorted-asc' : 'sorted-desc');
}