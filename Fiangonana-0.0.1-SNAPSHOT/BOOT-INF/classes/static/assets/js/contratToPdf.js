function exportCDItoPDF() {
    const element = document.querySelector('.container');
    const options = {
        margin:       1,
        filename:     'Contrat_CDI.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 2 },
        jsPDF:        { unit: 'cm', format: 'a4', orientation: 'portrait' }
    };
    html2pdf().set(options).from(element).save();
}

function exportCDDtoPDF() {
    const element = document.querySelector('.container');
    const options = {
        margin:       1,
        filename:     'Contrat_CDD.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 2 },
        jsPDF:        { unit: 'cm', format: 'a4', orientation: 'portrait' }
    };
    html2pdf().set(options).from(element).save();
}

function exportCEtoPDF() {
    const element = document.querySelector('.container');
    const options = {
        margin:       1,
        filename:     'Contrat_CE.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 2 },
        jsPDF:        { unit: 'cm', format: 'a4', orientation: 'portrait' }
    };
    html2pdf().set(options).from(element).save();
}