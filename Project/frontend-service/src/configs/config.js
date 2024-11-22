import firebase from 'firebase/compat/app';
import 'firebase/compat/auth';
import 'firebase/compat/firestore';
import "firebase/compat/database";
import "firebase/compat/storage";

const firebaseConfig = {
    apiKey: "AIzaSyCXarsIm4cEop1q6q2hiBd3LoocbM4-vQw",
    authDomain: "mtsds-26bcc.firebaseapp.com",
    projectId: "mtsds-26bcc",
    storageBucket: "mtsds-26bcc.appspot.com",
    messagingSenderId: "459649899265",
    appId: "1:459649899265:web:351fafa53d7d5e0c201900",
    measurementId: "G-P7C70277PT"
  };

// Initialize Firebase
firebase.initializeApp(firebaseConfig);

const projectStorage = firebase.storage();
const projectFirestore = firebase.firestore();
const timestamp = firebase.firestore.FieldValue.serverTimestamp;

export { projectStorage, projectFirestore, timestamp };