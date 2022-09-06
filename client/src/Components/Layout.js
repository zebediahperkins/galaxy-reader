// import { IconAddItem, IconCaretCircleLeft, IconCaretCircleRight, IconUpload } from '@lmig/lmds-react-icons';
import "../App.css";
import DriveFolderUpload from '@mui/icons-material/DriveFolderUpload';


import React, { useState, useRef } from 'react'
import PDFViewer from "./PDFViewer";


function Layout() {
    const [pdfFile, setPdfFile] = useState(null);

    // File selection event - only allows pdf
    const allowedFiles = ['application/pdf'];
    const handleFile = (e) =>{
        let selectedFile = e.target.files[0];
        if(selectedFile){ // when file is selected
            if(selectedFile && allowedFiles.includes(selectedFile.type)){
                let reader = new FileReader();
                reader.readAsDataURL(selectedFile);
               
                reader.onloadend = (e) => {
                    setPdfFile(e.target.result);
                    const base64 = e.target.result.split(',')[1];
                    const raw = JSON.stringify({'base64': base64});
                    const requestOption = {
                        method: 'POST',
                        headers: {     "Content-Type": "application/json"   },
                        body: raw,
                        redirect: 'follow',
                    }
                    fetch('http://localhost:8080/api/convert', requestOption)
                        .then(response => response.json())
                        .then((result) => {
                            setPdfFile(result.response);
                        })
                        .catch((error) => {
                            console.error('Error:', error);
                        });

                    setPdfFile(e.target.result);
                }
            }
            else{
                console.log('Not a valid pdf: Please select only PDF');
                setPdfFile('');
            }
        }
        else{
            console.log('please select a PDF');
        }
      

    }
    const hiddenFileInput = React.useRef(null);

    const handleClick = event => {
        hiddenFileInput.current.click();
    };

    function refreshPage() {
        window.location.reload();
    }

        return (

            <div className="App">
                
                <div className="body">
                    {pdfFile &&
                       <div>
                        
                            <button className="returnButton" onClick={refreshPage} >
                                Return
                            </button>
                        <PDFViewer pdfFile={pdfFile} />
                       </div> 
                        }
                    {!pdfFile &&
                        <div>

                           
                            <button className="uploadButton" onClick={handleClick}>
                                <DriveFolderUpload className="add" /> Upload File
                            </button>
                            <input type='file' className='custom-file-upload' ref={hiddenFileInput}  onChange={handleFile} style={{ display: 'none' }}></input>
                                

                        </div>
                    }
                   

                </div>


            </div>
        );
    }


export default Layout;

