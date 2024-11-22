import React from 'react';
import api from '../../service/api';
import UpdateUser from './component_update_user';

class ListClients extends React.Component {

    constructor(props) {
        super(props)
        this.onEdit = props.onEdit;
        this.state = {
            managers: [],
            token: "",
        }
        this.state.token = localStorage.getItem("token");
        document.title = "Clientes"
    }

    detail(id) {
        window.location.href = '/user/' + id;
    }

    refuse(id) {
        api.delete("/users/clients/" + id, {
            headers: { Authorization: `Bearer ${this.state.token}` }

        }).then(alert("Removido com sucesso")
        );
    }

    componentDidMount() {
        const response = api.get("/users/clients", {
            headers: {
                Authorization: `Bearer ${this.state.token}`,
                'Content-Type': 'application/json'
            }
        });

        const response2 = api.get("/users/clients", {
            headers: {
                Authorization: `Bearer ${this.state.token}`,
                'Content-Type': 'application/json'
            }
        });

        response.then(result => this.setState({ managers: result.data['content'] != undefined ? result.data['content'] : []}));

        response2.then(result => console.log(result));
    }

    capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    render() {
        return (
            <>
            <div class="container-fluid pt-4 px-4">
                        <div class="row g-4">
                            <div class="col-sm-12 col-xl-12">
                                <div className="col-sm-12 col-xl-12" style={{ textAlign: 'center' }}>
                                    <h1 >Clientes</h1>
                                </div>
                                <div class="bg-light rounded h-100 p-4">
                                    <h6 class="mb-4">Clientes</h6>
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>#</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Username </th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Nome </th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Apelido</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Estado</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Tipo de cliente</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Editar</th>
                                                <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Ação</th>

                                            </tr>
                                        </thead>
                                        {this.state.managers != undefined &&
                                            <>
                                                <tbody>
                                                    {this.state.managers.map((user, index) => (
                                                        <>
                                                            <tr>
                                                                <th style={{ textAlign: 'center' }}><img src="img/c.png" style={{ height: 30 }} /></th>
                                                                <td style={{ textAlign: 'center' }}>{user.username}</td>
                                                                <td style={{ textAlign: 'center' }}>{user.firstName}</td>
                                                                <td style={{ textAlign: 'center' }}>{user.lastName}</td>
                                                                <td style={{ textAlign: 'center' }}>{user.userState!= null ? this.capitalizeFirstLetter(user.userState.toLowerCase()) : ""}</td>
                                                                <td style={{ textAlign: 'center' }}>{this.capitalizeFirstLetter(user.typeClient.toLowerCase())}</td>
                                                                <td style={{ textAlign: 'center' }}><button type="button" onClick={() => this.onEdit(<UpdateUser user={user} isEmployee ={false}/>)} class="btn btn-warning rounded-pill m-2">Editar</button></td>
                                                                <td style={{ textAlign: 'center' }}><button type="button" onClick={() => this.refuse(user.id)} class="btn btn-danger rounded-pill m-2">Eliminar</button></td>
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
            </>
        )
    }
}


export default ListClients

