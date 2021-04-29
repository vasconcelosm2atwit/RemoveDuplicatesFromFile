import React, { useState, useEffect } from "react";
import Axios from "axios";
import { getAllData, getDuplicates} from '../actions/DataAction';

function ShowData() {
    const [person, setPerson] = useState([]);
    const [duplicates, setDuplicates] = useState([]);

    const getData = () => {
        getAllData().then((data) => setPerson(data)).catch((error) => console.error(error))
        getDuplicates().then((data) => setDuplicates(data)).catch((error) => console.error(error))

    };

    useEffect(() => {
        getData();
    }, []);

    return (
        <div style={{ display: "flex"}}>
            <div >
                <h1 style={{ textAlign: "center" }}>Data</h1>
                <div>
                    <table >
                        <thead style={{ marginLeft: "auto", marginRight: "auto", textAlign: "center" }}>
                            <tr>
                                <th>id</th>
                                <th>Full Name</th>
                                <th>Company</th>
                                <th>Email</th>
                                <th>Address</th>
                            </tr>
                        </thead>
                        {person.map((p) => (
                            <tbody style={{ marginLeft: "auto", marginRight: "auto", textAlign: "left" }}>
                                <td>{p.id}</td>
                                <td>{p.fullName}</td>
                                <td>{p.company}</td>
                                <td>{p.email}</td>
                                <td>{p.fullAddress}</td>
                            </tbody>
                        ))}
                    </table>
                </div>
            </div>
            <div>
                <h1>Duplicate Data</h1>
                <table>
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>Full Name</th>
                            <th>Company</th>
                            <th>Email</th>
                            <th>Address</th>
                        </tr>
                    </thead>

                {duplicates.map((p) =>
                    <tbody>
                        <tr>
                        <th scope="row">{p.id}</th>
                        <td>{p.fullName}</td>
                        <td>{p.company}</td>
                        <td>{p.email}</td>
                        <td>{p.fullAddress}</td>
                        </tr>
                    </tbody>
                
                )}
                
                </table>
            </div>
        </div>
    );
}

export default ShowData;
