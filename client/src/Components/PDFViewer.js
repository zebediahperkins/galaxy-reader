import React from 'react'

function PDFViewer(props) {
    const {pdfFile} = props;
    const resultData = "data:application/pdf;base64," + pdfFile; // info needed to load in iframe
    return (
        <div>
            <iframe src={resultData} width="75%" height="800px" title="pdfFrame">
            </iframe>
        </div>
    );
}

export default PDFViewer;


