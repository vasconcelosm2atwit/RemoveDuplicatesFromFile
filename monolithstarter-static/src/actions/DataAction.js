import axios from 'axios';

export async function getAllData() {
//   axios.get("http://localhost:8080/api/data").then((response) => {
//             let data = response.data
//         });

        return (await axios.get("http://localhost:8080/api/data")).data;

        
}

export async function getDuplicates(){
        return (await axios.get("http://localhost:8080/api/duplicates")).data;
}