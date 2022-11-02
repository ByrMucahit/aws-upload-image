import React, {useState, useEffect, useCallback} from "react";
import './App.css';
import axios from 'axios';
import {useDropzone} from 'react-dropzone'


const UserProfiles = () => {
    const [userProfiles, setUserProfiles] = useState([]);

    const fetchUserProfiles = () => {
        axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
            console.log(res);
            setUserProfiles(res.data);
        })
    }

    useEffect(() => {
        fetchUserProfiles();
    }, [])

    return userProfiles.map((userProfiles, index) => {
        return (
            <div key={index}>
                <br/>
                <h1>{userProfiles.userName}</h1>
                <p>{userProfiles.userProfileId}</p>
                <Dropzone/>
                <br/>
            </div>)
    });
}

function Dropzone() {
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        console.log(file);
    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                isDragActive ?
                    <p>Drop the imaeg here ...</p> :
                    <p>Drag 'n' drop profile image, or click to select profile image</p>
            }
        </div>
    )
}

function App() {
    return (
        <div className="App">
            <UserProfiles/>
        </div>
    );
}

export default App;