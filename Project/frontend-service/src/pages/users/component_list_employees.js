import React from 'react';


import api from '../../service/api';
import UpdateUser from './component_update_user';


class ListEmployees extends React.Component {

    constructor(props) {
        super(props)
        this.onEdit = props.onEdit;
        this.state = {
            employees: [],
            token: "",
        }
        this.state.token = localStorage.getItem("token");
        document.title = "Employees"
    }

    detail(id) {
        window.location.href = '/user/' + id;
    }

    refuse(id) {
        api.delete("/users/employees/" + id, {
            headers: { Authorization: `Bearer ${this.state.token}` }

        });
    }

    componentDidMount() {
        const response2 = api.get("/users/employees", {
            headers: {
                Authorization: `Bearer ${this.state.token}`,
                'Content-Type': 'application/json'
            }
        });

        response2.then(result => this.setState({ employees: result.data['content'] != undefined ? result.data['content'] : []}));
    }



    
    render() {
        return (
            <div class="container-fluid pt-4 px-4">
                        <div class="row g-4">
                            <div class="col-sm-12 col-xl-12">
                                <div className="col-sm-12 col-xl-12" style={{ textAlign: 'center' }}>
                                    <h1 >Funcionários</h1>
                                </div>
                                <div class="bg-light rounded h-100 p-4">
                                    <h6 class="mb-4">Clientes</h6>
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>#</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>No</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Username </th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Nome </th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Apelido</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Estado</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Editar</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Ação</th>
                                            </tr>
                                        </thead>
                                        {this.state.employees != undefined &&
                                            <>
                                                <tbody>
                                                {this.state.employees.map((employee, index) => (
                                                <>
                                                    <tr>
                                                        <th style={{ textAlign: 'center' }}><img src="img/c.png" style={{ height: 30 }} /></th>
                                                        <td style={{ textAlign: 'center' }} scope="row">{index}</td>
                                                        <td style={{ textAlign: 'center' }}>{employee.username}</td>
                                                        <td style={{ textAlign: 'center' }}>{employee.firstName}</td>
                                                        <td style={{ textAlign: 'center' }}>{employee.lastName}</td>
                                                        <td style={{ textAlign: 'center' }}>{employee.userState}</td>
                                                        <td style={{ textAlign: 'center' }}><button type="button" onClick={() => this.onEdit(<UpdateUser user={employee} isEmployee ={true}/>)} class="btn btn-warning rounded-pill m-2">Editar</button></td>
                                                        <td style={{ textAlign: 'center' }}><button type="button" onClick={() => this.refuse(employee.id)} class="btn btn-danger rounded-pill m-2">Eliminar</button></td>
                                                    </tr>
                                                </>
                                            ))}
                                                </tbody>
                                            </>
                                        }
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
        )
    }
}


export default ListEmployees

