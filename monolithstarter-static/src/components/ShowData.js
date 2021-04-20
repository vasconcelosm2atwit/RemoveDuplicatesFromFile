import React, { useState, useEffect } from "react";
import Axios from "axios";

function ShowData() {
    const [person, setPerson] = useState([]);
    const [duplicates, setDuplicates] = useState([]);

    const getData = () => {
        Axios.get("http://localhost:8080/api/data").then((response) => {
            setPerson(response.data);
        });

        Axios.get("http://localhost:8080/api/duplicates").then((response) => {
            console.log(response);
            setDuplicates(response.data);
        });
    };

    useEffect(() => {
        getData();
    }, []);

    return (
        <div style={{ display: "flex", textAlign: "center" }}>
            <div Style={{}}>
                <h1>List of People after duplicates removal</h1>
                <div>
                    <table style={{ marginLeft: "auto", marginRight: "auto" }}>
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>Full Name</th>
                                <th>Company</th>
                                <th>Email</th>
                                <th>Address</th>
                            </tr>
                        </thead>

                        {person.map((p) => (
                            <tbody>
                                <td>{p.id}</td>
                                <td>{p.fullName}</td>
                                <td>{p.company}</td>
                                <td>{p.email}</td>
                                <td>{p.address}</td>
                            </tbody>
                        ))}
                    </table>
                </div>
            </div>
            <div>
                <h1>Duplicates removed</h1>
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

                    {duplicates.map((p) => (
                        <tbody>
                            <td>{p.id}</td>
                            <td>{p.fullName}</td>
                            <td>{p.company}</td>
                            <td>{p.email}</td>
                            <td>{p.address}</td>
                        </tbody>
                    ))}
                </table>
            </div>
        </div>
    );
}

export default ShowData;
