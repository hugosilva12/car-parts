import React from 'react';


import api from '../../service/api';

class ListParts extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            parts: [],
            token: "",
        }
        this.state.token = localStorage.getItem("token");
        document.title = "Partes"
    }

    refuse(id) {
        this.forceUpdate();
        api.delete("/cardisassembly/itemcar/" + id, {
            headers: { Authorization: `Bearer ${this.state.token}` }

        }).then(res => {
            this.forceUpdate();
        });
    }

    formatDate(date) {

        return date.toString().split("T")[0]
    }

    componentDidMount() {
        const response = api.get("/cardisassembly/itemcar", {
            headers: {
                Authorization: `Bearer ${this.state.token}`,
                'Content-Type': 'application/json'
            }
        });

        response.then(result => this.setState({ parts: result.data['content'] != undefined ? result.data['content'] : [] }));
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
                                <h1 >Peças</h1>
                            </div>
                            <div class="bg-light rounded h-100 p-4">
                                <h6 class="mb-4">Peças</h6>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>#</th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>SKU </th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Estado da peça </th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Preço estimado </th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Data de desmontagem</th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Estado</th>
                                            <th scope="col" style={{ textAlign: 'center', fontSize: 20 }}>Ação</th>
                                        </tr> 
                                    </thead>
                                    <tbody>
                                        {this.state.parts.map((part, index) => (
                                            <>
                                                <tr>
                                                    <th style={{ textAlign: 'center' }}><img src={`${part.photoPath}`} style={{ height: 30 }} /></th>
                                                    <th style={{ textAlign: 'center' }}>{part.itemSku}</th>
                                                    <th style={{ textAlign: 'center' }}>{part.itemState.percentage}%</th>
                                                    <th style={{ textAlign: 'center' }}>{part.price}€</th>
                                                    <th style={{ textAlign: 'center' }}>{this.formatDate(part.date)}</th>
                                                    <th style={{ textAlign: 'center' }}>{this.capitalizeFirstLetter(part.itemCarLifecycle.toLowerCase())}</th>
                                                    
                                                    {part.itemCarLifecycle != 'SOLD' &&
                                                        <td style={{ textAlign: 'center' }}><button type="button" onClick={() => this.refuse(part.id)} class="btn btn-danger rounded-pill m-2">Eliminar</button></td>
                                                    }
                                                    {part.itemCarLifecycle == 'SOLD' &&
                                                        <td style={{ textAlign: 'center' }}><button disabled type="button" onClick={() => this.refuse(part.id)} class="btn btn-danger rounded-pill m-2">Eliminar</button></td>
                                                    }
                                                    
                                                </tr>
                                            </>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
            </>
        )
    }
}


export default ListParts

